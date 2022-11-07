package com.delington.sudoku

import kotlin.math.sqrt


class SudokuField {

    private val field = Array(SIZE) {
        Array(SIZE) { 0 }
    }

    fun SudokuField() {
    }

    // Sudoku Generator
    fun fillValues() {
        // Fill the diagonal blocks
        fillDiagonalBlocks()

        // Fill remaining blocks
        fillRemaining(0, BLOCK_SIZE)

        // Remove Randomly K digits to make game
        removeKDigits()
    }

    private fun fillRemaining(i: Int, srn: Any) {
        //TODO
    }

    private fun removeKDigits() {
        //TODO("Not yet implemented")
    }

    fun fillDiagonalBlocks() {
        for (i in 0..6 step 3) {
            randomizeDiagonalBlock(i)
        }
    }

    private fun randomizeDiagonalBlock(index: Int) {
        val simpleSet = SET_OF_SUDOKU_NUMBERS.toMutableSet()

        for (i in 0 until BLOCK_SIZE) {
            for (j in 0 until BLOCK_SIZE) {
                val setElement: Int = simpleSet.random()
                field[i + index][j + index] = setElement
                simpleSet.remove(setElement)
            }
        }
    }

    fun printToConsole() {
        for (i in 0 until SIZE) {
            printBlockSeparatorRowPart(i)

            for (j in 0 until SIZE) {
                printBlockSeparatorColumnPart(j)
                printElement(field[i][j])
            }
            println()
        }
    }

    private fun printBlockSeparatorColumnPart(j: Int) {
        if (j % 3 == 0) {
            print("  ")
        }
    }

    private fun printBlockSeparatorRowPart(i: Int) {
        if (i % 3 == 0) {
            println()
        }
    }

    private fun printElement(element: Int) = if (element == 0) {
        print(".  ")
    } else {
        print("$element  ")
    }

    companion object {
        const val SIZE = 9
        val BLOCK_SIZE = sqrt(SIZE.toDouble()).toInt()
        val SET_OF_SUDOKU_NUMBERS = generateSequence(1) { it + 1 }.take(9)
    }
}
