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

class FruitCat : AppCompatActivity() {
    private var fruitPhotos=arrayOf(R.drawable.watermelon,R.drawable.apple,/*R.drawable.avocado,R.drawable.banana,
        R.drawable.blueberry,R.drawable.cantaloupe,R.drawable.cherry,R.drawable.grape,R.drawable.jackfruit,
        R.drawable.lemon,R.drawable.mango,R.drawable.orange,R.drawable.papaya,R.drawable.plum,R.drawable.pumpkin,
        R.drawable.strawberry,R.drawable.tomato,R.drawable.coconut,R.drawable.mangosteen,R.drawable.rambutan*/)
    /////////////////////////////////////////////////////////////////////
    private lateinit var mPlayer:MediaPlayer
    private lateinit var myErrorSounds:ArrayList<Int>      //images of fruit in View:
    private lateinit var appleShot:ImageView        //images of fruit in View:
    private lateinit var userEnterE:EditText        //user editText
    private lateinit var scrambledFieldE:TextView   //scrambled text
    private lateinit var useHint:TextView           //instruction foe letter use
    private lateinit var dispThaiword:TextView      //user Thai Word
    private lateinit var wordInEArray:String        //reference
    private lateinit var marksString:String         //reference
    private var  wrongEng: ArrayList<String> = ArrayList()
    private var wrongThai: ArrayList<String> = ArrayList()
    private  var sizeOfArray = fruitPhotos.size
    private var numToInc:Int = 0         //num for incrementing array
    private var numOfErrorsE:Int = 0     //num for errors
    private var wrongAnswersE:Int = 0     //num for errors
    private var numOfAttempts:Int = 0    //num for number of attempts
    private var myGrades:Double = 0.0
    private var theSndFile:Int = 0
    private var adjustedMark:Double = 0.0
    private val myArrays = TheArrays()   //instantciate custom class
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
        mPlayer = MediaPlayer()
        myErrorSounds = myArrays.errorSndArr
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
        if(userEnterE.text.toString() == myArrays.efruitTxt[numToInc] && numToInc < sizeOfArray ){
            numToInc +=1     // if spelling correct increments arrays
            numOfMistakes()  //  if spelling correct changes to new activity "GradeForEnglish"  gives 100%
            reSetFruit()     // if spelling correct uses numToInc to reset next image
        }
        else{
            numOfAttempts +=1
            numOfErrorsE +=1
            accumulateErrors(numOfErrorsE)//returns var from numOfErrorsE/one var for grades/one var reset = 0 for corrections
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
        marksString = markToPass(getGrade())      //"marksString" used in "intent" in endOfArray()
        passPeram()                               //calls threeWrong().. if errors = 3 - 6 - 9 etc
        whenWrongAnswer()
    }
    ///////////////////////////////////////////////////////////////////
    private fun whenWrongAnswer(){
        createArraysForWrongAnswers()             //creates arrays for wrong answers
        cleanUpToContinue()                       //sets up scrambled field clears incorrect user entry
    }
    ////////////////////////////////////////////////////////////////////
    private fun reSetFruit(){
     numOfErrorsE = 0
     endOfArray()
    appleShot.setImageResource(fruitPhotos[numToInc])
    dispThaiword.text = myArrays.tthaiFruit[numToInc]
    with(scrambledFieldE) { text = myArrays.escrambledFruits[numToInc] }
    userEnterE.setText("")
    scrambledFieldE.alpha =0.toFloat()
    0.toFloat().also { useHint.alpha = it }
    }
    ////////////////////////////////////////////////////////////////////

