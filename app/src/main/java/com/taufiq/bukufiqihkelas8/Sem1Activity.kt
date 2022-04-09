package com.taufiq.bukufiqihkelas8

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.taufiq.bukufiqihkelas8.databinding.ActivitySem1Binding

class Sem1Activity : AppCompatActivity() {
    private lateinit var binding: ActivitySem1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySem1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            tvBab1.setOnClickListener {
                val i = Intent(this@Sem1Activity, PdfReaderActivity::class.java)
                i.putExtra("start", 16)
                i.putExtra("end", 37)
                i.putExtra("bab", 1)
                startActivity(i)
            }
            tvBab2.setOnClickListener {
                val i = Intent(this@Sem1Activity, PdfReaderActivity::class.java)
                i.putExtra("start", 39)
                i.putExtra("end", 59)
                i.putExtra("bab", 2)
                startActivity(i)
            }
            tvBab3.setOnClickListener {
                val i = Intent(this@Sem1Activity, PdfReaderActivity::class.java)
                i.putExtra("start", 61)
                i.putExtra("end", 84)
                i.putExtra("bab", 3)
                startActivity(i)
            }
            tvBab4.setOnClickListener {
                val i = Intent(this@Sem1Activity, PdfReaderActivity::class.java)
                i.putExtra("start", 86)
                i.putExtra("end", 99)
                i.putExtra("bab", 4)
                startActivity(i)
            }
        }
    }
}