package com.mango.catagories

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

class FruitThai : AppCompatActivity() {
    private lateinit var toFruitt: ImageButton//thaiDigit
    private lateinit var thaiDigit: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowInsetsControllerCompat(window, window.decorView).apply {
            hide(WindowInsetsCompat.Type.statusBars())
        }
        setContentView(R.layout.activity_fruit_thai)
        toFruitt = findViewById(R.id.toFruitt)
        toFruitt.setOnClickListener {
            val intent = Intent(this, ThaiCats::class.java)
            startActivity(intent)
        }
        Toast.makeText(this@FruitThai, "Its a FruitThai class!", Toast.LENGTH_SHORT).show()
        thaiDigit = findViewById(R.id.thaiDigit)
        thaiDigit.setOnClickListener {
            println("thaiDigit button clicked should see action spell ThaiNumbers  page")
            val intent1 = Intent(this, ThaiNumbers::class.java)
            startActivity(intent1)
        }

    }
    /////////////////////////////////////////////////////////////////////////
} //end of class