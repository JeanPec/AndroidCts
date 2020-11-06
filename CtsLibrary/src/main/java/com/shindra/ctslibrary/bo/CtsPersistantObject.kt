package com.shindra.ctslibrary.bo

import com.shindra.arrakis.bo.Persistant
import java.time.Duration
import java.util.*

open class CtsPersistentBo  : Persistant{

    /**
     * By default the object are valid for one hour
     */
    override fun isValid(dateOfInsertion: Date): Boolean {
        val now = Calendar.getInstance().time

        return false /*dateOfInsertion.time + hourToMs(1) < now.time*/
    }

    private fun hourToMs(hour : Int) : Long{
        return hour * 3600L
    }

}