package com.example.randomcolorgenerator.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.randomcolorgenerator.R
import com.example.randomcolorgenerator.adapters.GenerateColorSchemeAdapter
import com.example.randomcolorgenerator.adapters.HexListSchemeAdapter
import com.example.randomcolorgenerator.room_local_database.ColorGeneratorDatabase
import kotlinx.android.synthetic.main.activity_sheme_name.*

class ShcemeNameActivity : AppCompatActivity() {
    lateinit var hexListSchemeAdapter: HexListSchemeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sheme_name)
        val primayID : Int = intent.getIntExtra("id",0)
        val  db = ColorGeneratorDatabase(this)
        hexListSchemeAdapter = HexListSchemeAdapter()
        rv_hex_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        loadFromDB(db,primayID)
        btn_Back.setOnClickListener {
            finish()
        }
    }

    fun loadFromDB(dbSave : ColorGeneratorDatabase, primayId : Int)
    {
        var hexList1: ArrayList<String> = ArrayList()
        val data = dbSave.colorGenDao().findByPrimaryKey(primayId)
        val hexPrimary: ArrayList<String> =  data.hex_value?.split("/") as ArrayList<String>
        val name = data.schemeName
        txtSchemeName_last.setText(name)
        var x = 0
        while (x < hexPrimary.size){
            hexList1.add(hexPrimary[x])
            x++
        }

        hexListSchemeAdapter.submitList(hexList1)
        rv_hex_list.adapter = hexListSchemeAdapter




    }
}
