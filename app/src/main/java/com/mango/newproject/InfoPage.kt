package com.mango.newproject

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
Players attempt to spell the word,
that defines the image displayed on screen.
If the word is spelled correctly the image on screen will change 
to the next photo in that category and the game continues.

If the word is misspelled a new 
field is presented, wherein the letters to spell that word correctly 
are displayed.
However, the letters in this new field are scrambled.The player then 
unscrambles these letters in order spell the word that defines
the displayed image correctly.
Sound file prompts are provided
for each image.
If the vocabulary to be spelled has multiple words, 
for example "สามชั่วโมง" 
the scrambled word clue separates the individual words  
with a ' / ' character. ( i.e. ามส/ว ่ช  ั/มโง )
At the end of each category a score is awarded
as well as a list of corrections.
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