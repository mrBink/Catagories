package com.mango.catagories

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

class Fruit : AppCompatActivity() {
    private lateinit var toFruits:ImageButton
    private lateinit var numbers:ImageButton
    private lateinit var catBtns: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowInsetsControllerCompat(window, window.decorView).apply {
            hide(WindowInsetsCompat.Type.statusBars())
        }
        setContentView(R.layout.activity_fruit)
        toFruits = findViewById(R.id.toFruits)
        toFruits.setOnClickListener {
            //println("toFruits button clicked should see action fruit spell eng page")
            val intent = Intent(this, FruitCat::class.java)
            startActivity(intent)
        }
        catBtns = findViewById(R.id.catBtns)
        numbers = findViewById(R.id.numbers)
        numbers.setOnClickListener {
            //println("numbers button clicked should see action  num  eng page")
           val intent1 = Intent(this, ArabicNum::class.java)
            startActivity(intent1)
        }
    }
} //end of class