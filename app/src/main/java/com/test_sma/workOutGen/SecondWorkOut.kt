package com.test_sma.workOutGen

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.*
import com.test_sma.R
import kotlinx.android.synthetic.main.activity_second_work_out.*

private lateinit var recyclerView: RecyclerView
private lateinit var secondaryList: ArrayList<SelectWorkOuts.WorkOut>
private lateinit var myAdapter: CustomAdapter
private lateinit var db : FirebaseFirestore
public lateinit var context:Context
var secondaryCList : ArrayList<SelectWorkOuts.WorkOut> = arrayListOf()
class SecondWorkOut : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_work_out)
        context=this
        setCount(0)
        val secondary = intent.getStringExtra("Secondary")
        val pList = intent.getParcelableArrayListExtra<SelectWorkOuts.WorkOut>("primaryList")
        recyclerView = findViewById(R.id.recyclerView2)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        secondaryList = arrayListOf()

        title="Secondary Workout"
        myAdapter = CustomAdapter(secondaryList, secondaryCList)

        recyclerView.adapter = myAdapter

        EventChangeListener( secondary.toString())

        continueBTN2.setOnClickListener{
            if (getCount()<2){
                Toast.makeText(this,"Choose atleast Two!",Toast.LENGTH_SHORT).show()
            }
            else{
                val intent=Intent(this,CurrentWKProgram::class.java)
                intent.flags =
                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                intent.putParcelableArrayListExtra("primaryList", pList)
                intent.putParcelableArrayListExtra("secondaryList", secondaryCList)
                startActivity(intent)
                finish()
            }
        }
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

                        secondaryList.add(dc.document.toObject(SelectWorkOuts.WorkOut::class.java))


                    }

                }
                myAdapter.notifyDataSetChanged()

            }
        })

}

