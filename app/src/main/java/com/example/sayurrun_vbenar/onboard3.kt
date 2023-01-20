package com.example.sayurrun_vbenar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_onboard3.*

class onboard3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboard3)
        btnMasukListener()
        btnDaftarListener()
    }
    private fun btnDaftarListener() {
        btnRegister.setOnClickListener {
            startActivity(Intent(this, register::class.java))
        }
    }
    private fun btnMasukListener() {
        btnLogin.setOnClickListener {
            startActivity(Intent(this, login::class.java))
        }
    }
}