package com.example.accordianapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val btnLogRegister = findViewById(R.id.btnLogRegister) as Button

        btnLogRegister.setOnClickListener {
            onBackPressed()
        }
        val btnRegister = findViewById(R.id.btnRegister) as Button
        btnRegister.setOnClickListener(){
//            Toast.makeText(this,"login button clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right)
    }

}