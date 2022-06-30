package com.mango.catagories

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GradeForEnglish :AppCompatActivity  () {
    private lateinit var element: String
    private lateinit var elementTh: String
    private lateinit var delim: String
    private lateinit var misspelledEng: List<String>
    private lateinit var anElement: String
    private lateinit var delimT: String
    private lateinit var misspelledThai: List<String>
    private lateinit var anElementTh: String
    private lateinit var reportCard: TextView

    ///////////////////////////////////////////////////////////
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grade_for_english)
        reportCard = findViewById(R.id.reportCard)
        val unSerial = Deserializer()
        val incorrecteList = intent.getSerializableExtra("key")
        val incorrecteListTh = intent.getSerializableExtra("key2")
        val myMarks = intent.getStringExtra("key3")
        println("$incorrecteList  this is incorrecteList" )
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
       }// end of constructor
      // /////////////////////////////////////////////

      /////////////////////////////////////////////
    }// end of class




/*

  fun ifPossibleToSplitString(theElement:String,delimer:String,theElementT:String,delimerT:String,)
    {
        if(theElement.isNotEmpty())
        {
            val arr = theElement.split(delimer).toTypedArray()
            println("${arr[0]} this is typed Array English")
            println(theElement)
        }else{
            println("all good no  error element is Empty ")}
        if(theElementT.isNotEmpty())
        {
            val arr2 = theElementT.split(delimerT).toTypedArray()
            println("${arr2[0]} this is typed Array Thai")
            println(theElementT)
        }else{
            println("all good no  error elementTh is Empty ")}
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
