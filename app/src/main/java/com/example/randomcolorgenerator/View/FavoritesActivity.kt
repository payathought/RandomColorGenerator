package com.example.randomcolorgenerator.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.randomcolorgenerator.R
import com.example.randomcolorgenerator.adapters.ParentRecyclerViewAdapter
import com.example.randomcolorgenerator.model.ChildRvModel
import com.example.randomcolorgenerator.room_local_database.ColorGeneratorDatabase
import kotlinx.android.synthetic.main.activity_favorites.*

class FavoritesActivity : AppCompatActivity() {

    val TAG = "FavoritesActivity"
    lateinit var parentRecyclerViewAdapter: ParentRecyclerViewAdapter

    var childList : ArrayList<ChildRvModel> = ArrayList()
    var hexList: ArrayList<String> = ArrayList()
    var  name : String = ""
    lateinit var childRvModel : ChildRvModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)
        parent_rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        parentRecyclerViewAdapter = ParentRecyclerViewAdapter()
        val  db = ColorGeneratorDatabase(this)
        loadFromDatabase(db)
        btnBack.setOnClickListener {
            finish()
        }

    }


    private fun loadFromDatabase(dbSave : ColorGeneratorDatabase)
    {
        val data = dbSave.colorGenDao().getAllFromSchemeNameList()
        var hexList1: ArrayList<String> = ArrayList()
        var hex: ArrayList<String>


//        var i = 0
//        while (i < data.size){
//            var hexList: ArrayList<String> = ArrayList()
//            hex = data[i].hex_value?.split("/") as ArrayList<String>
//            name  = data[i].schemeName.toString()
//
//            var x = 0
//            while (x < hex.size){
//                hexList1.add(hex[x])
//                x++
//
//            }
//
//            hexList = hexList1
//            childRvModel = ChildRvModel(scheme_name = name, hexList = hexList )
//            childList.add(childRvModel)
//            i++
//        }




        data.forEach {

            Log.d(TAG, "Database data -> ${it.schemeName} -> ${it.schemeName_id} -> ${it.hex_value}" )

            hex = it.hex_value?.split("/") as ArrayList<String>
            name  = it.schemeName.toString()
            var i = 0
            while (i < hex.size){
                hexList1.add(hex[i])
                i++
                hexList = hexList1
            }


            childRvModel = ChildRvModel(scheme_name = name, hexList = hexList )
            childList.add(childRvModel)
        }

        parentRecyclerViewAdapter.submitList(context = applicationContext,childItem = childList)
        parentRecyclerViewAdapter.notifyDataSetChanged()
        parent_rv.adapter = parentRecyclerViewAdapter




    }


}
