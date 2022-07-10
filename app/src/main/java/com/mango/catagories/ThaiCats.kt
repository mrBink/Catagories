package com.mango.catagories

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

class ThaiCats : AppCompatActivity() {
    private var fruitPhotos=arrayOf(R.drawable.orange,R.drawable.apple/*,R.drawable.avocado,R.drawable.banana,
        R.drawable.blueberry,R.drawable.cantaloupe,R.drawable.cherry,R.drawable.grape,R.drawable.jackfruit,
        R.drawable.lemon,R.drawable.mango,R.drawable.watermelon,R.drawable.papaya,R.drawable.plum,R.drawable.pumpkin,
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
    private var numOfCorrectT:Int = 0
    private var arrayIndexT:Int = 0
    private var wrongAnswersT:Int = 0
    private var numOfAttemptsT:Int = 0    //num for number of attempts
    private var myGradesT:Double = 0.0
    private var theSndFileT:Int = 0
    private var adjustedMark:Double = 0.0//number of wrong answers "no repeats"
    private val myArrays = TheArrays()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowInsetsControllerCompat(window, window.decorView).apply {
            hide(WindowInsetsCompat.Type.statusBars())
        }
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
                checkWords()
                hideSoftKeyboard()
                true
            } else false
        }
    } //end of constructor
    /////////////////////////////////////////////////////////////////////
    private fun findIndex(arr: Array<String>, item: String): Int  //returns array index
    {
        return arr.indexOf(item)
    }
    ///////////////////////////////////////////////////////////////////////////////////
    private fun checkWords(){
        if(userEntert.text.toString() == myArrays.tthaiFruit[numToInc] && numToInc < sizeOfArray ){
            findIndex(myArrays.efruitTxt, myArrays.efruitTxt[numToInc])
            checkIndividualEntry()
            println(" this is $arrayIndexT the current index called from checkIndividualEntry()")

            // numToInc +=1     // if spelling correct increments arrays
            //numOfMistakes()   // if spelling correct changes to new activity "GradeForEnglish"  gives 100%
            // reSetFruit()     // if spelling correct uses numToInc to reset next image
        }
        else{
            numOfAttemptsT +=1
            accumulateErrors(numOfErrorsT)//returns var from numOfErrorsE/one var for grades/one var reset = 0 for corrections
            respondToErrors()
            println("$numOfAttemptsT this is numberOfAttempts from else in checkWords()")
        }
    }
    ///////////////////////////////////////////////////////////////////
    private fun Activity.hideSoftKeyboard() {
        currentFocus?.let {
            val inputMethodManager = ContextCompat.getSystemService(this, InputMethodManager::class.java)!!
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun checkIndividualEntry()
    {
        arrayIndexT = findIndex(myArrays.efruitTxt, myArrays.efruitTxt[numToInc])
        println(" this is $arrayIndexT the current index called from checkIndividualEntry()")
        when (findIndex(myArrays.efruitTxt, myArrays.efruitTxt[numToInc]))
        {
            // 0 -> advanceWhenCorrect()     // this is "watermelon"
            //1 -> advanceWhenCorrect()     // this is "apple"
            0 -> advanceEntryNowCorrect (numOfAttemptsT)     // this is "watermelon"
            1 -> advanceEntryNowCorrect (numOfAttemptsT)     // this is "apple"
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun advanceEntryNowCorrect (tries:Int) {
        when (tries) {
            0 -> resetForZeroAttempts()
            1 -> resetForOneOrTwoAttempts()
            2 -> resetForOneOrTwoAttempts()
            3 -> passPeram()
        }
    }
    private fun resetForZeroAttempts() //only called when correct..numOfAttempts = 0 when correct
    {
        println(" this is $arrayIndexT the current index called from resetForZeroAttempts()")
        numOfCorrectT +=1 // for Marks
        numToInc +=1      // if spelling correct increments arrays
        numOfMistakes()   //  if spelling correct changes to new activity "GradeForEnglish"  gives 100%
        numOfAttemptsT =0  // reset for next index number
        reSetFruit()      // if spelling correct uses numToInc to reset next image
    }

   ////////////////////////////////////////////////////////////////////////////////////////////////
   private fun resetForOneOrTwoAttempts() //only called when correct..numOfAttempts = 0 when correct
   {
       println(" this is $arrayIndexT the current index called from resetForOneOrTwoAttempts()")
       numToInc += 1      // if spelling correct increments arrays
       numOfAttemptsT = 0  // reset for next index number
       reSetFruit()       // if spelling correct uses numToInc to reset next image
   }

////////////////////////////////////////////////////////////////////////////////////////////////

    private fun respondToErrors() {
        adjustedMark = lessThanZero(getGrade())   //numbers below 0 reset to 0.0% for your grade
        marksStringT = markToPass(getGrade())      //"marksString" used in "intent" in endOfArray()
        passPeram()
        reSetNumOfAttempts() // if 3 Attempts back to 0
        whenWrongAnswer()
        //advanceIfCorrect()
        println(" $numOfAttemptsT this is number of attempts called from respondToErrors()...Get Fucking Fucked")
    }
    ///////////////////////////////////////////////////////////////////
    private fun whenWrongAnswer(){
        createArraysForWrongAnswers()             //creates arrays for wrong answers
        cleanUpToContinue()
        //println("${numOfErrorsE.toString()} this is numOfErrorsE from whenWrongAnswer")//sets up scrambled field clears incorrect user entry
    }
    ///////////////////////////////////////////////////////////////
    private fun reSetFruit(){
        endOfArray()
        appleShot.setImageResource(fruitPhotos[numToInc])
        dispThaiWord.text = myArrays.tthaiFruit[numToInc]
        with(scrambledFieldt) { text = myArrays.tscrambleThFruit[numToInc] }
        userEntert.setText("")
        scrambledFieldt.alpha =0.toFloat()
        0.toFloat().also { useHintT.alpha = it }
    }
    ///////////////////////////////////////////////////////////////
    private fun endOfArray() {    //when array done pass intents and change activities "GradeForEnglish"
        if (numToInc == sizeOfArray) {
            println("this is where you switch activities... from FruitCat")
            val intent = Intent(this, GradeForEnglish::class.java)
            intent.putExtra("key", wrongEng)
            intent.putExtra("key2", wrongThai)
            intent.putExtra("key3", marksStringT)
            startActivity(intent)
        }
    }
    //////////////////////////////////////////////////////////////////////////////
    private fun reSetForThreeErrors(){
        numToInc += 1
        println("$numToInc this is numToInc from reSetForThreeErrors()")
        println("$numOfErrorsT this is numToInc from reSetForThreeErrors()")
        endOfArray()
        appleShot.setImageResource(fruitPhotos[numToInc])
        dispThaiWord.text = myArrays.tthaiFruit[numToInc]
        with(scrambledFieldt) { text = myArrays.escrambledFruits[numToInc] }
        userEntert.setText("")
        scrambledFieldt.alpha =0.toFloat()
        0.toFloat().also { useHintT.alpha = it }
        //println("${numOfErrorsE.toString()} this is numOfErrorsE from reSetFruit()=0")
    }
    /////////////////////////////////////////////////////////////
    private fun threeErrors (){   //when errors = 3 - 6 - 9 etc
        theSndFileT = (0..3).random()
        mPlayer = MediaPlayer.create(this, myArrays.errorSndArr[theSndFileT])
        mPlayer.setVolume(1.0f , 1.0f)
        //println(" $numOfErrorsE this is number of errors called  threeErrors ()()" )
        mPlayer.start()
        //numToInc +=1 //whenWrongAnswer() (0..3).random()
        reSetForThreeErrors()
    }
    //////////////////////////////////////////////
    private fun passPeram() // comes from HelperFunctions deals with turnover at 3.6..9.
    {
        HelperFunctions.errorsAdvance(numOfAttemptsT, ::threeErrors)
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
    private fun getGrade():Double
    {
        //println("$numOfCorrectE this is numOfCorrect from getGrade()")
        myGradesT = (numOfCorrectT.toDouble()/sizeOfArray)*100
        return myGradesT
    }
    ////////////////////////////////////////////////////////////////////
    private fun accumulateErrors(incorrect:Int):Int {//use numOfErrorsE
        //println("${numOfErrorsE.toString()} this is numOfErrorsE from accumulateErrors")
        when (incorrect) {
            in 1..100 -> wrongAnswersT += 1
        }
        return wrongAnswersT
    }
    //////////////////////////////////////////////////////////////////////
    private fun lessThanZero(allWrong:Double):Double {
        // println("${numOfErrorsE.toString()} this is numOfErrorsE from lessThanZero")
        if (allWrong < 0.0){
            myGradesT = 0.0
        }
        return myGradesT
    }
    /////////////////////////////////////////////////////////////////////
    private fun markToPass(allWrong:Double):String {
        // println("${numOfErrorsE.toString()} this is numOfErrorsE from markToPass")
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
        if (numOfErrorsT == 0 && numToInc == sizeOfArray) {
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
    private fun reSetNumOfAttempts()
    {
        if (numOfAttemptsT == 3) {
            numOfAttemptsT = 0
            println("$numOfAttemptsT this is number of attempts called from reSetNumOfAttempts")
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////



}//end of class


