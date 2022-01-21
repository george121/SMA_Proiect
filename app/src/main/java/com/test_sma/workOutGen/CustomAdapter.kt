package com.test_sma.workOutGen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test_sma.R

class CustomAdapter(private val exerciseList : ArrayList<SelectWorkOuts.WorkOut>):RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return MyViewHolder(view)
}
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = exerciseList[position]

        holder.nameView.text = item.name
        holder.qtyView.text = item.quantity
        holder.repsView.text=item.reps

    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val repsView: TextView=itemView.findViewById(R.id.repsView)
        val qtyView: TextView=itemView.findViewById(R.id.qtyView)
        val nameView: TextView = itemView.findViewById(R.id.nameView)
    }
}
