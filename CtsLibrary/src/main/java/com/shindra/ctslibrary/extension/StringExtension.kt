package com.shindra.ctslibrary.extension

import java.time.Duration

fun String.toDuration() = Duration.parse(this)