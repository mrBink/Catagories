package com.mango.catagories

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton


class Fruit : AppCompatActivity() {
    private lateinit var toFruits:ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruit)
        toFruits = findViewById(R.id.toFruits)
        toFruits.setOnClickListener {
           val intent = Intent(this, FruitCat::class.java)

            startActivity(intent)
        }
    }
} //end of class