package com.shevy.androidcourse

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shevy.androidcourse.databinding.ActivityOrderBinding

class OrderActivity : AppCompatActivity() {
    lateinit var binding: ActivityOrderBinding
    private var addresses: String = "dmitriy_shevyakov@mail.ru"
    private var subject: String = "Order from Music Shop"
    lateinit var emailText: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val receivedOrderIntent = intent
        val userName: String = receivedOrderIntent.getStringExtra("userNameForIntent").toString()
        val goodsName: String = receivedOrderIntent.getStringExtra("goodsName").toString()
        val quantity: Int = receivedOrderIntent.getIntExtra("quantity", 0)
        val orderPrice: Double = receivedOrderIntent.getDoubleExtra("orderPrice", 0.0)

        emailText = "Customer name: $userName \n" +
                "Goods name: $goodsName \n" +
                "Quantity: $quantity \n" +
                "Order price: $orderPrice"

        binding.apply {
            orderTextView.text = emailText
/*            orderTextView.text = userName
            orderTextView3.text = quantity.toString()
            orderTextView4.text = orderPrice.toString()*/

            sendButton.setOnClickListener {
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:") // only email apps should handle this
                    putExtra(Intent.EXTRA_EMAIL, addresses)
                    putExtra(Intent.EXTRA_SUBJECT, subject)
                    putExtra(Intent.EXTRA_TEXT, emailText)
                }
                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                }
            }
        }
    }
}