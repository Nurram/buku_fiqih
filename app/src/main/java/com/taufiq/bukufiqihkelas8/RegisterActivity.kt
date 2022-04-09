package com.taufiq.bukufiqihkelas8

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.taufiq.bukufiqihkelas8.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.apply {
            btnRegister.setOnClickListener {
                val email = etEmail.text.toString()
                val password = etPassword.text.toString()
                setVisibility(true)

                register(email, password)
            }
        }
    }

    private fun register(email: String?, password: String?) {
        if (email.isNullOrBlank() || password.isNullOrBlank()) {
            showToast(R.string.please_fill)
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showToast(R.string.enter_valid_email)
        } else if (password.length < 6) {
            showToast(R.string.minimum_length_6)
        } else {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    setVisibility(false)

                    if (it.isSuccessful) {
                        val i = Intent(this, HomeActivity::class.java)
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        startActivity(i)
                        finish()
                    } else {
                        showToast(it.exception?.message)
                    }
                }.addOnFailureListener {
                    setVisibility(false)

                    if (it.message != null)
                        showToast(it.message)
                }
        }
    }

    private fun showToast(msg: Int) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun showToast(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun setVisibility(show: Boolean) {
        binding.apply {
            if (show) {
                btnRegister.visibility = View.GONE
                pbRegister.visibility = View.VISIBLE
            } else {
                btnRegister.visibility = View.VISIBLE
                pbRegister.visibility = View.GONE
            }
        }
    }
}