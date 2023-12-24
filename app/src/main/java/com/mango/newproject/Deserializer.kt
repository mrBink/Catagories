package com.mango.newproject

import java.io.Serializable

class Deserializer : Serializable {
    fun unDoThis(any: Serializable?): String {

        return any.toString()
    }
    //////////////////////////////////////////////////////////
}//end of class