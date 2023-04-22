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
  familyEng
Btn >>>>Fruit_class >>>>>>>>engfamilynames
lish_class
engfamilynames
lish_class >>  user types in eng
 */
class Family: AppCompatActivity() {
    private var familyPicsEng=arrayOf( ///for people writing in Thai. 23
        R.drawable.baby,R.drawable.son,R.drawable.daughter, R.drawable.boychild,R.drawable.girlchild,
        R.drawable.teengirl,R.drawable.teenboy,R.drawable.adult,R.drawable.oldwoman,R.drawable.oldman,
        R.drawable.father,R.drawable.mother,R.drawable.peternalgranddad,R.drawable.maternalgranddad,
        R.drawable.paternalgranny,R.drawable.maternalgranny,
        R.drawable.twins,R.drawable.husbandth, R.drawable.wifeth, R.drawable.family,
        R.drawable.oldetbrotherth, R.drawable.oldersisterth,R.drawable.youngerbrotherth,
        R.drawable.youngersisterth)
                /////////////////////////////////////////////////////////////////////////
    private var mSoundPool: SoundPool? = null
    private lateinit var sndBtn2: ImageButton
    private lateinit var familyMemberEng: ImageView
    private lateinit var bgFruitPics: ImageView                //this is gold BG image
    private lateinit var userInputEng: EditText                 //switched from
    private lateinit var scrambledFieldinEng: TextView       //scrambled text in eng
    private lateinit  var useHintinThai: TextView                //instruction for letter use
    private lateinit var dispThaiHereWord: TextView              //actually is
    private lateinit var wordInEArray:String                   //reference
    private lateinit var marksString:String                     //reference
    private lateinit var timer: CountDownTimer
    private var  wrongEng: ArrayList<String> = ArrayList()
    private var wrongThai: ArrayList<String> = ArrayList()
    private  var sizeOfArray =familyPicsEng.size
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
    private var engfamilynames_01   = 8
    private var engfamilynames_02   = 9
    private var engfamilynames_03   = 10
    private var engfamilynames_04   = 11
    private var engfamilynames_05   = 12
    private var engfamilynames_06   = 13
    private var engfamilynames_07   = 14
    private var engfamilynames_08   = 15
    private var engfamilynames_09   = 16
    private var engfamilynames_10   = 17
    private var engfamilynames_11   = 18
    private var engfamilynames_12   = 19
    private var engfamilynames_13   = 20
    private var engfamilynames_14   = 21
    private var engfamilynames_15   = 22
    private var engfamilynames_16   = 23
    private var engfamilynames_17   = 24
    private var engfamilynames_18   = 25
    private var engfamilynames_19   = 26
    private var engfamilynames_20   = 27
    private var engfamilynames_21   = 28
    private var engfamilynames_22   = 29
    private var engfamilynames_23   = 30
    private var engfamilynames_24   =31
    private  var sndThai = arrayListOf(
        engfamilynames_01,engfamilynames_02,engfamilynames_03,engfamilynames_04,engfamilynames_05,
        engfamilynames_06,engfamilynames_07,engfamilynames_08,engfamilynames_09,engfamilynames_10,
        engfamilynames_11,engfamilynames_12,engfamilynames_13,engfamilynames_14,engfamilynames_15,
        engfamilynames_16,engfamilynames_17,engfamilynames_18,engfamilynames_19,engfamilynames_20,
        engfamilynames_21,engfamilynames_22,engfamilynames_23,engfamilynames_24)
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
        setContentView(R.layout.activity_family)
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
        engfamilynames_01 = mSoundPool?.load(this, R.raw.engfamilynames_01, 1)!!
        engfamilynames_02 = mSoundPool?.load(this, R.raw.engfamilynames_02, 1)!!
        engfamilynames_03 = mSoundPool?.load(this, R.raw.engfamilynames_03, 1)!!
        engfamilynames_04 = mSoundPool?.load(this, R.raw.engfamilynames_04, 1)!!
        engfamilynames_05 = mSoundPool?.load(this, R.raw.engfamilynames_05, 1)!!
        engfamilynames_06 = mSoundPool?.load(this, R.raw.engfamilynames_06, 1)!!
        engfamilynames_07 = mSoundPool?.load(this, R.raw.engfamilynames_07, 1)!!
        engfamilynames_08 = mSoundPool?.load(this, R.raw.engfamilynames_08, 1)!!
        engfamilynames_09 = mSoundPool?.load(this, R.raw.engfamilynames_09, 1)!!
        engfamilynames_10 = mSoundPool?.load(this, R.raw.engfamilynames_10, 1)!!
        engfamilynames_11 = mSoundPool?.load(this, R.raw.engfamilynames_11, 1)!!
        engfamilynames_12 = mSoundPool?.load(this, R.raw.engfamilynames_12, 1)!!
        engfamilynames_13 = mSoundPool?.load(this, R.raw.engfamilynames_13, 1)!!
        engfamilynames_14 = mSoundPool?.load(this, R.raw.engfamilynames_14, 1)!!
        engfamilynames_15 = mSoundPool?.load(this, R.raw.engfamilynames_15, 1)!!
        engfamilynames_16 = mSoundPool?.load(this, R.raw.engfamilynames_16, 1)!!
        engfamilynames_17 = mSoundPool?.load(this, R.raw.engfamilynames_17, 1)!!
        engfamilynames_18 = mSoundPool?.load(this, R.raw.engfamilynames_18, 1)!!
        engfamilynames_19 = mSoundPool?.load(this, R.raw.engfamilynames_19, 1)!!
        engfamilynames_20 = mSoundPool?.load(this, R.raw.engfamilynames_20, 1)!!
        engfamilynames_21 = mSoundPool?.load(this, R.raw.engfamilynames_21, 1)!!
        engfamilynames_22 = mSoundPool?.load(this, R.raw.engfamilynames_22, 1)!!
        engfamilynames_23 = mSoundPool?.load(this, R.raw.engfamilynames_23, 1)!!
        engfamilynames_24 = mSoundPool?.load(this, R.raw.engfamilynames_24, 1)!!



