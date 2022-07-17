package com.example.dbaccessv2

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_row.view.*

class AdapterClassA(private val places : ArrayList<UserPlace>, var clickListener: OnPlaceItemClickListener): RecyclerView.Adapter<AdapterClassA.CustomViewHolder>()  {

    class CustomViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val placeList: TextView = itemView.textPlaceList

        fun initialize(item: UserPlace, action: OnPlaceItemClickListener) {
            placeList.text = item.place

            itemView.setOnClickListener {
                action.onItemClick(item, adapterPosition)
            }
        }
    }

    override fun getItemCount(): Int {
        return places.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val cellForRow = LayoutInflater.from(parent.context).inflate(R.layout.layout_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
//        val place : UserPlace = places[position]
//        val selectedPosition = -1
//        holder.placeList.text = place.place
//
//        if (selectedPosition == position)
//            holder.itemView.setBackgroundColor(Color.parseColor("#000000"))
//        else
//            holder.itemView.setBackgroundColor(Color.parseColor("#ffffff"))

        holder.initialize(places[position], clickListener)
    }
}

interface OnPlaceItemClickListener {

    fun onItemClick(places: UserPlace, position: Int)
}