package com.week.campuscoffeeorder

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
//        Log creation for life cycle tracking
        Log.d("MainActivity", "Created")
            super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Variable Declaration

        // Order name and balance
        val ordName = findViewById<EditText>(R.id.ordName)
        val ordBalance = findViewById<EditText>(R.id.ordBalance)

        // Spinners
        val coffee_type: Spinner = findViewById<Spinner>(R.id.coffee_type_spinner)
        val coffee_size: Spinner = findViewById<Spinner>(R.id.coffee_size_spinner)

        // Users selected size and coffee, for passing to the next view
        var selected_size = ""
        var selected_coffee = ""

        // Create a spinner from the declared string array in res/strings called coffee_sizes
        // Create the adapter for the spinner and set it to the spinner
        ArrayAdapter.createFromResource(
            this,
            R.array.coffee_sizes,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            coffee_size.adapter = adapter
        }


        // Create a spinner from the declared string array in res/strings called coffee_types
        // Create the adapter for the spinner and set it to the spinner
        ArrayAdapter.createFromResource(
            this,
            R.array.coffee_types,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            coffee_type.adapter = adapter
        }



        // Listener for the coffee size spinner, takes the current selected item and sets the variable
        // To match the currently selected item for passing to the activity
        coffee_size.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            public override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                selected_size = coffee_size.selectedItem.toString()
            }

            public override fun onNothingSelected(parentView: AdapterView<*>?) {
            }
        })

        // Listener for the coffee size spinner, takes the current selected item and sets the variable
        // To match the currently selected item for passing to the activity
        coffee_type.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            public override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                selected_coffee = coffee_type.selectedItem.toString()
            }

            public override fun onNothingSelected(parentView: AdapterView<*>?) {
            }
        })

        // Navigation Button
        val btnNav = findViewById<Button>(R.id.btnPlace)


        // Simple check to make sure we don't pass any null values to the next view
        // Alerts user if either required field is blank via the error popup built into EditTextView
        fun CheckAllFields(): Boolean {
            if (ordName.length() == 0) {
                ordName.error = "This field is required"
                return false
            } else if (ordBalance.length() == 0){
                ordBalance.error = "This field is required"
                return false
            }
            return true
        }


        // Event listener for navigation button
        btnNav.setOnClickListener {
            // Call check function to validate data
            val data_valid = CheckAllFields()
            // If valid, do stuff, otherwise don't
            if (data_valid){
                // Get the name and balnace from user input and grab the current spinner selections
                val name = ordName.text.toString()
                val bal = ordBalance.text.toString().toDouble()
                val size = selected_size
                val type = selected_coffee

                // Our intent for the OS with the extras that we got from the user
                val intent = Intent(this@MainActivity, ActivityDetail::class.java)
                intent.putExtra("name", name)
                intent.putExtra("balance", bal)
                intent.putExtra("size", size)
                intent.putExtra("type", type)

                startActivity(intent)
            }
        }
    }
    // Life cycle logging
    override fun onStart() { super.onStart(); Log.d("MainActivity", "Started") }
    override fun onResume() { super.onResume(); Log.d("MainActivity", "Resumed ") }
    override fun onPause() { super.onPause(); Log.d("MainActivity", "Paused ") }
    override fun onStop() { super.onStop(); Log.d("MainActivity", "Stopped ") }
    override fun onDestroy() { super.onDestroy(); Log.d("MainActivity", "Destroyed ") }
}

