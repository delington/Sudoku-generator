package com.delington.sudoku

import com.delington.sudoku.constants.SUDOKU_SIZE
import com.delington.sudoku.service.dealer.OrtogonalMatrixDealer
import com.delington.sudoku.service.generator.SudokuGrid
import com.delington.sudoku.service.printer.SudokuPrinter

fun main() {
    val field = MutableList(SUDOKU_SIZE) {
        MutableList(SUDOKU_SIZE) { 0 }
    }

    val dealer = OrtogonalMatrixDealer()
    val sudoku = SudokuGrid(field, dealer, SudokuPrinter())
    sudoku.generate()
}
