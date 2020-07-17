package com.example.testingrobolectric

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvHelloWorld.setText("Hello World!")
        employeeID.setText("001")
        name.setText("ABC")
        address.setText("1500 Street")
        country.setText("USA")
        dob.setText("02/29")
        language.setText("English")
        age.setText("25")


        btnLaunchNextActivity.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        button_changeAge.setOnClickListener {
            val ageTextView = findViewById<TextView>(R.id.age)
            if (ageTextView.text == "25") {
                ageTextView.text = "50"
            } else {
                ageTextView.text = "25"
            }
        }
    }
}