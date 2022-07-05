package com.mango.catagories

import android.app.Activity
import android.content.Intent
import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat


class FruitCat : AppCompatActivity() {
    private var fruitPhotos=arrayOf(
        R.drawable.orange, R.drawable.apple,/*R.drawable.avocado,R.drawable.banana,
        R.drawable.blueberry,R.drawable.cantaloupe,R.drawable.cherry,R.drawable.grape,R.drawable.jackfruit,
        R.drawable.lemon,R.drawable.mango,R.drawable.watermelon,R.drawable.papaya,R.drawable.plum,R.drawable.pumpkin,
        R.drawable.strawberry,R.drawable.tomato,R.drawable.coconut,R.drawable.mangosteen,R.drawable.rambutan*/
    )
    /////////////////////////////////////////////////////////////////////
    private var soundPool: SoundPool? = null
    private var sound1 = 1
    private lateinit var appleShot:ImageView        //images of fruit in View:
    private lateinit var userEnterE:EditText        //user editText
    private lateinit var scrambledFieldE:TextView   //scrambled text
    private lateinit var useHint:TextView           //instruction foe letter use
    private lateinit var dispEnglishWord:TextView      //user Thai Word
    private lateinit var wordInEArray:String        //reference
    private lateinit var marksString:String         //reference
    private var  wrongEng: ArrayList<String> = ArrayList()
    private var wrongThai: ArrayList<String> = ArrayList()
    private  var sizeOfArray = fruitPhotos.size
    private var numToInc:Int = 0         //num for incrementing array
    private var numOfErrorsE:Int = 0     //num for errors
    private var numOfCorrectE:Int = 0     //num for correct
    private var arrayIndex:Int = 0     //num for correct
    //private var wrongAnswersE:Int = 0     //num for errors
    private var numOfAttempts:Int = 0    //num for number of attempts
    private var myGrades:Double = 0.0
    private var adjustedMark:Double = 0.0
    private val myArrays = TheArrays()



