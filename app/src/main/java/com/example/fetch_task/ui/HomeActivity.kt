package com.example.fetch_task.ui

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.fetch_task.R
import com.example.fetch_task.ui.TaskActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val button = findViewById<Button>(R.id.TaskButton)
        button.setOnClickListener{
            val intent = Intent(this , TaskActivity::class.java )
            startActivity(intent)
            finish()
    }
    }
}
