package com.mango.catagories

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

class MainActivity : AppCompatActivity() {
    private lateinit var writeEnglish:ImageButton
    private lateinit var writeThai:ImageButton
    private lateinit var bgScene:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowInsetsControllerCompat(window, window.decorView).apply {
            hide(WindowInsetsCompat.Type.statusBars())
        }
        setContentView(R.layout.activity_main)
        bgScene = findViewById(R.id.bgScene)
        writeEnglish = findViewById(R.id.writeEnglish)
        writeThai = findViewById(R.id.writeThai)

        writeEnglish.setOnClickListener {
            //Toast.makeText(this@MainActivity, "Its a toast!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Fruit::class.java)
            startActivity(intent)
           // println("The eng scribe is clicked from Main")
        }

        writeThai.setOnClickListener {
            Toast.makeText(this@MainActivity, "Its a toast!", Toast.LENGTH_SHORT).show()
            val intent1 = Intent(this, FruitThai::class.java)
            startActivity(intent1)
           //println("The Thai scribe is from Main")
        }



    }//end of constructor
    ///////////////////////////////////////////////////////////////////////////////////////
}//end of class