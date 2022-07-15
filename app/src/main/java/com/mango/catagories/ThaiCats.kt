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

class ThaiCats : AppCompatActivity() {
    private var fruitPhotos=arrayOf(R.drawable.orange,R.drawable.apple/*,R.drawable.avocado,R.drawable.banana,
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
    private var numOfCorrectT:Int = 0
    private var arrayIndexT:Int = 0
    private var numOfAttemptsT:Int = 0    //num for number of attempts
    private var myGradesT:Double = 0.0
    private var adjustedMark:Double = 0.0//number of wrong answers "no repeats"
    private val myArrays = TheArrays()
    ////////////////////////////////////////
    /////////////added July 11///////////
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

    /////////////////////////////////////////


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowInsetsControllerCompat(window, window.decorView).apply {
            hide(WindowInsetsCompat.Type.statusBars())
        }
        setContentView(R.layout.activity_thai_cats)
        ////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////added July 11////////////////////
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
        //////////////////////////////////////////////////////
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

        ////////////////////////////////////////////////////////////////////////////////////////////////

        appleShot = findViewById(R.id.appleShot)
        appleShot.setImageResource(fruitPhotos[numToInc])
        dispThaiWord= findViewById(R.id.dispThaiWord)
        userEntert = findViewById(R.id.userEntert)
        scrambledFieldt = findViewById(R.id.scrambledFieldt)
        useHintT = findViewById(R.id.useHintT)
        sndBtn = findViewById(R.id.sndBtn)
        sndBtn.setOnClickListener {

            soundPool?.play(fruitSnds[numToInc], 1.0f, 1.0f, 0, 0, 1.0f)
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
        }
        else{
            numOfAttemptsT +=1
            determineErrorsSnd()
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
       numToInc += 1       // if spelling correct increments arrays
       numOfAttemptsT = 0  // reset for next index number
       reSetFruit()        // if spelling correct uses numToInc to reset next image
   }

////////////////////////////////////////////////////////////////////////////////////////////////

    private fun respondToErrors() {
        adjustedMark = lessThanZero(getGrade())   //numbers below 0 reset to 0.0% for your grade
        marksStringT = markToPass(getGrade())      //"marksString" used in "intent" in endOfArray()
        passPeram()
        reSetNumOfAttempts() // if 3 Attempts back to 0
        whenWrongAnswer()
        println(" $numOfAttemptsT this is number of attempts called from respondToErrors()...Get Fucking Fucked")
    }
    ///////////////////////////////////////////////////////////////////
    private fun whenWrongAnswer(){
        createArraysForWrongAnswers()             //creates arrays for wrong answers
        cleanUpToContinue()
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
        useAWhen(soundId)// soundId = random number
        Toast.makeText(this, "Playing sound. . . .", Toast.LENGTH_SHORT).show()
        listenForComplete()
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
    ///////////////////////////////////////////////////////////////////////////////////////////////
    private fun determineErrorsSnd():Int {
        soundId = (30..36).random()
        println("$soundId this is myRandom from determineErrorsSnd()")
        return soundId
    }
    /////////////////////////////////////////////////////////////////////////////////////////////
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

    ///////////////////////////////////////////////////////////////////////////////////////////////


}//end of class


