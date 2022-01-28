package com.test_sma.workOutGen

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.*
import com.test_sma.R
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.activity_select_work_outs.*

private lateinit var recyclerView: RecyclerView
private lateinit var primaryList:ArrayList<SelectWorkOuts.WorkOut>
private lateinit var secondaryList: ArrayList<SelectWorkOuts.WorkOut>
private lateinit var myAdapter: CustomAdapter
private lateinit var db : FirebaseFirestore
private var count : Int =0

public var chosenPList:ArrayList<SelectWorkOuts.WorkOut> = arrayListOf()
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

        title="Primary Workout : $count/4"
        myAdapter = CustomAdapter(primaryList, chosenPList)

        recyclerView.adapter = myAdapter

        EventChangeListener( primary.toString())
        continueBTN.setOnClickListener{
            if(count<2)
            {
                Toast.makeText(this,"Select atleast two!",Toast.LENGTH_SHORT).show()
            }
            else
            {
                val intent= Intent(this,SecondWorkOut::class.java)
                intent.flags =
                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                intent.putExtra("Secondary",secondary)
                intent.putParcelableArrayListExtra("primaryList", chosenPList)
                startActivity(intent)
                finish()
            }
        }
    }

@Parcelize
    data class WorkOut(
        var name: String? = null,
        var quantity: String? = null,
        var reps: String? = null,

    ):Parcelable
}

public fun setCount(newCount:Int){
    count = newCount
}
public fun getCount(): Int {
    return count
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
