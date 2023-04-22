package com.mango.catagories

import android.app.Activity
import android.content.Intent
import android.media.AudioAttributes
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.StrictMode
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import kotlin.math.roundToInt

class ClimateWordsTH: AppCompatActivity() {
    private var climatePics=arrayOf(
        R.drawable.summer,R.drawable.winter,
        R.drawable.spring, R.drawable.autumn,
        R.drawable.dew,R.drawable.smog,
        R.drawable.overcast, R.drawable.fog,
        R.drawable.humid,R.drawable.rainbow,
        R.drawable.lightning,R.drawable.rain, R.drawable.drought,R.drawable.hot, R.drawable.cold,  R.drawable.windyday,R.drawable.thunderstorm,
        R.drawable.temperature,R.drawable.flood, R.drawable.umbrella)

    /////////////////////////////////////////////////////////////////////////
    private var mSoundPool: SoundPool? = null
    private lateinit var sndBtn2: ImageButton
    private lateinit var imagesClimateWords: ImageView  //images of climatepics in View:imagesClimateWords
    private lateinit var bgFruitPics: ImageView                //this is gold BG image
    private lateinit var userInputThai: EditText                 //switched from userInputThai
    private lateinit var scrambledFieldinThai: TextView       //scrambled text in eng
    private lateinit  var useHintinThai: TextView                //instruction for letter use
    private lateinit var dispEngWord: TextView              //actually is dispEngWordWord
    private lateinit var wordInEArray:String                   //reference
    private lateinit var marksString:String                     //reference
    private lateinit var timer: CountDownTimer
    private var  wrongEng: ArrayList<String> = ArrayList()
    private var wrongThai: ArrayList<String> = ArrayList()
    private  var sizeOfArray = climatePics.size
    private var numToInc:Int = 0         //num for incrementing array
    private var numOfErrorsE:Int = 0
    private var arrayIndex:Int = 0     //num for correct
    private var numOfAttempts:Int = 0    //num for number of attempts
    private var collectWrongAns:Int = 0 //num for tries
    private var myGoofs:Int = 0 //num for tries
    private var myGrades:Double = 0.0
    private var adjustedMark:Double = 0.0
    private val myArrays = TheArrays()
    private  var collectedIncorrect: MutableList<Int> = mutableListOf()
    private var noise1 = 1
    private var noise2 = 2
    private var noise3 = 3
    private var noise4 = 4
    private var noise5 = 5
    private var noise6 = 6
    private var noise7 = 7
    private var soundId  = 500
    ////////////////////////////////////////////////////////////////////////////////////////////
    private var climatethai_01   = 8
    private var climatethai_02   = 9
    private var climatethai_03   = 10
    private var climatethai_04   = 11
    private var climatethai_05   = 12
    private var climatethai_06   = 13
    private var climatethai_07   = 14
    private var climatethai_08   = 15
    private var climatethai_09   = 16
    private var climatethai_10   = 17
    private var climatethai_11   = 18
    private var climatethai_12   = 19
    private var climatethai_13   = 20
    private var climatethai_14   = 21
    private var climatethai_15   = 22
    private var climatethai_16   = 23
    private var climatethai_17   = 24
    private var climatethai_18   = 25
    private var climatethai_19   = 26
    private var climatethai_20   = 27
    private  var sndThai = arrayListOf(
        climatethai_01,climatethai_02,climatethai_03,climatethai_04,climatethai_05,
        climatethai_06,climatethai_07,climatethai_08,climatethai_09,climatethai_10,
        climatethai_11,climatethai_12,climatethai_13,climatethai_14,climatethai_15,
        climatethai_16,climatethai_17,climatethai_18,climatethai_19,climatethai_20)
    //////////////////////////////////////////////////////////////////////////////////////////

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StrictMode.setVmPolicy(
            StrictMode.VmPolicy.Builder(StrictMode.getVmPolicy())
                .detectLeakedClosableObjects()
                .build()
        )

        WindowInsetsControllerCompat(window, window.decorView).apply {
            hide(WindowInsetsCompat.Type.statusBars())
        }

        setContentView(R.layout.activity_climate_words_th)
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        val spb = SoundPool.Builder()
        spb.setMaxStreams(1)
            .setAudioAttributes(audioAttributes)
        mSoundPool = spb.build()

