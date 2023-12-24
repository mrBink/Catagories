package com.mango.newproject

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import java.io.Serializable

@Suppress("UNCHECKED_CAST")
class GradesForThai : AppCompatActivity() {
    //marks for writing in Thai /comments in eng
    private lateinit var element: String
    private lateinit var elementTh: String
    private lateinit var delim: String
    private lateinit var delimT: String
    private lateinit var reportCard: TextView
    private lateinit var correctionField: TextView
    private lateinit var backToStartT:ImageButton
    private lateinit var marksbg:ImageView
    private lateinit var cellBannerT: AdView
    /////////////////////////////////////////////////////////////////////////////
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowInsetsControllerCompat(window, window.decorView).apply {
        hide(WindowInsetsCompat.Type.statusBars())}
        setContentView(R.layout.activity_grades_for_thai)
        /*
        MobileAds.initialize(this) {}
        cellBannerT = findViewById(R.id.cellBannerT)
        val adRequest = AdRequest.Builder().build()
        cellBannerT.loadAd(adRequest)

         */
        marksbg = findViewById(R.id.marksbg)
        reportCard = findViewById(R.id.reportCard)
        correctionField = findViewById(R.id.correctionField)
        backToStartT = findViewById(R.id.backToStartT)
        backToStartT.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        val unSerial = Deserializer()
        val incorrecteList = getSerializable(this,"key", ArrayList::class.java)
        val incorrecteListTh = getSerializable(this,"key2", ArrayList::class.java)
        val marksStringT = intent.getStringExtra("key3")
        val myMarksT2= marksStringT?.substring(0,3)
        val myNumberOfErrorsT = intent.getIntExtra("key4",0)
        println(marksStringT)
        "This is your mark\n           $myMarksT2%".also { reportCard.text = it }
       //"นี่คือเครื่องหมายของคุณ\n".also { reportCard.text = it }

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

    fun <T : Serializable?> getSerializable(activity: Activity, name: String, clazz: Class<T>): T
    {
        return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            activity.intent.getSerializableExtra(name, clazz)!!
        else
            activity.intent.getSerializableExtra(name) as T
    }

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
        "These are your corrections.\n".also { errorField.text = it }

        for (i in 0..correctSize) {
            errorField.append("${engRight[i]}    =    ${thaiRight[i]}\n")
        }
        val ssb = SpannableStringBuilder(errorField.text)
        val fcsGreen = ForegroundColorSpan(Color.RED)
        with(ssb) {
            setSpan(fcsGreen, 0, 27, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        with(errorField) {
            ssb.setSpan(fcsGreen, 0, 27, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            text = ssb
        }

    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun thePerfectScore(myTextView: TextView){
        //"ทุกสิ่งทุกอย่างถูกต้อง".also {myTextView.text = it }
        "There are no corrections".also {myTextView.text = it }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////
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

/*
       val string = "hello orld".replaceRange(5,13,"=")
        println(string) //this will print 'hello world'

        fun String.addCharAtIndex(char: Char, index: Int) =
         StringBuilder(this).apply { insert(index, char) }.toString()
         var s ="engRight[i]    ".addCharAtIndex('=', 13)
          var newWord = "   thaiRight[i]"
          s+=newWord
          s.toString()
          println(s)
         var s2 ="bob           ".addCharAtIndex('=', 13)
         s2.toString()
         var newWords = "   ส้ม"
         s2 += newWords
println(s2)

         */




