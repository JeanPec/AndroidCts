package com.shindra.arrakis.bo

import com.google.gson.Gson
import java.util.*

interface Persistant {

    fun isValid(dateOfInsertion : Date) : Boolean

    fun toJson() : String{
        return Gson().toJson(this)
    }

}