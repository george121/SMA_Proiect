package com.test_sma.workOutGen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.test_sma.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class SelectWorkOuts : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_work_outs)

        val recyclerView = findViewById<RecyclerView>(R.id.recycleView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val primary = intent.getStringExtra("typePrimary")
        val secondary = intent.getStringExtra("typeSecondary")
        val primaryList = getWorkOut("Primary", primary.toString())
        val secondaryList = getWorkOut("Secondary", secondary.toString())
        val data = ArrayList<ItemsViewModel>()
        for (workout in primaryList) {
            data.add(
                ItemsViewModel(
                    R.drawable.ic_fitness,
                    workout.name.toString(),
                    workout.qty!!.toInt(),
                    workout.reps!!.toInt()
                )
            )
        }
        for (workout in secondaryList) {
            data.add(
                ItemsViewModel(
                    R.drawable.ic_fitness,
                    workout.name.toString(),
                    workout.qty!!.toInt(),
                    workout.reps!!.toInt()
                )
            )
        }
        val adapter = CustomAdapter(data)
        recyclerView.adapter = adapter

    }

    private val firestore = FirebaseFirestore.getInstance()
    fun getWorkOut(type: String, bodyPart: String): MutableList<WorkOut> {
        val exerciseList: MutableList<WorkOut> = mutableListOf()
        val collection = firestore.collection("training").document(type).collection(bodyPart)
        collection.get()
            .addOnSuccessListener { documents ->
                if (documents != null) {
                    for (document in documents) {
                        val workOut = WorkOut()
                        workOut.name = document["name"].toString()
                        workOut.qty = document["quantity"].toString().toInt()
                        workOut.reps = document["reps"].toString().toInt()
                        exerciseList.add(workOut)
                    }
                } else {
                    Log.d("TAG", "No elements!")
                }
            }
            .addOnFailureListener { it ->
                Log.d("TAG", " error : $it")
            }

        return exerciseList
    }
}
data class WorkOut(
    var reps: Int? = null,
    var name: String? = null,
    var qty: Int? = null,
)