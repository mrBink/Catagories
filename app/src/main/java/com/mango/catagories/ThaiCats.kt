package com.mango.catagories

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat

class ThaiCats : AppCompatActivity() {
    private var fruitPhotos=arrayOf(R.drawable.watermelon,R.drawable.apple/*,R.drawable.avocado,R.drawable.banana,
        R.drawable.blueberry,R.drawable.cantaloupe,R.drawable.cherry,R.drawable.grape,R.drawable.jackfruit,
        R.drawable.lemon,R.drawable.mango,R.drawable.orange,R.drawable.papaya,R.drawable.plum,R.drawable.pumpkin,
        R.drawable.strawberry,R.drawable.tomato,R.drawable.coconut,R.drawable.mangosteen,R.drawable.rambutan*/)
    /////////////////////////////////////////////////////////////////////
    private lateinit var mPlayer: MediaPlayer
    private lateinit var myErrorSounds:ArrayList<Int>
    private lateinit var appleShot: ImageView        //images of fruit in View
    private lateinit var userEntert: EditText        //user editText
    private lateinit var scrambledFieldt: TextView  //scrambled text
    private lateinit var useHintT: TextView          //instruction foe letter use
    private lateinit var dispThaiWord: TextView  //user Thai Word
    private lateinit var wordInTArray:String         //reference
    private lateinit var marksStringT:String         //reference
    private var  wrongEng: ArrayList<String> = ArrayList()
    private var wrongThai: ArrayList<String> = ArrayList()
    private  var sizeOfArray = fruitPhotos.size
    private var numToInc:Int = 0         //num for incrementing array
    private var numOfErrorsT:Int = 0     //num for errors
    private var wrongAnswersT:Int = 0
    private var numOfAttempts:Int = 0    //num for number of attempts
    private var myGradesT:Double = 0.0
    private var theSndFileT:Int = 0
    private var adjustedMark:Double = 0.0//number of wrong answers "no repeats"
    private val myArrays = TheArrays()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_thai_cats)
        appleShot = findViewById(R.id.appleShot)
        appleShot.setImageResource(fruitPhotos[numToInc])
        dispThaiWord= findViewById(R.id.dispThaiWord)
        userEntert = findViewById(R.id.userEntert)
        scrambledFieldt = findViewById(R.id.scrambledFieldt)
        useHintT = findViewById(R.id.useHintT)
        dispThaiWord.append(myArrays.efruitTxt[numToInc])
        scrambledFieldt.append(myArrays.tscrambleThFruit[numToInc])
        useHintT.alpha = 0.toFloat()
        scrambledFieldt.alpha =0.toFloat()
        wordInTArray = userEntert.toString()
        mPlayer = MediaPlayer()
        myErrorSounds = myArrays.errorSndArr
        userEntert.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                //callback.invoke()

                checkWords()
                hideSoftKeyboard()
                true
            } else false
        }

    } //end of constructor
    private fun checkWords(){
        if(userEntert.text.toString() == myArrays.tthaiFruit[numToInc]){
            numToInc +=1     // if spelling correct increments arrays
            numOfMistakes()  //  if spelling correct changes to new activity "GradeForEnglish"  gives 100%
            reSetFruit()
        }
        else{
            numOfAttempts +=1
            numOfErrorsT +=1
            accumulateErrors(numOfErrorsT)//returns var from numOfErrorsE/one var for grades/one var reset = 0 for corrections
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
        numOfAttempts +=1                         //actual number of attempts
        adjustedMark = lessThanZero(getGrade())   //numbers below 0 reset to 0.0% for your grade
        marksStringT = markToPass(getGrade())      //"marksString" used in "intent" in endOfArray()
        passPeram()                               //calls threeWrong().. if errors = 3 - 6 - 9 etc
        whenWrongAnswer()
        println("${numOfErrorsT.toString()} this is * numOfErrorsT from respondToErrors()")
    }
    ///////////////////////////////////////////////////////////////////
    private fun whenWrongAnswer(){
        createArraysForWrongAnswers()             //creates arrays for wrong answers
        cleanUpToContinue()
        println("${numOfErrorsT.toString()} this is * numOfErrorsT from whenWrongAnswer")
    }
    ///////////////////////////////////////////////////////////////
    private fun reSetFruit(){
        numOfErrorsT = 0
        endOfArray()
        appleShot.setImageResource(fruitPhotos[numToInc])
        dispThaiWord.text = myArrays.tthaiFruit[numToInc]
        with(scrambledFieldt) { text = myArrays.escrambledFruits[numToInc] }
        userEntert.setText("")
        scrambledFieldt.alpha =0.toFloat()
        0.toFloat().also { useHintT.alpha = it }
    }
    ///////////////////////////////////////////////////////////////
    private fun endOfArray(){
        if(numToInc == myArrays.efruitTxt.size ){

            println("this is where you switch activities... from FruitCat")
            val intent = Intent(this, GradeForEnglish::class.java)
            intent.putExtra("key", wrongEng)
            intent.putExtra("key2", wrongThai)
            intent.putExtra("key3", marksStringT)
            startActivity(intent)
        }
    }
    private fun threeErrors() {
        theSndFileT = (0..3).random()
        mPlayer = MediaPlayer.create(this, myArrays.errorSndArr[theSndFileT])
        mPlayer.setVolume(1.0f , 1.0f)
        println(" $numOfErrorsT  this is number of errors called  threeErrors ()()" )
        mPlayer.start()
        numToInc +=1 //whenWrongAnswer() (0..3).random()
        reSetFruit()
        numOfAttempts = 0
    }
    //////////////////////////////////////////////
    private fun passPeram() // comes from HelperFunctions deals with turnover at 3.6..9.
    {
        HelperFunctions.errorsAdvance(numOfErrorsT , ::threeErrors)
    }
    ///////////////////////////////////////////////////////////////
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
    ///////////////////////////////////////////////////////////////////////
    private fun getGrade():Double{
        println("$numOfErrorsT this is numOfErrorsT from getGrade()")
        val numToSubtract = accumulateErrors(numOfErrorsT )
        val numOfCorrect = sizeOfArray - numToSubtract
        myGradesT = (numOfCorrect.toDouble()/sizeOfArray)*100
        return myGradesT
    }
    ////////////////////////////////////////////////////////////////////
    private fun accumulateErrors(incorrect:Int):Int {
        when (incorrect) {
            in 1..100 -> wrongAnswersT += 1
        }
        return wrongAnswersT
    }
    //////////////////////////////////////////////////////////////////////
    private fun lessThanZero(allWrong:Double):Double {
        if (allWrong < 0.0){
            myGradesT = 0.0
        }
        return myGradesT
    }
    /////////////////////////////////////////////////////////////////////
    private fun markToPass(allWrong:Double):String {
        myGradesT = if (allWrong >= 0.0){
            getGrade()
        } else{
            adjustedMark
        }
        return myGradesT.toString()
    }
    //////////////////////////////////////////////////////////////////////
    private fun numOfMistakes()
    {
        if (numOfErrorsT  == 0 && numToInc == sizeOfArray) {
            marksStringT = markToPass(getGrade())
            println(" $marksStringT this is marksString" )
            val intent = Intent(this, GradeForEnglish::class.java)
            intent.putExtra("key3", marksStringT)
            startActivity(intent)
        }
    }
    ///////////////////////////////////////////////////////////
    private fun cleanUpToContinue()
    {
        if (numToInc <= sizeOfArray) {
            userEntert.setText("")
            useHintT.alpha =1.toFloat()
            scrambledFieldt.alpha =1.toFloat()
        }
    }
    ///////////////////////////////////////////////////////
}//end of class


