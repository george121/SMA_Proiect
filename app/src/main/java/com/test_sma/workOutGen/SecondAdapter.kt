package com.test_sma.workOutGen

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.test_sma.MainActivity
import com.test_sma.R

class SecondAdapter(private val exercList : ArrayList<SelectWorkOuts.WorkOut>,mContext:Context):
    RecyclerView.Adapter<SecondAdapter.MySecondViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MySecondViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.curent_workout_card, parent, false)

        return MySecondViewHolder(view)
    }
    var myContext=mContext
    override fun onBindViewHolder(holder: MySecondViewHolder, position: Int) {
        val item = exercList[position]

        holder.init(item,position)

    }

    override fun getItemCount(): Int {
        return exercList.size
    }

    fun deleteItem(position: Int){
        exercList.removeAt(position)
        my2Adapter.notifyDataSetChanged()
        if(exercList.isEmpty())
        {
            Toast.makeText(context,"Congratulations You Finished!",Toast.LENGTH_SHORT).show()
            myContext.startActivity(Intent(myContext,MainActivity::class.java))
        }
    }

    inner class MySecondViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val repetsView: TextView =itemView.findViewById(R.id.repetView)
        val quantityView: TextView =itemView.findViewById(R.id.quantityView)
        val exercView: TextView = itemView.findViewById(R.id.exerciseView)
        val buttonAdd : ImageButton =itemView.findViewById(R.id.imageButton)
        var myCount:Int=0;
        fun init(item:SelectWorkOuts.WorkOut,index: Int){
            exercView.text = item.name
            quantityView.text = item.quantity
            repetsView.text=(item.reps.toString().toInt()-myCount).toString()
            buttonAdd.setOnClickListener{
                myCount+=1
                Toast.makeText(context,"Completed! Left = ${4-myCount}>", Toast.LENGTH_SHORT).show()
                repetsView.text=(item.reps.toString().toInt()-myCount).toString()
                my2Adapter.notifyDataSetChanged()
                if(myCount==4){
                    deleteItem(index)
                    myCount=0
                }

            }
        }

    }
}