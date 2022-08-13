package com.shevy.androidcourse

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.shevy.androidcourse.databinding.ActivityMainBinding
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var isTomVisible = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageViewTom.setOnClickListener {
            if (isTomVisible) {
                binding.imageViewTom.animate().alpha(0f).duration = 3000
                binding.imageViewJerry.animate().alpha(1f).duration = 3000
                isTomVisible = false
            } else {

                binding.imageViewJerry.animate().alpha(0f).duration = 3000
                binding.imageViewTom.animate().alpha(1f).duration = 3000
                isTomVisible = true

            }
        }
    }
}