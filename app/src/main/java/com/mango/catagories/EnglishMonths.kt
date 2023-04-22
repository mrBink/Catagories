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
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import java.util.*
import kotlin.math.roundToInt

class EnglishMonths : AppCompatActivity() {

    private var monthsPhotos=arrayOf(
        R.drawable.tmay, R.drawable.tthursday,R.drawable.tfriday,R.drawable.tnovember,
        R.drawable.t_saturday,R.drawable.toctober,R.drawable.tseptember,R.drawable.tapril,R.drawable.t_sunday,
        R.drawable.tjanuary,R.drawable.t_monday,R.drawable.tmarch,R.drawable.t_tuesday,R.drawable.tdecember,R.drawable.tjune,
        R.drawable.tfebruary,R.drawable.taugust,R.drawable.tjuly,R.drawable.t_calendar,R.drawable.t_wednesday)
    /////////////////////////////////////////////////////////////////////////////////////////////
    private var mSoundPool: SoundPool? = null
    private lateinit var sndBtn2: ImageButton
    private lateinit var seeMonths: ImageView        //images of fruit in View:
    private lateinit var goldBackGround: ImageView
    private lateinit var enterEng: EditText
    private lateinit var scrambledE: TextView   //scrambled text
    private lateinit var useHint: TextView           //instruction foe letter use
    private lateinit var dispThai: TextView      //user Thai Word
    private lateinit var wordInEArray:String        //reference
    private lateinit var marksString:String         //reference
    private lateinit var timer: CountDownTimer
    private var  wrongEng: ArrayList<String> = ArrayList()
    private var wrongThai: ArrayList<String> = ArrayList()
    private  var sizeOfArray = monthsPhotos.size
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
    private var dayseng01   = 8
    private var dayseng02   = 9
    private var dayseng03   = 10
    private var dayseng04   = 11
    private var dayseng05   = 12
    private var dayseng06   = 13
    private var dayseng07   = 14
    private var dayseng08   = 15
    private var dayseng09   = 16
    private var dayseng10   = 17
    private var dayseng11   = 18
    private var dayseng12   = 19
    private var dayseng13   = 20
    private var dayseng14   = 21
    private var dayseng15   = 22
    private var dayseng16   = 23
    private var dayseng17   = 24
    private var dayseng18   = 25
    private var dayseng19   = 26
    private var dayseng20   = 27


    private  var sndMonths = arrayListOf(
        dayseng01,dayseng02,dayseng03,dayseng04,dayseng05,
        dayseng06,dayseng07,dayseng08,dayseng09,dayseng10,
        dayseng11,dayseng12,dayseng13,dayseng14,dayseng15,
        dayseng16,dayseng17,dayseng18,dayseng19,dayseng20)

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
        setContentView(R.layout.activity_english_months)
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        val spb = SoundPool.Builder()
        spb.setMaxStreams(1)
            .setAudioAttributes(audioAttributes)
        mSoundPool=spb.build()

        noise1 = mSoundPool?.load(this, R.raw.domdomsnd, 1)!!
        noise2 = mSoundPool?.load(this, R.raw.fart, 1)!!
        noise3 = mSoundPool?.load(this, R.raw.yart, 1)!!
        noise4 = mSoundPool?.load(this, R.raw.errorsnd, 1)!!
        noise5 = mSoundPool?.load(this, R.raw.mistake, 1)!!
        noise6 = mSoundPool?.load(this, R.raw.ohhhh, 1)!!
        noise7 = mSoundPool?.load(this, R.raw.burp, 1)!!
        ////////////////////////////////////////////////////////////////////////////////////////////
        dayseng01  = mSoundPool?.load(this, R.raw.dayseng01, 1)!!
        dayseng02  = mSoundPool?.load(this, R.raw.dayseng02, 1)!!
        dayseng03  = mSoundPool?.load(this, R.raw.dayseng03, 1)!!
        dayseng04  = mSoundPool?.load(this, R.raw.dayseng04, 1)!!
        dayseng05  = mSoundPool?.load(this, R.raw.dayseng05, 1)!!
        dayseng06  = mSoundPool?.load(this, R.raw.dayseng06, 1)!!
        dayseng07  = mSoundPool?.load(this, R.raw.dayseng07, 1)!!
        dayseng08 = mSoundPool?.load(this, R.raw.dayseng08, 1)!!
        dayseng09  = mSoundPool?.load(this, R.raw.dayseng09, 1)!!
        dayseng10  = mSoundPool?.load(this, R.raw.dayseng10, 1)!!
        dayseng11  = mSoundPool?.load(this, R.raw.dayseng11, 1)!!
        dayseng12  = mSoundPool?.load(this, R.raw.dayseng12, 1)!!
        dayseng13  = mSoundPool?.load(this, R.raw.dayseng13, 1)!!
        dayseng14  = mSoundPool?.load(this, R.raw.dayseng14, 1)!!
        dayseng15  = mSoundPool?.load(this, R.raw.dayseng15, 1)!!
        dayseng16  = mSoundPool?.load(this, R.raw.dayseng16, 1)!!
        dayseng17  = mSoundPool?.load(this, R.raw.dayseng17, 1)!!
        dayseng18  = mSoundPool?.load(this, R.raw.dayseng18, 1)!!
        dayseng19  = mSoundPool?.load(this, R.raw.dayseng19, 1)!!
        dayseng20  = mSoundPool?.load(this, R.raw.dayseng20, 1)!!

