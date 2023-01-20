package com.example.sayurrun_vbenar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.sayurrun_vbenar.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class register : AppCompatActivity() {
    lateinit var button : Button
    lateinit var edtnama: EditText
    lateinit var edtemaill: EditText
    lateinit var edtpass: EditText
    lateinit var email: String
    lateinit var password: String
    lateinit var nama: String


    lateinit var bindingregister : ActivityRegisterBinding
    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        bindingregister = ActivityRegisterBinding.inflate(layoutInflater)

        auth = FirebaseAuth.getInstance()

        button = findViewById(R.id.btnregister)
        edtnama = findViewById(R.id.edtnama)
        edtemaill = findViewById(R.id.edtemaill)
        edtpass = findViewById(R.id.edtpass)

        button.setOnClickListener() {
            nama = edtnama.text.toString()
            email = edtemaill.text.toString()
            password = edtpass.text.toString()
            Log.i("data:", nama + email + password)
            if (email.isEmpty()){
                bindingregister.edtemaill.error = "Email Harus Diisi"
                bindingregister.edtemaill.requestFocus()
                return@setOnClickListener
            }

            //Validasi email tidak sesuai
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                bindingregister.edtemaill.error = "Email Tidak Valid"
                bindingregister.edtemaill.requestFocus()
                return@setOnClickListener
            }

            //Validasi password
            if (password.isEmpty()){
                bindingregister.edtpass.error = "Password Harus Diisi"
                bindingregister.edtpass.requestFocus()
                return@setOnClickListener
            }

            //Validasi panjang password
            if (password.length < 6){
                bindingregister.edtpass.error = "Password Minimal 6 Karakter"
                bindingregister.edtpass.requestFocus()
                return@setOnClickListener
            }

            RegisterFirebase(email, password)
        }
    }
    private fun RegisterFirebase(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    Toast.makeText(this, "Register Berhasil", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this,login::class.java)
                    startActivity(intent)
                } else{
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}