    private fun endOfArray() {    //when array done pass intents and change activities "GradeForEnglish"
        if (numToInc == sizeOfArray) {
            println("this is where you switch activities... from FruitCat")
            val intent = Intent(this, GradeForEnglish::class.java)
            intent.putExtra("key", wrongEng)
            intent.putExtra("key2", wrongThai)
            intent.putExtra("key3", marksString)
            startActivity(intent)
        }
    }
    ////////////////////////////////////////////////////////////////////(this, myArrays.errorSndArr[1])
    private fun threeErrors (){   //when errors = 3 - 6 - 9 etc
        // theSndFile = (0..3).random()

        mPlayer = MediaPlayer.create(this, myArrays.errorSndArr[4])
        mPlayer.setVolume(1.0f , 1.0f)
        println(" $numOfErrorsE this is number of errors called  threeErrors ()()" )
        mPlayer.start()
        numToInc +=1 //whenWrongAnswer() (0..3).random()
        reSetFruit()
        numOfAttempts = 0
    }
    //////////////////////////////////////////////
    private fun passPeram() // comes from HelperFunctions deals with turnover at 3.6..9.
    {
        HelperFunctions.errorsAdvance(numOfErrorsE, ::threeErrors)
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
        val numToSubtract = accumulateErrors(numOfErrorsE)
        val numOfCorrect = sizeOfArray - numToSubtract
        myGrades = (numOfCorrect.toDouble()/sizeOfArray)*100
        return myGrades
    }
    ////////////////////////////////////////////////////////////////////
    private fun accumulateErrors(incorrect:Int):Int {
        when (incorrect) {
            in 1..100 -> wrongAnswersE += 1
        }
        return wrongAnswersE
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
        myGrades = if (allWrong >= 0.0){
            getGrade()
        } else{
            adjustedMark
        }
    return myGrades.toString()
    }
    //////////////////////////////////////////////////////////////////////
    private fun numOfMistakes()
    {
        if (numOfErrorsE == 0 && numToInc == sizeOfArray) {
            marksString = markToPass(getGrade())
            println(" $marksString this is marksString" )
            val intent = Intent(this, GradeForEnglish::class.java)
            intent.putExtra("key3", marksString)
            startActivity(intent)
        }
    }
    ///////////////////////////////////////////////////////////
    private fun cleanUpToContinue()
    {
        if (numToInc <= sizeOfArray) {
            userEnterE.setText("")
            useHint.alpha =1.toFloat()
            scrambledFieldE.alpha =1.toFloat()
        }
    }
    /////////////////////////////////////////////////////////

}//end of class

/*
          println(" $numToInc this is numToInc" )
          println(" threeErrors () is called" )
          println("accumulateErrors is called" )
          //println("$myArrays.efruitTxt.size")
          //println("${numOfErrorsE.toString()} this is numOf Errors from respondToErrors")
          println(" $numOfErrorsE this is numOfErrorsE" )
        //println(" $adjustedMark  this is adjustedMark")
        //println(" $marksString  this is the result of my math")
        //println(" $numOfErrorsE this is number of errors" )
        //println(" $numOfAttempts this is number of attempts" )
        //println(myGrades.toString()) //createListsNoErrorMessage()
        //println(wrongEng).toString()
        // println(" IF in numOfMistakesIs3 is elected...  $numToInc this is numToInc after inc" )
        //println("cleanUpToContinue is called" )
        //println("${numOfErrorsE.toString()} this is numOfErrorsE called from accumulateErrors" )
         //println("Branch the next talk about transition of image" )
        //println(" $numOfErrorsE this is number of errors called from passPeram()" )
        //println(" $numOfAttempts this is number of attempts called from passPeram()" )
        //println("$numOfErrorsE) this is numOfErrorsE from endOfArray")
         println("$numOfAttempts this is number of attempts called from reSetNumOfAttempts")
         //println(" $numOfErrorsE this is number of errors called from whenWrongAnswer" )
         // println("$sizeOfArray  this is the size of the array")

                 //accumulateErrors(numOfAttempts)





            println("numOfMistakesIs3 this is called" )
            val intent = Intent(this, GradeForEnglish::class.java)
            intent.putExtra("key", wrongEng)
            intent.putExtra("key2", wrongThai)
            intent.putExtra("key3", marksString)
            startActivity(intent)

              private fun reSetNumOfAttempts()
    {
        if (numOfAttempts == 3) {
            numOfAttempts = 1
            println("$numOfAttempts this is number of attempts called from reSetNumOfAttempts")
        }
    }
     ///////////////////////////////////////////////////////////////
     private fun numOfMistakesIs3()
    {
        if (numOfErrorsE == 3) {
           // println(" IF in numOfErrorsE is elected...  $numToInc this is numToInc" )
           // println(" IF in numOfMistakesIs3 is elected...reSetFruit() is called")

            numToInc =1
            reSetFruit()

        }
    }



 private fun reSetNumOfAttempts()
    {
        if (numOfAttempts == 3) {
        numOfAttempts = 1
        println(" $numOfAttempts this is number of attempts" )
        }
    }
    private fun createListsNoErrorMessage()
    {
        if (numOfErrorsE == 0) {
            wrongEng.add("There are no mistakes")
            wrongThai.add("There are no mistakes")
            println(wrongEng).toString()
            //I am thinking this will resolve the "intent"
            // not passing an emptyArrayList
        }
    }
    ///////////////////////////////////////////////////////

*/






