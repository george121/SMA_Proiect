package com.test_sma

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.test_sma.auth.Login
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val userId = intent.getStringExtra("user_id")
        val emailId = intent.getStringExtra("email_id")
        tv_uid.text = "User ID :: $userId"
        tv_emailid.text = "Email :: $emailId"

        btn_logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this@MainActivity,Login::class.java))
            finish()
        }
    }
}