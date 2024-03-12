package com.agata.firstapp.view

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.agata.firstapp.databinding.ActivityMainBinding
import java.time.LocalDate
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

//    private fun emailEstaValido(email: String): Boolean {}
//    val arroba = email.find { it == '@' }
//    return arroba == '@'
////}
}