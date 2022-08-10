package com.shevy.androidcourse

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.shevy.androidcourse.databinding.ActivityMainBinding
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    var quantity = 0
    lateinit var binding: ActivityMainBinding
    private lateinit var spinner: Spinner
    lateinit var goodsMap: MutableMap<String, Double>
    lateinit var goodsName: String
    var price by Delegates.notNull<Double>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        spinner = binding.spinner
        val spinnerArrayList = arrayListOf<String>()

        spinnerArrayList.add("guitar")
        spinnerArrayList.add("drums")
        spinnerArrayList.add("keyboard")

        val spinnerAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            spinnerArrayList
        )
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinnerAdapter

        goodsMap = mutableMapOf()
        goodsMap["guitar"] = 500.0
        goodsMap["drums"] = 1500.0
        goodsMap["keyboard"] = 1000.0

        binding.apply {
            plusButton.setOnClickListener {
                quantity++
                quantityCountTextView.text = quantity.toString()
                binding.priceTextView.text = (quantity * price).toString()
            }
            minusButton.setOnClickListener {
                quantity--
                if (quantity < 0) quantity = 0
                quantityCountTextView.text = quantity.toString()
                binding.priceTextView.text = (quantity * price).toString()
            }
            spinner.onItemSelectedListener = this@MainActivity
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        goodsName = spinner.selectedItem.toString()
        price = goodsMap[goodsName]!!
        binding.priceTextView.text = (quantity * price).toString()
        when (goodsName) {
            "guitar" -> binding.goodsImageView.setImageResource(R.drawable.guitar)
            "drums" -> binding.goodsImageView.setImageResource(R.drawable.drums)
            "keyboard" -> binding.goodsImageView.setImageResource(R.drawable.keyboard)
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }
}