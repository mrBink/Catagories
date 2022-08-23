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

class AnatomyE : AppCompatActivity() {
    private var anatomyT=arrayOf(
        R.drawable.ear,R.drawable.tthumb,R.drawable.head, R.drawable.tfinger,R.drawable.toes,
        R.drawable.tongue,R.drawable.tneck, R.drawable.lips,R.drawable.nose,R.drawable.hand,
        R.drawable.teeth,R.drawable.legs, R.drawable.knee,R.drawable.eye, R.drawable.hair,
        R.drawable.feet,R.drawable.eyelash, R.drawable.tback,R.drawable.stomach,
        R.drawable.fingernail)
    /////////////////////////////////////////////////////////////////////////////////////////////
    private var mSoundPool: SoundPool? = null
    private lateinit var sndBtn2: ImageButton
    private lateinit var bodyView: ImageView        //images of fruit in View:
    private lateinit var goldBackGround: ImageView
    private lateinit var enterEng: EditText
    private lateinit var scrambledFieldE: TextView   //scrambled text
    private lateinit var useHint: TextView           //instruction foe letter use
    private lateinit var dispEnglishWord: TextView      //user Thai Word
    private lateinit var wordInEArray:String        //reference
    private lateinit var marksString:String         //reference
    private lateinit var timer: CountDownTimer
    private var  wrongEng: ArrayList<String> = ArrayList()
    private var wrongThai: ArrayList<String> = ArrayList()
    private  var sizeOfArray = anatomyT.size
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
    private var ear        = 8
    private var thumb     = 9
    private var head       = 10
    private var finger    = 11
    private var toes       = 12
    private var tongue     = 13
    private var neck      = 14
    private var lips       = 15
    private var nose       = 16
    private var hand       = 17
    private var teeth      = 18
    private var legs       = 19
    private var knee       = 20
    private var eye        = 21
    private var hair       = 22
    private var feet       = 23
    private var eyelash    = 24
    private var tback      = 25
    private var stomach    = 26
    private var fingernail   = 27


    private  var bodySnds = arrayListOf( ear,thumb,head ,finger,toes,tongue,neck,lips,nose,hand,teeth,legs,
        knee,eye,hair,feet,eyelash,tback,stomach,fingernail)

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
        setContentView(R.layout.activity_anatomy_e)
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
        ear       = mSoundPool?.load(this, R.raw.ear, 1)!!
        thumb     = mSoundPool?.load(this, R.raw.thumb, 1)!!
        head      = mSoundPool?.load(this, R.raw.head , 1)!!
        finger    = mSoundPool?.load(this, R.raw.finger, 1)!!
        toes      = mSoundPool?.load(this, R.raw.toes, 1)!!
        tongue    = mSoundPool?.load(this, R.raw.tongue, 1)!!
        neck      = mSoundPool?.load(this, R.raw.neck, 1)!!
        lips      = mSoundPool?.load(this, R.raw.lips, 1)!!
        nose      = mSoundPool?.load(this, R.raw.nose, 1)!!
        hand      = mSoundPool?.load(this, R.raw.hand, 1)!!
        teeth     = mSoundPool?.load(this, R.raw.teeth, 1)!!
        legs       = mSoundPool?.load(this, R.raw.legs, 1)!!
        knee      = mSoundPool?.load(this, R.raw.knee, 1)!!
        eye       = mSoundPool?.load(this, R.raw.eye, 1)!!
        hair      = mSoundPool?.load(this, R.raw.hair, 1)!!
        feet      = mSoundPool?.load(this, R.raw.feet, 1)!!
        eyelash   = mSoundPool?.load(this, R.raw.eyelash, 1)!!
        tback      = mSoundPool?.load(this, R.raw.tback, 1)!!
        stomach   = mSoundPool?.load(this, R.raw.stomach, 1)!!
        fingernail = mSoundPool?.load(this, R.raw.fingernail, 1)!!

        goldBackGround = findViewById(R.id.goldBackGround)
        bodyView = findViewById(R.id.bodyView)
        bodyView.setImageResource(anatomyT[numToInc])
        dispEnglishWord = findViewById(R.id.dispEnglishWord)
        enterEng = findViewById(R.id.enterEng)
        scrambledFieldE = findViewById(R.id.scrambledFieldT)
        useHint = findViewById(R.id.useHint)
        sndBtn2 = findViewById(R.id.sndBtn2)
        sndBtn2.setOnClickListener {

            mSoundPool?.play(bodySnds[numToInc], 1.0f, 1.0f, 0, 0, 1.0f)
        }
        dispEnglishWord.append(myArrays.tAnatomy[numToInc])
        scrambledFieldE.append(myArrays.tScramBody[numToInc])
        scrambledFieldE.alpha =0.toFloat()
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
    ////////////////////////////////////////////////////////////////////////////////////////////
    private fun checkWords(){
        if(enterEng.text.toString() == myArrays.eAnatomy[numToInc] && numToInc < sizeOfArray ){
            arrayIndex =findIndex(myArrays.eAnatomy, myArrays.eAnatomy[numToInc])
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
        if(numToInc <=anatomyT.size-1) {
            bodyView.setImageResource(anatomyT[numToInc])
        }
        dispEnglishWord.text = myArrays.tAnatomy[numToInc]
        with(scrambledFieldE) { text = myArrays.tScramBody[numToInc] }
        enterEng.setText("")
        scrambledFieldE.alpha =0.toFloat()
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
        if(numToInc <=anatomyT.size-1) {
            bodyView.setImageResource(anatomyT[numToInc])
        }
        dispEnglishWord.text = myArrays.tAnatomy[numToInc]
        with(scrambledFieldE) { text = myArrays.tScramBody[numToInc] }
        enterEng.setText("")
        scrambledFieldE.alpha =0.toFloat()
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
        val aWord = myArrays.eAnatomy[numToInc]
        val result = wrongEng.contains(aWord)
        if(!result){
            wrongEng.add(aWord)
        }
        val aWordTh = myArrays.tAnatomy[numToInc]
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
            scrambledFieldE.alpha =1.toFloat()
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
        if (numToInc <= anatomyT.size -1)
        {
            numToInc += 1
        }
        println("This is numToInc.... $numToInc called from from incrementingLimit()")
    }
    ////////////////////////////////////////////////////////////////////////////////////////////

    }//end of class
