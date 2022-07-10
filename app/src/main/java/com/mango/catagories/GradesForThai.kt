package com.mango.catagories

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

class GradesForThai : AppCompatActivity() {

    private lateinit var element: String
    private lateinit var elementTh: String
    private lateinit var delim: String
    private lateinit var delimT: String
    private lateinit var reportCard: TextView
    private lateinit var correctionField: TextView


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowInsetsControllerCompat(window, window.decorView).apply {
        hide(WindowInsetsCompat.Type.statusBars())}
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
        val arr = element.split(delim)
        println("{$arr[1] } this is arr[1]  a string from arr last thing done this round I want water" )
        arr[0].replace("[", "").replace("]", "")
        val arrTh = elementTh.split(delimT)
        arrTh[0].replace("[", "").replace("]", "")
        val noESquareBrace = arrangeEngTextForMarks(arr)
        arrangeEngTextForMarks(arr)
        val noTSquareBrace = arrangeThaiTextForMarks(arrTh)
        iterateForMarks(arr, noESquareBrace, noTSquareBrace)


    }// end of constructor
    //////////////////////////////////////////////////////////////////////////////////////////////
    private fun arrangeEngTextForMarks(eng: List<String>):ArrayList<String>{
        val length = eng.size - 1
        val engNoBracket: ArrayList<String> = ArrayList()
        for (i in 0..length) {
            val newEngArranged = eng[i].replace("[", "").replace("]", "")
            engNoBracket.add(newEngArranged)
        }
        return engNoBracket
    }
    //////////////////////////////////////////////////////////////////////////////////////////////
    private fun arrangeThaiTextForMarks(thai:List<String>):ArrayList<String>{
        val length = thai.size - 1
        val thaiNoBracket: ArrayList<String> = ArrayList()
        for (i in 0..length) {
            val newThaiArranged = thai[i].replace("[", "").replace("]", "")
            thaiNoBracket.add(newThaiArranged)
        }
        return thaiNoBracket
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////

    @SuppressLint("SetTextI18n")
    fun iterateForMarks(sizeArray:List<String>, engRight:List<String>,thaiRight:List<String>){
        val correctSize =sizeArray.size - 1
        correctionField.text = "These are the corrections\n"
        for (i in 0..correctSize){
            correctionField.append("${thaiRight[i]}    =   ${engRight[i]}\n")
        }
    }
//
    //////////////////////////////////////////////////////////////////////////////////////////////
}// end of class




