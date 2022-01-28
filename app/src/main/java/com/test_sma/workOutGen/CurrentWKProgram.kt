package com.test_sma.workOutGen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test_sma.MainActivity
import com.test_sma.R
import java.util.ArrayList
private lateinit var recyclerView: RecyclerView
lateinit var my2Adapter: SecondAdapter
class CurrentWKProgram : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_wkprogram)
        title="Nice Job! Keep Going!"
        val primaryList=intent.getParcelableArrayListExtra<SelectWorkOuts.WorkOut>("primaryList")
        val secondaryList=intent.getParcelableArrayListExtra<SelectWorkOuts.WorkOut>("secondaryList")
        var myList:ArrayList<SelectWorkOuts.WorkOut> = primaryList!!
        if (secondaryList != null) {
            myList.addAll(secondaryList)
        }
        val mContext=this


            recyclerView = findViewById(R.id.recyclerView3)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.setHasFixedSize(true)
            my2Adapter=SecondAdapter(myList, mContext)
            recyclerView.adapter = my2Adapter


    }

}
