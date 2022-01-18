package com.test_sma.workOutGen

import android.app.DownloadManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import com.test_sma.workOutGen.ui.theme.TEST_SMATheme
import javax.inject.Inject
import javax.inject.Singleton
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class TypeSelect : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val name = intent.getStringExtra("userName").toString()
        setContent {
            TEST_SMATheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                }
            }
        }
    }
}

@Composable
fun Greeting2() {

}

