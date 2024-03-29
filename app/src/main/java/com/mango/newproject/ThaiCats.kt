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
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import kotlin.math.roundToInt


class ThaiCats : AppCompatActivity() {
    private var fruitPhotos=arrayOf(
        R.drawable.orange2,R.drawable.apple,R.drawable.avocado,R.drawable.banana,
        R.drawable.blueberry,R.drawable.cantaloupe,R.drawable.cherry,R.drawable.grape,R.drawable.jackfruit,
        R.drawable.lemon,R.drawable.mango,R.drawable.watermelon,R.drawable.papaya,R.drawable.twocherries,R.drawable.pumpkin,
        R.drawable.strawberry,R.drawable.tomato,R.drawable.coconut,R.drawable.mangosteen,R.drawable.threemangos)
    /////////////////////////////////////////////////////////////////////
    private var mSoundPool: SoundPool? = null
    private lateinit var speakerbtn:ImageButton
    private lateinit var appleShot: ImageView        //images of fruit in View
    private lateinit var userEntert: EditText        //user editText
    private lateinit var scrambledFieldt: TextView  //scrambled text
    private lateinit var useHintT: TextView          //instruction foe letter use
    private lateinit var dispThaiWord: TextView  //user Thai Word
    private lateinit var wordInTArray:String         //reference
    private lateinit var marksStringT:String         //reference
    private lateinit var timer: CountDownTimer
    private var  wrongEng: ArrayList<String> = ArrayList()
    private var wrongThai: ArrayList<String> = ArrayList()
    private  var sizeOfArray = fruitPhotos.size
    private var numToInc:Int = 0         //num for incrementing array
    private var numOfErrorsT:Int = 0     //num for errors
    private var arrayIndexT:Int = 0
    private var numOfAttemptsT:Int = 0    //num for number of attempts
    private var collectWrongAns:Int = 0 //num for tries
    private var threeWrongs:Int = 0 //num for tries
    private var myGoofs:Int = 0 //num from errorArray
    private var myGradesT:Double = 0.0
    private var adjustedMark:Double = 0.0//number of wrong answers "no repeats"
    private  var voiceSnd = 0
    private val myArrays = TheArrays()
    private val TAG = "THAICATS"
    private var mInterstitialAd: InterstitialAd? = null
    private var mInterstitialAdTwo: InterstitialAd? = null
    lateinit var adRequest: AdRequest
    lateinit var adRequest2: AdRequest
    lateinit var adRequest3: AdRequest

    private lateinit var cellBannerT: AdView
    private  var collectedIncorrect: MutableList<Int> = mutableListOf()

    /////////////added July 11//////////////////////////////////////////////////////////////////////
    private var noise1 = 1
    private var noise2 = 2
    private var noise3 = 3
    private var noise4 = 4
    private var noise5 = 5
    private var noise6 = 6
    private var noise7 = 7
    private var soundId  = 500
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private var torange     = 8
    private var tapple      = 9
    private var tavocado    = 10
    private var tbanana     = 11
    private var tblueberry  = 12
    private var tCantaloupe = 13
    private var tcherry     = 14
    private var tgrape      = 15
    private var tjackfruit  = 16
    private var tlemon      = 17
    private var tmango      = 18
    private var twatermelon = 19
    private var tpapaya     = 20
    private var ttwocherries = 21
    private var tpumpkin    = 22
    private var tstrawberry = 23
    private var ttomato     = 24
    private var tcoconut    = 25
    private var tmangosteen = 26
    private var tthreemangos   = 27

