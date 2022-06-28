package com.mango.catagories

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.Serializable

class GradeForEnglish :AppCompatActivity  () {
    private lateinit var  element:String
    private lateinit var  delim:String
    private lateinit var  misspelledEng:List<String>
    private lateinit var  anElement:String
    private lateinit var  elementTh:String
    private lateinit var  delimT:String
    private lateinit var  misspelledThai:List<String>
    private lateinit var  anElementTh:String
    private lateinit var  reportCard:TextView


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grade_for_english)
        reportCard = findViewById(R.id.reportCard)
        val unSerial = Deserializer ()
        val incorrecteList = intent.getSerializableExtra("key")
        val incorrecteListTh = intent.getSerializableExtra("key2")
        val myMarks = intent.getStringExtra("key3")
        println(incorrecteList)
        println(incorrecteListTh)
        println(myMarks)
        reportCard.setText("This is your mark\n           $myMarks%")
        element =unSerial.unDoThis(incorrecteList)
         //element = incorrecteList.toString()
        println(element).toString()
        elementTh =unSerial.unDoThis(incorrecteListTh)
        println(elementTh).toString()
        /*
        delim = ","            //delimiter for split()
        misspelledEng = element.split(delim)//Serialized to List
        anElement = misspelledEng[1]       //access element from list
        println(misspelledEng)
        println(anElement)
        //serializeToStringThai(incorrecteListTh)

         */
    } // end of constructor
    ///////////////////////////////////////////////
    fun serializeToStringThai(any: Serializable?){
        elementTh  = any.toString()//Serializable to String
        println(elementTh )
        delimT = ","          //delimiter for split()
        misspelledThai = elementTh.split(delimT)//Serialized to List
        anElementTh = misspelledThai[1]       //access element from list
        println(misspelledThai)
        println(anElementTh)
    }
        ///////////////////////////////////////////

    }// end of class

    //////////////////////////////////////////////////////////////////////
