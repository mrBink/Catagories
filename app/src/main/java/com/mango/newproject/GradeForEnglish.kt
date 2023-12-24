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
class GradeForEnglish :AppCompatActivity  () {
    //marks for writing in English/  comments in thai
    private lateinit var element: String
    private lateinit var elementTh: String
    private lateinit var delim: String
    private lateinit var delimT: String
    private lateinit var reportCard: TextView
    private lateinit var correctionField: TextView
    private lateinit var backToStart: ImageButton
    private lateinit var marksbg:ImageView
    private lateinit var cellBannerE: AdView

    ///////////////////////////////////////////////////////////////////////////////////////
    //@SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowInsetsControllerCompat(window, window.decorView).apply {
            hide(WindowInsetsCompat.Type.statusBars())
        }
        setContentView(R.layout.activity_grade_for_english)
        /*
        MobileAds.initialize(this) {}
        cellBannerE = findViewById(R.id.cellBannerE)
        val adRequest = AdRequest.Builder().build()
        cellBannerE.loadAd(adRequest)
        */

        marksbg = findViewById(R.id.marksbg)
        reportCard = findViewById(R.id.reportCard)
        correctionField = findViewById(R.id.correctionField)
        backToStart  = findViewById(R.id.backToStart)
        backToStart.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            }

        //val bundle = intent.extras  = intent.getSerializableExtra
        val unSerial = Deserializer()

       val incorrecteList = getSerializable(this,"key", ArrayList::class.java)

      // val incorrecteList = intent.getSerializableExtra("key")  // this was depricated commented long ago //val incorrecteListTh = intent.getSerializableExtra("key2")// this was depricated commented long ago
        val incorrecteListTh = getSerializable(this,"key2", ArrayList::class.java)
        val myMarks = intent.getStringExtra("key3")
        val myMarks2= myMarks?.substring(0,3)
        val myNumberOfErrorsE = intent.getIntExtra("key4",0)
       "นี่คือคะแนนของคุณ\n           $myMarks2%".also { reportCard.text = it }
       // "This is your mark.\n           $myMarks2%".also { reportCard.text = it }
        //"This is your mark\n           $myMarks%".also { reportCard.text = it }
       // println("this $myMarks2 is new string percentage")
        element = unSerial.unDoThis(incorrecteList)
        elementTh = unSerial.unDoThis(incorrecteListTh)
        delim = ","
        delimT = ","

