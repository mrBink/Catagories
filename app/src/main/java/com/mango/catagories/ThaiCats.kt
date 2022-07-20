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

class ThaiCats : AppCompatActivity() {
    private var fruitPhotos=arrayOf(R.drawable.orange,R.drawable.apple,R.drawable.avocado/*,R.drawable.banana,
        R.drawable.blueberry,R.drawable.cantaloupe,R.drawable.cherry,R.drawable.grape,R.drawable.jackfruit,
        R.drawable.lemon,R.drawable.mango,R.drawable.watermelon,R.drawable.papaya,R.drawable.plum,R.drawable.pumpkin,
        R.drawable.strawberry,R.drawable.tomato,R.drawable.coconut,R.drawable.mangosteen,R.drawable.rambutan*/)
    /////////////////////////////////////////////////////////////////////
    private var soundPool: SoundPool? = null
    private lateinit var sndBtn:ImageButton
    private lateinit var appleShot: ImageView        //images of fruit in View
    private lateinit var userEntert: EditText        //user editText
    private lateinit var scrambledFieldt: TextView  //scrambled text
    private lateinit var useHintT: TextView          //instruction foe letter use
    private lateinit var dispThaiWord: TextView  //user Thai Word
    private lateinit var wordInTArray:String         //reference
    private lateinit var marksStringT:String         //reference
    private lateinit var timer: CountDownTimer
    private var  wrongEng: ArrayList<String> = ArrayList()
    private var wrongThai: ArrayList<String> = ArrayList()
    private  var sizeOfArray = fruitPhotos.size
    private var numToInc:Int = 0         //num for incrementing array
    private var numOfErrorsT:Int = 0     //num for errors
    private var arrayIndexT:Int = 0
    private var numOfAttemptsT:Int = 0    //num for number of attempts
    private var collectWrongAns:Int = 0 //num for tries
    private var threeWrongs:Int = 0 //num for tries
    private var myGoofs:Int = 0 //num from errorArray
    private var myGradesT:Double = 0.0
    private var adjustedMark:Double = 0.0//number of wrong answers "no repeats"
    private val myArrays = TheArrays()
    private val collectedIncorrect = Array(fruitPhotos.size) { 0 }

    /////////////added July 11//////////////////////////////////////////////////////////////////////
    private var noise1 = 1
    private var noise2 = 2
    private var noise3 = 3
    private var noise4 = 4
    private var noise5 = 5
    private var noise6 = 6
    private var noise7 = 7
    private var soundId  = 500
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private var t_orange     = 8
    private var t_apple      = 9
    private var t_avocado    = 10
    private var t_banana     = 11
    private var t_blueberry  = 12
    private var t_cantaloupe = 13
    private var t_cherry     = 14
    private var t_grape      = 15
    private var t_jackfruit  = 16
    private var t_lemon      = 17
    private var t_mango      = 18
    private var t_watermelon = 19
    private var t_papaya     = 20
    private var t_plum       = 21
    private var t_pumpkin    = 22
    private var t_strawberry = 23
    private var t_tomato     = 24
    private var t_coconut    = 25
    private var t_mangosteen = 26
    private var t_rambutan   = 27


    private  var t_fruitSnds = arrayListOf( t_orange,t_apple,t_avocado,t_banana,
        t_blueberry,t_cantaloupe,t_cherry/*,t_grape,
        t_jackfruit,t_lemon,t_mango,t_watermelon,
        t_papaya,t_plum,t_pumpkin,t_strawberry,
        t_tomato,t_coconut,t_mangosteen,t_watermelon*/)

    ////////////////////////////////////////////////////////////////////////////////////////////////


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowInsetsControllerCompat(window, window.decorView).apply {
            hide(WindowInsetsCompat.Type.statusBars())
        }
        setContentView(R.layout.activity_thai_cats)
        ////////////////////////////added July 11///////////////////////////////////////////////////
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
        t_orange       = soundPool?.load(this, R.raw.t_orange, 1)!!
        t_apple        = soundPool?.load(this, R.raw.t_apple, 1)!!
        t_avocado      = soundPool?.load(this, R.raw.t_avocado, 1)!!
        t_banana       = soundPool?.load(this, R.raw.t_banana, 1)!!
        t_blueberry    = soundPool?.load(this, R.raw.t_blueberry, 1)!!
        t_cantaloupe   = soundPool?.load(this, R.raw.t_cantaloupe, 1)!!
        t_cherry       = soundPool?.load(this, R.raw.t_cherry, 1)!!
        t_grape        = soundPool?.load(this, R.raw.t_grape, 1)!!
        t_jackfruit    = soundPool?.load(this, R.raw.t_jackfruit, 1)!!
        t_lemon        = soundPool?.load(this, R.raw.t_lemon, 1)!!
        t_mango        = soundPool?.load(this, R.raw.t_mango, 1)!!
        t_watermelon   = soundPool?.load(this, R.raw.t_watermelon, 1)!!
        t_papaya       = soundPool?.load(this, R.raw.t_papaya, 1)!!
        t_plum         = soundPool?.load(this, R.raw.t_plum, 1)!!
        t_pumpkin      = soundPool?.load(this, R.raw.t_pumpkin, 1)!!
        t_strawberry   = soundPool?.load(this, R.raw.t_strawberry, 1)!!
        t_tomato       = soundPool?.load(this, R.raw.t_tomato, 1)!!
        t_coconut      = soundPool?.load(this, R.raw.t_coconut, 1)!!
        t_mangosteen   = soundPool?.load(this, R.raw.t_mangosteen, 1)!!
        t_rambutan     = soundPool?.load(this, R.raw.t_rambutan, 1)!!

