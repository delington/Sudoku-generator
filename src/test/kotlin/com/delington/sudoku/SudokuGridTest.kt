package com.delington.sudoku

import com.delington.sudoku.constants.SUDOKU_SIZE
import com.delington.sudoku.service.dealer.FewNumberDealer
import com.delington.sudoku.service.generator.SudokuGrid
import com.delington.sudoku.service.printer.SudokuPrinter
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class SudokuGridTest {

    @Test
    fun assignRandomNumbers() {
        val field = MutableList(SUDOKU_SIZE) {
            MutableList(SUDOKU_SIZE) { 0 }
        }

        val printer = SudokuPrinter()
        val sudokuGrid = SudokuGrid(field, FewNumberDealer(), printer)
        sudokuGrid.assignRandomNumbers()
        printer.printToConsole(field)
        checkAllNumbers(field)
    }

    private fun checkAllNumbers(field: MutableList<MutableList<Int>>) {
        val simpleSet = mutableSetOf<Int>()
        field.forEachIndexed { i, row ->
            row.forEachIndexed { j, element ->
                simpleSet.add(element)
            }
        }
        for (i in 0..9) {
            assertTrue(simpleSet.contains(i))
        }
    }
}