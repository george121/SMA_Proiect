package com.test_sma

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.test_sma.auth.Login
import com.test_sma.workOutGen.SelectWorkOuts
import com.test_sma.workOutGen.TypeTimeSelect
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title="Home"
        val db = Firebase.firestore
        val docRef = db.collection("users")
        val uid=FirebaseAuth.getInstance().currentUser!!.uid
        var userName:String =""
        docRef.document(uid).get().addOnSuccessListener { document->
            userName= document.get("Name").toString()
            tv_uid.text = "Hello $userName"


        }
            .addOnFailureListener {Exception->
                Log.d("TAG","Failed with",Exception)

            }

        btn_logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this@MainActivity,Login::class.java))
            finish()
        }
        startBtn.setOnClickListener {
            intent=Intent(this,TypeTimeSelect::class.java)
            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.putExtra("userName",userName)
            startActivity(intent)
            finish()
        }

        reviewBtn.setOnClickListener {
            Toast.makeText(this,"To-Do, Not yet implemented",Toast.LENGTH_SHORT).show()
        }


    }
}