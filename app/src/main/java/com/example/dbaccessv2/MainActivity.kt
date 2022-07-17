package com.example.dbaccessv2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DataBaseHandler(this).insertData()

        btn_Selection.setOnClickListener() {
            val intent = Intent(this, SelectionActivity::class.java)
            startActivity(intent)
        }

        btn_Locate.setOnClickListener() {
            val intent = Intent(this, LocationActivity::class.java)
            startActivity(intent)
        }

        btn_Quit.setOnClickListener() {
            moveTaskToBack(true)
            android.os.Process.killProcess(android.os.Process.myPid())
            exitProcess(1)
        }
    }
}
