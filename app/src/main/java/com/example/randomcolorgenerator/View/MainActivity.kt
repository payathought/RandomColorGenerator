package com.example.randomcolorgenerator.View

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.randomcolorgenerator.R
import com.example.randomcolorgenerator.adapters.GenerateColorSchemeAdapter
import com.example.randomcolorgenerator.interfaces.APIClient
import com.example.randomcolorgenerator.remoteService.ServiceBuilder
import com.example.randomcolorgenerator.model.ColorSchemeModel
import com.example.randomcolorgenerator.room_local_database.ColorGeneratorDatabase
import com.example.randomcolorgenerator.room_local_database.ColorGeneratorEntities

import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    val TAG = "MAIN ACTIVITY"
    var colorRes : String = ""
    var colors : ArrayList<String> = ArrayList()
    var color_hex_values : String? = ""
    lateinit var generateColorSchemeAdapter: GenerateColorSchemeAdapter
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_addfavorite.isEnabled = false
        val  db = ColorGeneratorDatabase(this)
        rv_color_scheme.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        generateColorSchemeAdapter = GenerateColorSchemeAdapter()
        btn_Favorites.setOnClickListener{ startActivity(Intent(this, FavoritesActivity::class.java)) }
        btn_generate.setOnClickListener { loadDestination() }
        btn_addfavorite.setOnClickListener { saveToLocalDatabase(db)}

    }

    private fun loadDestination ()
    {

        val apiClient : APIClient = ServiceBuilder.buildService(APIClient::class.java)
        val results : Call<ColorSchemeModel> =  apiClient.getResult()
        results.enqueue(object : Callback<ColorSchemeModel> {
            override fun onFailure(call: Call<ColorSchemeModel>, t: Throwable) {
                Log.d(TAG,"Results not found: \n " + t.message)

            }

            override fun onResponse(call: Call<ColorSchemeModel>, response: Response<ColorSchemeModel>)
            {
                if (response.isSuccessful)
                {
                    var i = 0
                    colors.clear()
                    color_hex_values = ""
                    val colorSchemeModelList : ColorSchemeModel? = response.body()
                    Log.d(TAG,"Result $colorSchemeModelList")
                    if (colorSchemeModelList?.component1()?.get(0)?.colors?.size != 0 &&  colorSchemeModelList?.component1()?.get(0)!!.tags[0].name.isNotEmpty())
                    {
                        scheme_name.setText(colorSchemeModelList?.component1()?.get(0)!!.tags[0].name)
                        while (i < colorSchemeModelList?.component1()?.get(0)?.colors!!.size)
                        {
                            colorRes  = colorSchemeModelList.component1()[0].colors[i]
                            Log.d(TAG,"Result of Component 1 "  + colorRes )

                            colors.add(colorRes)

                            if (i == 0)
                            {
                                color_hex_values = colorRes
                            }else{
                                color_hex_values += "/$colorRes"
                            }
                            i++
                                                    }
                        btn_addfavorite.isEnabled = true
                        Log.d(TAG,"color_hex_values "  + color_hex_values )
                        btn_addfavorite.setBackgroundResource(R.drawable.bitmap_heart_button_colored)
                    }else
                    { Toast.makeText(this@MainActivity, "No Colors generated.", Toast.LENGTH_SHORT).show()}

                    generateColorSchemeAdapter.submitList(colors)
                    rv_color_scheme.adapter = generateColorSchemeAdapter
                    Log.d(TAG,"Index 0 -> " + colorSchemeModelList)
                }else
                { Log.d(TAG,"Results not found") }
            }

        })

    }
    private fun saveToLocalDatabase(dbSave : ColorGeneratorDatabase)
    {


        dbSave.colorGenDao().insertAlltoColorGen(
            ColorGeneratorEntities(
                schemeName = scheme_name.text.toString(), hex_value = color_hex_values))
        val data = dbSave.colorGenDao().getAllFromSchemeNameList()

        data.forEach {
            Log.d(TAG, "Database data -> ${it.schemeName} -> ${it.schemeName_id} -> ${it.hex_value}" )
        }
        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setTitle("Random Color Generator")
        builder.setMessage("Saved to Favorites")
        val dialog: AlertDialog = builder.create()
        dialog.show()

        btn_addfavorite.isEnabled = false
        colors.clear()
        generateColorSchemeAdapter.submitList(colors)
        rv_color_scheme.adapter = generateColorSchemeAdapter
        btn_addfavorite.setBackgroundResource(R.drawable.bitmap_heart_image_button)
        scheme_name.setText("SCHEME COLOR")

    }

}
