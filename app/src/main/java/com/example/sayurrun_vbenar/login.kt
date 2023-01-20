package com.example.sayurrun_vbenar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.example.sayurrun_vbenar.databinding.ActivityLoginBinding

class login : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var auth: FirebaseAuth
    lateinit var button: Button
    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var isiEmail: String
    lateinit var isiPassword: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()

        title = "KotlinApp"
        button = findViewById(R.id.btnmasuk)
        email = findViewById(R.id.editemail)
        password = findViewById(R.id.editpassword)

        button.setOnClickListener() {
            isiEmail = email.text.toString()
            isiPassword = password.text.toString()
            LoginFirebase(isiEmail, isiPassword)
            Log.i("data : ", isiEmail + isiPassword)

            //Validasi email
            if (isiEmail.isEmpty()) {
                email.error = "Email Harus Diisi"
                email.requestFocus()
                return@setOnClickListener
            }

            //Jika email salah
            if (!Patterns.EMAIL_ADDRESS.matcher(isiEmail).matches()) {
                email.error = "Email Tidak Valid"
                password.requestFocus()
                return@setOnClickListener
            }

            //Validasi Password
            if (isiPassword.isEmpty()) {
                password.error = "Password Harus Diisi"
                password.requestFocus()
                return@setOnClickListener
            }
        }

    }

    private fun LoginFirebase(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Selamat Datang $email", Toast.LENGTH_SHORT).show()
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}