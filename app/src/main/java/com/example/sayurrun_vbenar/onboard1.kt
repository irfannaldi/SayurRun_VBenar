package com.example.sayurrun_vbenar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_onboard1.*

class onboard1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboard1)

        btnNextListener()
    }
    private fun btnNextListener() {
        on1.setOnClickListener {
            startActivity(Intent(this, onboard2::class.java))
        }
    }
}