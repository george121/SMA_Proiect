package com.test_sma.workOutGen

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.*
import com.test_sma.R
private lateinit var recyclerView: RecyclerView
private lateinit var primaryList:ArrayList<SelectWorkOuts.WorkOut>
private lateinit var secondaryList: ArrayList<SelectWorkOuts.WorkOut>
private lateinit var myAdapter: CustomAdapter
private lateinit var db : FirebaseFirestore
public lateinit var context:Context
class SecondWorkOut : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_work_out)
        context=this

        val secondary = intent.getStringExtra("typeSecondary")

        recyclerView = findViewById(R.id.recycleView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        primaryList = arrayListOf()
        secondaryList = arrayListOf()

        title="Secondary Workout"
        myAdapter = CustomAdapter(primaryList)

        recyclerView.adapter = myAdapter

        EventChangeListener( secondary.toString())
    }
}

private fun EventChangeListener( bodyPart: String) {
    db = FirebaseFirestore.getInstance()
    db.collection("training").document("Secondary").collection(bodyPart)
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
                        countChanged(count)


                    }

                }
                myAdapter.notifyDataSetChanged()

            }
        })

}
private fun countChanged(count:Int)
{
    if(count>0){
        Toast.makeText(context,"Count Increased",Toast.LENGTH_SHORT).show()
    }
}