        ////////////////////////////////////////////////////////////////////////////////////////////

        appleShot = findViewById(R.id.appleShot)
        appleShot.setImageResource(fruitPhotos[numToInc])
        dispThaiWord= findViewById(R.id.dispThaiWord)
        userEntert = findViewById(R.id.userEntert)
        scrambledFieldt = findViewById(R.id.scrambledFieldt)
        useHintT = findViewById(R.id.useHintT)
        sndBtn = findViewById(R.id.sndBtn)
        sndBtn.setOnClickListener {

            soundPool?.play(t_fruitSnds[numToInc], 1.0f, 1.0f, 0, 0, 1.0f)
        }
        dispThaiWord.append(myArrays.efruitTxt[numToInc])
        scrambledFieldt.append(myArrays.tscrambleThFruit[numToInc])
        useHintT.alpha = 0.toFloat()
        scrambledFieldt.alpha =0.toFloat()
        wordInTArray = userEntert.toString()

        //myErrorSounds = myArrays.errorSndArr
        userEntert.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                checkWords()
                hideSoftKeyboard()
                true
            } else false
        }
    } //end of constructor
    ////////////////////////////////////////////////////////////////////////////////////////////
    private fun findIndex(arr: Array<String>, item: String): Int  //returns array index
    {
        return arr.indexOf(item)
    }
    ////////////////////////////////////////////////////////////////////////////////////////////
    private fun checkWords(){
        if(userEntert.text.toString() == myArrays.tthaiFruit[numToInc] && numToInc < sizeOfArray ){
            findIndex(myArrays.efruitTxt, myArrays.efruitTxt[numToInc])
            //checkIndividualEntry()
            getNextFruit()
        }
        else{
            numOfAttemptsT +=1
            newIndividualEntryForErrors()
            determineErrorsSnd()
            respondToErrors()
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////
    private fun Activity.hideSoftKeyboard() {
        currentFocus?.let {
            val inputMethodManager = ContextCompat.getSystemService(this, InputMethodManager::class.java)!!
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private fun newIndividualEntryForErrors()
    {
        arrayIndexT = findIndex(myArrays.efruitTxt, myArrays.efruitTxt[numToInc])
        println(" this is $arrayIndexT the current index called from checkIndividualEntry()")
        when (findIndex(myArrays.efruitTxt, myArrays.efruitTxt[numToInc]))
        {
            in 0..fruitPhotos.size -> individualErrorCount()//should limit errors per item (fruit))     // this is "watermelon"
            //1 -> individualErrorCount()//should limit errors per item (fruit))    // this is "apple"
        }
    }
        ////////////////////////////////////////////////////////////////////////////////////////////
        private fun individualErrorCount()      // this is called per array element
        {
            myGoofs = modifyNumOfErrorsE(numOfAttemptsT)  // if numOfAttemptsT == 1 ..numOfErrorsT  = 1

            //println(" this is $numOfErrorsT errors  called from individualErrorCount()/checkWords()/else")
            //collectWrongAns += numOfErrorsE   // used in results()
            //collectWrongAns =numOfAttempts   // this collects the ones to compute the Grade
            //private val collectedIncorrect:ArrayList<Int> = ArrayList()collectedIncorrect
            setNumberOfIncorrect(collectedIncorrect,numToInc ,myGoofs)//sets index of array element value = 1
            collectWrongAns= accumulatedErrors(collectedIncorrect) // this returns all the wrong ans in entire array ie wrong = 2
            modifyCollectedWrongs(numOfAttemptsT)
           // println(" this is $collectWrongAns this is collectWrongAns in collectedIncorrect Array")
            //collectWrongAns =accumulatedErrors(collectedIncorrect)//accumulatedErrors iterates ArrayList
            //collectWrongAns =accumulatedErrors()//accumulatedErrors iterates ArrayList
            println(" this is $collectWrongAns after accumulatedErrors  array iterated")
        }
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    private fun respondToErrors() {
       // adjustedMark = lessThanZero(getGrade())   //numbers below 0 reset to 0.0% for your grade
        //marksStringT = markToPass(getGrade())      //"marksString" used in "intent" in endOfArray()
        passPeram()
        reSetNumOfAttempts() // if 3 Attempts back to 0
        whenWrongAnswer()
        println(" $numOfAttemptsT this is number of attempts called from respondToErrors()...Get Fucking Fucked")
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun whenWrongAnswer(){
        createArraysForWrongAnswers()             //creates arrays for wrong answers
        cleanUpToContinue()
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun reSetFruit(){
        endOfArray()
        appleShot.setImageResource(fruitPhotos[numToInc])
        dispThaiWord.text = myArrays. efruitTxt[numToInc]
        with(scrambledFieldt) { text = myArrays.tscrambleThFruit[numToInc] }
        userEntert.setText("")
        scrambledFieldt.alpha =0.toFloat()
        0.toFloat().also { useHintT.alpha = it }
    }
    //////////////////////////////////////////////////////////////////////////////////////
    private fun endOfArray() {    //when array done pass intents and change activities "GradeForEnglish"
        setErrorResults()
        results(collectWrongAns)
        if (numToInc == sizeOfArray) {
            println("this is where you switch activities... from FruitCat")
            val intent = Intent(this, GradeForEnglish::class.java)
            intent.putExtra("key", wrongEng)
            intent.putExtra("key2", wrongThai)
            intent.putExtra("key3", marksStringT)
            intent.putExtra("key4", numOfErrorsT)
            startActivity(intent)
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
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
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun threeErrors (){   //when errors = 3 - 6 - 9 etc
        useAWhen(soundId)// soundId = random number
        Toast.makeText(this, "Playing sound. . . .", Toast.LENGTH_SHORT).show()
        listenForComplete()
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun passPeram() // comes from HelperFunctions deals with turnover at 3.6..9.
    {
        HelperFunctions.errorsAdvance(numOfAttemptsT, ::threeErrors)
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
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
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun getGrade():Double
    {
        //println("$numOfCorrectE this is numOfCorrect from getGrade()")
        //myGradesT = (numOfErrorsT.toDouble()/sizeOfArray)*100
        myGradesT = (sizeOfArray- collectWrongAns.toDouble()) / sizeOfArray * 100
        return (myGradesT * 100).roundToInt() / 100.0
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun lessThanZero(allWrong:Double):Double {
        // println("${numOfErrorsE.toString()} this is numOfErrorsE from lessThanZero")
        if (allWrong < 0.0){
            myGradesT = 0.0
        }
        return myGradesT
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun markToPass(allWrong:Double):String {
        // println("${numOfErrorsE.toString()} this is numOfErrorsE from markToPass")
        myGradesT = if (allWrong >= 0.0){
            getGrade()
        } else{
            adjustedMark
        }
        return myGradesT.toString()
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun numOfMistakes()
    {
        if (numOfErrorsT == 0 && numToInc == sizeOfArray) {
            marksStringT = markToPass(getGrade())
            println(" $marksStringT this is marksString" )
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun cleanUpToContinue()
    {
        if (numToInc <= sizeOfArray) {
            userEntert.setText("")
            useHintT.alpha =1.toFloat()
            scrambledFieldt.alpha =1.toFloat()
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun reSetNumOfAttempts()
    {
        if (numOfAttemptsT == 3) {
            numOfAttemptsT = 0
            println("$numOfAttemptsT this is number of attempts called from reSetNumOfAttempts")
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
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
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun determineErrorsSnd():Int {
        soundId = (30..36).random()
        println("$soundId this is myRandom from determineErrorsSnd()")
        return soundId
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
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
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun getNextFruit(){
        numToInc +=1      // if spelling correct increments arrays
        numOfAttemptsT =0  // reset for next index number
        reSetFruit()
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun setErrorResults()//called in endOfArray
    {
        println("This function setErrorResults() should be called in endOfArray")
        adjustedMark = lessThanZero(getGrade()) //may not need below 0 not possible if everything works
        marksStringT = markToPass(getGrade())    //"marksString" used in "intent" in endOfArray()
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
     //setNumberOfIncorrect(collectedIncorrect,numToInc ,numOfErrorsT)
    private fun setNumberOfIncorrect(listOfGoofs:Array<Int>,index:Int,theGoof:Int) {
    //myNumToInc = listOfGoofs
    listOfGoofs[index]= theGoof
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
            numOfErrorsT = 1
        }
        return numOfErrorsT
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun modifyCollectedWrongs(numOfTries:Int) {
       // this to use in event of three errors in one index  ie. orange = (wrong)*3
         threeWrongs  += numOfTries
    }

    ////////////////////////////////End of Class////////////////////////////////////////////////////
    }//end of class


