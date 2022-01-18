package com.test_sma.workOutGen.ui.theme.ui

import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Singleton

@Singleton
class WorkOutRepo {
    private val firestore= FirebaseFirestore.getInstance()
    val exerciseList : MutableList<WorkOut> = mutableListOf()
    fun getWorkOut(type:String,bodyPart: String)= callbackFlow <List<WorkOut>> {
        val collection = firestore.collection("training").document(type).collection(bodyPart)
        collection.get()
            .addOnSuccessListener { documents->
            if (documents!=null){
                for (document in documents){
                    val workOut=WorkOut()
                    workOut.name=document["name"].toString()
                    workOut.qty=document["quantity"].toString().toInt()
                    workOut.reps=document["reps"].toString().toInt()
                    exerciseList.add(workOut)
                }
            }
            else{
                Log.d("TAG","No elements!")
            }
        }
            .addOnFailureListener{it->
                Log.d("TAG"," error : $it")
            }


    }

}



data class WorkOut(
    var reps: Int? = null,
    var name: String? = null,
    var qty: Int? = null,
)