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


class ArabicNum : AppCompatActivity() {


    private var thaiNumarals=arrayOf(
        R.drawable.t1400, R.drawable.t4, R.drawable.t11, R.drawable.t1000000,
        R.drawable.t2, R.drawable.t624, R.drawable.t1000, R.drawable.t8, R.drawable.t7, R.drawable.t10,
        R.drawable.t100000, R.drawable.t9, R.drawable.t10000, R.drawable.t54, R.drawable.t333,
        R.drawable.t5, R.drawable.t71000, R.drawable.t0, R.drawable.t3, R.drawable.t6)

    /////////////////////////////////////////////////////////////////////
    private var mSoundPool: SoundPool? = null
    private lateinit var sndBtn2:ImageButton//bg_fruitPics
    private lateinit var engNumbers:ImageView
    private lateinit var numPics:ImageView        //images of fruit in View:
    private lateinit var userEnterE:EditText        //user editText
    private lateinit var scrambledFieldE:TextView   //scrambled text
    private lateinit var useHint:TextView           //instruction foe letter use
    private lateinit var dispEnglishWord:TextView      //user Thai Word
    private lateinit var wordInEArray:String        //reference
    private lateinit var marksString:String         //reference
    private lateinit var timer: CountDownTimer
    private var  wrongEng: ArrayList<String> = ArrayList()
    private var wrongThai: ArrayList<String> = ArrayList()
    private  var sizeOfArray = thaiNumarals.size
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
    private var e1402    = 8
    private var e4       = 9
    private var e11      = 10
    private var e1000000 = 11
    private var e2    = 12
    private var e624     = 13
    private var e1000    = 14
    private var e8       = 15
    private var e7       = 16
    private var e10      = 17
    private var e100000  = 18
    private var e9       = 19
    private var e10000   = 20
    private var e54   = 21
    private var e333     = 22
    private var e5       = 23
    private var e71000   = 24
    private var e0       = 25
    private var e3       = 26
    private var e6       = 27


    private  var engNumSnd = arrayListOf( e1402,e4,e11,e1000000,
        e2,e624,e1000,e8,
        e7,e10,e100000,e9,
        e10000,e54,e333,e5,
       e71000,e0,e3,e6)

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


        setContentView(R.layout.activity_arabic_num)
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
        e1402       = mSoundPool?.load(this, R.raw.e1400, 1)!!
        e4        = mSoundPool?.load(this, R.raw.e4, 1)!!
        e11      = mSoundPool?.load(this, R.raw.e11, 1)!!
        e1000000       = mSoundPool?.load(this, R.raw.e1000000, 1)!!
        e2    = mSoundPool?.load(this, R.raw.e2, 1)!!
        e624   = mSoundPool?.load(this, R.raw.e624, 1)!!
        e1000       = mSoundPool?.load(this, R.raw.e1000, 1)!!
        e8        = mSoundPool?.load(this, R.raw.e8, 1)!!
        e7    = mSoundPool?.load(this, R.raw. e7, 1)!!
        e10        = mSoundPool?.load(this, R.raw.e10, 1)!!
        e100000        = mSoundPool?.load(this, R.raw.e100000, 1)!!
        e9   = mSoundPool?.load(this, R.raw.e9, 1)!!
        e10000      = mSoundPool?.load(this, R.raw.e10000, 1)!!
        e54         = mSoundPool?.load(this, R.raw.e54, 1)!!
        e333      = mSoundPool?.load(this, R.raw.e333, 1)!!
        e5   = mSoundPool?.load(this, R.raw.e5, 1)!!
        e71000       = mSoundPool?.load(this, R.raw.e71000, 1)!!
        e0      = mSoundPool?.load(this, R.raw.e0, 1)!!
        e3   = mSoundPool?.load(this, R.raw.e3, 1)!!
        e6     = mSoundPool?.load(this, R.raw.e6, 1)!!
        engNumbers = findViewById(R.id.engNumbers)
        numPics = findViewById(R.id.numPics)
        numPics.setImageResource(thaiNumarals[numToInc])
        dispEnglishWord = findViewById(R.id.dispEnglishWord)
        userEnterE = findViewById(R.id.userEnter_E)
        scrambledFieldE = findViewById(R.id.scrambledField_e)
        useHint = findViewById(R.id.useHint)
        sndBtn2 = findViewById(R.id.sndBtn2)
        sndBtn2.setOnClickListener {

            mSoundPool?.play(engNumSnd[numToInc], 1.0f, 1.0f, 0, 0, 1.0f)
        }
        dispEnglishWord.append(myArrays.thaiNums[numToInc])
        scrambledFieldE.append(myArrays.arabNumScram[numToInc])
        scrambledFieldE.alpha =0.toFloat()
        useHint.alpha =0.toFloat()
        wordInEArray = userEnterE.toString()
        userEnterE.setOnEditorActionListener { _, actionId, _ ->//activates "done keyboard"
            if (actionId == EditorInfo.IME_ACTION_DONE) {

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
        if(userEnterE.text.toString() == myArrays.arabicNumerals[numToInc] && numToInc < sizeOfArray ){
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
        if(numToInc <=thaiNumarals.size-1) {
            numPics.setImageResource(thaiNumarals[numToInc])
        }
        dispEnglishWord.text = myArrays.thaiNums[numToInc]
        with(scrambledFieldE) { text = myArrays.arabNumScram[numToInc] }
        userEnterE.setText("")
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
        if(numToInc <=thaiNumarals.size-1) {
            numPics.setImageResource(thaiNumarals[numToInc])
        }
        dispEnglishWord.text = myArrays.thaiNums[numToInc]
        with(scrambledFieldE) { text = myArrays.arabNumScram[numToInc] }
        userEnterE.setText("")
        scrambledFieldE.alpha =0.toFloat()
        0.toFloat().also { useHint.alpha = it }
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
//private fun getGrade():Double
    private fun getGrade(): Double {
        //modifyThreeErrors(collectWrongAns) this can not be here because 3 errors are possible with big array
        // println("$collectWrongAns called from getGrade")
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
        if (numToInc <= thaiNumarals.size -1)
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





