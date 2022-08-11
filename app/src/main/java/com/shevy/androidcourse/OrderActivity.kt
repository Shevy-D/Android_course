package com.shevy.androidcourse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shevy.androidcourse.databinding.ActivityOrderBinding

class OrderActivity : AppCompatActivity() {
    lateinit var binding: ActivityOrderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val receivedOrderIntent = intent
        val userName: String = receivedOrderIntent.getStringExtra("userNameForIntent").toString()
        val goodsName: String = receivedOrderIntent.getStringExtra("goodsName").toString()
        val quantity: Int = receivedOrderIntent.getIntExtra("quantity", 0)
        val orderPrice: Double = receivedOrderIntent.getDoubleExtra("orderPrice", 0.0)
        binding.apply {
            orderTextView.text = "Customer name: $userName \n" +
                    "Goods name: $goodsName \n" +
                    "Quantity: $quantity \n" +
                    "Order price: $orderPrice"
/*            orderTextView.text = userName
            orderTextView2.text = goodsName
            orderTextView3.text = quantity.toString()
            orderTextView4.text = orderPrice.toString()*/

            sendButton.setOnClickListener {

            }
        }
    }
}