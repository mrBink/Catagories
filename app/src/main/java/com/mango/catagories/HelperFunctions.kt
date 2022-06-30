package com.mango.catagories

import java.io.Serializable
import kotlin.Function as KotlinFunction

class HelperFunctions {
    companion object {
        fun getMark(numberPlayed: Int, numberOfAttempts: Int): Double {
            var percent = 0.0
            percent = (numberPlayed.toDouble() / numberOfAttempts) * 100

            return percent
        }

        ///////////////////////////////////////////////////////
        //this will have numOfErrors as "mistakes" parameter it will have "three errors()" as the function parameter
        fun errorsAdvance(mistakes: Int, reactErrors: () -> Unit) {
            when (mistakes) {
                3 -> reactErrors()
                6 -> reactErrors()
                9 -> reactErrors()
                12 -> reactErrors()
                15 -> reactErrors()
                18 -> reactErrors()
                21 -> reactErrors()
                24 -> reactErrors()
                27 -> reactErrors()
                30 -> reactErrors()
                33 -> reactErrors()
                36 -> reactErrors()
                39 -> reactErrors()
                42 -> reactErrors()
                45 -> reactErrors()
                48 -> reactErrors()
                51 -> reactErrors()
                54 -> reactErrors()
                57 -> reactErrors()
                60 -> reactErrors()
            }

        }
        ///////////////////////////////////////////////////
        fun getGrade(arraySize:Int,numErrors:Int,theGrades:Double):Double{
            var marks = theGrades
            var numOfCorrect = arraySize - numErrors
            numOfCorrect = arraySize - numErrors
            marks=(numOfCorrect.toDouble()/arraySize)*100
            return marks

            //val numOfCorrect = sizeOfArray - numOfErrorsE
           // myGrades = (numOfCorrect.toDouble()/sizeOfArray)*100
            //return myGrades
        }

    }//end of Companion Object
      ///////////////////////////////////////////////




    //////////////////////////////////////////////////////
}  //end of Class

/*
 private fun serializeToString(any: Serializable?,anyWord:String,limit:String,missEng:List<String>,eleOne:String){
        var any2 = any
        var anyWord2 = anyWord
        var limit2 = limit
        var missEng2 =missEng
        var eleOne2 = eleOne
        anyWord2 = any2.toString()//Serializable to String
        println(anyWord2)
        limit2 = ","            //delimiter for split()
        missEng2 = element.split(limit2)//Serialized to List
        eleOne2 = missEng2[1]       //access element from list
        println(missEng2)
        println(eleOne2)
    }
 */