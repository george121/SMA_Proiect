package com.test_sma.workOutGen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.test_sma.MainActivity
import com.test_sma.R

class TypeTimeSelect : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_type_time_select)
    }


    fun getExercises(type1:String,type2:String){
        val db = Firebase.firestore
        val dbRef = db.collection("training").document(type1).collection(type2)
        dbRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Toast.makeText(
                        this@TypeTimeSelect,
                        "Getting exercises", Toast.LENGTH_SHORT
                    ).show()
                    val exercise1 =  dbRef.whereGreaterThan("time",20).limit(2)
                }
            }
    }
}
