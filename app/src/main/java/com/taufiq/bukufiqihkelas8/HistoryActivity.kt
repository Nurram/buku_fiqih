package com.taufiq.bukufiqihkelas8

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.taufiq.bukufiqihkelas8.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            tvBab1.setOnClickListener { moveToQuiz(1) }
            tvBab2.setOnClickListener { moveToQuiz(2) }
            tvBab3.setOnClickListener { moveToQuiz(3) }
            tvBab4.setOnClickListener { moveToQuiz(4) }
            tvBab5.setOnClickListener { moveToQuiz(5) }
            tvBab6.setOnClickListener { moveToQuiz(6) }
            tvBab7.setOnClickListener { moveToQuiz(7) }
        }
    }

    private fun moveToQuiz(bab: Int) {
        val i = Intent(this, QuizActivity::class.java)
        i.putExtra("bab", bab)
        i.putExtra("history", true)
        startActivity(i)
    }
}