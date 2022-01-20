package com.test_sma.workOutGen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.test_sma.R
import kotlinx.android.extensions.LayoutContainer

class CustomAdapter (options: FirestoreRecyclerOptions<WorkOut>) : FirestoreRecyclerAdapter<WorkOut, CustomAdapter.ViewHolder>(options) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
}
    override fun onBindViewHolder(holder: ViewHolder, position: Int, item: WorkOut) {
        // val id = snapshots.getSnapshot(position).id

        holder.apply {
            nameView.text = item.name
            qtyView.text = item.qty
            repsView.text=item.reps
        }
    }


    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val repsView: TextView=itemView.findViewById(R.id.repsView)
        val qtyView: TextView=itemView.findViewById(R.id.qtyView)
        val nameView: TextView = itemView.findViewById(R.id.nameView)
    }
}
