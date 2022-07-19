package com.mango.catagories

import android.app.Activity
import android.content.Intent
import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import android.os.CountDownTimer
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import kotlin.math.roundToInt


class FruitCat : AppCompatActivity() {
    private var fruitPhotos=arrayOf(
        R.drawable.orange, R.drawable.apple,R.drawable.avocado/*,R.drawable.banana,
        R.drawable.blueberry,R.drawable.cantaloupe,R.drawable.cherry,R.drawable.grape,R.drawable.jackfruit,
        R.drawable.lemon,R.drawable.mango,R.drawable.watermelon,R.drawable.papaya,R.drawable.plum,R.drawable.pumpkin,
        R.drawable.strawberry,R.drawable.tomato,R.drawable.coconut,R.drawable.mangosteen,R.drawable.rambutan*/
    )
    /////////////////////////////////////////////////////////////////////
    private var soundPool: SoundPool? = null
    private lateinit var sndBtn2:ImageButton
    private lateinit var appleShot:ImageView        //images of fruit in View:
    private lateinit var userEnterE:EditText        //user editText
    private lateinit var scrambledFieldE:TextView   //scrambled text
    private lateinit var useHint:TextView           //instruction foe letter use
    private lateinit var dispEnglishWord:TextView      //user Thai Word
    private lateinit var wordInEArray:String        //reference
    private lateinit var marksString:String         //reference
    private lateinit var timer: CountDownTimer
    private var  wrongEng: ArrayList<String> = ArrayList()
    private var wrongThai: ArrayList<String> = ArrayList()
    private  var sizeOfArray = fruitPhotos.size
    private var numToInc:Int = 0         //num for incrementing array
    private var numOfErrorsE:Int = 0
    private var arrayIndex:Int = 0     //num for correct
    private var numOfAttempts:Int = 0    //num for number of attempts
    private var collectWrongAns:Int = 0 //num for tries
    private var threeWrongsE:Int = 0 //num for tries
    private var myGoofs:Int = 0 //num for tries
    private var myGrades:Double = 0.0
    private var adjustedMark:Double = 0.0
    private val myArrays = TheArrays()
    private val collectedIncorrect = Array(fruitPhotos.size) { 0 }
    private var noise1 = 1
    private var noise2 = 2
    private var noise3 = 3
    private var noise4 = 4
    private var noise5 = 5
    private var noise6 = 6
    private var noise7 = 7
    private var soundId  = 500
    ////////////////////////////////////////////////////////////////////////////////////////////
    private var orange     = 8
    private var apple      = 9
    private var avocado    = 10
    private var banana     = 11
    private var blueberry  = 12
    private var cantaloupe = 13
    private var cherry     = 14
    private var grape      = 15
    private var jackfruit  = 16
    private var lemon      = 17
    private var mango      = 18
    private var watermelon = 19
    private var papaya     = 20
    private var plum       = 21
    private var pumpkin    = 22
    private var strawberry = 23
    private var tomato     = 24
    private var coconut    = 25
    private var mangosteen = 26
    private var rambutan   = 27


    private  var fruitSnds = arrayListOf( orange,apple,avocado,banana,
        blueberry,cantaloupe,cherry/*,grape,
        jackfruit,lemon,mango,watermelon,
        papaya,plum,pumpkin,strawberry,
        tomato,coconut,mangosteen,watermelon*/)

    ////////////////////////////////////////////////////////////////////////////////////////////////
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowInsetsControllerCompat(window, window.decorView).apply {
            hide(WindowInsetsCompat.Type.statusBars())
        }
        setContentView(R.layout.activity_fruit_cat)
       //println("this is ${fruitPhotos.size} fruitPhotos.size" )
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        soundPool = SoundPool.Builder()
            .setMaxStreams(1)
            .setAudioAttributes(audioAttributes)
            .build()

