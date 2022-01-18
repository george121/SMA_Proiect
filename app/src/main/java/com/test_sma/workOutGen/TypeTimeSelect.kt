package com.test_sma.workOutGen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import com.test_sma.R
import kotlinx.android.synthetic.main.activity_type_time_select.*

class TypeTimeSelect : AppCompatActivity() {
    lateinit var radioButton1: RadioButton
    lateinit var radioButton2: RadioButton
    val userName=intent.getStringExtra("userName")
    private lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Hello $userName, select part to train"
        setContentView(R.layout.activity_type_time_select)
        button = findViewById(R.id.submitBtn)
        submitBtn.setOnClickListener(){
            val selected1 = primaryGroup.checkedRadioButtonId
            val selected2 = secondaryGroup.checkedRadioButtonId
            radioButton1 = findViewById(selected1)
            radioButton2 = findViewById(selected2)
            val intent = Intent(this,SelectWorkOuts::class.java)
            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.putExtra("typePrimary",radioButton1.text)
            intent.putExtra("typeSecondary",radioButton2.text)
            startActivity(intent)
            finish()

        }
    }
}

