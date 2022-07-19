package com.mango.catagories

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
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
    ////////////////////////////////////////////////////////////////////////////////////////////////
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowInsetsControllerCompat(window, window.decorView).apply {
        hide(WindowInsetsCompat.Type.statusBars())}
        setContentView(R.layout.activity_grades_for_thai)
        reportCard = findViewById(R.id.reportCard)
        correctionField = findViewById(R.id.correctionField)
        val unSerial = Deserializer()
        val incorrecteList = intent.getSerializableExtra("key")
        val incorrecteListTh = intent.getSerializableExtra("key2")
        val marksStringT = intent.getStringExtra("key3")
        val myNumberOfErrorsT = intent.getIntExtra("key4",0)
        println(marksStringT)
        "This is your mark\n           $marksStringT%".also { reportCard.text = it }
        element = unSerial.unDoThis(incorrecteList)
        elementTh = unSerial.unDoThis(incorrecteListTh)
        delim = ","
        delimT = ","
        val arr = element.split(delim)
        val mistakesThai: Array<String> = arr.toTypedArray()
        mistakesThai.forEach { println(it) }
        arr[0].replace("[", "").replace("]", "")
        val arrTh = elementTh.split(delimT)
        arrTh[0].replace("[", "").replace("]", "")
        val noESquareBrace = arrangeEngTextForMarks(arr)
        arrangeEngTextForMarks(arr)
        val noTSquareBrace = arrangeThaiTextForMarks(arrTh)
        displayGrades( myNumberOfErrorsT,
            { thePerfectScore(correctionField) },
            { errorsToCorrectionField(arr,correctionField,noESquareBrace,noTSquareBrace) })
        displayGrades(myNumberOfErrorsT, { thePerfectScore(correctionField) } ,
            { errorsToCorrectionField(arr,correctionField,noESquareBrace,noTSquareBrace) })
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
    private fun errorsToCorrectionField(sizeArray:List<String>,errorField:TextView,engRight:List<String>,thaiRight:List<String>){
        val correctSize =sizeArray.size-1
        "These are the corrections\n".also { errorField.text = it }
        for (i in 0..correctSize) {

            errorField.append("${engRight[i]}    =    ${thaiRight[i]}\n")
        }
        val ssb = SpannableStringBuilder(errorField.text)
        val fcsGreen = ForegroundColorSpan(Color.RED)
        with(ssb) {
            setSpan(fcsGreen, 0, 25, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        with(errorField) {
            ssb.setSpan(fcsGreen, 0, 25, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            text = ssb
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun thePerfectScore(myTextView: TextView){
        "There are no corrections".also {myTextView.text = it }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun displayGrades(tries:Int,idealScore:() -> Unit,someErrors:() -> Unit)
    {
        when (tries) {
            0 -> idealScore() // actual fun thePerfectScore()
            1 -> someErrors()   // actual fun errorsToCorrectionField()
            else -> println("called from inside displayGrades")
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////
    }// end of class




