package com.mango.catagories

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

class FruitThai : AppCompatActivity() {
    private lateinit var toFruitt: ImageButton
    private lateinit var thaiDigit: ImageButton
    private lateinit var tBody: ImageButton
    private lateinit var engDays: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowInsetsControllerCompat(window, window.decorView).apply {
            hide(WindowInsetsCompat.Type.statusBars())
        }
        setContentView(R.layout.activity_fruit_thai)
        toFruitt = findViewById(R.id.toFruitt)
        toFruitt.setOnClickListener {
            val intent7 = Intent(this, ThaiCats::class.java)
            startActivity(intent7)
        }
        Toast.makeText(this@FruitThai, "Its a FruitThai class!", Toast.LENGTH_SHORT).show()
        thaiDigit = findViewById(R.id.thaiDigit)
        thaiDigit.setOnClickListener {
           // println("thaiDigit button clicked should see action spell ThaiNumbers  page")
            val intent6 = Intent(this, ThaiNumbers::class.java)
            startActivity(intent6)
        }
        tBody = findViewById(R.id.tBody)
        tBody.setOnClickListener {
           println("numbers button clicked should see action  num  tBody")
           val intent5 = Intent(this, AnatomyT::class.java)
            startActivity(intent5)
        }

        engDays = findViewById(R.id.engDays)
        engDays.setOnClickListener {
           println("numbers button clicked should see action  num  ThaiMonths")
           val intent6 = Intent(this, ThaiMonths::class.java)
            startActivity(intent6)
        }




    }
    /////////////////////////////////////////////////////////////////////////
} //end of class