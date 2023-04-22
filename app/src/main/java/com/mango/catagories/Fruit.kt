package com.mango.catagories

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

class Fruit : AppCompatActivity() {
    private lateinit var toFruits:ImageButton
    private lateinit var numbers:ImageButton
    private lateinit var eBody:ImageButton
    private lateinit var engmonths:ImageButton
    private lateinit var engTime:ImageButton
    private lateinit var toCashth:ImageButton
    private lateinit var engWeatherStartBtn:ImageButton
    private lateinit var transengStartBtn:ImageButton
    private lateinit var compassengStartBtn:ImageButton
    private lateinit var engmailstart:ImageButton
    private lateinit var toCashth2:ImageButton//goes to Family



    //private lateinit var catBtns: ImageView  transengStartBtn  compassengStartBtn
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowInsetsControllerCompat(window, window.decorView).apply {
            hide(WindowInsetsCompat.Type.statusBars())
        }
        setContentView(R.layout.activity_fruit)
       // catBtns = findViewById(R.id.catBtns)//bg image

        toFruits = findViewById(R.id.toFruits)
        toFruits.setOnClickListener {
            //println("toFruits button clicked should see action fruit spell eng page")
            val intent = Intent(this, FruitCat::class.java)
            startActivity(intent)
        }
        numbers = findViewById(R.id.numbers)
        eBody = findViewById(R.id.eBody)
        engmonths = findViewById(R.id.engmonths)
        engTime = findViewById(R.id.engTime)
        toCashth = findViewById(R.id.toCashth)
        engWeatherStartBtn = findViewById(R.id.engWeatherStartBtn)
        transengStartBtn = findViewById(R.id.transengStartBtn)
        compassengStartBtn = findViewById(R.id.compassengStartBtn)
        engmailstart = findViewById(R.id.engmailstart)
        toCashth2 = findViewById(R.id.toCashth2)




        numbers.setOnClickListener {
            //println("numbers button clicked should see action  num  eng page")
           val intent1 = Intent(this, ArabicNum::class.java)
            startActivity(intent1)
        }

        eBody.setOnClickListener {
            //println("numbers   eBody clicked should see action bodyPartsThai")
           val intent2 = Intent(this, AnatomyE::class.java)
            startActivity(intent2)
        }

         engmonths.setOnClickListener {
           // println("numbers   eBody clicked should see action EnglishMonths")
           val intent3 = Intent(this, EnglishMonths::class.java)
            startActivity(intent3)
        }

        engTime.setOnClickListener {
            //println("numbers   eBody clicked should see action EnglishMonths")
           val intent4 = Intent(this, SpellInEnglish::class.java)
            startActivity(intent4)
        }

            toCashth.setOnClickListener {
            //println("numbers   eBody clicked should see action EnglishMonths")
           val intent5 = Intent(this, MoneyThai::class.java)
            startActivity(intent5)
        }

           engWeatherStartBtn.setOnClickListener {
            //println("engWeatherStartBtn clicked should see action ClimateWordsEng")
           val intent6 = Intent(this, ClimateWordsEng::class.java)
            startActivity(intent6)
        }

        transengStartBtn.setOnClickListener {
            //println("transengStartBtn clicked should see action TransportEnglish")
           val intent7 = Intent(this, TransportEnglish::class.java)
            startActivity(intent7)
        }
        compassengStartBtn.setOnClickListener {
            //println("transengStartBtn clicked should see action TransportEnglish")
            val intent8 = Intent(this, Compass::class.java)
            startActivity(intent8)
        }

        engmailstart.setOnClickListener {
            //println("engmailstart clicked should see action EnglishMail")
            val intent9 = Intent(this, EnglishMail::class.java)
            startActivity(intent9)
        }
         toCashth2.setOnClickListener {
            //println("toCashth2 clicked should see action Family")
            val intent10 = Intent(this, Family::class.java)
            startActivity(intent10)
        }



    }
} //end of class