    ////////////////////////////////////////////////////////////////////

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruit_cat)
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        soundPool = SoundPool.Builder()
            .setMaxStreams(1)
            .setAudioAttributes(audioAttributes)
            .build()
        sound1 = soundPool?.load(this, R.raw.domdomsnd, 1)!!

        appleShot = findViewById(R.id.appleShot)
        appleShot.setImageResource(fruitPhotos[numToInc])
        dispEnglishWord = findViewById(R.id.dispEnglishWord)
        userEnterE = findViewById(R.id.userEnter_E)
        scrambledFieldE = findViewById(R.id.scrambledField_e)
        useHint = findViewById(R.id.useHint)
        dispEnglishWord.append(myArrays.tthaiFruit[numToInc])
        scrambledFieldE.append(myArrays.escrambledFruits[numToInc])
        scrambledFieldE.alpha =0.toFloat()
        useHint.alpha =0.toFloat()
        wordInEArray = userEnterE.toString()
        userEnterE.setOnEditorActionListener { _, actionId, _ ->//activates "done keyboard"
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                checkWords()
               hideSoftKeyboard()
                true
            } else false
        }

    } //end of constructor
    /////////////////////////////////////////////////////////////////////

    private fun findIndex(arr: Array<String>, item: String): Int  //returns array index
    {
        return arr.indexOf(item)
    }
    ///////////////////////////////////////////////////////////////////////////////////
    private fun checkWords(){
        if(userEnterE.text.toString() == myArrays.efruitTxt[numToInc] && numToInc < sizeOfArray ){
            findIndex(myArrays.efruitTxt, myArrays.efruitTxt[numToInc])
            checkIndividualEntry()
            println(" this is $arrayIndex the current index called from checkIndividualEntry()")
        }
        else{
            numOfAttempts +=1
            //accumulateErrors(numOfAttempts)//returns var from numOfErrorsE/one var for grades/one var reset = 0 for corrections
            respondToErrors()
           // println("$numOfAttempts this is numberOfAttempts from else in checkWords()")
        }
    }
    ///////////////////////////////////////////////////////////////////
    private fun Activity.hideSoftKeyboard() {
        currentFocus?.let {
            val inputMethodManager = ContextCompat.getSystemService(this, InputMethodManager::class.java)!!
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun checkIndividualEntry()
    {
         arrayIndex = findIndex(myArrays.efruitTxt, myArrays.efruitTxt[numToInc])
       // println(" this is $arrayIndex the current index called from checkIndividualEntry()")
        when (findIndex(myArrays.efruitTxt, myArrays.efruitTxt[numToInc]))
        {
            0 -> advanceEntryNowCorrect (numOfAttempts)     // this is "watermelon"
            1 -> advanceEntryNowCorrect (numOfAttempts)     // this is "apple"
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun advanceEntryNowCorrect (tries:Int) {
        when (tries) {
            0 -> resetForZeroAttempts()
            1 -> resetForOneOrTwoAttempts()
            2 -> resetForOneOrTwoAttempts()
            3 -> passPeram()
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun resetForZeroAttempts() //only called when correct..numOfAttempts = 0 when correct
    {
        //println(" this is $arrayIndex the current index called from resetForZeroAttempts()")
        numOfCorrectE +=1 // for Marks
        numToInc +=1      // if spelling correct increments arrays
        numOfMistakes()   //  if spelling correct changes to new activity "GradeForEnglish"  gives 100%
        numOfAttempts =0  // reset for next index number
        reSetFruit()      // if spelling correct uses numToInc to reset next image
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

       private fun resetForOneOrTwoAttempts() //only called when correct..numOfAttempts = 0 when correct
            {
               // println(" this is $arrayIndex the current index called from resetForOneOrTwoAttempts()")
                numToInc += 1      // if spelling correct increments arrays
                numOfAttempts = 0  // reset for next index number
                reSetFruit()       // if spelling correct uses numToInc to reset next image
            }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    private fun respondToErrors() {
        adjustedMark = lessThanZero(getGrade())   //numbers below 0 reset to 0.0% for your grade
        marksString = markToPass(getGrade())      //"marksString" used in "intent" in endOfArray()
        passPeram()
        reSetNumOfAttempts() // if 3 Attempts back to 0
        whenWrongAnswer()
        //advanceIfCorrect()
       // println(" $numOfAttempts this is number of attempts called from respondToErrors()...Get Fucking Fucked")

    }
    ///////////////////////////////////////////////////////////////////
    private fun whenWrongAnswer(){
        createArraysForWrongAnswers()             //creates arrays for wrong answers
        cleanUpToContinue()
        //println("${numOfErrorsE.toString()} this is numOfErrorsE from whenWrongAnswer")//sets up scrambled field clears incorrect user entry
    }
    ////////////////////////////////////////////////////////////////////
    private fun reSetFruit(){
    endOfArray()
    appleShot.setImageResource(fruitPhotos[numToInc])
    dispEnglishWord.text = myArrays.tthaiFruit[numToInc]
    with(scrambledFieldE) { text = myArrays.escrambledFruits[numToInc] }
    userEnterE.setText("")
    scrambledFieldE.alpha =0.toFloat()
    0.toFloat().also { useHint.alpha = it }
    }
    ////////////////////////////////////////////////////////////////////

    private fun endOfArray() {    //when array done pass intents and change activities "GradeForEnglish"
       // println("this is $wrongEng this is wrongEng from endOfArray() i want water as #1")
        if (numToInc == sizeOfArray) {
            println("this is where you switch activities... from FruitCat")
            val intent = Intent(this, GradeForEnglish::class.java)
            intent.putExtra("key", wrongEng)
            intent.putExtra("key2", wrongThai)
            intent.putExtra("key3", marksString)
            startActivity(intent)
        }
    }
    /////////////////////////////////////////////////////////////////
    private fun reSetForThreeErrors(){
        numToInc += 1
        //println("$numToInc this is numToInc from reSetForThreeErrors()")
        //println("$numOfErrorsE this is numToInc from reSetForThreeErrors()")
        endOfArray()
        appleShot.setImageResource(fruitPhotos[numToInc])
        dispEnglishWord.text = myArrays.tthaiFruit[numToInc]
        with(scrambledFieldE) { text = myArrays.escrambledFruits[numToInc] }
        userEnterE.setText("")
        scrambledFieldE.alpha =0.toFloat()
        0.toFloat().also { useHint.alpha = it }
        //println("${numOfErrorsE.toString()} this is numOfErrorsE from reSetFruit()=0")
    }

    ////////////////////////////////////////////////////////////////////
    private fun threeErrors (){   //when errors = 3 - 6 - 9 etc
        soundPool?.play(sound1, 1F, 1F, 0, 0, 1F)
        Toast.makeText(this, "Playing sound. . . .", Toast.LENGTH_SHORT).show()
        reSetForThreeErrors()
    }
    //////////////////////////////////////////////
    private fun passPeram() // comes from HelperFunctions deals with turnover at 3.6..9.
    {
        HelperFunctions.errorsAdvance(numOfAttempts, ::threeErrors)
    }
    /////////////////////////////////////////////////////////////////////
    private fun createArraysForWrongAnswers(){
     val aWord = myArrays.efruitTxt[numToInc]
        val result = wrongEng.contains(aWord)
        if(!result){
            wrongEng.add(aWord)
            //println("this is ${wrongEng.size} this is wrongEng.size from createArraysForWrongAnswers()")
           // println("this is $wrongEng this is wrongEng from createArraysForWrongAnswers()i want watermelon")
                }
        println(result) // true
        val aWordTh = myArrays.tthaiFruit[numToInc]
        val result2 = wrongThai.contains(aWordTh)
        if(!result2){
            wrongThai.add(aWordTh)
            //println(wrongThai)
        }
    }
    ////////////////////////////////////////////////////////////////////
    private fun getGrade():Double
    {
        //println("$numOfCorrectE this is numOfCorrect from getGrade()")
        myGrades = (numOfCorrectE.toDouble()/sizeOfArray)*100
        return myGrades
    }
    ////////////////////////////////////////////////////////////////////

    private fun lessThanZero(allWrong:Double):Double {
       // println("${numOfErrorsE.toString()} this is numOfErrorsE from lessThanZero")
        if (allWrong < 0.0){
            myGrades = 0.0
        }
        return myGrades
    }
    /////////////////////////////////////////////////////////////////////
    private fun markToPass(allWrong:Double):String {
       // println("${numOfErrorsE.toString()} this is numOfErrorsE from markToPass")
        myGrades = if (allWrong >= 0.0){
            getGrade()
        } else{
            adjustedMark
        }
    return myGrades.toString()
    }
    //////////////////////////////////////////////////////////////////////
    private fun numOfMistakes()
    {
        if (numOfErrorsE == 0 && numToInc == sizeOfArray) {
            marksString = markToPass(getGrade())
            println(" $marksString this is marksString" )
            val intent = Intent(this, GradeForEnglish::class.java)
            intent.putExtra("key3", marksString)
            startActivity(intent)
        }
    }
    ///////////////////////////////////////////////////////////
    private fun cleanUpToContinue()
    {
        if (numToInc <= sizeOfArray) {
            userEnterE.setText("")
            useHint.alpha =1.toFloat()
            scrambledFieldE.alpha =1.toFloat()
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
    private fun reSetNumOfAttempts()
    {
        if (numOfAttempts == 3) {
            numOfAttempts = 0
            println("$numOfAttempts this is number of attempts called from reSetNumOfAttempts")
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
  }//end of class
//////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////







/*

  val rasbarry = arrayListOf(sound0,sound1,sound2, sound3)
        val myRandom = (0..3).random()
        val theFart = rasbarry[myRandom]
        soundPool.play(theFart, 1F, 1F, 0, 0, 1F)

 private fun determineErrorsSnd(): Int {
        val myRandom = (0..3).random()
        return rasbarry[myRandom]
    }


private fun getRandonErrorsnd():Int{
    val theSndFile = (0..3).random()
    var playThis = myArrays.errorSndArr[theSndFile]
    //val soundId = mSoundPool!!.load(this, R.raw.myArrays.errorSndArr[theSndFile], 1)
    return  soundId
}
private fun getRandonErrorsnd{
    val theSndFile = (0..3).random()
    val playThis = myArrays.errorSndArr[theSndFile]
    val soundId = mSoundPool!!.load(this, R.raw.playThis, 1)
}
///////////////////////////////////////////////////////

private fun determineErrorsSnd( ) {
     val myRandom = (0..3).random()
    when (myRandom) {
        0 -> sndOne()
        1 -> sndTwo()
        2-> sndThree()
        3-> sndFour()
        }
      }

 */

        /////////////////////////////////////////////////////////  /*
//    what I will attempt.
//    I want to collect the right answers and calculate a final mark from them.
//    This equals the number of correct answers divided by the number of questions * 100.
//    My problem is calculating the number of correct answers.
//    If the user only had one stab at spelling the word correctly this calculation would be easy.
//    However the user has a number of tries at spelling the word correctly.
//    I wish to increase the "numOfCorrect" variable  (+=1) only if the user gets it first time.
//    The checkWords() triggered by a button press will examine the user input.
//    If the user input is correct the "numOfCorrect" variable  (+=1) is incremented.
//    If the user input is incorrect the "else" is triggered.
//    This "else" provides the user a second opportunity to spell the word correctly.
//    If in the second opportunity the user spells the word correctly the "numOfCorrect" variable is incremented.
//    This can not happen > My code must be written to only increment when the user's first attempt is correct.
//
//     */



/*
fun findIndex(arr: Array<String>, item: String): Int {
return arr.indexOf(item)
}
//////////////////////////
fun findIndex(arr: Array<String>, item: String): Int {
val item = "watermelon"
return arr.indexOf(item)
}

private fun reSetForThreeErrors(){
numToInc += 1
endOfArray()
appleShot.setImageResource(fruitPhotos[numToInc])
dispEnglishWord.text = myArrays.tthaiFruit[numToInc]
with(scrambledFieldE) { text = myArrays.escrambledFruits[numToInc] }
userEnterE.setText("")
scrambledFieldE.alpha =0.toFloat()
0.toFloat().also { useHint.alpha = it }
//println("${numOfErrorsE.toString()} this is numOfErrorsE from reSetFruit()=0")
}

      println(" $numToInc this is numToInc" )
              //println("${numOfErrorsE.toString()} this is numOfErrorsE from reSetFruit()=0")
      println(" threeErrors () is called" )
      println("accumulateErrors is called" )
      //println("$myArrays.efruitTxt.size")
      //println("${numOfErrorsT.toString()} this is numOf Errors from respondToErrors")
      println(" $numOfErrorsE this is numOfErrorsE" )
    //println(" $adjustedMark  this is adjustedMark")
    //println(" $marksString  this is the result of my math")
    //println(" $numOfErrorsE this is number of errors" )
    //println(" $numOfAttempts this is number of attempts" )
    //println(myGrades.toString()) //createListsNoErrorMessage()
    //println(wrongEng).toString()
    // println(" IF in numOfMistakesIs3 is elected...  $numToInc this is numToInc after inc" )
    //println("cleanUpToContinue is called" )
    //println("${numOfErrorsE.toString()} this is numOfErrorsE called from accumulateErrors" )
     //println("Branch the next talk about transition of image" )
    //println(" $numOfErrorsE this is number of errors called from passPeram()" )
    //println(" $numOfAttempts this is number of attempts called from passPeram()" )
    //println("$numOfErrorsE) this is numOfErrorsE from endOfArray")
     println("$numOfAttempts this is number of attempts called from reSetNumOfAttempts")
     //println(" $numOfErrorsE this is number of errors called from whenWrongAnswer" )
     // println("$sizeOfArray  this is the size of the array") // println("${numOfErrorsE.toString()} this is numOfErrorsE from getGrade()")
     //       // println("${sizeOfArray.toString()} this is sizeOfArray from getGrade()")
     //        //val numToSubtract = accumulateErrors(numOfErrorsE)
     //        //determineErrorsFromAttempts( numOfAttempts)
     //        //val numOfCorrect = sizeOfArray - accumulateErrors(numOfErrorsE)
     //       // val numOfCorrect = sizeOfArray - determineErrorsFromAttempts( numOfAttempts)
     //        //if(numOfAttempts ==3){numOfErrorsE =2 }

    ////////////////////////////////////////////////////////////////////
    this to go  into "checkWords()"

     fun checkIndividualEntry(tries:Int)
     {
        when (tries)
          {
            1 -> resetForOneOrTwoAttempts()
            2 -> resetForOneOrTwoAttempts()
          {
      }

      ////////////////////////
      resetForOneOrTwoAttempts()
      {
      numToInc +=1     // if spelling correct increments arrays
      reSetFruit()     // if spelling correct uses numToInc to reset next image
      }


    ////////////////////////////////////////////////////////////////

  numOfCorrectE +=1
      numToInc +=1     // if spelling correct increments arrays
      numOfMistakes()  //  if spelling correct changes to new activity "GradeForEnglish"  gives 100%
      reSetFruit()     // if spelling correct uses numToInc to reset next image
    ////////////////////////////////////////////////


       fun determineErrorsFromAttempts( tries:Int,accumulatedWrong):Int {
        when (tries) {
            1 -> accumulatedWrong =1
            2 -> accumulatedWrong =1
            3 -> accumulatedWrong =1
            return accumulatedWrong
           {





        println("numOfMistakesIs3 this is called" )
        val intent = Intent(this, GradeForEnglish::class.java)
        intent.putExtra("key", wrongEng)
        intent.putExtra("key2", wrongThai)
        intent.putExtra("key3", marksString)
        startActivity(intent)

private fun reSetNumOfAttempts()
{
    if (numOfAttempts == 3) {
        numOfAttempts = 1
        println("$numOfAttempts this is number of attempts called from reSetNumOfAttempts")
    }
}
 ///////////////////////////////////////////////////////////////
 private fun advanceWhenCorrect()
{
    if (numOfAttempts == 0 )
    {
        numOfCorrect +=1
        numToInc +=1     // if spelling correct increments arrays
        numOfMistakes()  //  if spelling correct changes to new activity "GradeForEnglish"  gives 100%
        reSetFruit()     // if spelling correct uses numToInc to reset next image
     }else
     {
        numOfAttempts +=1
        respondToErrors()
        println(" $numOfAttempts this is number of attempts called from passPeram()")
        println("")
      }
 }



private fun setVar(any:Int,value:Int){

    any = 1
}
private fun createListsNoErrorMessage()
{
    if (numOfErrorsE == 0) {
        wrongEng.add("There are no mistakes")
        wrongThai.add("There are no mistakes")
        println(wrongEng).toString()
        //I am thinking this will resolve the "intent"
        // not passing an emptyArrayList
    }
}
///////////////////////////////////////////////////////

*/
/*
THIS WAS THE OLD CODE IN  checkWords()
THE PRObLEM WAS GETTING CORRECT GRADES AND IMAGE ADVANCING WITH A CORRECT ANSWER
  if (numOfAttempts == 0 )
  {
      numOfCorrectE +=1
      numToInc +=1     // if spelling correct increments arrays
      numOfMistakes()  //  if spelling correct changes to new activity "GradeForEnglish"  gives 100%
      reSetFruit()     // if spelling correct uses numToInc to reset next image
  }else
  {
      numOfAttempts +=1
      respondToErrors()
      //reSetFruit()
    //  println(" $numOfAttempts this is number of attempts called from advanceWhenCorrect()")
     // println("Get Fucking Fucked")
  }
 private fun accumulateErrors(incorrect:Int):Int {//use numOfErrorsE
        //println("${numOfErrorsE.toString()} this is numOfErrorsE from accumulateErrors")
        wrongAnswersE += 1
        /*
        when (incorrect) {
            in 0..100 -> wrongAnswersE += 1
        }

         */
        return wrongAnswersE
    }
    //////////////////////////////////////////////////////////////////////
   */






