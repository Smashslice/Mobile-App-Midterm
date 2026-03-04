package com.week.campuscoffeeorder

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ActivityDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val order_details = findViewById<TextView>(R.id.order_details)
        val success_fail_img = findViewById<ImageView>(R.id.successFailImg)

        val name = intent.getStringExtra("name")
        val balance = intent.getDoubleExtra("balance", 0.0)
        val type = intent.getStringExtra("type")
        val size = intent.getStringExtra("size")

        var order_placed = false

        if (size == "Heart Attack" && balance < 8) {
            order_placed = false
        } else if (size == "Large" && balance < 5){
            order_placed = false
        } else if (size == "Medium" && balance < 4){
            order_placed = false
        } else if (size == "Small" && balance < 3){
            order_placed = false
        } else {
            order_placed = true
        }

        if (order_placed == false){
            order_details.text = "Failed to place order, you're a bit too greedy"
            success_fail_img.setImageResource(R.drawable.cross)
        } else {
            order_details.text = "$name placed an order for a $size, $type and paid $balance"
            success_fail_img.setImageResource(R.drawable.check)
        }


    }
}