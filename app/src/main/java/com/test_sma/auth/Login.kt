package com.test_sma.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.test_sma.MainActivity
import com.test_sma.R
import com.test_sma.setDetails.AccountDetails
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class Login : AppCompatActivity() {
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
                            val firebaseUser = FirebaseAuth.getInstance().currentUser
                            Toast.makeText(
                                this@Login,
                                "Login succesfull",
                                Toast.LENGTH_SHORT
                            ).show()

                            val intent =
                                Intent(this@Login, AccountDetails::class.java)
                            intent.flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            intent.putExtra("user_id", FirebaseAuth.getInstance().currentUser!!.uid)
                            intent.putExtra("email_id", email)

                            startActivity(intent)
                            finish()
                        }
                        .addOnFailureListener{
                            Toast.makeText(this,"Login Failed!",Toast.LENGTH_SHORT).show()
                        }


                }
            }

        }
    }

}
