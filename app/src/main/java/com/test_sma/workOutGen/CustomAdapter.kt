package com.test_sma.workOutGen

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.test_sma.R

class CustomAdapter(private val exerciseList : ArrayList<SelectWorkOuts.WorkOut>,private var chosenList: ArrayList<SelectWorkOuts.WorkOut>):RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return MyViewHolder(view)
}
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = exerciseList[position]

        holder.init(item,chosenList)

    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val repsView: TextView=itemView.findViewById(R.id.repView)
        val qtyView: TextView=itemView.findViewById(R.id.qtyView)
        val nameView: TextView = itemView.findViewById(R.id.nameView)
        val button : Button=itemView.findViewById(R.id.addBtn)
        var myCount:Int=0;
        fun init(item:SelectWorkOuts.WorkOut,newList: ArrayList<SelectWorkOuts.WorkOut>){
            nameView.text = item.name
            qtyView.text = item.quantity
            repsView.text=item.reps
            button.setOnClickListener{
                button.text="Added"
                button.isEnabled=false
                button.setBackgroundColor(Color.GRAY)
                newList.add(item)
                myCount= getCount() +1
                setCount(myCount)
                Toast.makeText(context,"Added count = $myCount",Toast.LENGTH_SHORT).show()
            }
        }

    }
}
