package com.mango.catagories

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat

class ThaiCats : AppCompatActivity() {
    private var fruitPhotos=arrayOf(R.drawable.watermelon,R.drawable.apple/*,R.drawable.avocado,R.drawable.banana,
        R.drawable.blueberry,R.drawable.cantaloupe,R.drawable.cherry,R.drawable.grape,R.drawable.jackfruit,
        R.drawable.lemon,R.drawable.mango,R.drawable.orange,R.drawable.papaya,R.drawable.plum,R.drawable.pumpkin,
        R.drawable.strawberry,R.drawable.tomato,R.drawable.coconut,R.drawable.mangosteen,R.drawable.rambutan*/)
    /////////////////////////////////////////////////////////////////////
    private lateinit var appleShot: ImageView        //images of fruit in View
    private lateinit var userEntert: EditText        //user editText
    private lateinit var scrambledFieldt: TextView  //scrambled text
    private lateinit var useHintT: TextView          //instruction foe letter use
    private lateinit var dispEnglishWord: TextView  //user Thai Word
    private lateinit var wordInEArray:String         //reference
    private lateinit var marksForThai:String         //reference
    private var  wrongEng: ArrayList<String> = ArrayList()
    private var wrongThai: ArrayList<String> = ArrayList()
    private var numToInc:Int = 0                     //increments Arrays
    private var numOfErrors:Int = 0
    private var myGradesT:Int = 0//number of wrong answers "no repeats"
    private val myArrays = TheArrays()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_thai_cats)
        appleShot = findViewById(R.id.appleShot)
        appleShot.setImageResource(fruitPhotos[numToInc])
        dispEnglishWord = findViewById(R.id.dispEnglish_word)
        userEntert = findViewById(R.id.userEntert)
        scrambledFieldt = findViewById(R.id.scrambledFieldt)
        useHintT = findViewById(R.id.useHintT)
        dispEnglishWord.append(myArrays.efruitTxt[numToInc])
        scrambledFieldt.append(myArrays.tscrambleThFruit[numToInc])
        scrambledFieldt.alpha =0.toFloat()
        useHintT.alpha = 0.toFloat()
        wordInEArray = userEntert.toString()
        userEntert.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                //callback.invoke()

                checkWords()
                hideSoftKeyboard()
                true
            } else false
        }

    } //end of constructor
    private fun checkWords(){
        if(userEntert.text.toString() == myArrays.tthaiFruit[numToInc]){
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
        numOfErrors   +=1    //actual errors
        println(numOfErrors)
        whenWrongAnswer()
        threeErrors()
    }
    ///////////////////////////////////////////////////////////////////
    private fun whenWrongAnswer(){
        createArraysForWrongAnswers()
        userEntert.setText("")
        useHintT.alpha =1.toFloat()
        scrambledFieldt.alpha =1.toFloat()
    }
    ///////////////////////////////////////////////////////////////
    private fun reSetFruit(){
        numToInc +=1
        myGradesT += 5
        marksForThai = myGradesT.toString()
        endOfArray()
        appleShot.setImageResource(fruitPhotos[numToInc])
        dispEnglishWord.text = myArrays.efruitTxt[numToInc]
        scrambledFieldt.text = myArrays.tscrambleThFruit[numToInc]
        userEntert.setText("")
        scrambledFieldt.alpha =0.toFloat()
        0.toFloat().also { useHintT.alpha = it }
        //useHintT.alpha =0.toFloat()
        numOfErrors = 0
    }
    ///////////////////////////////////////////////////////////////
    private fun endOfArray(){
        if(numToInc == myArrays.efruitTxt.size ){

            println("this is where you switch activities... from FruitCat")
            val intent = Intent(this@ThaiCats, GradesForThai::class.java)
            intent.putExtra("key", wrongThai)
            intent.putExtra("key2", wrongEng)
            intent.putExtra("key3", marksForThai)
            startActivity(intent)
        }
    }
    private fun threeErrors() {
        if(numOfErrors ==3) {
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
        val aWordTh = myArrays.tthaiFruit[numToInc]
        val result2 = wrongThai.contains(aWordTh)
        if(!result2){
            wrongThai.add(aWordTh)
            // println(wrongThai)
        }
    }
    ///////////////////////////////////////////////////////////////////////
}//end of class