    private  var tFruitSnds = arrayListOf( torange,tapple,tavocado,tbanana,
        tblueberry,tCantaloupe,tcherry,tgrape,
        tjackfruit,tlemon,tmango,twatermelon,
        tpapaya,ttwocherries,tpumpkin,tstrawberry,
        ttomato,tcoconut,tmangosteen,tthreemangos)
    ////////////////////////////////////////////////////////////////////////////////////////////////
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
        setContentView(R.layout.activity_thai_cats)
        ////////////////////////////////////////////////////////////////////////////////////////////////
        adRequest = AdRequest.Builder().build() //this ad is video
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
        ///////////////////////////////////////////////////////////////////////////////////////////
        adRequest3 = AdRequest.Builder().build() ////this ad is full screen interstitial
        InterstitialAd.load(this,"ca-app-pub-1764819666519183/7990146985", adRequest3, object : InterstitialAdLoadCallback() {

            override fun onAdFailedToLoad(adError: LoadAdError) {
                adError.toString().let { Log.d(TAG, it) }
                mInterstitialAdTwo = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                Log.d(TAG, "Ad was loaded.")
                mInterstitialAdTwo = interstitialAd
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

     ////////////////////////////////////////////////////////////////////////////////////////////////
        adRequest2 = AdRequest.Builder().build()
        MobileAds.initialize(this) {}
        cellBannerT = findViewById(R.id.cellBannerT)
        adRequest = AdRequest.Builder().build()
        cellBannerT.loadAd(adRequest2)

        ////////////////////////////added July 11///////////////////////////////////////////////////
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
        torange       = mSoundPool?.load(this, R.raw.torange, 1)!!
        tapple        = mSoundPool?.load(this, R.raw.tapple, 1)!!
        tavocado      = mSoundPool?.load(this, R.raw.tavocado, 1)!!
        tbanana       = mSoundPool?.load(this, R.raw.tbanana, 1)!!
        tblueberry    = mSoundPool?.load(this, R.raw.tblueberry, 1)!!
        tCantaloupe   = mSoundPool?.load(this, R.raw.tcantaloupe, 1)!!
        tcherry       = mSoundPool?.load(this, R.raw.tcherry, 1)!!
        tgrape        = mSoundPool?.load(this, R.raw.tgrape, 1)!!
        tjackfruit    = mSoundPool?.load(this, R.raw.tjackfruit, 1)!!
        tlemon        = mSoundPool?.load(this, R.raw.tlemon, 1)!!
        tmango        = mSoundPool?.load(this, R.raw. tmango, 1)!!
        twatermelon   = mSoundPool?.load(this, R.raw.twatermelon, 1)!!
        tpapaya       = mSoundPool?.load(this, R.raw.tpapaya, 1)!!
        ttwocherries  = mSoundPool?.load(this, R.raw.ttwocherries, 1)!!
        tpumpkin      = mSoundPool?.load(this, R.raw.tpumpkin, 1)!!
        tstrawberry   = mSoundPool?.load(this, R.raw.tstrawberry, 1)!!
        ttomato       = mSoundPool?.load(this, R.raw.ttomato, 1)!!
        tcoconut      = mSoundPool?.load(this, R.raw.tcoconut, 1)!!
        tmangosteen   = mSoundPool?.load(this, R.raw.tmangosteen, 1)!!
        tthreemangos  = mSoundPool?.load(this, R.raw.tthreemangos, 1)!!
        ////////////////////////////////////////////////////////////////////////////////////////////
        appleShot = findViewById(R.id.appleShot)
        appleShot.setImageResource(fruitPhotos[numToInc])
        dispThaiWord= findViewById(R.id.useThaiHere)
        userEntert = findViewById(R.id.userEntert)
        scrambledFieldt = findViewById(R.id.scrambledFieldt)
        useHintT = findViewById(R.id.useHintT)
        speakerbtn = findViewById(R.id.speakerbtn)
        speakerbtn.setOnClickListener {
            voiceSnd = 1
            mSoundPool?.play(tFruitSnds[numToInc], 1.0f, 1.0f, 0, 0, 1.0f)
        }
        dispThaiWord.append(myArrays.efruitTxt[numToInc])
        scrambledFieldt.append(myArrays.tscrambleThFruit[numToInc])
        useHintT.alpha = 0.toFloat()
        scrambledFieldt.alpha =0.toFloat()
        wordInTArray = userEntert.toString()

        userEntert.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                checkWords()
                hideSoftKeyboard()
                true
            } else false
        }
    } //end of constructor
    ////////////////////////////////////////////////////////////////////////////////////////////
    private fun playInterstitial(myInter:InterstitialAd?,afterThis:Int,showAt:Int){
        if (myInter != null && afterThis==showAt) {

            myInter.show(this)
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.")
        }
    }
    ////////////////////////////////////////////////////////////////
    private fun findIndex(arr: Array<String>, item: String): Int  //returns array index
    {
        return arr.indexOf(item)
    }
    ////////////////////////////////////////////////////////////////////////////////////////////
    private fun checkWords(){
        if(userEntert.text.toString() == myArrays.tthaiFruit[numToInc] && numToInc < sizeOfArray ){
            findIndex(myArrays.efruitTxt, myArrays.efruitTxt[numToInc])
            getNextFruit()
        }
        else{
            numOfAttemptsT +=1
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
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private fun newIndividualEntryForErrors()
    {
        arrayIndexT = findIndex(myArrays.efruitTxt, myArrays.efruitTxt[numToInc])
        println(" this is $arrayIndexT the current index called from checkIndividualEntry()")
        when (findIndex(myArrays.efruitTxt, myArrays.efruitTxt[numToInc]))
        {
            in 0..fruitPhotos.size -> individualErrorCount()//should limit errors per item (fruit))     // this is "watermelon"
            //1 -> individualErrorCount()//should limit errors per item (fruit))    // this is "apple"
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////
    private fun individualErrorCount()      // this is called per array element
    {
        myGoofs = modifyNumOfErrorsE(numOfAttemptsT)  // if numOfAttemptsT == 1 ..numOfErrorsT  = 1
        setNumberOfIncorrect(collectedIncorrect,myGoofs)//sets index of array element value = 1
        collectWrongAns= accumulatedErrors(collectedIncorrect) // this returns all the wrong ans in entire array ie wrong = 2
        modifyCollectedWrongs(numOfAttemptsT)
        println(" this is $collectWrongAns after accumulatedErrors  array iterated")
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    private fun respondToErrors() {
        // adjustedMark = lessThanZero(getGrade())//numbers below 0 reset to 0.0% for your grade
        //marksStringT = markToPass(getGrade())//"marksString" used in "intent" in endOfArray()
        passPeram()
        reSetNumOfAttempts() // if 3 Attempts back to 0
        whenWrongAnswer()
        println(" $numOfAttemptsT this is number of attempts called from respondToErrors()...Get Fucking Fucked")
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun whenWrongAnswer(){
        createArraysForWrongAnswers()             //creates arrays for wrong answers
        cleanUpToContinue()
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun reSetFruit(){
        endOfArray()
        if(numToInc <=fruitPhotos.size-1) {
            appleShot.setImageResource(fruitPhotos[numToInc])
        }
        dispThaiWord.text = myArrays. efruitTxt[numToInc]
        with(scrambledFieldt) { text = myArrays.tscrambleThFruit[numToInc] }
        userEntert.setText("")
        scrambledFieldt.alpha =0.toFloat()
        0.toFloat().also { useHintT.alpha = it }
    }
    //////////////////////////////////////////////////////////////////////////////////////
    private fun endOfArray() {    //when array done pass intents and change activities "GradeForEnglish"
        //setErrorResults()....moved inside conditional aug 6
        //results(collectWrongAns)....moved inside conditional aug 6
        if (numToInc == sizeOfArray) {
            setErrorResults()
            results(collectWrongAns)
            println("this is where you switch activities... from FruitCat")
           // val intent = Intent(this, GradeForEnglish::class.java)
            val intent = Intent(this, GradesForThai::class.java)
            intent.putExtra("key", wrongEng)
            intent.putExtra("key2", wrongThai)
            intent.putExtra("key3", marksStringT)
            intent.putExtra("key4", numOfErrorsT)
            startActivity(intent)
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun reSetForThreeErrors(){
        incrementingLimit()
        endOfArray()
        if(numToInc <=fruitPhotos.size-1) {
            appleShot.setImageResource(fruitPhotos[numToInc])
        }
        dispThaiWord.text = myArrays.tthaiFruit[numToInc]
        with(scrambledFieldt) { text = myArrays.escrambledFruits[numToInc] }
        userEntert.setText("")
        scrambledFieldt.alpha =0.toFloat()
        0.toFloat().also { useHintT.alpha = it }
        //println("${numOfErrorsE.toString()} this is numOfErrorsE from reSetFruit()=0")
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun threeErrors (){   //when errors = 3 - 6 - 9 etc
        useAWhen(determineErrorsSnd())// soundId = random number
        //
        listenForComplete()
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun passPeram() // comes from HelperFunctions deals with turnover at 3.6..9.
    {
        HelperFunctions.errorsAdvance(numOfAttemptsT, ::threeErrors)
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun createArraysForWrongAnswers(){
        val aWord = myArrays.efruitTxt[numToInc]
        val result = wrongEng.contains(aWord)
        if(!result){
            wrongEng.add(aWord)
        }
        println(result) // true
        val aWordTh = myArrays.tthaiFruit[numToInc]
        val result2 = wrongThai.contains(aWordTh)
        if(!result2){
            wrongThai.add(aWordTh)
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun getGrade():Double//wrongThai.size
    {
        myGradesT = (sizeOfArray- wrongThai.size.toDouble()) / sizeOfArray * 100
        return (myGradesT * 100).roundToInt() / 100.0
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun lessThanZero(allWrong:Double):Double {
        // println("${numOfErrorsE.toString()} this is numOfErrorsE from lessThanZero")
        if (allWrong < 0.0){
            myGradesT = 0.0
        }
        return myGradesT
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun markToPass(allWrong:Double):String {
        // println("${numOfErrorsE.toString()} this is numOfErrorsE from markToPass")
        myGradesT = if (allWrong >= 0.0){
            getGrade()
        } else{
            adjustedMark
        }
        return myGradesT.toString()
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun numOfMistakes()
    {
        if (numOfErrorsT == 0 && numToInc == sizeOfArray) {
            marksStringT = markToPass(getGrade())
            println(" $marksStringT this is marksString" )
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun cleanUpToContinue()
    {
        if (numToInc <= sizeOfArray-1) {
            userEntert.setText("")
            useHintT.alpha =1.toFloat()
            scrambledFieldt.alpha =1.toFloat()
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun reSetNumOfAttempts()
    {
        if (numOfAttemptsT == 3) {
            numOfAttemptsT = 0
            println("$numOfAttemptsT this is number of attempts called from reSetNumOfAttempts")
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
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
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun determineErrorsSnd():Int {
        soundId = (30..36).random()
        println("$soundId this is myRandom from determineErrorsSnd()")
        return soundId
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
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
        incrementingLimit()
        playInterstitial(mInterstitialAd,numToInc,7)
        playInterstitial(mInterstitialAdTwo,numToInc,16)
        numOfAttemptsT =0  // reset for next index number
        reSetFruit()
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun setErrorResults()//called in endOfArray
    {
        println("This function setErrorResults() should be called in endOfArray")
        adjustedMark = lessThanZero(getGrade()) //may not need below 0 not possible if everything works
        marksStringT = markToPass(getGrade())    //"marksString" used in "intent" in endOfArray()
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun results(allTheWrong:Int)//collectWrongAns
    {
        println("This function results should be called in endOfArray")
        when (allTheWrong)
        {
            0 -> numOfMistakes()    //noErrors 100%
            1 -> setErrorResults() // Errors and less than 100%
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //setNumberOfIncorrect(collectedIncorrect,numToInc ,numOfErrorsT)
    private fun setNumberOfIncorrect(listOfGoofs: MutableList<Int>,theGoof:Int) {
        listOfGoofs.add(theGoof)
        println("{${listOfGoofs[0]}}..... listOfGoofs   lzlzlzl")

    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun accumulatedErrors(listOfGoofs: MutableList<Int>):Int {
        var gatheredWrong = 0
        for (item in listOfGoofs)
        {
            gatheredWrong += item
        }
        return gatheredWrong
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun modifyNumOfErrorsE(numOfTries:Int):Int {
        if (numOfTries == 1) {
            numOfErrorsT = 1
        }
        return numOfErrorsT
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun modifyCollectedWrongs(numOfTries:Int) {
        // this to use in event of three errors in one index  ie. orange = (wrong)*3
        threeWrongs  += numOfTries
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private fun incrementingLimit() {
        if (numToInc <= fruitPhotos.size -1)
        {
            numToInc += 1

        }
        println("This is numToInc.... $numToInc called from from incrementingLimit()")
    }
    ////////////////////////////////End of Class////////////////////////////////////////////////////
  }//end of class
