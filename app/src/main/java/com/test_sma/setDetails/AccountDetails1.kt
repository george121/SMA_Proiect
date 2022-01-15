package com.test_sma.setDetails

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.test_sma.R
import kotlinx.android.synthetic.main.accountdetails1.*
import kotlinx.android.synthetic.main.activity_register.*
import java.text.DateFormat

class AccountDetails1 : AppCompatActivity() {
    val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.accountdetails1)
        Continuebtn.setOnClickListener {
            when {
                TextUtils.isEmpty(editName.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@AccountDetails1,
                        "Please enter your name!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(editPhoneNo.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@AccountDetails1,
                        "Please enter your Phone number!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(editDoB.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@AccountDetails1,
                        "Please enter your birth date!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(editWeight.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@AccountDetails1,
                        "Please enter your current weight!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                TextUtils.isEmpty(editHeight.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@AccountDetails1,
                        "Please enter your height!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else ->{
                    val name = editName.text.toString().trim { it <= ' ' }
                    val phonenum =editPhoneNo.text.toString().trim { it <= ' ' }
                    val dateofbirth = editDoB.text.toString().trim { it <= ' ' }
                    val weight = editWeight.text.toString().trim { it <= ' ' }
                    val height = editHeight.text.toString().trim { it <= ' ' }
                    addToFireStore1(name,phonenum,dateofbirth,weight,height)

                    val intent = Intent(this@AccountDetails1,AccountDetails2::class.java)
                    intent.flags =
                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()

                }
            }



        }
    }


    fun addToFireStore1(name : String,phoneno:String,dateofbirth:String,weight:String,height:String){
        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        val user = hashMapOf(
            "Name" to name,
            "Phone Number" to phoneno,
            "Birth date" to dateofbirth,
            "Weight" to weight,
            "Height" to height
        )
        db.collection("users").document(userId)
            .set(user)
            .addOnSuccessListener {
                Toast.makeText(this@AccountDetails1,"Success!",Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this@AccountDetails1,"Epic Fail!",Toast.LENGTH_SHORT).show()
            }
    }
}