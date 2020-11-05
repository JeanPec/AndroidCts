package com.shindra.ctslibrary.bo

import com.shindra.arrakis.bo.Persistant
import java.util.*

open class CtsPersistentObject  : Persistant{

    /**
     * By default the object are valid for one hour
     */
    override fun isValid(dateOfInsertion: Date): Boolean {
        val now = Calendar.getInstance().time

        return dateOfInsertion.time + hourToMs(1) < now.time
    }

    private fun hourToMs(hour : Int) : Long{
        return hour * 3600L
    }

}