package com.test_sma.setDetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.test_sma.R
import kotlinx.android.synthetic.main.activity_account_details.*
import kotlinx.android.synthetic.main.activity_register.*

class AccountDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_details)
        val database = FirebaseDatabase.getInstance().getReference("users")
        val phoneNum=intent.getStringExtra("PhoneNum")
        val name= intent.getStringExtra("name")

        @IgnoreExtraProperties
        data class User(val username: String? = null, val PhoneNo: String? = null,val bdType : String?=null) {

        }

        fun writeNewUser( name: String, PhoneNo: String,bdType:String) {
            val user = User(name, phoneNum, bdType)

            database.child(PhoneNo).setValue(user).addOnSuccessListener  {
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
            }
                .addOnFailureListener {
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()

                }
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()

        }

        EndoBtn.setOnClickListener{
            if (name != null&&phoneNum!=null) {
                writeNewUser(name, phoneNum, "EndoMorph")
            }
        }

        EctoBtn.setOnClickListener{
            if (name != null&&phoneNum!=null) {
                writeNewUser(name, phoneNum, "EctoMorph")
            }
        }

        MesoBtn.setOnClickListener{
            if (name != null&&phoneNum!=null) {
                writeNewUser(name, phoneNum, "MesoMorph")
            }
        }

    }
}