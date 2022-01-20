package com.test_sma.workOutGen

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.firebase.ui.firestore.SnapshotParser
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.test_sma.R
import kotlinx.android.synthetic.main.activity_main.*

var adapter:CustomAdapter?=null
private val firestore = Firebase.firestore
class SelectWorkOuts : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lateinit var primaryList:ArrayList<WorkOut>
        lateinit var secondaryList: ArrayList<WorkOut>
        setContentView(R.layout.activity_select_work_outs)
        var recyclerView = findViewById<RecyclerView>(R.id.recycleView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val primary = intent.getStringExtra("typePrimary")
        val secondary = intent.getStringExtra("typeSecondary")
        val query =firestore.collection("training").document("Primary").collection(primary.toString())
      //  secondaryList = getWorkOut("Secondary", secondary.toString())
        val options: FirestoreRecyclerOptions<WorkOut> = FirestoreRecyclerOptions.Builder<WorkOut>()
            .setQuery(query, WorkOut::class.java)
            .build()


        val builder = FirestoreRecyclerOptions.Builder<WorkOut>()
            .setQuery(query, object : SnapshotParser<WorkOut> {
                override fun parseSnapshot(snapshot: DocumentSnapshot): WorkOut {
                    return snapshot.toObject(WorkOut::class.java)!!
                }
            })
            .setLifecycleOwner(this)

        val adapter = CustomAdapter(options)
        recyclerView.adapter = adapter
    }
    override fun onStart() {
        super.onStart()
        adapter!!.startListening()
    }

    override fun onStop() {
        super.onStop()

        if (adapter != null) {
            adapter!!.stopListening()
        }
    }

    fun getList(mutableList: MutableList<WorkOut>): MutableList<WorkOut> {
        return mutableList
    }
     fun getWorkOut(type: String, bodyPart: String): ArrayList<WorkOut> {
        var exerciseList: ArrayList<WorkOut> = ArrayList()
         firestore.collection("training").document(type).collection(bodyPart)
             .get().addOnCompleteListener { task ->
                 when {
                     task.isSuccessful -> {
                            val documents = task.result
                            for(document: QueryDocumentSnapshot in documents){
                             exerciseList.add(document.toObject(WorkOut::class.java))

                         }

                     }
                 }
             }
            .addOnFailureListener { it ->
                Log.d("TAG", " error : $it")
            }



        return exerciseList
    }
}


data class WorkOut(
    var reps: String? = null,
    var name: String? = null,
    var qty: String? = null,
)