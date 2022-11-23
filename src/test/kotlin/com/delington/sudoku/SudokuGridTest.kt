package com.delington.sudoku

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class SudokuGridTest {

    @Test
    fun assignRandomNumbers() {
        val field = MutableList(Sudoku.SIZE) {
            MutableList(Sudoku.SIZE) { 0 }
        }

        val sudokuGrid = SudokuGrid(field)
        sudokuGrid.assignRandomNumbers()
        sudokuGrid.printToConsole()
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