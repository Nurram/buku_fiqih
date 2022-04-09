package com.taufiq.bukufiqihkelas8

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.taufiq.bukufiqihkelas8.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var ref: DatabaseReference

    private var email: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        database = Firebase.database
        ref = database.getReference("buku_fiqih_kelas_8")
    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null) {
            email = auth.currentUser!!.email
            email = email?.replace("@", "")
            email = email?.replace(".", "")

            initUI()
        } else {
            showToast("Terjadi kesalahan, Periksa koneksi anda!")
            finish()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initUI() {
        val isHistory = intent.getBooleanExtra("history", false)
        val bab = intent.getIntExtra("bab", 1)
        val questions = QuizBank.questions[bab]

        binding.apply {
            ivBack.setOnClickListener { finish() }

            tvQuestion1.text = "1. ${questions!![0]}"
            tvQuestion2.text = "2. ${questions[1]}"
            tvQuestion3.text = "3. ${questions[2]}"
            tvQuestion4.text = "4. ${questions[3]}"
            tvQuestion5.text = "5. ${questions[4]}"

            btnKirim.setOnClickListener {
                showConfirmDialog(bab)
            }

            if (isHistory) {
                fetchHistoryData(bab)
            }
        }
    }

    private fun fetchHistoryData(bab: Int) {
        binding.pbHistory.visibility = View.VISIBLE

        val answers = arrayListOf<String>()
        val child = ref.child(email!!)
        val babChild = child.child("Bab $bab")

        babChild.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                binding.pbHistory.visibility = View.GONE

                snapshot.children.forEach { ref ->
                    val answer = ref.value as String
                    answers.add(answer)
                }

                if (answers.size < 5) {
                    for (i in 0 until 5 - answers.size) {
                        answers.add("")
                    }
                }

                initHistoryUI(answers)
            }

            override fun onCancelled(error: DatabaseError) {
                binding.pbHistory.visibility = View.GONE
                showToast(error.message)
            }
        })
    }

    private fun initHistoryUI(answers: List<String>) {
        binding.apply {
            etQuestion1.setText(answers[0])
            etQuestion2.setText(answers[1])
            etQuestion3.setText(answers[2])
            etQuestion4.setText(answers[3])
            etQuestion5.setText(answers[4])

            etQuestion1.isEnabled = false
            etQuestion2.isEnabled = false
            etQuestion3.isEnabled = false
            etQuestion4.isEnabled = false
            etQuestion5.isEnabled = false

            btnKirim.visibility = View.GONE
        }
    }

    private fun showConfirmDialog(bab: Int) {
        val builder = AlertDialog.Builder(this)
        builder.apply {
            setMessage("Kirim jawaban? Pastikan semua soal telah dijawab dengan benar dan koneksi stabil")
            setTitle("Perhatian")
            setPositiveButton("Kirim") { _, _ ->

                val child = ref.child(email!!)
                val babChild = child.child("Bab $bab")

                binding.apply {
                    babChild.child("Nomor 1").setValue(etQuestion1.text.toString().trim())
                    babChild.child("Nomor 2").setValue(etQuestion2.text.toString().trim())
                    babChild.child("Nomor 3").setValue(etQuestion3.text.toString().trim())
                    babChild.child("Nomor 4").setValue(etQuestion4.text.toString().trim())
                    babChild.child("Nomor 5").setValue(etQuestion5.text.toString().trim())
                        .addOnCompleteListener {
                            finish()
                        }.addOnFailureListener {
                            showToast(it.message)
                        }
                }
            }
            setNegativeButton("Batal") { _, _ -> }
        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun showToast(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}