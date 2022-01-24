package com.test_sma.workOutGen

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.*
import com.test_sma.R

private lateinit var recyclerView: RecyclerView
private lateinit var primaryList:ArrayList<SelectWorkOuts.WorkOut>
private lateinit var secondaryList: ArrayList<SelectWorkOuts.WorkOut>
private lateinit var myAdapter: CustomAdapter
private lateinit var db : FirebaseFirestore
public var count : Int =0

class SelectWorkOuts : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_work_outs)
        context=this
        val primary = intent.getStringExtra("typePrimary")
        val secondary = intent.getStringExtra("typeSecondary")

        recyclerView = findViewById(R.id.recycleView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        primaryList = arrayListOf()
        secondaryList = arrayListOf()

        title="Primary Workout"
        myAdapter = CustomAdapter(primaryList)

        recyclerView.adapter = myAdapter

        EventChangeListener( primary.toString())

    }


    data class WorkOut(
        var name: String? = null,
        var quantity: String? = null,
        var reps: String? = null,

    )
}
private fun EventChangeListener( bodyPart: String) {
    db = FirebaseFirestore.getInstance()
    db.collection("training").document("Primary").collection(bodyPart)
        .addSnapshotListener(object : EventListener<QuerySnapshot> {
            override fun onEvent(
                value: QuerySnapshot?,
                error: FirebaseFirestoreException?
            ) {
                if (error != null) {
                    Log.e("Firestore Error", error.message.toString())
                    return
                }
                for (dc: DocumentChange in value?.documentChanges!!) {

                    if (dc.type == DocumentChange.Type.ADDED) {

                        primaryList.add(dc.document.toObject(SelectWorkOuts.WorkOut::class.java))


                    }

                }
                myAdapter.notifyDataSetChanged()

            }
        })

}
