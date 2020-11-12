package com.shindra.arrakis.extension

fun <T> List<T>?.toArrayList(): ArrayList<T>? {
    return ArrayList(this ?: ArrayList())
}