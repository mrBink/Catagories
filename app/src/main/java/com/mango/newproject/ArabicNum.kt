package com.mango.newproject

import android.app.Activity
import android.content.Intent
import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import android.os.CountDownTimer
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import kotlin.math.roundToInt


class ArabicNum : AppCompatActivity() {


    //USEr WRITES IN ENGLISH SEES IN THAI

    private var thaiNumarals=arrayOf(
        R.drawable.t1400, R.drawable.t4, R.drawable.t11, R.drawable.t1000000,
        R.drawable.t2, R.drawable.t624, R.drawable.t1000, R.drawable.t8, R.drawable.t7, R.drawable.t10,
        R.drawable.t100000, R.drawable.t9, R.drawable.t10000, R.drawable.t54, R.drawable.t333,
        R.drawable.t5, R.drawable.t71000, R.drawable.t0, R.drawable.t3, R.drawable.t6)

    /////////////////////////////////////////////////////////////////////
    private var mSoundPool: SoundPool? = null
    private lateinit var speakerbtn:ImageButton//bg_fruitPics
    private lateinit var engNumbers:ImageView
    private lateinit var numPics:ImageView        //images of fruit in View:
    private lateinit var userEnterThai:EditText        //user editText
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
    private val TAG = "ArabicNums"
    private var mInterstitialAd: InterstitialAd? = null
    private var mInterstitialAdTwo: InterstitialAd? = null
    lateinit var adRequest:  AdRequest   //interstitial
    lateinit var adRequest2: AdRequest  //banner
    lateinit var adRequest3: AdRequest  //interstitial
    private lateinit var numAd:AdView
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
    private var engnumberssnd_01    = 8
    private var engnumberssnd_02    = 9
    private var engnumberssnd_03    = 10
    private var engnumberssnd_04    = 11
    private var engnumberssnd_05    = 12
    private var engnumberssnd_06    = 13
    private var engnumberssnd_07    = 14
    private var engnumberssnd_08    = 15
    private var engnumberssnd_09    = 16
    private var engnumberssnd_10    = 17
    private var engnumberssnd_11    = 18
    private var engnumberssnd_12    = 19
    private var engnumberssnd_13    = 20
    private var engnumberssnd_14    = 21
    private var engnumberssnd_15    = 22
    private var engnumberssnd_16    = 23
    private var engnumberssnd_17    = 24
    private var engnumberssnd_18    = 25
    private var engnumberssnd_19    = 26
    private var engnumberssnd_20    = 27


    private  var engNumSnd = arrayListOf( engnumberssnd_01,engnumberssnd_02,engnumberssnd_03,engnumberssnd_04,
        engnumberssnd_05,engnumberssnd_06,engnumberssnd_07,engnumberssnd_08,
        engnumberssnd_09,engnumberssnd_10,engnumberssnd_11,engnumberssnd_12,
        engnumberssnd_13,engnumberssnd_14,engnumberssnd_15,engnumberssnd_16,
        engnumberssnd_17,engnumberssnd_18,engnumberssnd_19,engnumberssnd_20)

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

        //ca-app-pub-1764819666519183/3322738692  ad video (test)
        //ca-app-pub-1764819666519183/7990146985 large ad pic (test)
        setContentView(R.layout.activity_arabic_num)
         adRequest = AdRequest.Builder().build()

        InterstitialAd.load(this,"ca-app-pub-1764819666519183/7283569974", adRequest, object : InterstitialAdLoadCallback() {

            override fun onAdFailedToLoad(adError: LoadAdError) {
                adError.toString().let { Log.d(TAG, it) }
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                Log.d(TAG, "Ad was loaded.")
                mInterstitialAd = interstitialAd
                mInterstitialAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
                    override fun onAdClicked() {
                        // Called when a click is recorded for an ad.
                        Log.d(TAG, "Ad was clicked.")
                    }

                    override fun onAdDismissedFullScreenContent() {
                        // Called when ad is dismissed.
                        Log.d(TAG, "Ad dismissed fullscreen content.")
                        mInterstitialAd = null
                    }

                    override fun onAdImpression() {
                        // Called when an impression is recorded for an ad.
                        Log.d(TAG, "Ad recorded an impression.")
                    }

                    override fun onAdShowedFullScreenContent() {
                        // Called when ad is shown.
                        Log.d(TAG, "Ad showed fullscreen content.")
                    }
                }

            }

        })

