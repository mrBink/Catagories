package com.mango.catagories

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.ImageButton
import android.widget.TextView

class InfoPage : AppCompatActivity() {
    private lateinit var bluebackarrow: ImageButton
    private lateinit var instructionField:TextView

    private lateinit var myInstruction: String

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_page)
        bluebackarrow = findViewById(R.id.bluebackarrow)
        instructionField = findViewById(R.id.instructionField)
    ///////////////////////////////////////////////////////////////////////////////////////
        myInstruction =
            """
The User,employing the language being
studied, will attempt to spell the word,
that defines the image displayed on screen. If 
the word is spelled correctly the image on  
screen will change to the next photo in that 
category.If the word is misspelled a new 
field is presented,wherein the appropriate
letters to spell the word are displayed.
However the letters in this new field are  
scrambled.The User is to unscrambles these
letters in order to make a second attempt at 
defining the image presented.
When the spelling is correct the image displayed changes to the next item in the category.
The Thai word defining the image accompanies each photo. 
Sound files in the Users language are provided 
for each image.
If the vocabulary to be spelled has multiple words, such as 'two hours' the scrambled word clue separates the individual words  with a '|' character. 
i.e. 'owt | orush'  
It is recommended that you toggle off the 
'predictive text' option in your device 
settings.
    """
    ///////////////////////////////////////////////////////////////////////////////////////

        instructionField.text = this.myInstruction
    ///////////////////////////////////////////////////////////////////////////////////////
        bluebackarrow.setOnClickListener {
            //println("numbers button clicked should see action  num  ThaiMonths")
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    ///////////////////////////////////////////////////////////////////////////////////////
        instructionField.movementMethod = ScrollingMovementMethod()
    }//end of constructor
    ///////////////////////////////////////////////////////////////////////////////////////

}//end of class