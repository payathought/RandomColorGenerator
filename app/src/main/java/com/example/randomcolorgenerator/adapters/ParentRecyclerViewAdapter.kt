package com.example.randomcolorgenerator.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.randomcolorgenerator.R
import com.example.randomcolorgenerator.View.ShcemeNameActivity
import com.example.randomcolorgenerator.model.ChildRvModel
import kotlinx.android.synthetic.main.item_layout_recycler_view_favorites_vertical.view.*


class ParentRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private  var childItemList : ArrayList<ChildRvModel> = ArrayList()
    private lateinit var parentContext : Context
    val TAG = "PARENT ADAPTER"
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout_recycler_view_favorites_vertical,parent,false)
        )
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(holder)
        {
            is ViewHolder ->
            {

                holder.txtIdSchemeName.setText(childItemList[position].scheme_name)
                holder.bind(context = parentContext, colors = childItemList[position].hexListChild )


                Log.d(TAG, "Onbindview has been called ${childItemList[position].primaId}" )


//                holder.parent_layout_id
//                    .setOnClickListener {
//                        val i = Intent(parentContext, ShcemeNameActivity::class.java)
//                        val primaryID : Int = childItemList[position].primaId
//                        i.putExtra("id", primaryID)
//                        parentContext.startActivity(i)
//                    }
                holder.itemView.setOnClickListener {
                    val i = Intent(parentContext, ShcemeNameActivity::class.java)
                    val primaryID : Int = childItemList[position].primaId
                    i.putExtra("id", primaryID)
                    parentContext.startActivity(i)
                }

            }
        }
    }


    override fun getItemCount(): Int {

        return childItemList.size

    }
    fun submitList(childItem : ArrayList<ChildRvModel>, context: Context)
    {
        childItemList = childItem
        parentContext = context
    }



    class ViewHolder constructor(itemView: View)
        : RecyclerView.ViewHolder(itemView){

        val txtIdSchemeName : TextView = itemView.txtIdSchemeName
        val child_rv : RecyclerView = itemView.child_rv
        val parent_layout_id : LinearLayout = itemView.parent_layout_id
        lateinit var generateColorSchemeAdapter: GenerateColorSchemeAdapter

            fun bind(context: Context, colors: ArrayList<String>)
            {
                child_rv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                generateColorSchemeAdapter = GenerateColorSchemeAdapter()
                generateColorSchemeAdapter.submitList(colors)
                generateColorSchemeAdapter.notifyDataSetChanged()
                child_rv.adapter = generateColorSchemeAdapter




            }

        // val hexvalues = child.hexListChild


    }

}