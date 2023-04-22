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

/*
the start Btn txt is english...user types in eng
transengBtn >>>>Fruit_class >>>>>>>>TransportEnglish_class
TransportEnglish_class >>  user types in eng
 */
class TransportEnglish : AppCompatActivity() {
    private var transportPics=arrayOf(
        R.drawable.tourbus,R.drawable.campervan,
        R.drawable.carferry, R.drawable.citybus,
        R.drawable.familycar,R.drawable.helicopter,
        R.drawable.longtailboat, R.drawable.trailerhome,
        R.drawable.motorcycle,R.drawable.bicycle,
        R.drawable.pickuptruck,R.drawable.scooter, R.drawable.skytrain,R.drawable.sportscar, R.drawable.subway,  R.drawable.taxicabs,R.drawable.truck,
        R.drawable.tuktuk,R.drawable.ambulance, R.drawable.wheelchair,R.drawable.passengerplane)
    /////////////////////////////////////////////////////////////////////////
    private var mSoundPool: SoundPool? = null
    private lateinit var sndBtn2: ImageButton
    private lateinit var imagesTransportWords: ImageView  //images of transportPics in View:imagesTransportWords
    private lateinit var bgFruitPics: ImageView                //this is gold BG image
    private lateinit var userInputEng: EditText                 //switched from userInputEng
    private lateinit var scrambledFieldinEng: TextView       //scrambled text in eng
    private lateinit  var useHintinThai: TextView                //instruction for letter use
    private lateinit var dispThaiHereWord: TextView              //actually is dispThaiHereWordWord
    private lateinit var wordInEArray:String                   //reference
    private lateinit var marksString:String                     //reference
    private lateinit var timer: CountDownTimer
    private var  wrongEng: ArrayList<String> = ArrayList()
    private var wrongThai: ArrayList<String> = ArrayList()
    private  var sizeOfArray = transportPics.size
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
    private var transporteng_01   = 8
    private var transporteng_02   = 9
    private var transporteng_03   = 10
    private var transporteng_04   = 11
    private var transporteng_05   = 12
    private var transporteng_06   = 13
    private var transporteng_07   = 14
    private var transporteng_08   = 15
    private var transporteng_09   = 16
    private var transporteng_10   = 17
    private var transporteng_11   = 18
    private var transporteng_12   = 19
    private var transporteng_13   = 20
    private var transporteng_14   = 21
    private var transporteng_15   = 22
    private var transporteng_16   = 23
    private var transporteng_17   = 24
    private var transporteng_18   = 25
    private var transporteng_19   = 26
    private var transporteng_20   = 27
    private var transporteng_21   = 28
    private  var sndThai = arrayListOf(
        transporteng_01,transporteng_02,transporteng_03,transporteng_04,transporteng_05,
        transporteng_06,transporteng_07,transporteng_08,transporteng_09,transporteng_10,
        transporteng_11,transporteng_12,transporteng_13,transporteng_14,transporteng_15,
        transporteng_16,transporteng_17,transporteng_18,transporteng_19,transporteng_20,
        transporteng_21)
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
        setContentView(R.layout.activity_transport_english)
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
        ////////////////////////////////////////////////
        transporteng_01 = mSoundPool?.load(this, R.raw.transporteng_01, 1)!!
        transporteng_02 = mSoundPool?.load(this, R.raw.transporteng_02, 1)!!
        transporteng_03 = mSoundPool?.load(this, R.raw.transporteng_03, 1)!!
        transporteng_04 = mSoundPool?.load(this, R.raw.transporteng_04, 1)!!
        transporteng_05 = mSoundPool?.load(this, R.raw.transporteng_05, 1)!!
        transporteng_06 = mSoundPool?.load(this, R.raw.transporteng_06, 1)!!
        transporteng_07 = mSoundPool?.load(this, R.raw.transporteng_07, 1)!!
        transporteng_08 = mSoundPool?.load(this, R.raw.transporteng_08, 1)!!
        transporteng_09 = mSoundPool?.load(this, R.raw.transporteng_09, 1)!!
        transporteng_10 = mSoundPool?.load(this, R.raw.transporteng_10, 1)!!
        transporteng_11 = mSoundPool?.load(this, R.raw.transporteng_11, 1)!!
        transporteng_12 = mSoundPool?.load(this, R.raw.transporteng_12, 1)!!
        transporteng_13 = mSoundPool?.load(this, R.raw.transporteng_13, 1)!!
        transporteng_14 = mSoundPool?.load(this, R.raw.transporteng_14, 1)!!
        transporteng_15 = mSoundPool?.load(this, R.raw.transporteng_15, 1)!!
        transporteng_16 = mSoundPool?.load(this, R.raw.transporteng_16, 1)!!
        transporteng_17 = mSoundPool?.load(this, R.raw.transporteng_17, 1)!!
        transporteng_18 = mSoundPool?.load(this, R.raw.transporteng_18, 1)!!
        transporteng_19 = mSoundPool?.load(this, R.raw.transporteng_19, 1)!!
        transporteng_20 = mSoundPool?.load(this, R.raw.transporteng_20, 1)!!
        transporteng_21 = mSoundPool?.load(this, R.raw.transporteng_21, 1)!!

