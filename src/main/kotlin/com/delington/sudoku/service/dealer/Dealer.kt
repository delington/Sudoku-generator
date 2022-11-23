package com.delington.sudoku.service.dealer

interface Dealer {
    fun dealRandomNumbers (field: MutableList<MutableList<Int>>)
}