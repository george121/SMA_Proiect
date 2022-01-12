package com.test_sma.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test_sma.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import android.content.Intent
import android.provider.ContactsContract
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.test_sma.MainActivity
import com.test_sma.setDetails.AccountDetails
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.view.*
import java.util.logging.Logger

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
                TextUtils.isEmpty(SetPhoneNum.text.toString().trim{it <= ' '})-> {
                    Toast.makeText(
                        this@Register,
                        "Please enter phone number!",
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
                    val PhoneNum: String = SetPhoneNum.text.toString().trim{it <= ' '}
                    val name: String = NameRegister.text.toString().trim{it <= ' '}


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
                                        Intent(this@Register, AccountDetails::class.java)
                                    intent.flags =
                                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    intent.putExtra("PhoneNum", PhoneNum)
                                    intent.putExtra("name", name)

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


