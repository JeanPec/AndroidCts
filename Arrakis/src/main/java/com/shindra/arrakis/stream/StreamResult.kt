package com.shindra.arrakis.stream

sealed class StreamResult

class StreamLoading : StreamResult()
class StreamSuccess(val data : Any? = null) : StreamResult()
class StreamError(val error : Throwable) : StreamResult()