package com.test_sma.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test_sma.R
import com.google.firebase.auth.FirebaseAuth
import android.content.Intent
import android.text.TextUtils
import android.widget.Toast
import com.test_sma.setDetails.AccountDetails1
import com.test_sma.setDetails.AccountDetails2
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.view.*

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R .layout.activity_register)
        btn_register.setOnClickListener{
            when{
                TextUtils.isEmpty(RegisterEmailAddress.text.toString().trim{it <= ' '})-> {
                    Toast.makeText(
                        this@Register,
                        "Please enter email!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(SetPassword.text.toString().trim{it <= ' '})->{
                    Toast.makeText(
                        this@Register,
                        "Please enter password!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(ConfirmPassword.text.toString().trim{it <= ' '})-> {
                    Toast.makeText(
                        this@Register,
                        "Please confirm password!",
                        Toast.LENGTH_SHORT
                    ).show()
                }


                !TextUtils.equals(SetPassword.text.toString().trim{it <= ' '},
                    ConfirmPassword.text.toString().trim{it <= ' '})->{
                        Toast.makeText(
                            this@Register,
                            "Passwords do not match",
                            Toast.LENGTH_SHORT
                        ).show()
                    }


                else -> {
                    val email : String = RegisterEmailAddress.text.toString().trim{it <= ' '}
                    val password: String = SetPassword.text.toString().trim{it <= ' '}


                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnFailureListener{
                        Toast.makeText(this,"Registration Failed", Toast.LENGTH_SHORT).show()
                    }
                        .addOnSuccessListener{
                                    val firebaseUser= FirebaseAuth.getInstance().currentUser

                                    Toast.makeText(
                                        this@Register,
                                        "Register succesfull",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    val intent =
                                        Intent(this@Register, AccountDetails1::class.java)
                                    intent.flags =
                                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK


                                    startActivity(intent)
                                    finish()
                            }
                        .addOnFailureListener {
                            Toast.makeText(
                            this@Register,
                            "Registration Failed",
                            Toast.LENGTH_SHORT
                        ).show()
                        }


                        }


                }


            }


        }


    }


