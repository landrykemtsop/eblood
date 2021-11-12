package com.fomus.eblood.utils

import java.util.*

object GeneralUtils {
    fun dateDiffDays(date1: Date, date2: Date): Long {
        val diff: Long = date1.getTime() - date2.time
        val seconds = diff / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        val days = hours / 24
        return days
    }
}