//"
       val arr = element.split(delim)
        val mistakesEng: Array<String> = arr.toTypedArray()
        mistakesEng.forEach { println(it) }
        println("${mistakesEng.size}  this is mistakesEng.size  = arr converted to ArrayList" )
        arr[0].replace("[", "").replace("]", "")
        val arrTh = elementTh.split(delimT)
        arrTh[0].replace("[", "").replace("]", "")
        val noESquareBrace = arrangeEngTextForMarks(arr)
        arrangeEngTextForMarks(arr)
        val noTSquareBrace = arrangeThaiTextForMarks(arrTh)
        displayGrades( myNumberOfErrorsE,
            { thePerfectScore(correctionField) },
            { errorsToCorrectionField(arr,correctionField,noESquareBrace,noTSquareBrace) })
        displayGrades(myNumberOfErrorsE, { thePerfectScore(correctionField) } ,
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
        "นี่คือการแก้ไขของคุณ\n".also { errorField.text = it }
       //"These are your corrections.\n".also { errorField.text = it }
        for (i in 0..correctSize) {

            errorField.append("${thaiRight[i]} = ${engRight[i]}\n")
            //errorField.append("${engRight[i]}    =    ${thaiRight[i]}\n")

        }
        val ssb = SpannableStringBuilder(errorField.text)
        val fcsGreen = ForegroundColorSpan(Color.RED)
        with(ssb) {
            setSpan(fcsGreen, 0, 20, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        with(errorField) {
            ssb.setSpan(fcsGreen, 0, 20, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            text = ssb
        }

    }
    ///////////////////////////////////////////////////////////////////////////
    private fun thePerfectScore(myTextView: TextView){

        println("called from inside thePerfectScore")
        //errorField.text = "There are no corrections"
        println("There are no corrections")
        "ทุกสิ่งทุกอย่างถูกต้อง".also {myTextView.text = it }
        //"There are no corrections".also {myTextView.text = it }

    }
    ////////////////////////////////////////////////////////////////////////
    private fun displayGrades(tries:Int,idealScore:() -> Unit,someErrors:() -> Unit)
    {
        when (tries) {
            0 -> idealScore() // actual fun thePerfectScore()
            1 -> someErrors()   // actual fun errorsToCorrectionField()
            else -> println("called from inside displayGrades")
        }
    }
    ///////////////////////////////////////////////////////////////////////////




    }// end of class




/*
 ///////////////////////////////////////////////////////////////////////////
                DON'T LOOSE THIS

        correctionField.text = "These are the corrections\n"
        for (i in 0..correctSize) {

            correctionField.append("${engRight[i]}    =    ${thaiRight[i]}\n")
        }
         @SuppressLint("SetTextI18n")
   private fun iterateForMarks(sizeArray:List<String>,engRight:List<String>,thaiRight:List<String>){

        val correctSize =sizeArray.size-1
        val correctSize2 =engRight.size-1

          println("num of mistakes collected in in engRight $engRight called from iterate marks")
         val ssb = SpannableStringBuilder(correctionField.text)
         val fcsGreen = ForegroundColorSpan(Color.RED)
         with(ssb) {
         setSpan(fcsGreen, 0, 25, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
       }
         with(correctionField) {
         ssb.setSpan(fcsGreen, 0, 25, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
         text = ssb
       }

   }
    ///////////////////////////////////////////////////////////////////////////////////////////////
    //@SuppressLint("SetTextI18n")
    private fun gotOneHundred(noErrorsMade:List<String>,errorField:TextView){
        val perfect = noErrorsMade.size -1
        println("num of mistakes collected in in engRight $perfect called from gotOneHundred")
        if(noErrorsMade.size -1 <= 0)
        {
            println("num of mistakes collected in in engRight ${noErrorsMade.size -1} called from inside If expression in gotOneHundred")
            //errorField.text = "There are no corrections"
            println("There are no corrections")
            "There are no corrections".also { errorField.text = it }
        }
    }

        ///////////////////////////////////////////////////////////////////////////

        @SuppressLint("SetTextI18n")
    private fun gotOneHundred(sizeArray:List<String>, engRight:List<String>, thaiRight:List<String>)
    {
        val correctSize =sizeArray.size-1
        if( correctSize == 0)
        {
            correctionField.text = "There are no corrections"
        }else{
            for (i in 0..correctSize){
                correctionField.append("${engRight[i]}    =    ${thaiRight[i]}\n")

            }
        }
    }



  private fun callGotOneHundred(noErrorsMade:Int,reactErrors:() -> Unit){

        if(noErrorsMade== 0)
        {
            reactErrors()
        }
    }
    ///////////////////////////////////////////////////////////////////////////
     private fun thePerfectScore(errorField:TextView){

            println println("called from inside thePerfectScore")
            //errorField.text = "There are no corrections"
            println("There are no corrections")
            "There are no corrections".also { errorField.text = it }
    }
    //////////////////////////////////////////////////////////////////////////

private fun displayGrades(tries:Int,perfectScore:() -> Unit,someErrors:() -> Unit)
{
 when (tries) {
            0 -> perfectScore:() -> Unit // actual fun thePerfectScore()
            1 -> someErrors:() -> Unit   // actual fun errorsToCorrectionField()
            else -> -1
            )
        }
}










if (perfectMark == true) {
            println("go no further ")

        }

               //println("{$arr[1] } this is arr[1]  a string from arr last thing done this round I want water" )
        //var mistakesEng<String>(arr)
        //println("${mistakesEng.contentToString()}this is mistakesEng[0] without any errors" )

 private fun errorsToCorrectionField(errorField:TextView,theInt:Int){
        //should be correctionField
        errorField.text = "These are the corrections\n"
        for (i in 0..theInt) {

            errorField.append("${engRight[i]}    =    ${thaiRight[i]}\n")
        }
              }
val errorListSize=getListSize(arr)
 isEmpty(arr)
        checkArrayContent(arr,noESquareBrace,noTSquareBrace)
       // oneHundredPercent(arr,correctionField,noESquareBrace,noTSquareBrace)
       //printUpErrors(arr,noESquareBrace,noTSquareBrace)
        //iterateForMarks(arr, noESquareBrace, noTSquareBrace)

private fun getListSize(sizeArray:List<String>):Int{
        val theListSize =sizeArray.size -1
        return theListSize
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
    private fun isEmptyArray(any:Array<String>){
         if (any.contentToString() == "")
        {
            val noContent = true
            println("There are no corrections")
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    @SuppressLint("SetTextI18n")
    private fun checkArrayContent(theArrayList:List<String>,engRight:ArrayList<String>, thaiRight:ArrayList<String>) {
        val list = theArrayList

        val isEmpty = isEmpty(list)
        if (isEmpty) {
            correctionField.text = "There are no corrections"
        } else {
            for (i in 0..theArrayList.size ) {
                correctionField.text = "These are the corrections\n"
                correctionField.append("${engRight[i]}    =    ${thaiRight[i]}\n")
            }
        }
    }
    @SuppressLint("SetTextI18n")

    // parameters = arr,errorListSize,noESquareBrace,noTSquareBrace
    fun printUpErrors(sizeArray:List<String>,engRight:ArrayList<String>, thaiRight:ArrayList<String>) {
        //val correctSize = sizeArray.size
        //println("$correctSize this correctSize without any errors made ")
        if (sizeArray.size == 0) {
            println("sizeArray.isEmpty() this sizeArray.size without any errors made in condition incorrectSize == 0")
            correctionField.text = "There are no corrections"
        } else if (sizeArray.size > 0){
            println("${sizeArray.size} this sizeArray.size without any errors made in condition correctSize > 0")
            for (i in 0..sizeArray.size ) {
                correctionField.text = "These are the corrections\n"
                correctionField.append("${engRight[i]}    =    ${thaiRight[i]}\n")
            }
            }else{
                println("water buffalo farts")
            }
        }

 private fun getListSize(sizeArray:List<String>):Int{
        val theListSize =sizeArray.size
        return theListSize
    }
 fun printUpErrors(sizeArray:List<String>,engRight:ArrayList<String>, thaiRight:ArrayList<String>){
        var oneCondition = false
        val correctSize = sizeArray.size
        val correctSize2 = sizeArray.size
        println("$correctSize this correctSize without any errors made")
        if (correctSize == 0) {
            println("${engRight[0]} this engRight[0] without any errors made")

            correctionField.text = "There are no corrections"
        } else if (correctSize2 > 0){
            println("$correctSize this correctSize with  errors made")
            for (i in 0..correctSize ) {
                correctionField.text = "These are the corrections\n"
                correctionField.append("${engRight[i]}    =    ${thaiRight[i]}\n")
            }
            }else{
                println("water buffalo farts")
            }
        }





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
      fun printUpErrors(sizeArray:List<String>,dispResults:TextView ,engRight:List<String>, thaiRight:List<String>){
      val correctSize =sizeArray.size - 1
      //println("$correctSize this correctSize without any errors made")
      if( correctSize == -1){
          dispResults.text = "There are no corrections"
      }else{
            for (i in 0..correctSize){
            dispResults.append("${engRight[i]}    =    ${thaiRight[i]}\n")
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
