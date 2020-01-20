package com.example.randomcolorgenerator.View

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.example.randomcolorgenerator.R
import com.example.randomcolorgenerator.adapters.GenerateColorSchemeAdapter
import com.example.randomcolorgenerator.interfaces.APIClient
import com.example.randomcolorgenerator.remoteService.ServiceBuilder
import com.example.randomcolorgenerator.model.ColorSchemeModel
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    val TAG = "MAIN ACTIVITY"
    var colorRes : String = ""
    var colors : ArrayList<String> = ArrayList()

    lateinit var generateColorSchemeAdapter: GenerateColorSchemeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        rv_color_scheme.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        generateColorSchemeAdapter = GenerateColorSchemeAdapter()



        btn_Favorites.setOnClickListener{

            startActivity(Intent(this, FavoritesActivity::class.java))
        }
//        btn_generate.setBackgroundColor(Color.parseColor("#000000"))

        btn_generate.setOnClickListener {
            loadDestination()

    }

        btn_addfavorite.isClickable = false
        if(btn_addfavorite.isClickable)
        {
            btn_addfavorite.setOnClickListener {
                Toast.makeText(this, "Saved to Favorites" ,Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun loadDestination ()
    {
        val apiClient : APIClient = ServiceBuilder.buildService(APIClient::class.java)

       val results : Call<ColorSchemeModel> =  apiClient.getResult()
        results.enqueue(object : Callback<ColorSchemeModel> {
            override fun onFailure(call: Call<ColorSchemeModel>, t: Throwable) {
                Log.d(TAG,"Results not found: \n " + t.message)

            }

            override fun onResponse(call: Call<ColorSchemeModel>, response: Response<ColorSchemeModel>) {
                if (response.isSuccessful)
                {
                    colors.clear()

                    val colorSchemeModelList : ColorSchemeModel? = response.body()
                    Log.d(TAG,"Result $colorSchemeModelList")

                    scheme_name.setText(colorSchemeModelList?.component1()?.get(0)!!.tags[0].name)

                    var i = 0
                    while (i < colorSchemeModelList?.component1()?.get(0)?.colors!!.size)
                    {
                        colorRes  = colorSchemeModelList.component1()[0].colors[i]
                        Log.d(TAG,"Result of Component 1 "  + colorRes )
                        colors.add(colorRes)
                        i++
                    }
                    generateColorSchemeAdapter.submitList(colors)
                    rv_color_scheme.adapter = generateColorSchemeAdapter

                    btn_addfavorite.isClickable = true

                    btn_addfavorite.setBackgroundResource(R.drawable.bitmap_heart_button_colored)


                    Log.d(TAG,"Index 0 -> " + colorSchemeModelList)


                }else
                {
                    Log.d(TAG,"Results not found")

                }
                 //To change body of created functions use File | Settings | File Templates.
            }

        })

    }
    fun addData(list : ArrayList<String>)
    {
        generateColorSchemeAdapter.submitList(list)

    }


}
