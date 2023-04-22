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

class MoneyThai : AppCompatActivity() {
    private var cash_Eng = arrayOf(
        R.drawable.atm,
        R.drawable.banknote,
        R.drawable.bill,
        R.drawable.chequebook,
        R.drawable.creditcard,
        R.drawable.debitcard,
        R.drawable.exchangerate,
        R.drawable.gamble,
        R.drawable.gold,
        R.drawable.lottery,
        R.drawable.onlineshopping,
        R.drawable.payday,
        R.drawable.receipt,
        R.drawable.change,
        R.drawable.savings,
        R.drawable.wallet,
        R.drawable.withdraw,
        R.drawable.bankbook,
        R.drawable.transfermoney,
        R.drawable.taxes,
        R.drawable.invest,
        R.drawable.stockmarket,
        R.drawable.qrcode,
    )

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private var mSoundPool: SoundPool? = null
    private lateinit var sndBtn2: ImageButton //sound btn
    private lateinit var bodyView: ImageView    //images from image array
    private lateinit var goldBackGround: ImageView//bg_whole_screenImage
    private lateinit var enterEng: EditText               //switched from enterEng
    private lateinit var scrambledFieldT: TextView            //scrambled text
    private lateinit var useHint: TextView                  //instruction for letter use
    private lateinit var dispThaiHere: TextView  //dispThaiHere
    private lateinit var wordInEArray: String              //reference
    private lateinit var marksString: String                //reference
    private lateinit var timer: CountDownTimer
    private var wrongEng: ArrayList<String> = ArrayList()
    private var wrongThai: ArrayList<String> = ArrayList()
    private var sizeOfArray = cash_Eng.size
    private var numToInc: Int = 0         //num for incrementing array
    private var numOfErrorsE: Int = 0
    private var arrayIndex: Int = 0     //num for correct
    private var numOfAttempts: Int = 0    //num for number of attempts
    private var collectWrongAns: Int = 0 //num for tries
    private var myGoofs: Int = 0 //num for tries
    private var myGrades: Double = 0.0
    private var adjustedMark: Double = 0.0
    private val myArrays = TheArrays()
    private var collectedIncorrect: MutableList<Int> = mutableListOf()
    private var noise1 = 1
    private var noise2 = 2
    private var noise3 = 3
    private var noise4 = 4
    private var noise5 = 5
    private var noise6 = 6
    private var noise7 = 7
    private var soundId = 500

    ////////////////////////////////////////////////////////////////////////////////////////////
    private var cash_01 = 8
    private var cash_02 = 9
    private var cash_03 = 10
    private var cash_04 = 11
    private var cash_05 = 12
    private var cash_06 = 13
    private var cash_07 = 14
    private var cash_08 = 15
    private var cash_09 = 16
    private var cash_10 = 17
    private var cash_11 = 18
    private var cash_12 = 19
    private var cash_13 = 20
    private var cash_14 = 21
    private var cash_15 = 22
    private var cash_16 = 23
    private var cash_17 = 24
    private var cash_18 = 25
    private var cash_19 = 26
    private var cash_20 = 27
    private var cash_21 = 28
    private var cash_22 = 29
    private var cash_23 = 30
    private var sndEnglish = arrayListOf(
        cash_01, cash_02, cash_03, cash_04, cash_05,
        cash_06, cash_07, cash_08, cash_09, cash_10,
        cash_11, cash_12, cash_13, cash_14, cash_15,
        cash_16, cash_17, cash_18, cash_19, cash_20, cash_21,
        cash_22, cash_23)

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

