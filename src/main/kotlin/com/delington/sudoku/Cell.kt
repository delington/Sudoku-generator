package com.delington.sudoku

import com.delington.sudoku.Sudoku2.Companion.SET_OF_SUDOKU_NUMBERS

data class Cell(
    var base: Boolean,
    var number: Int,
    var candidates: MutableSet<Int>
) {
    constructor(): this(true, 0, SET_OF_SUDOKU_NUMBERS.toMutableSet())
}
