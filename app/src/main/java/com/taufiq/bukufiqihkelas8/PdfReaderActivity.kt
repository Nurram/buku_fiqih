package com.taufiq.bukufiqihkelas8

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.taufiq.bukufiqihkelas8.databinding.ActivityPdfReaderBinding
import java.io.IOException
import java.io.InputStream


class PdfReaderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPdfReaderBinding
    private lateinit var inputStream: InputStream

    private var default = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPdfReaderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            inputStream = assets.open("buku.pdf")
        } catch (e: IOException) {
            e.printStackTrace()
        }

        val start = intent.getIntExtra("start", 0)
        val end = intent.getIntExtra("end", 1)
        val bab = intent.getIntExtra("bab", 1)

        default = savedInstanceState?.getInt("def") ?: 0

        binding.pdfView.apply {
            fromStream(inputStream)
                .pages(*(start..end).toList().toIntArray())
                .defaultPage(default)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .enableAntialiasing(true)
                .onPageChange { page, pageCount ->
                    default = page

                    if (page == pageCount - 1) binding.btnQuiz.visibility = View.VISIBLE
                    else binding.btnQuiz.visibility = View.GONE
                }
                .load()
        }

        binding.btnQuiz.setOnClickListener {
            val i = Intent(this, QuizActivity::class.java)
            i.putExtra("bab", bab)
            startActivity(i)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("def", default)
    }
}