////////////////////////////////////////////////////////////////////////
        adRequest3 = AdRequest.Builder().build()
        InterstitialAd.load(this,"ca-app-pub-1764819666519183/7990146985", adRequest3, object : InterstitialAdLoadCallback() {

            override fun onAdFailedToLoad(adError: LoadAdError) {
                adError.toString().let { Log.d(TAG, it) }
                mInterstitialAdTwo = null
            }

            override fun onAdLoaded(interstitialAd2: InterstitialAd) {
                Log.d(TAG, "Ad was loaded.")
                mInterstitialAdTwo = interstitialAd2
                mInterstitialAdTwo?.fullScreenContentCallback = object: FullScreenContentCallback() {
                    override fun onAdClicked() {
                        // Called when a click is recorded for an ad.
                        Log.d(TAG, "Ad was clicked.")
                    }

                    override fun onAdDismissedFullScreenContent() {
                        // Called when ad is dismissed.
                        Log.d(TAG, "Ad dismissed fullscreen content.")
                        mInterstitialAdTwo = null
                    }

                    override fun onAdImpression() {
                        // Called when an impression is recorded for an ad.
                        Log.d(TAG, "Ad recorded an impression.")
                    }

                    override fun onAdShowedFullScreenContent() {
                        // Called when ad is shown.
                        Log.d(TAG, "Ad showed fullscreen content.")
                    }
                }
            }
        })
