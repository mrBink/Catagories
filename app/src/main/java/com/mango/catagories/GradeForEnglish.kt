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


class GradeForEnglish :AppCompatActivity  () {
    private lateinit var element: String
    private lateinit var elementTh: String
    private lateinit var delim: String
    private lateinit var delimT: String
    private lateinit var reportCard: TextView
    private lateinit var correctionField: TextView

    ///////////////////////////////////////////////////////////////////////////////////////
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowInsetsControllerCompat(window, window.decorView).apply {
            hide(WindowInsetsCompat.Type.statusBars())
        }
        setContentView(R.layout.activity_grade_for_english)
        reportCard = findViewById(R.id.reportCard)
        correctionField = findViewById(R.id.correctionField)
        val unSerial = Deserializer()
        val incorrecteList = intent.getSerializableExtra("key")
        val incorrecteListTh = intent.getSerializableExtra("key2")
        val myMarks = intent.getStringExtra("key3")
        reportCard.text = "This is your mark\n           $myMarks%"
        element = unSerial.unDoThis(incorrecteList)
            println("$element  this is element  a string from unSerial" )
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
    fun iterateForMarks(sizeArray:List<String>, engRight:List<String>, thaiRight:List<String>){
        val correctSize =sizeArray.size - 1
          println(" this is $sizeArray.size called from iterate marks")
        correctionField.text = "These are the corrections\n"

        for (i in 0..correctSize){
            correctionField.append("${engRight[i]}    =    ${thaiRight[i]}\n")

        }
        val ssb = SpannableStringBuilder(correctionField.text)
        val fcsGreen = ForegroundColorSpan(Color.RED)
        ssb.setSpan(fcsGreen, 0, 25, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        correctionField.setText(ssb)



    }
    ///////////////////////////////////////////////////////////////////////////////////////////////


    //////////////////////////////////////////////////////////////////////////////////////////////
    }// end of class




/*
        println("${noTSquareBrace[1]} this is noTSquareBrace[1] element")
println("${noESquareBrace[1]} this is noESquareBrace[1] element")
//println("${arrangeEngTextForMarks(arr)} this is the  arrangeEngTextForMarks function")
//println("${arrangeThaiTextForMarks(arrTh)} this is the arrangeThaiTextForMarks(arrTh) function")
        //println("$text this is after unserialize")
        //println("$text2 this is after unserialize")
        //correctionField.text = "These are the correction\n${text}   =   ${text2}"
     fun arrangeEngTextForMarks(eng:Array<String>):Array<String>{
        for (i in eng){
            val textEng =eng[1].replace("[", "").replace("]", "")
        }
        return textEng
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
      fun iterateForMarks(){
      val correctSize =arr.size - 1
        for (i in correctSize downTo 0){
           correctionField.text = "These are the correction\n${noESquareBrace[i]}   =   ${noTSquareBrace[i]\n}"
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////


println("$incorrecteList  this is incorrecteList" )
        println("$incorrecteListTh  this is incorrecteListTh" )
        println(myMarks)
// var text = arr[0].toString()
//println(arr.contentToString())
 //text = arr.toString().replace("[", "apple").replace("]", "");
        //val text2 = arr2.toString().replace("[", "").replace("]", "");
val w = s.replace("sunny", "rainy")
    println(w)
arr2[0].replace("[", "").replace("]", "")
        println("${arr2[0]} this is typed Array Thai")
println("${text[0]} this is typed Array English")
val text = arr[0].toString().replace("[", "").replace("]", "");
 fun newStrArrayT(): Array<String> {
              for (item in element) {
                  var indexer = 0
                  newThaiArray[indexer] = item.toString()
                  indexer +=1
              }
              return newThaiArray
          }
           newStrArrayT()
        println("${newStrArrayT()} this is newStrArrayT")
         private  var newThaiArray: Array<String> = arrayOf()

  fun fillCorrectionField()
    {
        val unSerial = Deserializer()
        val incorrecteList = intent.getSerializableExtra("key")
        val incorrecteListTh = intent.getSerializableExtra("key2")
        val myMarks = intent.getStringExtra("key3")
        println("$incorrecteList.size  this is incorrecteList.size" )
        println("$incorrecteListTh  this is incorrecteListTh" )
        println(myMarks)
        reportCard.text = "This is your mark\n           $myMarks%"
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

        correctionField.text = "These are the correction\n${arr[0]}   =   ${arr2[0]}"
    }
     fun newStrArrayT():Array<String>{
     var newThaiArray: Array<String> = arrayOf()
        for(item in element)
        {
           newThaiArray
        }
        return newThaiArray
    }
    ///////////////////////////////////////////////////////////////'

    ///////////////////////////////////////////////////////////////'
    fun strArrayToJoin():Array<String>{
     var joinedArray: Array<String> = arrayOf()
        newStrArrayE().concat(newStrArrayT())
        return joinedArray
    }

    fun stringToList(anyLang:String,langList:List<String>,oneElem:String){
        //val stringToUse = anyLang
        var eitherLanguage = langList
        val singleWord: String
        delimT = ","          //delimiter for split()     elementTh
        eitherLanguage = anyLang.split(delimT)
        singleWord = langList[0]       //access element from list
        println("$langList these are thai list elements")
        println("$singleWord this is a thai list element")
    }


        ///////////////////////////////////////////

         val arr = element.split(delim).toTypedArray()
         println("${arr[0]} this is typed Array English")
         println(element)
         elementTh =unSerial.unDoThis(incorrecteListTh)
         val arr2 = elementTh.split(delim).toTypedArray()
         println("${arr2[0]} this is typed Array Thai")
         println(elementTh)
         ///////////////////////

         seeWhatIsSent(errorsMade)
           fun processErrors(){
              if(errorsMade > 0)
              {
                val incorrecteList = intent.getSerializableExtra("key")
                val incorrecteListTh = intent.getSerializableExtra("key2")
                val myMarks = intent.getStringExtra("key3")
              }
              }


              if(errorsMade == 0)
              {
               val myMarks = intent.getStringExtra("key3")
              }
              }




var errorsMade = intent.getIntExtra("key4",0)

 val set: Set<String> = convertToSet(String)
 elementAt(2)


        //HelperFunctions.serialDelimited(element,misspelledEng)
        //println("$misspelledEng this is misspelled eng")
         //element = incorrecteList.toString()
        //println(element).toString()
        println("${misspelledEng.get(1).toString()} this is fucked")
        misspelledEng = element.split(delim //val arr = element.split(delim).toTypedArray()
        //println(arr[1]).toString()

//stringToList(element,misspelledEng,anElement)numbers.get(5)

 val arr = element.split(delim).toTypedArray()
delim = " "            //delimiter for split()
misspelledEng = element.split(delim)//Serialized to List
anElement = misspelledEng[1]       //access element from list

println(anElement)
//serializeToStringThai(incorrecteListTh)

 */
