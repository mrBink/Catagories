package com.mango.catagories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.io.Serializable

class GradesForThai : AppCompatActivity() {

    private lateinit var  element:String
    private lateinit var  delim:String
    private lateinit var  delimT:String
    private lateinit var  misspelledEng:List<String>
    private lateinit var  misspelledThai:List<String>
    private lateinit var  anElement:String
    private lateinit var  anElementTh:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grades_for_thai)
        val incorrecteListTh = intent .getSerializableExtra( "key" )
        val incorrecteListE = intent .getSerializableExtra( "key2" )
        val myMarksTH = intent.getStringExtra("key3")
        println(incorrecteListTh)
        println(incorrecteListE)
        println(myMarksTH)
        val elementTh = incorrecteListTh.toString()//Serializable to String
        val elementEng = incorrecteListE.toString()//Serializable to String
        println(elementEng)
        println(elementTh)
       // serializeToString(incorrecteListE)
       // serializeToStringThai(incorrecteListTh)

    }//end of constructor
    /////////////////////////////////////////////////////////////////////
    private fun serializeToString(any: Serializable?){
        element = any.toString()//Serializable to String
        println(element)
        delim = ","            //delimiter for split()
        misspelledEng = element.split(delim)//Serialized to List
        anElement = misspelledEng[1]       //access element from list
        println(misspelledEng)
        println(anElement)
    }
    ///////////////////////////////////////////////////////////
    private fun serializeToStringThai(any: Serializable?){
        element = any.toString()//Serializable to String
        println(element)
        delimT = ","          //delimiter for split()
        misspelledThai = element.split(delimT)//Serialized to List
        anElement = misspelledThai[1]         //access element from list
        println(misspelledThai)
        println(anElementTh)
    }
    ///////////////////////////////////////////////////////////
}//end of class
