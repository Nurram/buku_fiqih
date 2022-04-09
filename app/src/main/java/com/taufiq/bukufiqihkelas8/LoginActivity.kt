package com.taufiq.bukufiqihkelas8

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.taufiq.bukufiqihkelas8.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.apply {
            btnLogin.setOnClickListener {
                val email = etEmail.text.toString()
                val password = etPassword.text.toString()
                setVisibility(true)

                login(email, password)
            }
            tvRegister.setOnClickListener {
                val i = Intent(this@LoginActivity, RegisterActivity::class.java)
                startActivity(i)
            }
        }
    }

    override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser

        if (currentUser != null) {
            moveToHome()
        }
    }

    private fun moveToHome() {
        val i = Intent(this, HomeActivity::class.java)
        startActivity(i)
        finish()
    }

    private fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                setVisibility(false)

                if (it.isSuccessful) {
                    moveToHome()
                }

            }.addOnFailureListener {
                if (it.message!!.contains("The password is invalid"))
                    Toast.makeText(
                        this, R.string.incorrect_email_password, Toast.LENGTH_SHORT
                    ).show()
                else Toast.makeText(
                    this, it.message, Toast.LENGTH_SHORT
                ).show()
            }
    }

    private fun setVisibility(show: Boolean) {
        binding.apply {
            if (show) {
                btnLogin.visibility = View.GONE
                loginProgress.visibility = View.VISIBLE
            } else {
                btnLogin.visibility = View.VISIBLE
                loginProgress.visibility = View.GONE
            }
        }
    }
}