        noise1 = mSoundPool?.load(this, R.raw.domdomsnd, 1)!!
        noise2 = mSoundPool?.load(this, R.raw.fart, 1)!!
        noise3 = mSoundPool?.load(this, R.raw.yart, 1)!!
        noise4 = mSoundPool?.load(this, R.raw.errorsnd, 1)!!
        noise5 = mSoundPool?.load(this, R.raw.mistake, 1)!!
        noise6 = mSoundPool?.load(this, R.raw.ohhhh, 1)!!
        noise7 = mSoundPool?.load(this, R.raw.burp, 1)!!
        ////////////////////////////////////////////////////////////////////////////////////////////
        climatethai_01 = mSoundPool?.load(this, R.raw.climatethai_01, 1)!!
        climatethai_02 = mSoundPool?.load(this, R.raw.climatethai_02, 1)!!
        climatethai_03 = mSoundPool?.load(this, R.raw.climatethai_03, 1)!!
        climatethai_04 = mSoundPool?.load(this, R.raw.climatethai_04, 1)!!
        climatethai_05 = mSoundPool?.load(this, R.raw.climatethai_05, 1)!!
        climatethai_06 = mSoundPool?.load(this, R.raw.climatethai_06, 1)!!
        climatethai_07 = mSoundPool?.load(this, R.raw.climatethai_07, 1)!!
        climatethai_08 = mSoundPool?.load(this, R.raw.climatethai_08, 1)!!
        climatethai_09 = mSoundPool?.load(this, R.raw.climatethai_09, 1)!!
        climatethai_10 = mSoundPool?.load(this, R.raw.climatethai_10, 1)!!
        climatethai_11 = mSoundPool?.load(this, R.raw.climatethai_11, 1)!!
        climatethai_12 = mSoundPool?.load(this, R.raw.climatethai_12, 1)!!
        climatethai_13 = mSoundPool?.load(this, R.raw.climatethai_13, 1)!!
        climatethai_14 = mSoundPool?.load(this, R.raw.climatethai_14, 1)!!
        climatethai_15 = mSoundPool?.load(this, R.raw.climatethai_15, 1)!!
        climatethai_16 = mSoundPool?.load(this, R.raw.climatethai_16, 1)!!
        climatethai_17 = mSoundPool?.load(this, R.raw.climatethai_17, 1)!!
        climatethai_18 = mSoundPool?.load(this, R.raw.climatethai_18, 1)!!
        climatethai_19 = mSoundPool?.load(this, R.raw.climatethai_19, 1)!!
        climatethai_20 = mSoundPool?.load(this, R.raw.climatethai_20, 1)!!

        bgFruitPics = findViewById(R.id.bgFruitPics)
        imagesClimateWords = findViewById(R.id.imagesClimateWords)
        imagesClimateWords.setImageResource(climatePics[numToInc]) //fill image view

        dispEngWord = findViewById(R.id.dispEngWord)
        userInputThai = findViewById(R.id.userInputThai)
        scrambledFieldinThai = findViewById(R.id.scrambledFieldinThai)
        useHintinThai = findViewById(R.id.useHintinThai)
        sndBtn2 = findViewById(R.id.sndBtn2)
        sndBtn2.setOnClickListener {

            mSoundPool?.play(sndThai[numToInc], 1.0f, 1.0f, 0, 0, 1.0f)
        }
        dispEngWord.append(myArrays.engClimate[numToInc])
        scrambledFieldinThai.append(myArrays.climateThScram[numToInc])

