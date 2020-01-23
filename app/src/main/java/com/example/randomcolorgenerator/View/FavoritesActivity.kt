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

    lateinit var childList : ArrayList<ChildRvModel>
    var hexList: ArrayList<String> = ArrayList()
    var  name : String = ""
    var primaID : Int = 0
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

        var hex: ArrayList<String>
        childList = ArrayList()

        var i = 1

        while(i <= data.size)
        {
            var hexList1: ArrayList<String> = ArrayList()
            val dataPrimary =  dbSave.colorGenDao().findByPrimaryKey(i)
            val hexPrimary =  dataPrimary.hex_value?.split("/")
            name  = dataPrimary.schemeName.toString()
            primaID = dataPrimary.schemeName_id

            Log.d(TAG,"ID -> ${dataPrimary.schemeName_id} Name -> ${dataPrimary.schemeName} ")

            var x = 0
            while (x < hexPrimary!!.size){
                hexList1.add(hexPrimary[x])
                x++
            }
            hexList = hexList1
            childRvModel = ChildRvModel(scheme_name = name, hexList = hexList, primaId = primaID)
            childList.add(childRvModel)
            i++

        }

        parentRecyclerViewAdapter.submitList(context = applicationContext,childItem = childList)
        parentRecyclerViewAdapter.notifyDataSetChanged()
        parent_rv.adapter = parentRecyclerViewAdapter


/*
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

 */

    }


}
