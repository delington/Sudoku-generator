package com.delington.sudoku


class SudokuField {

    private val field = Array(SIZE) {
        Array(SIZE) { 0 }
    }

    fun SudokuField() {
    }

    fun generateDiagonalBlocks() {
        for (i in 0 until SIZE) {
            for (j in 0 until SIZE) {
                randomizeDiagonalBLock(0..2, 0..2)
                randomizeDiagonalBLock(3..5, 3..5)
                randomizeDiagonalBLock(6..8, 6..8)
            }
        }
    }

    private fun randomizeDiagonalBLock(columnIndexes: IntRange, rowIndexes: IntRange) {
        val simpleSet = SET_OF_SUDOKU_NUMBERS.toMutableSet()

        for (i in rowIndexes) {
            for (j in rowIndexes) {
                val setElement: Int = simpleSet.random()
                field[i][j] = setElement
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
        val SET_OF_SUDOKU_NUMBERS = generateSequence(1) { it + 1 }.take(9)
    }
}
