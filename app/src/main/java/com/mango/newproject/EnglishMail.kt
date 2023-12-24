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

/*
the start Btn txt is english...user types in eng
 snailMailEng
Btn >>>>Fruit_class >>>>>>>>engmailsnd
lish_class
engmailsnd
lish_class >>  user types in eng
 */
class EnglishMail : AppCompatActivity() {
    private var  snailMailPicsT=arrayOf( ///for people writing in English. 23
        R.drawable.letters,R.drawable.postcard,R.drawable.writingpaper, R.drawable.pen,R.drawable.pencil,
        R.drawable.writeletter,R.drawable.envelope,R.drawable.address ,R.drawable.stamps,R.drawable.sendmail,
        R.drawable.airmail,R.drawable.ems,R.drawable.registeredmail,R.drawable.receivemail,
        R.drawable.openletter,R.drawable.postman,
        R.drawable.postalvan,R.drawable.engfragile, R.drawable.engpostoffice, R.drawable.tape,
        R.drawable.packingbox, R.drawable.boxcutter,R.drawable.postboxth)
    /////////////////////////////////////////////////////////////////////////
    private var mSoundPool: SoundPool? = null
    private lateinit var speakerbtn: ImageButton
    private lateinit var postalWordsEng: ImageView
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
    private  var sizeOfArray =snailMailPicsT.size
    private var numToInc:Int = 0         //num for incrementing array
    private var numOfErrorsE:Int = 0
    private var arrayIndex:Int = 0     //num for correct
    private var numOfAttempts:Int = 0    //num for number of attempts
    private var collectWrongAns:Int = 0 //num for tries
    private var myGoofs:Int = 0 //num for tries
    private var myGrades:Double = 0.0
    private var adjustedMark:Double = 0.0
    private val myArrays = TheArrays()
    private lateinit var postMail: AdView
    private val TAG = "ENGLISHMAIL"
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
    private var engmailsnd_01   = 8
    private var engmailsnd_02   = 9
    private var engmailsnd_03   = 10
    private var engmailsnd_04   = 11
    private var engmailsnd_05   = 12
    private var engmailsnd_06   = 13
    private var engmailsnd_07   = 14
    private var engmailsnd_08   = 15
    private var engmailsnd_09   = 16
    private var engmailsnd_10   = 17
    private var engmailsnd_11   = 18
    private var engmailsnd_12   = 19
    private var engmailsnd_13   = 20
    private var engmailsnd_14   = 21
    private var engmailsnd_15   = 22
    private var engmailsnd_16   = 23
    private var engmailsnd_17   = 24
    private var engmailsnd_18   = 25
    private var engmailsnd_19   = 26
    private var engmailsnd_20   = 27
    private var engmailsnd_21   = 28
    private var engmailsnd_22   = 29
    private var engmailsnd_23   = 30
    private  var sndThai = arrayListOf(
        engmailsnd_01,engmailsnd_02,engmailsnd_03,engmailsnd_04,engmailsnd_05,
        engmailsnd_06,engmailsnd_07,engmailsnd_08,engmailsnd_09,engmailsnd_10,
        engmailsnd_11,engmailsnd_12,engmailsnd_13,engmailsnd_14,engmailsnd_15,
        engmailsnd_16,engmailsnd_17,engmailsnd_18,engmailsnd_19,engmailsnd_20,
        engmailsnd_21,engmailsnd_22,engmailsnd_23)
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
        setContentView(R.layout.activity_english_mail)
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
        postMail = findViewById(R.id.postMail)
        val adRequest2 = AdRequest.Builder().build()
        postMail.loadAd(adRequest2)
        postMail.adListener = object: AdListener() {
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

///////////////////////////////////////////////////

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
        engmailsnd_01 = mSoundPool?.load(this, R.raw.engmailsnd_01, 1)!!
        engmailsnd_02 = mSoundPool?.load(this, R.raw.engmailsnd_02, 1)!!
        engmailsnd_03 = mSoundPool?.load(this, R.raw.engmailsnd_03, 1)!!
        engmailsnd_04 = mSoundPool?.load(this, R.raw.engmailsnd_04, 1)!!
        engmailsnd_05 = mSoundPool?.load(this, R.raw.engmailsnd_05, 1)!!
        engmailsnd_06 = mSoundPool?.load(this, R.raw.engmailsnd_06, 1)!!
        engmailsnd_07 = mSoundPool?.load(this, R.raw.engmailsnd_07, 1)!!
        engmailsnd_08 = mSoundPool?.load(this, R.raw.engmailsnd_08, 1)!!
        engmailsnd_09 = mSoundPool?.load(this, R.raw.engmailsnd_09, 1)!!
        engmailsnd_10 = mSoundPool?.load(this, R.raw.engmailsnd_10, 1)!!
        engmailsnd_11 = mSoundPool?.load(this, R.raw.engmailsnd_11, 1)!!
        engmailsnd_12 = mSoundPool?.load(this, R.raw.engmailsnd_12, 1)!!
        engmailsnd_13 = mSoundPool?.load(this, R.raw.engmailsnd_13, 1)!!
        engmailsnd_14 = mSoundPool?.load(this, R.raw.engmailsnd_14, 1)!!
        engmailsnd_15 = mSoundPool?.load(this, R.raw.engmailsnd_15, 1)!!
        engmailsnd_16 = mSoundPool?.load(this, R.raw.engmailsnd_16, 1)!!
        engmailsnd_17 = mSoundPool?.load(this, R.raw.engmailsnd_17, 1)!!
        engmailsnd_18 = mSoundPool?.load(this, R.raw.engmailsnd_18, 1)!!
        engmailsnd_19 = mSoundPool?.load(this, R.raw.engmailsnd_19, 1)!!
        engmailsnd_20 = mSoundPool?.load(this, R.raw.engmailsnd_20, 1)!!
        engmailsnd_21 = mSoundPool?.load(this, R.raw.engmailsnd_21, 1)!!
        engmailsnd_22 = mSoundPool?.load(this, R.raw.engmailsnd_22, 1)!!
        engmailsnd_22 = mSoundPool?.load(this, R.raw.engmailsnd_23, 1)!!



        bgFruitPics = findViewById(R.id.bgFruitPics)
        postalWordsEng= findViewById(R.id.postalWordsEng)
        postalWordsEng.setImageResource(snailMailPicsT[numToInc]) //fill image view with snailMailPicsT
        dispThaiHereWord = findViewById(R.id.dispThaiHereWord)
        userInputEng = findViewById(R.id.userInputEng)
        scrambledFieldinEng = findViewById(R.id.scrambledFieldinEng)
        useHintinThai = findViewById(R.id.useHintinThai)
        speakerbtn = findViewById(R.id.speakerbtn)
        speakerbtn.setOnClickListener {

            mSoundPool?.play(sndThai[numToInc], 1.0f, 1.0f, 0, 0, 1.0f)
        }
        dispThaiHereWord.append(myArrays.snailMailTh[numToInc])
        scrambledFieldinEng.append(myArrays.snailMailEngScram[numToInc])
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

    private fun playInterstitial(myInter:InterstitialAd?,afterThis:Int,showAt:Int){
        if (myInter != null && afterThis==showAt) {

            myInter.show(this)
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.")
        }
    }
////////////////////////////////////////////////////////////////////////////////////////
    private fun findIndex(arr: Array<String>, item: String): Int  //returns array index
    {
        return arr.indexOf(item)
    }
    ////////////////////////////////////////////////////////
    private fun checkWords(){
        if(userInputEng.text.toString() == myArrays. snailMailEng[numToInc] && numToInc < sizeOfArray ){
            arrayIndex =findIndex(myArrays. snailMailEng, myArrays. snailMailEng[numToInc])
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
        arrayIndex = findIndex(myArrays. snailMailEng, myArrays. snailMailEng[numToInc])
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
        if(numToInc <=snailMailPicsT.size-1) {postalWordsEng.setImageResource(snailMailPicsT[numToInc])
        }
        dispThaiHereWord.text = myArrays.snailMailTh[numToInc]
        with(scrambledFieldinEng) { text = myArrays.snailMailEngScram[numToInc] }
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
        if(numToInc <=snailMailPicsT.size-1) { postalWordsEng.setImageResource(snailMailPicsT[numToInc])}
        dispThaiHereWord.text = myArrays.snailMailTh[numToInc]
        with(scrambledFieldinEng) { text = myArrays.snailMailEngScram[numToInc] }
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
        val aWord = myArrays. snailMailEng[numToInc]
        val result = wrongEng.contains(aWord)
        if(!result){
            wrongEng.add(aWord)
        }
        val aWordTh = myArrays.snailMailTh[numToInc]
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
        if (numToInc <=snailMailPicsT.size -1)
        {
            numToInc += 1
        }
        // println("This is numToInc.... $numToInc called from from incrementingLimit()")
    }
    ////////////////////////////////////////////////////////////////////////////////////////////


}//end of class