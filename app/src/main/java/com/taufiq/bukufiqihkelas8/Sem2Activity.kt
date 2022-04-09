package com.taufiq.bukufiqihkelas8

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.taufiq.bukufiqihkelas8.databinding.ActivitySem2Binding

class Sem2Activity : AppCompatActivity() {
    private lateinit var binding: ActivitySem2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySem2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            tvBab5.setOnClickListener {
                val i = Intent(this@Sem2Activity, PdfReaderActivity::class.java)
                i.putExtra("start", 109)
                i.putExtra("end", 125)
                i.putExtra("bab", 5)
                startActivity(i)
            }
            tvBab6.setOnClickListener {
                val i = Intent(this@Sem2Activity, PdfReaderActivity::class.java)
                i.putExtra("start", 127)
                i.putExtra("end", 157)
                i.putExtra("bab", 6)
                startActivity(i)
            }
            tvBab7.setOnClickListener {
                val i = Intent(this@Sem2Activity, PdfReaderActivity::class.java)
                i.putExtra("start", 159)
                i.putExtra("end", 182)
                i.putExtra("bab", 7)
                startActivity(i)
            }
        }
    }
}