        bgFruitPics = findViewById(R.id.bgFruitPics)
        familyMemberEng= findViewById(R.id.familyMemberEng)
        familyMemberEng.setImageResource(familyPicsEng[numToInc]) //fill image view with familyPicsEng
        dispThaiHereWord = findViewById(R.id.dispThaiHereWord)
        userInputEng = findViewById(R.id.userInputEng)
        scrambledFieldinEng = findViewById(R.id.scrambledFieldinEng)
        useHintinThai = findViewById(R.id.useHintinThai)
        sndBtn2 = findViewById(R.id.sndBtn2)
        sndBtn2.setOnClickListener {

            mSoundPool?.play(sndThai[numToInc], 1.0f, 1.0f, 0, 0, 1.0f)
        }
        dispThaiHereWord.append(myArrays.familyTh[numToInc])
        scrambledFieldinEng.append(myArrays.familyEngScram[numToInc])
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
        if(userInputEng.text.toString() == myArrays.familyEng[numToInc] && numToInc < sizeOfArray ){
            arrayIndex =findIndex(myArrays. familyEng, myArrays.familyEng[numToInc])
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
        arrayIndex = findIndex(myArrays.familyEng, myArrays.familyEng[numToInc])
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
            24-> individualErrorCount()
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
        if(numToInc <=familyPicsEng.size-1) {familyMemberEng.setImageResource(familyPicsEng[numToInc])
        }
        dispThaiHereWord.text = myArrays.familyTh[numToInc]
        with(scrambledFieldinEng) { text = myArrays.familyEngScram[numToInc] }
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
    //////////////////////////////////////////////////////////////////////
    private fun reSetForThreeErrors(){
        //numToInc += 1
        incrementingLimit()
        endOfArray()
        if(numToInc <=familyPicsEng.size-1) { familyMemberEng.setImageResource(familyPicsEng[numToInc])}
        dispThaiHereWord.text = myArrays.familyTh[numToInc]
        with(scrambledFieldinEng) { text = myArrays.familyEngScram[numToInc] }
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
        val aWord = myArrays.  familyEng[numToInc]
        val result = wrongEng.contains(aWord)
        if(!result){
            wrongEng.add(aWord)
        }
        val aWordTh = myArrays.familyTh[numToInc]
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
        if (numToInc <=familyPicsEng.size -1)
        {
            numToInc += 1
        }
        // println("This is numToInc.... $numToInc called from from incrementingLimit()")
    }
    ////////////////////////////////////////////////////////////////////////////////////////////


}//end of class