        goldBackGround = findViewById(R.id.goldBackGround)
        seeMonths = findViewById(R.id.seeMonths)
        seeMonths.setImageResource(monthsPhotos[numToInc])
        dispThai = findViewById(R.id.showThaiWord)
        enterEng = findViewById(R.id.enterEng)
        scrambledE = findViewById(R.id.scrambledE)
        useHint = findViewById(R.id.useHint)
        sndBtn2 = findViewById(R.id.sndBtn2)
        sndBtn2.setOnClickListener {

            mSoundPool?.play(sndMonths[numToInc], 1.0f, 1.0f, 0, 0, 1.0f)
        }
        dispThai.append(myArrays.monthsTxtThai[numToInc])
        //this is renamed it is now dispThai...... I have refactored this
        scrambledE.append(myArrays.monthsTxtEngScram[numToInc])
        //this is renamed it is now  scrambledE........ I have refactored this
        scrambledE.alpha =0.toFloat()
        useHint.alpha =0.toFloat()
        wordInEArray = enterEng.toString()
        enterEng.setOnEditorActionListener { _, actionId, _ ->//activates "done keyboard"
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
    //////////////////////////////////////////////////////////////////////
    private fun checkWords(){
        if(enterEng.text.toString() == myArrays.monthsTxtEng[numToInc] && numToInc < sizeOfArray ){
            arrayIndex =findIndex(myArrays.monthsTxtEng, myArrays.monthsTxtEng[numToInc])
            getNextFruit()

        }
        else{
            numOfAttempts +=1
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
    private fun newIndividualEntryForErrors()
    {
        arrayIndex = findIndex(myArrays.monthsTxtEng, myArrays.monthsTxtEng[numToInc])
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
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    private fun individualErrorCount()    // this is called per array element
    {
        myGoofs = modifyNumOfErrorsE(numOfAttempts)
        setNumberOfIncorrect(collectedIncorrect,myGoofs)//
        collectWrongAns= accumulatedErrors(collectedIncorrect) // this returns all the wrong ans in entire
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////// ////////////////////////////////////////////////////////////////////////////////////////////////

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
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    private fun reSetFruit(){
        endOfArray()
        if(numToInc <=monthsPhotos.size-1) {
            seeMonths.setImageResource(monthsPhotos[numToInc])
        }
        dispThai.text = myArrays.monthsTxtThai[numToInc]
        with(scrambledE) { text = myArrays.monthsTxtEngScram[numToInc] }
        enterEng.setText("")
        scrambledE.alpha =0.toFloat()
        0.toFloat().also { useHint.alpha = it }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////
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
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    private fun reSetForThreeErrors(){
        //numToInc += 1
        incrementingLimit()
        endOfArray()
        if(numToInc <=monthsPhotos.size-1) {
            seeMonths.setImageResource(monthsPhotos[numToInc])
        }
        dispThai.text = myArrays.monthsTxtThai[numToInc]
        with(scrambledE) { text = myArrays.monthsTxtEngScram[numToInc] }
        enterEng.setText("")
        scrambledE.alpha =0.toFloat()
        0.toFloat().also { useHint.alpha = it }
        timer.cancel()
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    private fun threeErrors (){   //when errors = 3 - 6 - 9 etc
        useAWhen(determineErrorsSnd())// soundId = random number
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
        val aWord = myArrays.monthsTxtEng[numToInc]
        val result = wrongEng.contains(aWord)
        if(!result){
            wrongEng.add(aWord)
        }
        val aWordTh = myArrays.monthsTxtThai[numToInc]
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
    //////////////////////////////////////////////////////////////////////////////////////
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

            println(" this is numToInc $numToInc andthis is arrayIndex $arrayIndex from cleanUpToContinue")
            enterEng.setText("")
            useHint.alpha =1.toFloat()
            scrambledE.alpha =1.toFloat()
        }
    }
    ////////////////////////////////////////////////////
    private fun reSetNumOfAttempts() {
        if (numOfAttempts == 3) {
            numOfAttempts = 0
            println("$numOfAttempts this is number of attempts called from reSetNumOfAttempts")
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
                println("This is the time left: $millisUntilFinished")
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
        if (numToInc <= monthsPhotos.size -1)
        {
            numToInc += 1
        }
        println("This is numToInc.... $numToInc called from from incrementingLimit()")
    }
    ////////////////////////////////////////////////////////////////////////////////////////////

}//end of class
