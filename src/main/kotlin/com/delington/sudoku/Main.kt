package com.delington.sudoku

fun main() {
    val sudoku = SudokuField()

    sudoku.fillDiagonalBlocks()
    sudoku.printToConsole()
}