        bgFruitPics = findViewById(R.id.bgFruitPics)
        imagesTransportWords = findViewById(R.id.imagesTransportWords)
        imagesTransportWords.setImageResource(transportPics[numToInc]) //fill image view

        dispThaiHereWord = findViewById(R.id.dispThaiHereWord)
        userInputEng = findViewById(R.id.userInputEng)
        scrambledFieldinEng = findViewById(R.id.scrambledFieldinEng)
        useHintinThai = findViewById(R.id.useHintinThai)
        sndBtn2 = findViewById(R.id.sndBtn2)
        sndBtn2.setOnClickListener {

            mSoundPool?.play(sndThai[numToInc], 1.0f, 1.0f, 0, 0, 1.0f)
        }
        dispThaiHereWord.append(myArrays.transportTh[numToInc])
        scrambledFieldinEng.append(myArrays.transEngScram[numToInc])

        scrambledFieldinEng.alpha = 0.toFloat()
        useHintinThai.alpha = 0.toFloat()
        wordInEArray = userInputEng.toString()
        userInputEng.setOnEditorActionListener { _, actionId, _ ->//activates "done keyboard"
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                checkWords()
                hideSoftKeyboard()
                true
            } else false
        }
    }//end of constructor
    ////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////
    private fun findIndex(arr: Array<String>, item: String): Int  //returns array index
    {
        return arr.indexOf(item)
    }
    ////////////////////////////////////////////////////////
    private fun checkWords(){
        if(userInputEng.text.toString() == myArrays.transEng[numToInc] && numToInc < sizeOfArray ){
            arrayIndex =findIndex(myArrays.transEng, myArrays.transEng[numToInc])
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
        arrayIndex = findIndex(myArrays.transEng, myArrays.transEng[numToInc])
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
            20-> individualErrorCount()
            21-> individualErrorCount()
            22-> individualErrorCount()
            23-> individualErrorCount()
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
        if(numToInc <=transportPics.size-1) {
            imagesTransportWords.setImageResource(transportPics[numToInc])
        }
        dispThaiHereWord.text = myArrays.transportTh[numToInc]
        with(scrambledFieldinEng) { text = myArrays.transEngScram[numToInc] }
        userInputEng.setText("")
        scrambledFieldinEng.alpha =0.toFloat()
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
        if(numToInc <=transportPics.size-1) {
            imagesTransportWords.setImageResource(transportPics[numToInc])
        }
        dispThaiHereWord.text = myArrays.transportTh[numToInc]
        with(scrambledFieldinEng) { text = myArrays.transEngScram[numToInc] }
        userInputEng.setText("")
        scrambledFieldinEng.alpha =0.toFloat()
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
        val aWord = myArrays.transEng[numToInc]
        val result = wrongEng.contains(aWord)
        if(!result){
            wrongEng.add(aWord)
        }
        val aWordTh = myArrays.transportTh[numToInc]
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
            userInputEng.setText("")
            useHintinThai.alpha =1.toFloat()
            scrambledFieldinEng.alpha =1.toFloat()
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
        if (numToInc <= transportPics.size -1)
        {
            numToInc += 1
        }
        // println("This is numToInc.... $numToInc called from from incrementingLimit()")
    }
    ////////////////////////////////////////////////////////////////////////////////////////////


}//end of class