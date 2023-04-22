package com.mango.catagories
import android.app.Activity
import android.content.Intent
import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import android.os.CountDownTimer
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import kotlin.math.roundToInt


class ThaiNumbers : AppCompatActivity() {

    private var arabicNumarals=arrayOf(
        R.drawable.e1400, R.drawable.e4, R.drawable.eeleven, R.drawable.e1000000,
        R.drawable.e2, R.drawable.e624, R.drawable.e1000, R.drawable.e8, R.drawable.e7, R.drawable.eten,
        R.drawable.e100000, R.drawable.e9, R.drawable.e10000, R.drawable.e54, R.drawable.ethreehundredthirtythree,
        R.drawable.e5, R.drawable.eseventyonethousand, R.drawable.ezero, R.drawable.e3, R.drawable.e6)

    /////////////////////////////////////////////////////////////////////


    private var mSoundPool: SoundPool? = null
    private lateinit var sndBtn2:ImageButton//bg_fruitPics useHintT
    private lateinit var thaiNumbers:ImageView
    private lateinit var thaiNumPics:ImageView        //images of fruit in View:
    private lateinit var userEnterEng:EditText        //user editText  userEntert
    private lateinit var scrambledFieldt:TextView   //scrambled text
    private lateinit var useHintT:TextView           //instruction foe letter use
    private lateinit var dispThaiWord:TextView      //user Thai Word
    private lateinit var wordInEArray:String        //reference
    private lateinit var marksString:String         //reference
    private lateinit var timer: CountDownTimer
    private var  wrongEng: ArrayList<String> = ArrayList()
    private var wrongThai: ArrayList<String> = ArrayList()
    private  var sizeOfArray = arabicNumarals.size
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
    private var t1402    = 8
    private var t4       = 9
    private var t11      = 10
    private var t1000000 = 11
    private var t2       = 12
    private var t624     = 13
    private var t1000    = 14
    private var t8       = 15
    private var t7       = 16
    private var t10      = 17
    private var t100000  = 18
    private var t9       = 19
    private var t10000   = 20
    private var t54      = 21
    private var t333     = 22
    private var t5       = 23
    private var t71000   = 24
    private var t0       = 25
    private var t3       = 26
    private var t6       = 27


    private  var thaiNumSnd = arrayListOf( t1402,t4,t11,t1000000, t2,t624,t1000,t8,
        t7,t10,t100000,t9, t10000,t54,t333,t5, t71000,t0,t3,t6)
    ////////////////////////////////////////////////////////////////////////////////////////////////
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StrictMode.setVmPolicy(
            VmPolicy.Builder(StrictMode.getVmPolicy())
                .detectLeakedClosableObjects()
                .build()
        )

        WindowInsetsControllerCompat(window, window.decorView).apply {
            hide(WindowInsetsCompat.Type.statusBars())
        }


