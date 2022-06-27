package com.mango.catagories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.io.Serializable

class GradeForEnglish : AppCompatActivity() {
    private lateinit var  element:String
    private lateinit var  delim:String
    private lateinit var  delimT:String
    private lateinit var  misspelledEng:List<String>
    private lateinit var  misspelledThai:List<String>
    private lateinit var  anElement:String
    private lateinit var  anElementTh:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grade_for_english)
        val incorrecteList = intent .getSerializableExtra( "key" )
        val incorrecteListTh = intent .getSerializableExtra( "key2" )
        val myMarks = intent.getStringExtra("key3")
        println(incorrecteList)
        println(incorrecteListTh)
        println(myMarks)
        serializeToString(incorrecteList)
        serializeToStringThai(incorrecteListTh)

    }
    private fun serializeToString(any: Serializable?){
        element = any.toString()//Serializable to String
        println(element)
        delim = ","            //delimiter for split()
        misspelledEng = element.split(delim)//Serialized to List
        anElement = misspelledEng[1]       //access element from list
        println(misspelledEng)
        println(anElement)
    }
    private fun serializeToStringThai(any: Serializable?){
        element = any.toString()//Serializable to String
        println(element)
        delimT = ","          //delimiter for split()
        misspelledThai = element.split(delimT)//Serialized to List
        anElementTh = misspelledThai[1]         //access element from list
        println(misspelledThai)
        println(anElementTh)
    }
    //////////////////////////////////////////////////////////////////////
}// end of class