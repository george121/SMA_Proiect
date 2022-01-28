package com.test_sma.setDetails

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.test_sma.MainActivity
import com.test_sma.R
import kotlinx.android.synthetic.main.activity_account_details2.*
import kotlinx.android.synthetic.main.activity_register.*

class AccountDetails2 : AppCompatActivity() {
    val userId = FirebaseAuth.getInstance().currentUser!!.uid
    val email = FirebaseAuth.getInstance().currentUser!!.email
    val db = Firebase.firestore
    val dbRef = db.collection("users").document(userId)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_details2)


        EndoBtn.setOnClickListener {
            addToFirestore("EndoMorph")
            changeActivity()

        }

        EctoBtn.setOnClickListener {
            addToFirestore("Ectomorph")
            changeActivity()

        }

        MesoBtn.setOnClickListener {
            addToFirestore("MesoMorph")
            changeActivity()

        }

    }

    fun addToFirestore(bodType: String) {

        val bdType = hashMapOf(
            "bodytype" to bodType
        )

        dbRef.set(bdType, SetOptions.merge())
            .addOnSuccessListener {
                Toast.makeText(this@AccountDetails2, "Success!", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this@AccountDetails2, "Epic Fail!", Toast.LENGTH_SHORT).show()
            }
    }

    fun changeActivity() {

        dbRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Toast.makeText(
                        this@AccountDetails2,
                        "Welcome Back!", Toast.LENGTH_SHORT
                    ).show()
                    val intent =
                        Intent(this@AccountDetails2, MainActivity::class.java)
                    intent.flags =
                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                }
            }
    }
}