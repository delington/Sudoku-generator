package com.delington.sudoku

fun main() {
    val field = MutableList(Sudoku.SIZE) {
        MutableList(Sudoku.SIZE) { 0 }
    }

    val sudoku = SudokuGrid(field)
    sudoku.generate()
}