        noise1 = soundPool?.load(this, R.raw.domdomsnd, 1)!!
        noise2 = soundPool?.load(this, R.raw.fart, 1)!!
        noise3 = soundPool?.load(this, R.raw.yart, 1)!!
        noise4 = soundPool?.load(this, R.raw.errorsnd, 1)!!
        noise5 = soundPool?.load(this, R.raw.mistake, 1)!!
        noise6 = soundPool?.load(this, R.raw.ohhhh, 1)!!
        noise7 = soundPool?.load(this, R.raw.burp, 1)!!
        ////////////////////////////////////////////////////////////////////////////////////////////
        orange       = soundPool?.load(this, R.raw.orange, 1)!!
        apple        = soundPool?.load(this, R.raw.apple, 1)!!
        avocado      = soundPool?.load(this, R.raw.avocado, 1)!!
        banana       = soundPool?.load(this, R.raw.banana, 1)!!
        blueberry    = soundPool?.load(this, R.raw.blueberry, 1)!!
        cantaloupe   = soundPool?.load(this, R.raw.cantaloupe, 1)!!
        cherry       = soundPool?.load(this, R.raw.cherry, 1)!!
        grape        = soundPool?.load(this, R.raw.cherry, 1)!!
        jackfruit    = soundPool?.load(this, R.raw.orange, 1)!!
        lemon        = soundPool?.load(this, R.raw.apple, 1)!!
        mango        = soundPool?.load(this, R.raw.avocado, 1)!!
        watermelon   = soundPool?.load(this, R.raw.banana, 1)!!
        papaya       = soundPool?.load(this, R.raw.blueberry, 1)!!
        plum         = soundPool?.load(this, R.raw.cantaloupe, 1)!!
        pumpkin      = soundPool?.load(this, R.raw.cherry, 1)!!
        strawberry   = soundPool?.load(this, R.raw.orange, 1)!!
        tomato       = soundPool?.load(this, R.raw.apple, 1)!!
        coconut      = soundPool?.load(this, R.raw.avocado, 1)!!
        mangosteen   = soundPool?.load(this, R.raw.banana, 1)!!
        rambutan     = soundPool?.load(this, R.raw.blueberry, 1)!!

