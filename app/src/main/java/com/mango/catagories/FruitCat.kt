package com.mango.catagories

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class FruitCat : AppCompatActivity() {
    private var fruitPhotos=arrayOf(R.drawable.watermelon,R.drawable.apple,/*R.drawable.avocado,R.drawable.banana,
        R.drawable.blueberry,R.drawable.cantaloupe,R.drawable.cherry,R.drawable.grape,R.drawable.jackfruit,
        R.drawable.lemon,R.drawable.mango,R.drawable.orange,R.drawable.papaya,R.drawable.plum,R.drawable.pumpkin,
        R.drawable.strawberry,R.drawable.tomato,R.drawable.coconut,R.drawable.mangosteen,R.drawable.rambutan*/)
    /////////////////////////////////////////////////////////////////////
    private lateinit var appleShot:ImageView         //images of fruit in View:
    private lateinit var userEnterE:EditText        //user editText
    private lateinit var scrambledFieldE:TextView   //scrambled text
    private lateinit var useHint:TextView         //instruction foe letter use
    private lateinit var dispThaiword:TextView        //user Thai Word
    private lateinit var wordInEArray:String        //reference
    private lateinit var marksString:String        //reference
    private var  wrongEng: ArrayList<String> = ArrayList()
    private var wrongThai: ArrayList<String> = ArrayList()
    private  var sizeOfArray = fruitPhotos.size
    private var numToInc:Int = 0        //num for incrementing array
    private var numOfErrorsE:Int = 0     //num for errors
    private var numOfAttempts:Int = 0  //num for number of attempts
    private var myGrades:Double = 0.0
    private var adjustedMark:Double = 0.0

    private val myArrays = TheArrays()
    /////////////////////////////////////////////////////////////////////

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruit_cat)
        appleShot = findViewById(R.id.appleShot)
        appleShot.setImageResource(fruitPhotos[numToInc])
        dispThaiword = findViewById(R.id.dispThai_word)
        userEnterE = findViewById(R.id.userEnter_E)
        scrambledFieldE = findViewById(R.id.scrambledField_e)
        useHint = findViewById(R.id.useHint)
        dispThaiword.append(myArrays.tthaiFruit[numToInc])
        scrambledFieldE.append(myArrays.escrambledFruits[numToInc])
        scrambledFieldE.alpha =0.toFloat()
        useHint.alpha =0.toFloat()
        wordInEArray = userEnterE.toString()
        println("$sizeOfArray  this is the size of the array")
        userEnterE.setOnEditorActionListener { _, actionId, _ ->//activates "done keyboard"
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                checkWords()
               hideSoftKeyboard()
                true
            } else false
        }
    } //end of constructor
    /////////////////////////////////////////////////////////////////////
    private fun checkWords(){
        if(userEnterE.text.toString() == myArrays.efruitTxt[numToInc]){
            reSetFruit()
        }
        else{
            respondToErrors()
        }
    }
    ///////////////////////////////////////////////////////////////////
    private fun Activity.hideSoftKeyboard() {
        currentFocus?.let {
            val inputMethodManager = ContextCompat.getSystemService(this, InputMethodManager::class.java)!!
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }
    ///////////////////////////////////////////////////////////////////
    private fun respondToErrors(){
        numOfAttempts +=1    //actual number of attempts
        accumulateErrors(numOfAttempts)
        adjustedMark = lessThanZero(getGrade())
        marksString = markToPass(getGrade())
        println(" $adjustedMark  this is adjustedMark")
        println(" $marksString  this is the result of my math")
        println(" $numOfErrorsE this is number of errors" )
        println(" $numOfAttempts this is number of attempts" )
        println(myGrades.toString())
        whenWrongAnswer()
        errorsAdvance(numOfErrorsE)
    }
    ///////////////////////////////////////////////////////////////////
    private fun whenWrongAnswer(){
        createArraysForWrongAnswers()
        userEnterE.setText("")
        useHint.alpha =1.toFloat()
        scrambledFieldE.alpha =1.toFloat()
    }
    ////////////////////////////////////////////////////////////////////
    private fun reSetFruit(){
    numToInc +=1
     endOfArray()
    appleShot.setImageResource(fruitPhotos[numToInc])
        dispThaiword.text = myArrays.tthaiFruit[numToInc]
        with(scrambledFieldE) { text = myArrays.escrambledFruits[numToInc] }
        userEnterE.setText("")
    scrambledFieldE.alpha =0.toFloat()
        0.toFloat().also { useHint.alpha = it }
    }
    ////////////////////////////////////////////////////////////////////
    private fun endOfArray(){
        if(numToInc == myArrays.efruitTxt.size ){
            println("this is where you switch activities... from FruitCat")
            val intent = Intent(this, GradeForEnglish::class.java)
            intent.putExtra("key", wrongEng)
            intent.putExtra("key2", wrongThai)
            intent.putExtra("key3",marksString)
            startActivity(intent)
        }
    }
    ////////////////////////////////////////////////////////////////////
    private fun threeErrors (){
        whenWrongAnswer()
        reSetFruit()
    }
    /////////////////////////////////////////////////////////////////////
    private fun errorsAdvance(mistakes:Int) {
        when (mistakes) {
            3 -> threeErrors()
            6 -> threeErrors()
            9 -> threeErrors()
            12 -> threeErrors()
            15 -> threeErrors()
            18 -> threeErrors()
            21 -> threeErrors()
            24 -> threeErrors()
            27 -> threeErrors()
            30 -> threeErrors()
            33 -> threeErrors()
            36 -> threeErrors()
            39 -> threeErrors()
            42 -> threeErrors()
            45-> threeErrors()
            48-> threeErrors()
            51-> threeErrors()
            54-> threeErrors()
            57-> threeErrors()
            60-> threeErrors()
        }
    }
    /////////////////////////////////////////////////////////////////////
    private fun createArraysForWrongAnswers(){
     val aWord = myArrays.efruitTxt[numToInc]
        val result = wrongEng.contains(aWord)
        if(!result){
            wrongEng.add(aWord)
            //println(wrongEng)
                }
        println(result) // true
        val aWordTh = myArrays.tthaiFruit[numToInc]
        val result2 = wrongThai.contains(aWordTh)
        if(!result2){
            wrongThai.add(aWordTh)
            //println(wrongThai)
        }
    }
    ////////////////////////////////////////////////////////////////////
    private fun getGrade():Double{
        var numOfCorrect = sizeOfArray - numOfErrorsE
        myGrades = (numOfCorrect.toDouble()/sizeOfArray)*100
        return myGrades

    }
    ////////////////////////////////////////////////////////////////////
    private fun accumulateErrors(numAttempts:Int) {
        when (numAttempts) {
            in 1..100 -> numOfErrorsE += 1
        }
    }
    //////////////////////////////////////////////////////////////////////
    private fun lessThanZero(allWrong:Double):Double {
        if (allWrong < 0.0){
            myGrades = 0.0
        }
        return myGrades
    }
    /////////////////////////////////////////////////////////////////////
    private fun markToPass(allWrong:Double):String {
        if (allWrong >= 0.0){
            myGrades = getGrade()
        }
        else{
            myGrades = adjustedMark
    }
    return myGrades.toString()
}
    //////////////////////////////////////////////////////////////////////

}//end of class
/*
private fun markToPass(allWrong:Double):String {
        if (allWrong > 0.0){
            myGrades = getGrade().toString()
        }
        else
        myGrades = adjustedMark.toString
        }
        return myGrades
    }









lessThanZero(getGrade())
     fun getGrade():Double{
       numOfCorrect = sizeOfArray - numOfErrorsE
      myGrades = (numOfCorrect.toDouble()/sizeOfArray)*100
      return myGrades
       println(numOfCorrect.toString())
       println(myGrades.toString())
    }
      private fun threeErrors (){
            whenWrongAnswer()
            reSetFruit()
     }
     fun main(args: Array<String>) {
    val a = 100
    when (a) {
        in 1..100 -> numOfErrorsE += 1
    }
    ////////////////////
    private fun accumulateErrors(mistakes:Int) {
             when (mistakes) {
                  3 -> threeErrors()
                  6 -> threeErrors()
                  9 -> threeErrors()
                  12 -> threeErrors()
                  15 -> threeErrors()
                  18 -> threeErrors()
                  21 -> threeErrors()
                  24 -> threeErrors()
                  27 -> threeErrors()
                  30 -> threeErrors()
                  33 -> threeErrors()
                  36 -> threeErrors()
                  39 -> threeErrors()
                  42 -> threeErrors()
                  45-> threeErrors()
                  48-> threeErrors()
                  51-> threeErrors()
                  54-> threeErrors()
                  57-> threeErrors()
                  60-> threeErrors()
            }
       }
}


 */


