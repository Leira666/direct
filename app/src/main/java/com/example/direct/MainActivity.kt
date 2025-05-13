package com.example.direct

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.direct.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class MainActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge() 
        setContentView(R.layout.activity_main)
        binding.registo.setOnClickListener{
            val email = binding.textemail.text.toString()
            val password = binding.textpassword.text.toString()
            registrarUsuario(email, password)
        }
        binding.btnlogin.setOnClickListener{
            val email = binding.textemail.text.toString()
            val password = binding.textpassword.text.toString()
            iniciarSesion(email, password)
        }

    }
    private val auth = FirebaseAuth.getInstance()

    private fun registrarUsuario (email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "¡Registro exitoso!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, secondActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "NEL SACATE: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
    private fun iniciarSesion(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "¡Bienvenido!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, secondActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
