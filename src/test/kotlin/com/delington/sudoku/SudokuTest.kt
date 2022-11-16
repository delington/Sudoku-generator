package com.delington.sudoku

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class SudokuTest {

    @Test
    fun isRowCorrect() {
        val field = mutableListOf(
            mutableListOf(1, 2, 3),
            mutableListOf(4, 5, 6),
            mutableListOf(7, 8, 9)
        )
        val sudoku = Sudoku(2, field)

        assertFalse(sudoku.isRowCorrect(0, 1))
        assertFalse(sudoku.isRowCorrect(1, 5))
        assertTrue(sudoku.isRowCorrect(0, 5))
    }

    @Test
    fun isColumnCorrect() {
        val field = mutableListOf(
            mutableListOf(1, 4, 7),
            mutableListOf(2, 5, 8),
            mutableListOf(3, 6, 9)
        )
        val sudoku = Sudoku(2, field)

        assertFalse(sudoku.isColumnCorrect(0, 1))
        assertFalse(sudoku.isColumnCorrect(1, 6))
        assertTrue(sudoku.isColumnCorrect(2, 4))
    }
}