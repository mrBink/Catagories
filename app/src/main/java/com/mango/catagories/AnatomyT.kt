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
import kotlin.math.roundToInt

class AnatomyT : AppCompatActivity() {
    private var anatomyE=arrayOf(
        R.drawable.ear,R.drawable.ethumb,R.drawable.head, R.drawable.efinger,R.drawable.toes,
        R.drawable.tongue,R.drawable.eneck, R.drawable.elips,R.drawable.nose,R.drawable.hand,
        R.drawable.teeth,R.drawable.legs, R.drawable.knee,R.drawable.eye, R.drawable.hair,
        R.drawable.feet,R.drawable.eyelash, R.drawable.eback,R.drawable.stomach,
        R.drawable.fingernail)
    ////////////////////////////////////////////////////////////////////////////////
    private var mSoundPool: SoundPool? = null
    private lateinit var sndBtn2: ImageButton
    private lateinit var bodyView: ImageView        //images of fruit in View:
    private lateinit var goldBackGround: ImageView
    private lateinit var userEntert:EditText
    private lateinit var scrambledFieldT: TextView   //scrambled text
    private lateinit var useHint: TextView
    private lateinit var demoEngWord: TextView      //user Thai Word
    private lateinit var wordInEArray:String        //reference
    private lateinit var marksString:String         //reference
    private lateinit var timer: CountDownTimer
    private var  wrongEng: ArrayList<String> = ArrayList()
    private var wrongThai: ArrayList<String> = ArrayList()
    private  var sizeOfArray = anatomyE.size
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
    private var tear        = 8
    private var tthumb     = 9
    private var thead       = 10
    private var tfinger    = 11
    private var ttoes       = 12
    private var ttongue     = 13
    private var tneck      = 14
    private var tlips       = 15
    private var tnose       = 16
    private var thand       = 17
    private var tteeth      = 18
    private var tleg       = 19
    private var tknees       = 20
    private var teye        = 21
    private var thair       = 22
    private var tfeet       = 23
    private var teyelash    = 24
    private var tback      = 25
    private var tstomach    = 26
    private var tfingernail   = 27


    private  var bodySnds = arrayListOf( tear,tthumb,thead ,tfinger,ttoes,ttongue,tneck,tlips,tnose,thand,tteeth,tleg,
        tknees,teye,thair,tfeet,teyelash,tback,tstomach,tfingernail)

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
        setContentView(R.layout.activity_anatomy_t)
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
        tear       = mSoundPool?.load(this, R.raw.tear, 1)!!
        tthumb     = mSoundPool?.load(this, R.raw.tthumb, 1)!!
        thead      = mSoundPool?.load(this, R.raw.thead , 1)!!
        tfinger    = mSoundPool?.load(this, R.raw.tfinger, 1)!!
        ttoes      = mSoundPool?.load(this, R.raw.ttoes, 1)!!
        ttongue    = mSoundPool?.load(this, R.raw.ttongue, 1)!!
        tneck      = mSoundPool?.load(this, R.raw.tneck, 1)!!
        tlips      = mSoundPool?.load(this, R.raw.tlips, 1)!!
        tnose      = mSoundPool?.load(this, R.raw.tnose, 1)!!
        thand      = mSoundPool?.load(this, R.raw.thand, 1)!!
        tteeth     = mSoundPool?.load(this, R.raw.tteeth, 1)!!
        tleg       = mSoundPool?.load(this, R.raw.tleg, 1)!!
        tknees      = mSoundPool?.load(this, R.raw.tknees, 1)!!
        teye       = mSoundPool?.load(this, R.raw.teye, 1)!!
        thair      = mSoundPool?.load(this, R.raw.thair, 1)!!
        tfeet      = mSoundPool?.load(this, R.raw.tfeet, 1)!!
        teyelash   = mSoundPool?.load(this, R.raw.teyelash, 1)!!
        tback      = mSoundPool?.load(this, R.raw.tback, 1)!!
        tstomach   = mSoundPool?.load(this, R.raw.tstomach, 1)!!
        tfingernail = mSoundPool?.load(this, R.raw.tfingernail, 1)!!

        goldBackGround = findViewById(R.id.goldBackGround)
        bodyView = findViewById(R.id.bodyView)
        bodyView.setImageResource(anatomyE[numToInc])
        demoEngWord = findViewById(R.id.demoEngWord)
        userEntert = findViewById(R.id.userEntert)
        scrambledFieldT = findViewById(R.id.scrambledFieldT)
        useHint = findViewById(R.id.useHint)
        sndBtn2 = findViewById(R.id.sndBtn2)
        sndBtn2.setOnClickListener {

            mSoundPool?.play(bodySnds[numToInc], 1.0f, 1.0f, 0, 0, 1.0f)
        }
        demoEngWord.append(myArrays.eAnatomy[numToInc])
        scrambledFieldT.append(myArrays.tScramBody[numToInc])
        scrambledFieldT.alpha =0.toFloat()
        useHint.alpha =0.toFloat()
        wordInEArray = userEntert.toString()
        userEntert.setOnEditorActionListener { _, actionId, _ ->//activates "done keyboard"
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                checkWords()
                hideSoftKeyboard()
                true
            } else false
        }
    }//end of constructor
    ////////////////////////////////////////////////////////////////////////////////////////////
    private fun findIndex(arr: Array<String>, item: String): Int  //returns array index
    {
        return arr.indexOf(item)
    }
    ////////////////////////////////////////////////////////////////////////////////////////////
    private fun checkWords(){
        if(userEntert.text.toString() == myArrays.tAnatomy[numToInc] && numToInc < sizeOfArray ){
            arrayIndex =findIndex(myArrays.tAnatomy, myArrays.tAnatomy[numToInc])
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
        arrayIndex = findIndex(myArrays.eAnatomy, myArrays.eAnatomy[numToInc])
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
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    private fun reSetFruit(){
        endOfArray()
        if(numToInc <=anatomyE.size-1) {
            bodyView.setImageResource(anatomyE[numToInc])
        }
        demoEngWord.text = myArrays.eAnatomy[numToInc]
        with(scrambledFieldT) { text = myArrays.tScramBody[numToInc] }
        userEntert.setText("")
        scrambledFieldT.alpha =0.toFloat()
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
        if(numToInc <=anatomyE.size-1) {
            bodyView.setImageResource(anatomyE[numToInc])
        }
        demoEngWord.text = myArrays.eAnatomy[numToInc]
        with(scrambledFieldT) { text = myArrays.tScramBody[numToInc] }
        userEntert.setText("")
        scrambledFieldT.alpha =0.toFloat()
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
        val aWordTh = myArrays.tAnatomy[numToInc]
        val result = wrongEng.contains(aWordTh)
        if(!result){
            wrongEng.add(aWordTh)
        }
        val aWord = myArrays.eAnatomy[numToInc]
        val result2 = wrongThai.contains(aWord)
        if(!result2){
            wrongThai.add(aWord)
        }

        //this may be goofy in marks page
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
            userEntert.setText("")
            useHint.alpha =1.toFloat()
            scrambledFieldT.alpha =1.toFloat()
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
    ////////////////////////////////////////////////////////////////////////////////////////////////
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
        if (numToInc <= anatomyE.size -1)
        {
            numToInc += 1
        }
        println("This is numToInc.... $numToInc called from from incrementingLimit()")
    }
    ////////////////////////////////////////////////////////////////////////////////////////////
}//end of class