        setContentView(R.layout.activity_thai_numbers)
        //println("this is ${fruitPhotos.size} fruitPhotos.size" )
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
        t1402    = mSoundPool?.load(this, R.raw.t1400, 1)!!
        t4       = mSoundPool?.load(this, R.raw.t4, 1)!!
        t11      = mSoundPool?.load(this, R.raw.t11, 1)!!
        t1000000 = mSoundPool?.load(this, R.raw.t1000000, 1)!!
        t2       = mSoundPool?.load(this, R.raw.t2, 1)!!
        t624     = mSoundPool?.load(this, R.raw.t624, 1)!!
        t1000    = mSoundPool?.load(this, R.raw.t1000, 1)!!
        t8       = mSoundPool?.load(this, R.raw.t8, 1)!!
        t7       = mSoundPool?.load(this, R.raw. t7, 1)!!
        t10      = mSoundPool?.load(this, R.raw.t10, 1)!!
        t100000  = mSoundPool?.load(this, R.raw.t100000, 1)!!
        t9       = mSoundPool?.load(this, R.raw.t9, 1)!!
        t10000   = mSoundPool?.load(this, R.raw.t10000, 1)!!
        t54      = mSoundPool?.load(this, R.raw.t54, 1)!!
        t333     = mSoundPool?.load(this, R.raw.t333, 1)!!
        t5       = mSoundPool?.load(this, R.raw.t5, 1)!!
        t71000   = mSoundPool?.load(this, R.raw.t71000, 1)!!
        t0       = mSoundPool?.load(this, R.raw.t0, 1)!!
        t3       = mSoundPool?.load(this, R.raw.t3, 1)!!
        t6       = mSoundPool?.load(this, R.raw.t6, 1)!!
        thaiNumbers = findViewById(R.id.thaiNumbers)
        thaiNumPics = findViewById(R.id.thaiNumPics)
        thaiNumPics.setImageResource(arabicNumarals[numToInc])
        dispThaiWord = findViewById(R.id.dispEngWord)
        userEnterEng = findViewById(R.id.userEnterEng)
        scrambledFieldt = findViewById(R.id.scrambledFieldt)
        useHintT = findViewById(R.id.useHintT)
        sndBtn2 = findViewById(R.id.sndBtn2)
        sndBtn2.setOnClickListener {

            mSoundPool?.play(thaiNumSnd[numToInc], 1.0f, 1.0f, 0, 0, 1.0f)
        }
        dispThaiWord.append(myArrays.arabicNumerals[numToInc])
        scrambledFieldt.append(myArrays.scramThaiNums[numToInc])
        scrambledFieldt.alpha =0.toFloat()
        useHintT.alpha =0.toFloat()
        wordInEArray = userEnterEng.toString()
        userEnterEng.setOnEditorActionListener { _, actionId, _ ->//activates "done keyboard"
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                println("this is ${arabicNumarals.size} arabicNumarals.size" )
                checkWords()
                //tryCatchBlock()
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
        if(userEnterEng.text.toString() == myArrays.thaiNums[numToInc] && numToInc < sizeOfArray ){
            arrayIndex =findIndex(myArrays.thaiNums, myArrays.thaiNums[numToInc])
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
        arrayIndex = findIndex(myArrays.thaiNums, myArrays.thaiNums[numToInc])
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
        println(" this is $myGoofs  myGoofs from modifyNumOfErrorsE")
        println(" this is ${myArrays.thaiNums[numToInc]} efruitTxt[numToInc] from individualErrorCount")
        setNumberOfIncorrect(collectedIncorrect,myGoofs)//
        collectWrongAns= accumulatedErrors(collectedIncorrect) // this returns all the wrong ans in entire
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
        if(numToInc <=arabicNumarals.size-1) {
            thaiNumPics.setImageResource(arabicNumarals[numToInc])
        }
        dispThaiWord.text = myArrays.arabicNumerals[numToInc]
        with(scrambledFieldt) { text = myArrays.scramThaiNums[numToInc] }
        userEnterEng.setText("")
        scrambledFieldt.alpha =0.toFloat()
        0.toFloat().also { useHintT.alpha = it }
    }
///////////////////////////userEntert/////////////////////////////////////////////////////////

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
        if(numToInc <=arabicNumarals.size-1) {
            thaiNumPics.setImageResource(arabicNumarals[numToInc])
        }
        dispThaiWord.text = myArrays.arabicNumerals[numToInc]
        with(scrambledFieldt) { text = myArrays.scramThaiNums[numToInc] }
        userEnterEng.setText("")
        scrambledFieldt.alpha =0.toFloat()
        0.toFloat().also { useHintT.alpha = it }
        timer.cancel()
        //println("${numOfErrorsE.toString()} this is numOfErrorsE from reSetFruit()=0")
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
        val aWord = myArrays.arabicNumerals[numToInc]
        val result = wrongEng.contains(aWord)
        if(!result){
            wrongEng.add(aWord)
        }
        val aWordTh = myArrays.thaiNums[numToInc]
        val result2 = wrongThai.contains(aWordTh)
        if(!result2){
            wrongThai.add(aWordTh)
            //println(wrongThai)
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////
//private fun getGrade():Double//wrongThai.size
    private fun getGrade(): Double {
        //modifyThreeErrors(collectWrongAns) this can not be here because 3 errors are possible with big array
        // println("$collectWrongAns called from getGrade")
        myGrades = (sizeOfArray - wrongThai.size.toDouble()) / sizeOfArray * 100

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
        //if (numOfErrorsE == 0 && numToInc == sizeOfArray) {
        if (numOfErrorsE == 0 && numToInc == sizeOfArray-1) {
            marksString = markToPass(getGrade())
            println(" $marksString this is marksString" )
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    private fun cleanUpToContinue()
    {
        // if (numToInc <= sizeOfArray) {
        if (numToInc <= sizeOfArray-1) {

            println(" this is numToInc $numToInc andthis is arrayIndex $arrayIndex from cleanUpToContinue")
            userEnterEng.setText("")
            useHintT.alpha =1.toFloat()
            scrambledFieldt.alpha =1.toFloat()
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
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    private fun determineErrorsSnd():Int {
        soundId = (30..36).random()
        //println("$soundId this is myRandom from determineErrorsSnd()")
        return soundId
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    private fun listenForComplete() {
        timer = object : CountDownTimer(1100, 1000) {
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
////////////////////////////////////////////////////////////////////////////////////////////////////

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
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun modifyNumOfErrorsE(numOfTries:Int):Int {
        if (numOfTries == 1) {
            numOfErrorsE = 1
        }
        return numOfErrorsE
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun incrementingLimit() {
        if (numToInc <= arabicNumarals.size -1)
        {
            numToInc += 1
        }
        println("This is numToInc.... $numToInc called from from incrementingLimit()")
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
//////////////////////numToInc//////////////////////////////////////////////
private fun incrementingLimit) {
    if (myNumToInc <fruitPhotos.size)
     {
        numOfErrorsE += 1
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
     private fun tryCatchBlock() {
       try {
        checkWords()
           println("here is not the mess")
     }
      catch(e: ArrayIndexOutOfBoundsException)
     {
        println("here is the mess 185/ checkWords())")
      }
    }
    /////////////////////////////////////////////////////////
    private fun releaseSoundPool() {
        if(mSoundPool != null)
        {
          mSoundPool!!.release()
          mSoundPool = null
        }
        mSoundPool!!.release()
    }
 //println(" this is $arrayIndex the current index called from newIndividualEntryForErrors")
    //println(" this is ${fruitPhotos.size-1} the fruitPhotos.size-1")
    when (findIndex(myArrays.efruitTxt, myArrays.efruitTxt[numToInc]))
    {
        in 0..fruitPhotos.size-1 -> individualErrorCount()//should limit errors per item (fruit))// this is "orange"
        //1 -> individualErrorCount()//should limit errors per item (fruit))    // this is "apple"
        //2 -> individualErrorCount()//should limit errors per item (fruit))// this is "avocado"
    }
 */