/////////////////////////////////////////////////////////////////////////
       MobileAds.initialize(this) {}
        numAd = findViewById(R.id.numAd)
        adRequest2 = AdRequest.Builder().build()
        numAd.loadAd(adRequest2)

        numAd.adListener = object: AdListener() {
            override fun onAdClicked() {
                // Code to be executed when the user clicks on an ad.
                println("Ad loaded")
            }

            override fun onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }

            override fun onAdFailedToLoad(adError : LoadAdError) {
                // Code to be executed when an ad request fails.
            }

            override fun onAdImpression() {
                // Code to be executed when an impression is recorded
                // for an ad.
                println("impression is recorded")
            }

            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                println(" ad finished loading")
            }

            override fun onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
                println("ad opened fullscreen ")
            }

        }


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
        engnumberssnd_01       = mSoundPool?.load(this, R.raw.engnumberssnd_01, 1)!!
        engnumberssnd_02        = mSoundPool?.load(this, R.raw.engnumberssnd_02, 1)!!
        engnumberssnd_03      = mSoundPool?.load(this, R.raw.engnumberssnd_03, 1)!!
        engnumberssnd_04       = mSoundPool?.load(this, R.raw.engnumberssnd_04, 1)!!
        engnumberssnd_05    = mSoundPool?.load(this, R.raw.engnumberssnd_05, 1)!!
        engnumberssnd_06   = mSoundPool?.load(this, R.raw.engnumberssnd_06, 1)!!
        engnumberssnd_07       = mSoundPool?.load(this, R.raw.engnumberssnd_07, 1)!!
        engnumberssnd_08        = mSoundPool?.load(this, R.raw.engnumberssnd_08, 1)!!
        engnumberssnd_09    = mSoundPool?.load(this, R.raw. engnumberssnd_09, 1)!!
        engnumberssnd_10        = mSoundPool?.load(this, R.raw.engnumberssnd_10, 1)!!
        engnumberssnd_11        = mSoundPool?.load(this, R.raw.engnumberssnd_11, 1)!!
        engnumberssnd_12   = mSoundPool?.load(this, R.raw.engnumberssnd_12, 1)!!
        engnumberssnd_13      = mSoundPool?.load(this, R.raw.engnumberssnd_13, 1)!!
        engnumberssnd_14         = mSoundPool?.load(this, R.raw.engnumberssnd_14, 1)!!
        engnumberssnd_15      = mSoundPool?.load(this, R.raw.engnumberssnd_15, 1)!!
        engnumberssnd_16   = mSoundPool?.load(this, R.raw.engnumberssnd_16, 1)!!
        engnumberssnd_17       = mSoundPool?.load(this, R.raw.engnumberssnd_17, 1)!!
        engnumberssnd_18      = mSoundPool?.load(this, R.raw.engnumberssnd_18, 1)!!
        engnumberssnd_19   = mSoundPool?.load(this, R.raw.engnumberssnd_19, 1)!!
        engnumberssnd_20     = mSoundPool?.load(this, R.raw.engnumberssnd_20, 1)!!
        engNumbers = findViewById(R.id.engNumbers)
        numPics = findViewById(R.id.numPics)
        numPics.setImageResource(thaiNumarals[numToInc])
        dispEnglishWord = findViewById(R.id.showThaiWord)
        userEnterThai = findViewById(R.id.thaigoeshere)
        scrambledFieldE = findViewById(R.id.scrambledFieldT)
        useHint = findViewById(R.id.useHint)
        speakerbtn = findViewById(R.id.speakerbtn)
        speakerbtn.setOnClickListener {

            mSoundPool?.play(engNumSnd[numToInc], 1.0f, 1.0f, 0, 0, 1.0f)
        }
        dispEnglishWord.append(myArrays.thaiNums[numToInc])
        scrambledFieldE.append(myArrays.arabNumScram[numToInc])
        scrambledFieldE.alpha =0.toFloat()
        useHint.alpha =0.toFloat()
        wordInEArray = userEnterThai.toString()
        userEnterThai.setOnEditorActionListener { _, actionId, _ ->//activates "done keyboard"
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                println("this is ${thaiNumarals.size} thaiNumarals.size" )
                checkWords()

                //tryCatchBlock()
                hideSoftKeyboard()
                true
            } else false
        }

    } //end of constructor
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun playInterstitial(myInter:InterstitialAd?,afterThis:Int,showAt:Int){
        if (myInter != null && afterThis==showAt) {

            myInter.show(this)
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.")
        }
    }

    private fun findIndex(arr: Array<String>, item: String): Int  //returns array index
    {
        return arr.indexOf(item)
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun checkWords(){
        if(userEnterThai.text.toString() == myArrays.arabicNumerals[numToInc] && numToInc < sizeOfArray ){
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
        userEnterThai.setText("")
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
        userEnterThai.setText("")
        scrambledFieldE.alpha =0.toFloat()
        0.toFloat().also { useHint.alpha = it }
        timer.cancel()
        //println("${numOfErrorsE.toString()} this is numOfErrorsE from reSetFruit()=0")
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    private fun threeErrors (){   //when errors = 3 - 6 - 9 etc
        useAWhen(determineErrorsSnd())// soundId = random number
        //
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
            println(wrongEng)
        }
        val aWordTh = myArrays.thaiNums[numToInc]
        val result2 = wrongThai.contains(aWordTh)
        if(!result2){
            wrongThai.add(aWordTh)
            println(wrongThai)
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////
//private fun getGrade():Double//wrongEng.size
    private fun getGrade(): Double {
        //modifyThreeErrors(collectWrongAns) this can not be here because 3 errors are possible with big array
        // println("$collectWrongAns called from getGrade")
        myGrades = (sizeOfArray - wrongEng.size.toDouble()) / sizeOfArray * 100

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
            userEnterThai.setText("")
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
        playInterstitial(mInterstitialAd,numToInc,7)
        playInterstitial(mInterstitialAdTwo,numToInc,16)
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





