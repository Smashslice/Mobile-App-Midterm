package com.week.campuscoffeeorder

import android.content.Intent
import android.os.Bundle
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
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val ordName = findViewById<EditText>(R.id.ordName)
        val ordBalance = findViewById<EditText>(R.id.ordBalance)

        // Spinners
        val coffee_type: Spinner = findViewById<Spinner>(R.id.coffee_type_spinner)
        val coffee_size: Spinner = findViewById<Spinner>(R.id.coffee_size_spinner)

        ArrayAdapter.createFromResource(
            this,
            R.array.coffee_sizes,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            coffee_size.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.coffee_types,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            coffee_type.adapter = adapter
        }

        var selected_size = ""
        var selected_coffee = ""

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

        btnNav.setOnClickListener {
            val data_valid = CheckAllFields()
            if (data_valid){
                val name = ordName.text.toString()
                val bal = ordBalance.text.toString().toDouble()
                val size = selected_size
                val type = selected_coffee

                val intent = Intent(this@MainActivity, ActivityDetail::class.java)
                intent.putExtra("name", name)
                intent.putExtra("balance", bal)
                intent.putExtra("size", size)
                intent.putExtra("type", type)

                startActivity(intent)
            }
        }
    }
}

