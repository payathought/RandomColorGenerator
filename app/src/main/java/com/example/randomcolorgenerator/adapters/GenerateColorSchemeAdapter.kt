package com.example.randomcolorgenerator.adapters

import android.graphics.Color
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.randomcolorgenerator.R
import kotlinx.android.synthetic.main.item_layout_recycler_view_color_scheme.view.*


class GenerateColorSchemeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    private  var colorHexItems : ArrayList<String> = ArrayList()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(p0.context).inflate(R.layout.item_layout_recycler_view_color_scheme,p0,false)
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
        val circle_color : androidx.cardview.widget.CardView = itemView.rv_card_color



        fun bind(colors: String)
        {
            circle_color.setCardBackgroundColor(Color.parseColor("#$colors"))
        }


    }



}

