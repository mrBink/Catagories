package com.mango.newproject

import android.app.Activity
import android.content.Intent
import android.media.AudioAttributes
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.StrictMode
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.*
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

class AnatomyE : AppCompatActivity() {
    private var anatomyT=arrayOf(
        R.drawable.ear,R.drawable.tthumb,R.drawable.head, R.drawable.tfinger,R.drawable.toes,
        R.drawable.tongue,R.drawable.tneck, R.drawable.tlips,R.drawable.nose,R.drawable.hand,
        R.drawable.teeth,R.drawable.legs, R.drawable.knee,R.drawable.eye, R.drawable.hair,
        R.drawable.feet,R.drawable.eyelash, R.drawable.tback,R.drawable.stomach,
        R.drawable.fingernail)
    /////////////////////////////////////////////////////////////////////////////////////////////
    private var mSoundPool: SoundPool? = null
    private lateinit var speakerbtn: ImageButton
    private lateinit var bodyView: ImageView        //images of fruit in View:
    private lateinit var goldBackGround: ImageView
    private lateinit var enterEng: EditText
    private lateinit var scrambledFieldE: TextView   //scrambled text
    private lateinit var useHint: TextView           //instruction foe letter use
    private lateinit var showThaiWord: TextView      //user Thai Word
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
    private lateinit var anatomyBannerE : AdView
    private val TAG = "AnatomyE"
    private var mInterstitialAd: InterstitialAd? = null
    private var mInterstitialAdTwo: InterstitialAd? = null
    lateinit var adRequest:  AdRequest   //interstitial
    lateinit var adRequest2: AdRequest  //banner
    lateinit var adRequest3: AdRequest  //interstitial
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
    private var elips       = 15
    private var nose       = 16
    private var hand       = 17
    private var teeth      = 18
    private var legs       = 19
    private var knee       = 20
    private var eye        = 21
    private var hair       = 22
    private var feet       = 23
    private var eyelash    = 24
    private var back      = 25
    private var stomache    = 26
    private var fingernail   = 27




    private  var bodySnds = arrayListOf( ear,thumb,head ,finger,toes,tongue,neck,elips,nose,hand,teeth,legs,
        knee,eye,hair,feet,eyelash,back,stomache,fingernail)

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
        //ca-app-pub-1764819666519183/3322738692  ad video (test)
        //ca-app-pub-1764819666519183/7990146985 large ad pic (test)
        setContentView(R.layout.activity_anatomy_e)
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
        //////////////////////////////////////////////////////////////////////////////
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
        ////////////////////////////////////////////////////////////////////////////////


        MobileAds.initialize(this) {}
        anatomyBannerE = findViewById(R.id.anatomyBannerE)
        adRequest2 = AdRequest.Builder().build()
        anatomyBannerE.loadAd(adRequest2)
        anatomyBannerE.adListener = object: AdListener() {
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
        ear       = mSoundPool?.load(this, R.raw.ear, 1)!!
        thumb     = mSoundPool?.load(this, R.raw.thumb, 1)!!
        head      = mSoundPool?.load(this, R.raw.head , 1)!!
        finger    = mSoundPool?.load(this, R.raw.finger, 1)!!
        toes      = mSoundPool?.load(this, R.raw.toes, 1)!!
        tongue    = mSoundPool?.load(this, R.raw.tongue, 1)!!
        neck      = mSoundPool?.load(this, R.raw.neck, 1)!!
        elips      = mSoundPool?.load(this, R.raw.elips, 1)!!
        nose      = mSoundPool?.load(this, R.raw.nose, 1)!!
        hand      = mSoundPool?.load(this, R.raw.hand, 1)!!
        teeth     = mSoundPool?.load(this, R.raw.teeth, 1)!!
        legs       = mSoundPool?.load(this, R.raw.legs, 1)!!
        knee      = mSoundPool?.load(this, R.raw.knee, 1)!!
        eye       = mSoundPool?.load(this, R.raw.eye, 1)!!
        hair      = mSoundPool?.load(this, R.raw.hair, 1)!!
        feet      = mSoundPool?.load(this, R.raw.feet, 1)!!
        eyelash   = mSoundPool?.load(this, R.raw.eyelash, 1)!!
        back      = mSoundPool?.load(this, R.raw.back, 1)!!
        stomache   = mSoundPool?.load(this, R.raw.stomache, 1)!!
        fingernail = mSoundPool?.load(this, R.raw.fingernail, 1)!!
        goldBackGround = findViewById(R.id.goldBackGround)
        bodyView = findViewById(R.id.bodyView)
        bodyView.setImageResource(anatomyT[numToInc])
        showThaiWord = findViewById(R.id.showThaiWord)
        enterEng = findViewById(R.id.enterEng)
        scrambledFieldE = findViewById(R.id.scrambledFieldT)
        useHint = findViewById(R.id.useHint)
        speakerbtn = findViewById(R.id.speakerbtn)
        speakerbtn.setOnClickListener {

            mSoundPool?.play(bodySnds[numToInc], 1.0f, 1.0f, 0, 0, 1.0f)
        }
        showThaiWord.append(myArrays.tAnatomy[numToInc])
        scrambledFieldE.append(myArrays.eScramBody[numToInc])
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
    ////////////////////////////////////////////////////////////////////////////////////////////
    private fun checkWords(){
        if(enterEng.text.toString() == myArrays.eAnatomy[numToInc] && numToInc < sizeOfArray ){
            arrayIndex =findIndex(myArrays.eAnatomy, myArrays.tAnatomy[numToInc])
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
        showThaiWord.text = myArrays.tAnatomy[numToInc]
        with(scrambledFieldE) { text = myArrays.eScramBody[numToInc] }
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
        showThaiWord.text = myArrays.tAnatomy[numToInc]
        with(scrambledFieldE) { text = myArrays.eScramBody[numToInc] }
        enterEng.setText("")
        scrambledFieldE.alpha =0.toFloat()
        0.toFloat().also { useHint.alpha = it }
        timer.cancel()
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
        playInterstitial(mInterstitialAd,numToInc,7)
        playInterstitial(mInterstitialAdTwo,numToInc,16)
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
