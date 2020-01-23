package com.example.randomcolorgenerator.adapters

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.randomcolorgenerator.R
import kotlinx.android.synthetic.main.item_layout_recycler_view_hex_list.view.*


class HexListSchemeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    private  var colorHexItems : ArrayList<String> = ArrayList()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(p0.context).inflate(R.layout.item_layout_recycler_view_hex_list,p0,false)
        )
    }


    override fun getItemCount(): Int {

        Log.d("Adapter", "length " + colorHexItems.size)
        return colorHexItems.size

    }

    fun submitList(colorHexItemsList : ArrayList<String> )
    {
        colorHexItems = colorHexItemsList
    }

    override fun onBindViewHolder(holder : RecyclerView.ViewHolder, p1: Int) {

        when(holder)
        {
            is ViewHolder -> {
                holder.bind(colorHexItems[p1])
                Log.d("Adapter", "Onbindview has been called" )
            }
        }

    }

    class ViewHolder constructor(itemView: View )
        : RecyclerView.ViewHolder(itemView){
        val rv_card_hex_color : androidx.cardview.widget.CardView = itemView.rv_card_hex_color
        val txt_hex_value : TextView = itemView.txt_hex_value



        fun bind(colors: String)
        {
            rv_card_hex_color.setCardBackgroundColor(Color.parseColor("#$colors"))
            txt_hex_value.setText("#$colors")
        }


    }



}

