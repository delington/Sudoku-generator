package com.delington.sudoku

fun main() {
    val sudoku = SudokuField()

    sudoku.generateDiagonalBlocks()
    sudoku.printToConsole()
}