        setContentView(R.layout.activity_money_thai)
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
        cash_01 = mSoundPool?.load(this, R.raw.cash_01, 1)!!
        cash_02 = mSoundPool?.load(this, R.raw.cash_02, 1)!!
        cash_03 = mSoundPool?.load(this, R.raw.cash_03, 1)!!
        cash_04 = mSoundPool?.load(this, R.raw.cash_04, 1)!!
        cash_05 = mSoundPool?.load(this, R.raw.cash_05, 1)!!
        cash_06 = mSoundPool?.load(this, R.raw.cash_06, 1)!!
        cash_07 = mSoundPool?.load(this, R.raw.cash_07, 1)!!
        cash_08 = mSoundPool?.load(this, R.raw.cash_08, 1)!!
        cash_09 = mSoundPool?.load(this, R.raw.cash_09, 1)!!
        cash_10 = mSoundPool?.load(this, R.raw.cash_10, 1)!!
        cash_11 = mSoundPool?.load(this, R.raw.cash_11, 1)!!
        cash_12 = mSoundPool?.load(this, R.raw.cash_12, 1)!!
        cash_13 = mSoundPool?.load(this, R.raw.cash_13, 1)!!
        cash_14 = mSoundPool?.load(this, R.raw.cash_14, 1)!!
        cash_15 = mSoundPool?.load(this, R.raw.cash_15, 1)!!
        cash_16 = mSoundPool?.load(this, R.raw.cash_16, 1)!!
        cash_17 = mSoundPool?.load(this, R.raw.cash_17, 1)!!
        cash_18 = mSoundPool?.load(this, R.raw.cash_18, 1)!!
        cash_19 = mSoundPool?.load(this, R.raw.cash_19, 1)!!
        cash_20 = mSoundPool?.load(this, R.raw.cash_20, 1)!!
        cash_21 = mSoundPool?.load(this, R.raw.cash_21, 1)!!
        cash_22 = mSoundPool?.load(this, R.raw.cash_22, 1)!!
        cash_23 = mSoundPool?.load(this, R.raw.cash_23, 1)!!


        goldBackGround = findViewById(R.id.goldBackGround)
        bodyView = findViewById(R.id.bodyView)
        bodyView.setImageResource(cash_Eng[numToInc]) //fill image view

        dispThaiHere = findViewById(R.id.showThaiWord)
        enterEng = findViewById(R.id.enterEng)
        scrambledFieldT = findViewById(R.id.scrambledFieldT)//should be scrambledFieldE
        useHint = findViewById(R.id.useHint)
        sndBtn2 = findViewById(R.id.sndBtn2)
        sndBtn2.setOnClickListener {

            mSoundPool?.play(sndEnglish[numToInc], 1.0f, 1.0f, 0, 0, 1.0f)
        }
        dispThaiHere.append(myArrays.thaiCash[numToInc])
        scrambledFieldT.append(myArrays.engCashScram[numToInc])
        scrambledFieldT.alpha = 0.toFloat()
        useHint.alpha = 0.toFloat()
        wordInEArray = enterEng.toString()
        enterEng.setOnEditorActionListener { _, actionId, _ ->//activates "done keyboard"
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                checkWords()
                hideSoftKeyboard()
                true
            } else false
        }
    }
    //end of constructor
    ////////////////////////////////////////////////////////////////////////
    private fun findIndex(arr: Array<String>, item: String): Int  //returns array index
    {
        return arr.indexOf(item)
    }
    ////////////////////////////////////////////////////////
    private fun checkWords(){
        if(enterEng.text.toString() == myArrays.engCash[numToInc] && numToInc < sizeOfArray ){
            arrayIndex =findIndex(myArrays.engCash, myArrays.engCash[numToInc])
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
        arrayIndex = findIndex(myArrays.thaiCash, myArrays.thaiCash[numToInc])
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
        if(numToInc <=cash_Eng.size-1) {
            bodyView .setImageResource(cash_Eng[numToInc])
        }
        dispThaiHere.text = myArrays.thaiCash[numToInc]
        with(scrambledFieldT) { text = myArrays.engCashScram[numToInc] }
        enterEng.setText("")
        scrambledFieldT.alpha =0.toFloat()
        0.toFloat().also {useHint.alpha = it }
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
        if(numToInc <=cash_Eng.size-1) {
            bodyView .setImageResource(cash_Eng[numToInc])
        }
        dispThaiHere.text = myArrays.thaiCash[numToInc]
        with(scrambledFieldT) { text = myArrays.engCashScram[numToInc] }
        enterEng.setText("")
        scrambledFieldT.alpha =0.toFloat()
        0.toFloat().also {useHint.alpha = it }
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
        val aWord = myArrays.engCash[numToInc]
        val result = wrongEng.contains(aWord)
        if(!result){
            wrongEng.add(aWord)
        }
        val aWordTh = myArrays.thaiCash[numToInc]
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
            enterEng.setText("")
            useHint.alpha =1.toFloat()
            scrambledFieldT.alpha =1.toFloat()
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
        if (numToInc <= cash_Eng.size -1)
        {
            numToInc += 1
        }
        // println("This is numToInc.... $numToInc called from from incrementingLimit()")
    }
    ////////////////////////////////////////////////////////////////////////////////////////////

}//end of Class



