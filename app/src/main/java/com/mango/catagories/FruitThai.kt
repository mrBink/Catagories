package com.mango.catagories

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

class FruitThai : AppCompatActivity() {
    private lateinit var toFruitt: ImageButton
    private lateinit var thaiDigit: ImageButton
    private lateinit var tBody: ImageButton
    private lateinit var t_calendar: ImageButton
    private lateinit var thaitime:ImageButton
    private lateinit var toCash:ImageButton
    private lateinit var thweatherbtn:ImageButton
    private lateinit var transthai:ImageButton
    private lateinit var compassThStartBtn:ImageButton
    private lateinit var startThaiMail:ImageButton
    private lateinit var toCash2: ImageButton

    //private lateinit var catBtns: ImageView  //backgrkound image
    //catBtns = findViewById(R.id.catBtns)

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

        t_calendar = findViewById(R.id.t_calendar)
        t_calendar.setOnClickListener {
           println("numbers button clicked should see action  num  ThaiMonths")
           val intent9 = Intent(this, ThaiMonths::class.java)
            startActivity(intent9)
        }


        thaitime = findViewById(R.id.thaitime)
        thaitime.setOnClickListener {
            println("numbers button clicked should see action  num  ThaiMonths")
            val intent8 = Intent(this, SpellInThai::class.java)
            startActivity(intent8)
        }


        toCash = findViewById(R.id.toCash)
        toCash.setOnClickListener {
            println("numbers button clicked should see action  num  ThaiMonths")
            val intent10 = Intent(this, Money::class.java)
            startActivity(intent10)
        }

        thweatherbtn = findViewById(R.id.thweatherbtn)
        thweatherbtn.setOnClickListener {
            println("numbers button clicked should see action ClimateWordsTH")
            val intent11 = Intent(this, ClimateWordsTH::class.java)
            startActivity(intent11)
        }

         transthai = findViewById(R.id.transthai)
        transthai.setOnClickListener {
            println("numbers button clicked should see action ClimateWordsTH")
            val intent12 = Intent(this,TransportThai::class.java)
            startActivity(intent12)
        }


        compassThStartBtn = findViewById(R.id.compassThStartBtn)
        compassThStartBtn.setOnClickListener {
            println("compassThStartBtn clicked should see action CompassThai")
            val intent13 = Intent(this,CompassThai::class.java)
            startActivity(intent13)
        }


        startThaiMail = findViewById(R.id.startThaiMail)
        startThaiMail.setOnClickListener {
            println("thmailstart clicked should see action ThaiMail")
            val intent14 = Intent(this,ThaiMail::class.java)
            startActivity(intent14)
        }

         toCash2 = findViewById(R.id.toCash2)
         toCash2.setOnClickListener {
            println("toCash2 clicked should see action FamilyTh")
            val intent15 = Intent(this,FamilyTh::class.java)
            startActivity(intent15)
        }

    }
    /////////////////////////////////////////////////////////////////////////
} //end of class