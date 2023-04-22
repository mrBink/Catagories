package com.mango.catagories

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

class MainActivity : AppCompatActivity() {
    private lateinit var writeEnglish:ImageButton
    private lateinit var writeThai:ImageButton
    private lateinit var bgScene:ImageView
    private lateinit var spellBtnEng:ImageButton
    private lateinit var thpointat:ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowInsetsControllerCompat(window, window.decorView).apply {
            hide(WindowInsetsCompat.Type.statusBars())
        }
        setContentView(R.layout.activity_main)
        bgScene = findViewById(R.id.bgScene)
        writeEnglish = findViewById(R.id.writeEnglish)
        writeThai = findViewById(R.id.writeThai)
        spellBtnEng = findViewById(R.id.spellBtnEng)
        thpointat = findViewById(R.id.thpointat)

        writeEnglish.setOnClickListener {

            val intent = Intent(this, Fruit::class.java)
            startActivity(intent)
           // println("The writeEnglish is clicked from Main")
        }

        writeThai.setOnClickListener {

            val intent1 = Intent(this, FruitThai::class.java)
            startActivity(intent1)
           println("The writeThai is from Main")
        }

        spellBtnEng.setOnClickListener {
            val intent2 = Intent(this, InfoPage::class.java)
            startActivity(intent2)
           println("The spellBtn is from Main")
        }

         thpointat.setOnClickListener {
            val intent3 = Intent(this, InfoPageThai::class.java)
            startActivity(intent3)
           println("The thpointat btn is from Main")
        }






    }//end of constructor
    ///////////////////////////////////////////////////////////////////////////////////////
}//end of class