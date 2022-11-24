package com.delington.sudoku

import com.delington.sudoku.constants.SUDOKU_SIZE
import com.delington.sudoku.service.dealer.OrthogonalMatrixDealer
import com.delington.sudoku.service.generator.SudokuGrid
import com.delington.sudoku.service.printer.SudokuPrinter
import com.delington.sudoku.service.remover.FewNumbersRemover

fun main() {
    val field = MutableList(SUDOKU_SIZE) {
        MutableList(SUDOKU_SIZE) { 0 }
    }

    val sudoku = SudokuGrid(
        field,
        OrthogonalMatrixDealer(),
        FewNumbersRemover(20),
        SudokuPrinter()
    )

    sudoku.generate()
}