        scrambledFieldinThai.alpha = 0.toFloat()
        useHintinThai.alpha = 0.toFloat()
        wordInEArray = userInputThai.toString()
        userInputThai.setOnEditorActionListener { _, actionId, _ ->//activates "done keyboard"
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                checkWords()
                hideSoftKeyboard()
                true
            } else false
        }
    }
    //end of constructor
    ////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////
    private fun findIndex(arr: Array<String>, item: String): Int  //returns array index
    {
        return arr.indexOf(item)
    }
    ////////////////////////////////////////////////////////
    private fun checkWords(){
        if(userInputThai.text.toString() == myArrays.climateTh[numToInc] && numToInc < sizeOfArray ){
            arrayIndex =findIndex(myArrays.climateTh, myArrays.climateTh[numToInc])
            getNextFruit()

        }
        else{
            numOfAttempts +=1
            newIndividualEntryForErrors()
            determineErrorsSnd()
            respondToErrors()
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////
    private fun Activity.hideSoftKeyboard() {
        currentFocus?.let {
            val inputMethodManager = ContextCompat.getSystemService(this, InputMethodManager::class.java)!!
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }
    ////////////////////////////////////////////////////////////////////////
    private fun newIndividualEntryForErrors()
    {
        arrayIndex = findIndex(myArrays.climateTh, myArrays.climateTh[numToInc])
        when (arrayIndex) {
            0 ->individualErrorCount()
            1 -> individualErrorCount()
            2 -> individualErrorCount()
            3 -> individualErrorCount()
            4 -> individualErrorCount()
            5 -> individualErrorCount()
            6 -> individualErrorCount()
            7 -> individualErrorCount()
            8 -> individualErrorCount()
            9 -> individualErrorCount()
            10-> individualErrorCount()
            11-> individualErrorCount()
            12-> individualErrorCount()
            13-> individualErrorCount()
            14-> individualErrorCount()
            15-> individualErrorCount()
            16-> individualErrorCount()
            17-> individualErrorCount()
            18-> individualErrorCount()
            19-> individualErrorCount()
            else -> println("I don't know anything about it")
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    private fun individualErrorCount()    // this is called per array element
    {
        myGoofs = modifyNumOfErrorsE(numOfAttempts)
        setNumberOfIncorrect(collectedIncorrect,myGoofs)//
        collectWrongAns= accumulatedErrors(collectedIncorrect) // this returns all the wrong ans in entire
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////// ////////////////
    private fun respondToErrors() {
        passPeram()
        reSetNumOfAttempts() // if 3 Attempts back to 0
        whenWrongAnswer()
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    private fun whenWrongAnswer(){
        createArraysForWrongAnswers()     //creates arrays for wrong answers
        cleanUpToContinue()
    }
    //////////////////////////////////////////////////////////////////////////////////////////

    private fun reSetFruit(){
        endOfArray()
        if(numToInc <=climatePics.size-1) {
            imagesClimateWords.setImageResource(climatePics[numToInc])
        }
        dispEngWord.text = myArrays.engClimate[numToInc]
        with(scrambledFieldinThai) { text = myArrays.climateThScram[numToInc] }
        userInputThai.setText("")
        scrambledFieldinThai.alpha =0.toFloat()
        0.toFloat().also {useHintinThai.alpha = it }
    }
    private fun endOfArray() {   // when array done pass intents and change activities "GradeForEnglish"
        if (numToInc == sizeOfArray) {
            setErrorResults()        // this should set stage for results()
            results(collectWrongAns)
            println("this is where you switch activities... from FruitCat")
            println("this $numToInc and this is $sizeOfArray from FruitCat")
            val intent = Intent(this, GradeForEnglish::class.java)
            intent.putExtra("key", wrongEng)
            intent.putExtra("key2", wrongThai)
            intent.putExtra("key3", marksString)
            intent.putExtra("key4", numOfErrorsE)
            startActivity(intent)
        }
    }
    ///////////////////////////////checked down from here for { ///////////////////////////////////////
    private fun reSetForThreeErrors(){
        //numToInc += 1
        incrementingLimit()
        endOfArray()
        if(numToInc <=climatePics.size-1) {
            imagesClimateWords.setImageResource(climatePics[numToInc])
        }
        dispEngWord.text = myArrays.engClimate[numToInc]
        with(scrambledFieldinThai) { text = myArrays.climateThScram[numToInc] }
        userInputThai.setText("")
        scrambledFieldinThai.alpha =0.toFloat()
        0.toFloat().also {useHintinThai.alpha = it }
        timer.cancel()
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    private fun threeErrors (){   //when errors = 3 - 6 - 9 etc
        useAWhen(determineErrorsSnd())// soundId = random number
        listenForComplete()
    }
    ////////////////////////////////////////////////////////////////
    private fun passPeram() // comes from HelperFunctions deals with turnover at 3.6..9.
    {
        HelperFunctions.errorsAdvance(numOfAttempts, ::threeErrors)
    }
    //////////////////////////////////////////////////////////////////////////////////////////
    private fun createArraysForWrongAnswers(){
        val aWord = myArrays.engClimate[numToInc]
        val result = wrongEng.contains(aWord)
        if(!result){
            wrongEng.add(aWord)
        }
        val aWordTh = myArrays.climateTh[numToInc]
        val result2 = wrongThai.contains(aWordTh)
        if(!result2){
            wrongThai.add(aWordTh)
        }
    }
    ///////////////////////////////////////////////////////////////////
    private fun getGrade(): Double {
        myGrades = (sizeOfArray - wrongEng.size.toDouble()) / sizeOfArray * 100

        return (myGrades * 100).roundToInt() / 100.0
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    private fun lessThanZero(allWrong:Double):Double {//changed from double
        if (allWrong < 0.0){
            myGrades = 0.0
        }
        return myGrades
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    private fun markToPass(allWrong:Double):String { //changed from double
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
        if (numOfErrorsE == 0 && numToInc == sizeOfArray-1) {
            marksString = markToPass(getGrade())
            println(" $marksString this is marksString" )
        }
    }
    //////////////////////////////////////////////////////////////////////////////////
    private fun cleanUpToContinue()
    {
        if (numToInc <= sizeOfArray-1) {

            //println(" this is numToInc $numToInc and this is arrayIndex $arrayIndex from cleanUpToContinue")
            userInputThai.setText("")
            useHintinThai.alpha =1.toFloat()
            scrambledFieldinThai.alpha =1.toFloat()
        }
    }
    ////////////////////////////////////////////////////
    private fun reSetNumOfAttempts() {
        if (numOfAttempts == 3) {
            numOfAttempts = 0
            //println("$numOfAttempts this is number of attempts called from reSetNumOfAttempts")
        }
    }
    //////////////////////////////////////////////////////
    private fun useAWhen(myInt: Int) {
        when (myInt) {
            30 -> mSoundPool?.play(noise1, 1.0f, 1.0f, 0, 0, 1.0f)
            31 -> mSoundPool?.play(noise2, 1.0f, 1.0f, 0, 0, 1.0f)
            32 -> mSoundPool?.play(noise3, 1.0f, 1.0f, 0, 0, 1.0f)
            33 -> mSoundPool?.play(noise4, 1.0f, 1.0f, 0, 0, 1.0f)
            34 -> mSoundPool?.play(noise5, 1.0f, 1.0f, 0, 0, 1.0f)
            35 -> mSoundPool?.play(noise6, 1.0f, 1.0f, 0, 0, 1.0f)
            36 -> mSoundPool?.play(noise7, 1.0f, 1.0f, 0, 0, 1.0f)
            else -> mSoundPool?.play(noise2, 1.0f, 1.0f, 0, 0, 1.0f)
        }
    }
    ////////////////////////////////////////////////////////////////////////////
    private fun determineErrorsSnd():Int {
        soundId = (30..36).random()
        return soundId
    }
    //////////////////////////////////////////////////////////////
    private fun listenForComplete() {
        timer = object : CountDownTimer(1250, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // println("This is the time left: $millisUntilFinished")
            }
            override fun onFinish() {
                reSetForThreeErrors()
                timer.cancel()
            }
        }
        timer.start()
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    private fun getNextFruit(){
        //numToInc +=1      // if spelling correct increments arrays
        incrementingLimit()  // if spelling correct increments arrays
        numOfAttempts =0  // reset for next index number
        reSetFruit()

    }
    ///////////////////////////////////////////////////////////////////////////////
    private fun setErrorResults()//called in endOfArray
    {
        //println("This function setErrorResults() should be called in endOfArray")
        adjustedMark = lessThanZero(getGrade()) //may not need below 0 not possible if everything works
        marksString = markToPass(getGrade())    //"marksString" used in "intent" in endOfArray()
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun results(allTheWrong:Int)//collectWrongAns
    {
        //println("This function results should be called in endOfArray")
        when (allTheWrong)
        {
            0 -> numOfMistakes()    //noErrors 100%
            1 -> setErrorResults() // Errors and less than 100%
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun setNumberOfIncorrect(listOfGoofs:MutableList<Int>,theGoof:Int) {
        listOfGoofs.add(theGoof)
        println("{${listOfGoofs[0]}} -> listOfGoofs from setNumberOfIncorrect")
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //collectedIncorrect is the listOfGoofs parameter
    private fun accumulatedErrors(listOfGoofs: MutableList<Int>):Int {
        var gatheredWrong = 0
        for (item in listOfGoofs)
        {
            gatheredWrong+= item
        }
        return gatheredWrong
    }
    //////////////////////////////////////////////////////////////////////
    private fun modifyNumOfErrorsE(numOfTries:Int):Int {
        if (numOfTries == 1) {
            numOfErrorsE = 1
        }
        return numOfErrorsE
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun incrementingLimit() {
        //if (numToInc <= anatomyE.size -1)
        if (numToInc <= climatePics.size -1)
        {
            numToInc += 1
        }
        // println("This is numToInc.... $numToInc called from from incrementingLimit()")
    }
    ////////////////////////////////////////////////////////////////////////////////////////////

}//end of Class