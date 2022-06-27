package com.mango.catagories

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat


class FruitCat : AppCompatActivity() {
    private var fruitPhotos=arrayOf(R.drawable.watermelon,R.drawable.apple,/*R.drawable.avocado,R.drawable.banana,
        R.drawable.blueberry,R.drawable.cantaloupe,R.drawable.cherry,R.drawable.grape,R.drawable.jackfruit,
        R.drawable.lemon,R.drawable.mango,R.drawable.orange,R.drawable.papaya,R.drawable.plum,R.drawable.pumpkin,
        R.drawable.strawberry,R.drawable.tomato,R.drawable.coconut,R.drawable.mangosteen,R.drawable.rambutan*/)
    /////////////////////////////////////////////////////////////////////
    private lateinit var appleShot:ImageView         //images of fruit in View
    private lateinit var userEnterE:EditText        //user editText
    private lateinit var scrambledFieldE:TextView   //scrambled text
    private lateinit var useHint:TextView         //instruction foe letter use
    private lateinit var dispThaiword:TextView        //user Thai Word
    private lateinit var wordInEArray:String        //reference
    private lateinit var marksString:String        //reference
    private var  wrongEng: ArrayList<String> = ArrayList()
    private var wrongThai: ArrayList<String> = ArrayList()
    private var numToInc:Int = 0
    private var numOfErrorsE:Int = 0  //num for incrementing array
    private var myGrades:Int = 0
    private val myArrays = TheArrays()
    /////////////////////////////////////////////////////////////////////

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruit_cat)
        appleShot = findViewById(R.id.appleShot)
        appleShot.setImageResource(fruitPhotos[numToInc])
        dispThaiword = findViewById(R.id.dispThai_word)
        userEnterE = findViewById(R.id.userEnter_E)
        scrambledFieldE = findViewById(R.id.scrambledField_e)
        useHint = findViewById(R.id.useHint)
        dispThaiword.append(myArrays.tthaiFruit[numToInc])
        scrambledFieldE.append(myArrays.escrambledFruits[numToInc])
        scrambledFieldE.alpha =0.toFloat()
        useHint.alpha =0.toFloat()
        wordInEArray = userEnterE.toString()

        userEnterE.setOnEditorActionListener { _, actionId, _ ->//activates "done keyboard"
            if (actionId == EditorInfo.IME_ACTION_DONE) {
              // println("$numToBeAttemptedE.....this is array all sizes")
                checkWords()
               hideSoftKeyboard()
                true
            } else false
        }

    } //end of constructor
    private fun checkWords(){
        if(userEnterE.text.toString() == myArrays.efruitTxt[numToInc]){
            reSetFruit()
        }
        else{
            respondToErrors()
        }
    }
    ///////////////////////////////////////////////////////////////////
    private fun Activity.hideSoftKeyboard() {
        currentFocus?.let {
            val inputMethodManager = ContextCompat.getSystemService(this, InputMethodManager::class.java)!!
            inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }
    ///////////////////////////////////////////////////////////////////
    private fun respondToErrors(){
        numOfErrorsE   +=1    //actual errors
        println(numOfErrorsE)
        whenWrongAnswer()
        threeErrors()
    }
    ///////////////////////////////////////////////////////////////////
    private fun whenWrongAnswer(){
        createArraysForWrongAnswers()
        //println(wrongEng)
        userEnterE.setText("")
        useHint.alpha =1.toFloat()
        scrambledFieldE.alpha =1.toFloat()
    }
    ///////////////////////////////////////////////////////////////
    private fun reSetFruit(){
    numToInc +=1
    myGrades += 5
    marksString = myGrades.toString()
     endOfArray()
    appleShot.setImageResource(fruitPhotos[numToInc])
        dispThaiword.text = myArrays.tthaiFruit[numToInc]
        with(scrambledFieldE) { text = myArrays.escrambledFruits[numToInc] }
        userEnterE.setText("")
    scrambledFieldE.alpha =0.toFloat()
        0.toFloat().also { useHint.alpha = it }
    numOfErrorsE = 0
    }
    ///////////////////////////////////////////////////////////////
    private fun endOfArray(){
        if(numToInc == myArrays.efruitTxt.size ){

            println("this is where you switch activities... from FruitCat")
            val intent = Intent(this@FruitCat, GradeForEnglish::class.java)

            intent.putExtra("key", wrongEng)
            intent.putExtra("key2", wrongThai)
            intent.putExtra("key3",marksString)
            startActivity(intent)
        }
    }
    ///////////////////////////////////////////////////////////////
        private fun threeErrors() {
        if(numOfErrorsE ==3) {
            whenWrongAnswer()
            reSetFruit()
        }
    }
    ///////////////////////////////////////////////////////////////
    private fun createArraysForWrongAnswers(){
     val aWord = myArrays.efruitTxt[numToInc]
        val result = wrongEng.contains(aWord)
        if(!result){
            wrongEng.add(aWord)
            //println(wrongEng)
                }
        println(result) // true
        val aWordTh = myArrays.tthaiFruit[numToInc]
        val result2 = wrongThai.contains(aWordTh)
        if(!result2){
            wrongThai.add(aWordTh)
           // println(wrongThai)
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////
}//end of class


