package com.mango.catagories

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GradesForThai : AppCompatActivity() {

    private lateinit var element: String
    private lateinit var elementTh: String
    private lateinit var delim: String
    private lateinit var delimT: String
    private lateinit var reportCard: TextView
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grades_for_thai)
        reportCard = findViewById(R.id.reportCard)
        val unSerial = Deserializer()
        val incorrecteList = intent.getSerializableExtra("key")
        val incorrecteListTh = intent.getSerializableExtra("key2")
        val marksStringT = intent.getStringExtra("key3")
        println("$incorrecteList  this is incorrecteList" )
        println("$incorrecteListTh  this is incorrecteListTh" )
        println(marksStringT)
        reportCard.text = "This is your mark\n           $marksStringT%"
        element = unSerial.unDoThis(incorrecteList)
        elementTh = unSerial.unDoThis(incorrecteListTh)
        delim = ","
        delimT = ","
        val arr = element.split(delim).toTypedArray()
        println("${arr[0]} this is typed Array English")
        println(arr.contentToString())
        val arr2 = elementTh.split(delimT).toTypedArray()
        println("${arr2[0]} this is typed Array Thai")
        println(arr.contentToString())
    }// end of constructor

    /////////////////////////////////////////////////////////////////////

}//end of class
