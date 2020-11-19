package com.shindra.ctslibrary.mapper

import com.shindra.arrakis.extension.toArrayList
import com.shindra.ctslibrary.apibo.Lines
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line
import io.reactivex.rxjava3.functions.Function

class LinesMapper() : Function<Lines, ArrayList<Line>> {
    override fun apply(t: Lines?): ArrayList<Line> {
        return t?.linesDelivery?.line?.map {
            //Line(it.lineName ?: "DEFAULT", it.lineExtension?.routeType ?: RouteType.UNDEFINED)
            Line(it.lineRef ?: "DEFAULT", it.lineExtension?.routeType ?: RouteType.UNDEFINED)

        }.toArrayList() ?: ArrayList()
    }

}