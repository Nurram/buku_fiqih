package com.taufiq.bukufiqihkelas8

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.taufiq.bukufiqihkelas8.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            tvSemester1.setOnClickListener {
                val i = Intent(this@HomeActivity, Sem1Activity::class.java)
                startActivity(i)
            }
            tvSemester2.setOnClickListener {
                val i = Intent(this@HomeActivity, Sem2Activity::class.java)
                startActivity(i)
            }
            tvHistory.setOnClickListener {
                val i = Intent(this@HomeActivity, HistoryActivity::class.java)
                startActivity(i)
            }
            tvLogout.setOnClickListener {
                FirebaseAuth.getInstance().signOut()

                val i = Intent(this@HomeActivity, LoginActivity::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(i)
            }
        }
    }
}