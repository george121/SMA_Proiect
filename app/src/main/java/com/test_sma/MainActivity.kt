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
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {
    val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val userName = intent.getStringExtra("userName")
        val emailId = intent.getStringExtra("email_id")

                tv_uid.text = "Hello :: $userName"
                tv_emailid.text = "Email :: $emailId"

                btn_logout.setOnClickListener {
                    FirebaseAuth.getInstance().signOut()
                    startActivity(Intent(this@MainActivity,Login::class.java))
                    finish()
                }

    }
}