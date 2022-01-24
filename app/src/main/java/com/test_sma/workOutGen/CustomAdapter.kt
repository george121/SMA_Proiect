package com.test_sma.workOutGen

import android.graphics.Color
import android.view.ContentInfo
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.api.Context
import com.test_sma.R
public var chosenList:ArrayList<SelectWorkOuts.WorkOut> = arrayListOf()
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

        holder.init(item)

    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val repsView: TextView=itemView.findViewById(R.id.repsView)
        val qtyView: TextView=itemView.findViewById(R.id.qtyView)
        val nameView: TextView = itemView.findViewById(R.id.nameView)
        val button : Button=itemView.findViewById(R.id.addBtn)
        fun init(item:SelectWorkOuts.WorkOut){
            nameView.text = item.name
            qtyView.text = item.quantity
            repsView.text=item.reps
            button.setOnClickListener{
                button.text="Added"
                button.isEnabled=false
                button.setBackgroundColor(Color.GRAY)
                chosenList.add(item)
                count+1
                Toast.makeText(context,"Added count = $count",Toast.LENGTH_SHORT).show()
            }
        }

    }
}