        appleShot = findViewById(R.id.appleShot)
        appleShot.setImageResource(fruitPhotos[numToInc])
        dispEnglishWord = findViewById(R.id.dispEnglishWord)
        userEnterE = findViewById(R.id.userEnter_E)
        scrambledFieldE = findViewById(R.id.scrambledField_e)
        useHint = findViewById(R.id.useHint)
        sndBtn2 = findViewById(R.id.sndBtn2)
        sndBtn2.setOnClickListener {

            soundPool?.play(fruitSnds[numToInc], 1.0f, 1.0f, 0, 0, 1.0f)
        }
        dispEnglishWord.append(myArrays.tthaiFruit[numToInc])
        scrambledFieldE.append(myArrays.escrambledFruits[numToInc])
        scrambledFieldE.alpha =0.toFloat()
        useHint.alpha =0.toFloat()
        wordInEArray = userEnterE.toString()
        userEnterE.setOnEditorActionListener { _, actionId, _ ->//activates "done keyboard"
            if (actionId == EditorInfo.IME_ACTION_DONE) {

                checkWords()
               hideSoftKeyboard()
                true
            } else false
        }

    } //end of constructor
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private fun findIndex(arr: Array<String>, item: String): Int  //returns array index
    {
        return arr.indexOf(item)
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun checkWords(){
        if(userEnterE.text.toString() == myArrays.efruitTxt[numToInc] && numToInc < sizeOfArray ){
            arrayIndex =findIndex(myArrays.efruitTxt, myArrays.efruitTxt[numToInc])
            getNextFruit()
        }
        else{
            numOfAttempts +=1
            newIndividualEntryForErrors()
            determineErrorsSnd()
            respondToErrors()
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun Activity.hideSoftKeyboard() {
        currentFocus?.let {
            val inputMethodManager = ContextCompat.getSystemService(this, InputMethodManager::class.java)!!
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
private fun newIndividualEntryForErrors()
{
    arrayIndex = findIndex(myArrays.efruitTxt, myArrays.efruitTxt[numToInc])
     println(" this is $arrayIndex the current index called from checkIndividualEntry()")
    when (findIndex(myArrays.efruitTxt, myArrays.efruitTxt[numToInc]))
    {
        in 0..fruitPhotos.size -> individualErrorCount()//should limit errors per item (fruit))     // this is "watermelon"
        //1 -> individualErrorCount()//should limit errors per item (fruit))    // this is "apple"
    }
}
////////////////////////////////////////////////////////////////////////////////////////////////////
private fun individualErrorCount()    // this is called per array element
{
    myGoofs =modifyNumOfErrorsE(numOfAttempts)
    println(" this is $myGoofs  myGoofs from modifyNumOfErrorsE")
    setNumberOfIncorrect(collectedIncorrect,numToInc ,myGoofs)//sets index of array element value = 1
    collectWrongAns= accumulatedErrors(collectedIncorrect) // this returns all the wrong ans in entire array ie wrong = 2
    println(" this is $collectWrongAns after accumulatedErrors  array iterated")
}
////////////////////////////////////////////////////////////////////////////////////////////////////
private fun respondToErrors() {
    passPeram()
    reSetNumOfAttempts() // if 3 Attempts back to 0
    whenWrongAnswer()
}
////////////////////////////////////////////////////////////////////////////////////////////////////
private fun whenWrongAnswer(){
    createArraysForWrongAnswers()     //creates arrays for wrong answers
    cleanUpToContinue()
    //println("${numOfErrorsE.toString()} this is numOfErrorsE from whenWrongAnswer")//sets up scrambled field clears incorrect user entry
}
////////////////////////////////////////////////////////////////////////////////////////////////////
private fun reSetFruit(){
 endOfArray()
appleShot.setImageResource(fruitPhotos[numToInc])
dispEnglishWord.text = myArrays.tthaiFruit[numToInc]
with(scrambledFieldE) { text = myArrays.escrambledFruits[numToInc] }
userEnterE.setText("")
scrambledFieldE.alpha =0.toFloat()
0.toFloat().also { useHint.alpha = it }
}
////////////////////////////////////////////////////////////////////////////////////////////////////

private fun endOfArray() {   // when array done pass intents and change activities "GradeForEnglish"
                             // println("this is $wrongEng this is wrongEng from endOfArray()")
    setErrorResults()        // this should set stage for results()
    results(collectWrongAns) // this should determine marks Perfect or Not So Much
    if (numToInc == sizeOfArray) {
        println("this is where you switch activities... from FruitCat")
        val intent = Intent(this, GradeForEnglish::class.java)
        intent.putExtra("key", wrongEng)
        intent.putExtra("key2", wrongThai)
        intent.putExtra("key3", marksString)
        intent.putExtra("key4", numOfErrorsE)
        startActivity(intent)
    }
}
////////////////////////////////////////////////////////////////////////////////////////////////////
private fun reSetForThreeErrors(){
    numToInc += 1
    endOfArray()
    appleShot.setImageResource(fruitPhotos[numToInc])
    dispEnglishWord.text = myArrays.tthaiFruit[numToInc]
    with(scrambledFieldE) { text = myArrays.escrambledFruits[numToInc] }
    userEnterE.setText("")
    scrambledFieldE.alpha =0.toFloat()
    0.toFloat().also { useHint.alpha = it }
    //println("${numOfErrorsE.toString()} this is numOfErrorsE from reSetFruit()=0")
}
////////////////////////////////////////////////////////////////////////////////////////////////////
private fun threeErrors (){   //when errors = 3 - 6 - 9 etc
   useAWhen(soundId)// soundId = random number
    Toast.makeText(this, "Playing sound. . . .", Toast.LENGTH_SHORT).show()
   listenForComplete()
}
////////////////////////////////////////////////////////////////////////////////////////////////////
private fun passPeram() // comes from HelperFunctions deals with turnover at 3.6..9.
{
    HelperFunctions.errorsAdvance(numOfAttempts, ::threeErrors)
}
////////////////////////////////////////////////////////////////////////////////////////////////////
private fun createArraysForWrongAnswers(){
 val aWord = myArrays.efruitTxt[numToInc]
    val result = wrongEng.contains(aWord)
    if(!result){
        wrongEng.add(aWord)
            }
    val aWordTh = myArrays.tthaiFruit[numToInc]
    val result2 = wrongThai.contains(aWordTh)
    if(!result2){
        wrongThai.add(aWordTh)
        //println(wrongThai)
    }
}

    ////////////////////////////////////////////////////////////////////////////////////////////////////
//private fun getGrade():Double
    private fun getGrade(): Double {
        //modifyThreeErrors(collectWrongAns) this can not be here because 3 errors are possible with big array
        println("$collectWrongAns called from getGrade")
        myGrades = (sizeOfArray - collectWrongAns.toDouble()) / sizeOfArray * 100

        return (myGrades * 100).roundToInt() / 100.0
    }
////////////////////////////////////////////////////////////////////////////////////////////////////

private fun lessThanZero(allWrong:Double):Double {//changed from double
   // println("${numOfErrorsE.toString()} this is numOfErrorsE from lessThanZero")
    if (allWrong < 0.0){
        myGrades = 0.0
    }
    return myGrades
}
////////////////////////////////////////////////////////////////////////////////////////////////////
private fun markToPass(allWrong:Double):String { //changed from double
   // println("${numOfErrorsE.toString()} this is numOfErrorsE from markToPass")
    myGrades = if (allWrong >= 0.0){
        getGrade()
    } else{
        adjustedMark
    }
return myGrades.toString()
}
////////////////////////////////////////////////////////////////////////////////////////////////////
private fun numOfMistakes()
{
    if (numOfErrorsE == 0 && numToInc == sizeOfArray) {
        marksString = markToPass(getGrade())
        println(" $marksString this is marksString" )
    }
}
////////////////////////////////////////////////////////////////////////////////////////////////////
private fun cleanUpToContinue()
{
    if (numToInc <= sizeOfArray) {
        userEnterE.setText("")
        useHint.alpha =1.toFloat()
        scrambledFieldE.alpha =1.toFloat()
    }
}
////////////////////////////////////////////////////////////////////////////////////////////////////
private fun reSetNumOfAttempts() {
    if (numOfAttempts == 3) {
        numOfAttempts = 0
        println("$numOfAttempts this is number of attempts called from reSetNumOfAttempts")
    }
}
////////////////////////////////////////////////////////////////////////////////////////////////////
private fun useAWhen(myInt: Int) {
    when (myInt) {
        30 -> soundPool?.play(noise1, 1.0f, 1.0f, 0, 0, 1.0f)
        31 -> soundPool?.play(noise2, 1.0f, 1.0f, 0, 0, 1.0f)
        32 -> soundPool?.play(noise3, 1.0f, 1.0f, 0, 0, 1.0f)
        33 -> soundPool?.play(noise4, 1.0f, 1.0f, 0, 0, 1.0f)
        34 -> soundPool?.play(noise5, 1.0f, 1.0f, 0, 0, 1.0f)
        35 -> soundPool?.play(noise6, 1.0f, 1.0f, 0, 0, 1.0f)
        36 -> soundPool?.play(noise7, 1.0f, 1.0f, 0, 0, 1.0f)
        else -> soundPool?.play(noise2, 1.0f, 1.0f, 0, 0, 1.0f)
    }
}
////////////////////////////////////////////////////////////////////////////////////////////////////
private fun determineErrorsSnd():Int {
    soundId = (30..36).random()
   // println("$soundId this is myRandom from determineErrorsSnd()")
    return soundId
}
////////////////////////////////////////////////////////////////////////////////////////////////////
private fun listenForComplete() {
    timer = object : CountDownTimer(1250, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            println("This is the time left: $millisUntilFinished")
            determineErrorsSnd()
        }
        override fun onFinish() {
            reSetForThreeErrors()
            println("This is the timer cancelled")
            timer.cancel()
        }
    }
    timer.start()
}
////////////////////////////////////////////////////////////////////////////////////////////////////
private fun getNextFruit(){
    numToInc +=1      // if spelling correct increments arrays
    numOfAttempts =0  // reset for next index number
    reSetFruit()
}
////////////////////////////////////////////////////////////////////////////////////////////////////

private fun setErrorResults()//called in endOfArray
{
    println("This function setErrorResults() should be called in endOfArray")
    adjustedMark = lessThanZero(getGrade()) //may not need below 0 not possible if everything works
    marksString = markToPass(getGrade())    //"marksString" used in "intent" in endOfArray()
}
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun results(allTheWrong:Int)//collectWrongAns
    {
     println("This function results should be called in endOfArray")
    when (allTheWrong)
    {
        0 -> numOfMistakes()    //noErrors 100%
        1 -> setErrorResults() // Errors and less than 100%
    }
}
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //setNumberOfIncorrect(collectedIncorrect,numToInc ,numOfErrorsE)
    private fun setNumberOfIncorrect(listOfGoofs:Array<Int>,index:Int,theGoof:Int) {
        //myNumToInc = listOfGoofs
        listOfGoofs[index]= theGoof
        println("{${listOfGoofs.contentToString()}}..... listOgGoofs[0]")
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun accumulatedErrors(listOfGoofs:Array<Int>):Int {
        var gatheredWrong = 0
        for (item in listOfGoofs)
        {
            gatheredWrong += item
        }
        return gatheredWrong
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun modifyNumOfErrorsE(numOfTries:Int):Int {
        if (numOfTries == 1) {
            numOfErrorsE = 1
        }
        return numOfErrorsE
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun modifyCollectedWrongs(numOfTries:Int) {
        // this to use in event of three errors in one index  ie. orange = (wrong)*3
        threeWrongsE  += numOfTries
    }

    ////////////////////////////////////////////////////////////////////////////////////////////

}//end of class
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////

/*
/////////////////////////////////////////////////////
private fun setErrorResults()
{
    adjustedMark = lessThanZero(getGrade()) //may not need below 0 not possible if everything works
    marksString = markToPass(getGrade())    //"marksString" used in "intent" in endOfArray()

}
/////////////////////////////////////////////////////
private fun results(allTheWrong:Int)//collectWrongAns
   when (allTheWrong)
    {
        0 -> numOfMistakes()
        1 -> setErrorResults()
    }
}
///////////////////// written But not used /////////////////////////
private fun setNumberOfIncorrect(listOfGoofs:List<Int>,theGoof:Int) {
//myNumToInc = listOfGoofs
   listOfGoofs.add(theGoof)

}
////////////////////////////////////////////////////////////////////
private fun accumulatedErrors(listOfGoofs:List<Int>):Int {
    var gatheredWrong
    for (item in == listOfGoofs)
    {
        gatheredWrong += 1
    }
    return gatheredWrong
}
///////////////////////////////////////////////////////////////////////////////////////////////////
private fun accumulateCorrect2(theArrayIndex:Int,myNumToInc:Int,amountOfRight:Int = 0):Int {
    var gatheredCorrect = amountOfRight
    if (theArrayIndex == myNumToInc)
    {
        gatheredCorrect  += 1
    }
    return gatheredCorrect
}
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun accumulateErrors1(theArrayIndex:Int,myNumToInc:Int,wrongAccumulated:Int):Int {
        var gatheredWrong = wrongAccumulated
        if (theArrayIndex == myNumToInc)
        {
            gatheredWrong += 1
        }
        return gatheredWrong
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    private fun accumulateErrors2(theArrayIndex:Int,myNumToInc:Int,amountOfWrong:Int = 0):Int {
        var gatheredWrong = amountOfWrong
        if (theArrayIndex == myNumToInc)
        {
            gatheredWrong  += 1
        }
        return gatheredWrong
    }
///////////////////////////////////////////////////////////////////////////////////////////////////
private fun modify(numOfTries:Int) {
    if (numOfTries > 0) {
        numOfErrorsE = 1
    }
}
////////////////////////////////////////////////////////////////////
             "Cut from numOfMistakes()"

        //oneHundredPercent = true
        //val intent = Intent(this, GradeForEnglish::class.java)
        //intent.putExtra("key3", marksString)
       // intent.putExtra("key4", oneHundredPercent)
        //startActivity(intent)
///////////////////////////////////////////////////////////////////

///////////////////////" cut from private fun respondToErrors() ///////////////////////////////
        adjustedMark = lessThanZero(getGrade())   //numbers below 0 reset to 0.0% for your grade
       marksString = markToPass(getGrade())      //"marksString" used in "intent" in endOfArray()
       /////////////////////////////////////////////////////////////////////////////////////////

       private fun modifyThreeErrors(goofs:Int) { //collectWrongAns ,fruitPhotos
        if (collectWrongAns >= 3)
        {
            var goofsShadow = goofs
            goofsShadow = 1
            println(" this is $goofsShadow collectWrongAns  new number")
        }
    }

        //private val collectedIncorrect:MutableList<Int> = mutableListOf()
    ////////////////////////////////////////////////////////////////////////////////////////////////
 */





