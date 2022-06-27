package com.mango.catagories

import java.io.Serializable

class HelperFunctions {
    companion object {
        fun getMark(numberPlayed:Int,numberOfAttempts:Int):Double
        {
            var percent = 0.0
            percent = (numberPlayed.toDouble() / numberOfAttempts)*100

            return percent
        }


    }
    ///////////////////////////////////////////////////////////

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