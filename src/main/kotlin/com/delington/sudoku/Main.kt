package com.delington.sudoku

fun main() {
    val field = MutableList(Sudoku.SIZE) {
        MutableList(Sudoku.SIZE) { 0 }
    }

    val sudoku = Sudoku(2, field)
    sudoku.fillValues()
    sudoku.printToConsole()

    //val sudoku2 = Sudoku2(2)
    //sudoku2.fillValues()
    //sudoku2.printToConsole()
}
