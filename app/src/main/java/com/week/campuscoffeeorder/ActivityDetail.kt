package com.week.campuscoffeeorder

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ActivityDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        //        Log creation for life cycle tracking
        Log.d("MainActivity", "Created")
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Variable declaration

        // Order details and image
        val order_details = findViewById<TextView>(R.id.order_details)
        val success_fail_img = findViewById<ImageView>(R.id.successFailImg)

        // Get the intent data from the previous activity
        val name = intent.getStringExtra("name")
        val balance = intent.getDoubleExtra("balance", 0.0)
        val type = intent.getStringExtra("type")
        val size = intent.getStringExtra("size")

        // Whether the order is placed or not, default is false
        var order_placed = false

        // Check whether the user paid enough for their drink
        // Yes, we don't tell them the prices, its more fun that way
        // If they don't pay enough, fail to place the order
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

        // Update the UI based on whether the order was placed or not
        // Set image and order details to match the current state
        if (order_placed == false){
            order_details.text = "Failed to place order, you're a bit too greedy"
            success_fail_img.setImageResource(R.drawable.cross)
        } else {
            order_details.text = "$name placed an order for a $size, $type and paid $balance"
            success_fail_img.setImageResource(R.drawable.check)
        }
    }
    // Life cycle logging
    override fun onStart() { super.onStart(); Log.d("MainActivity", "Started") }
    override fun onResume() { super.onResume(); Log.d("MainActivity", "Resumed ") }
    override fun onPause() { super.onPause(); Log.d("MainActivity", "Paused ") }
    override fun onStop() { super.onStop(); Log.d("MainActivity", "Stopped ") }
    override fun onDestroy() { super.onDestroy(); Log.d("MainActivity", "Destroyed ") }
}