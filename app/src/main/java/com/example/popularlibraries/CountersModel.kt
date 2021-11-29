package com.example.popularlibraries

import androidx.annotation.IntRange

class CountersModel {

    private val counters = mutableListOf(0, 0, 0)

    fun getCurrent(@IntRange(from = 0, to = 2) index: Int): Int {
        return counters[index]
    }

    fun increment(@IntRange(from = 0, to = 2) id: Int): Int {
        counters[id]++
        return getCurrent(id)
    }

    fun set(index: Int, value: Int) {
        counters[index] = value
    }
}