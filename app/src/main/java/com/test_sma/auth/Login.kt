package com.test_sma.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.core.Tag
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.test_sma.MainActivity
import com.test_sma.R
import com.test_sma.setDetails.AccountDetails1
import com.test_sma.setDetails.AccountDetails2
import com.test_sma.workOutGen.TypeSelect
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import java.lang.Exception

class Login : AppCompatActivity() {
    val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        RegisterNowButton.setOnClickListener {
            startActivity(Intent(this@Login, Register::class.java))
        }

        LoginButton.setOnClickListener {
            when {
                TextUtils.isEmpty(TextEmailAddress.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@Login,
                        "Please enter email!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(TextPassword.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@Login,
                        "Please enter password!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    val email: String = TextEmailAddress.text.toString().trim { it <= ' ' }
                    val passwrd: String = TextPassword.text.toString().trim { it <= ' ' }
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, passwrd)
                        .addOnSuccessListener {
                            val userId = FirebaseAuth.getInstance().currentUser!!.uid
                            Toast.makeText(
                                this@Login,
                                "Login succesfull",
                                Toast.LENGTH_SHORT
                            ).show()

                            val docRef = db.collection("users").document(userId)
                            docRef.get()
                                .addOnSuccessListener { document->
                                    if(document!=null){
                                        Toast.makeText(this@Login,
                                        "Welcome Back!",Toast.LENGTH_SHORT).show()
                                        val intent =
                                            Intent(this@Login, TypeSelect::class.java)
                                        intent.flags =
                                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                        intent.putExtra("userName",document.get("Name").toString())
                                        intent.putExtra("email_id",email)
                                        startActivity(intent)
                                        finish()
                                    }
                                    else{
                                        Toast.makeText(this@Login,
                                            "Taking you to setup account!",Toast.LENGTH_SHORT).show()
                                        val intent =
                                            Intent(this@Login, AccountDetails1::class.java)
                                        intent.flags =
                                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                        startActivity(intent)
                                        finish()

                                    }
                                }
                                .addOnFailureListener { Exception->
                                    Log.d("TAG","Failed with",Exception)
                                }

                        }
                        .addOnFailureListener{
                            Toast.makeText(this,"Login Failed!",Toast.LENGTH_SHORT).show()
                        }


                }
            }

        }
    }

}
