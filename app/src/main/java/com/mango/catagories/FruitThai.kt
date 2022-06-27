package com.mango.catagories

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast

class FruitThai : AppCompatActivity() {
    private lateinit var toFruits: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruit_thai)
        toFruits = findViewById(R.id.toFruits)
        toFruits.setOnClickListener {
            val intent = Intent(this, ThaiCats::class.java)
            startActivity(intent)
        }
        Toast.makeText(this@FruitThai, "Its a FruitThai class!", Toast.LENGTH_SHORT).show()
    }
    /////////////////////////////////////////////////////////////////////////
} //end of class