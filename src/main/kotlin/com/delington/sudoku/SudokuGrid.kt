package com.delington.sudoku

import kotlin.random.Random

fun MutableList<BooleanArray>.copy() = Array(size) { get(it).clone() }

fun MutableList<MutableList<Int>>.copy() = map { it.toMutableList() }.toMutableList()

class SudokuGrid(
    private var field: MutableList<MutableList<Int>>
) {

    fun assignRandomNumbers() {
        val simpleSet = SET_OF_SUDOKU_NUMBERS.toMutableSet()

        while (simpleSet.isNotEmpty()) {
            val randomRow = Random.nextInt(0, SUDOKU_MAX_NUMBER)
            val randomColumn = Random.nextInt(0, SUDOKU_MAX_NUMBER)

            if (field[randomRow][randomColumn] == 0) {
                val randomNumber = simpleSet.random()
                field[randomRow][randomColumn] = randomNumber
                simpleSet.remove(randomNumber)
            }
        }
    }

    fun generate() {
        assignRandomNumbers()
        printToConsole()
        val result = solve(field, Pair(0, 0), -1)
    }

    fun solve(field: MutableList<MutableList<Int>>, currentEmptyCell: Pair<Int, Int>?, value: Int): Boolean {
        // There are no more cell to fill
        if (currentEmptyCell == null) {
            this.field = field
            printToConsole()
            return true
        }
        val copyField = field.copy()

        // If it's not the root call of this function
        if (value != -1) {
            copyField[currentEmptyCell.first][currentEmptyCell.second] = value
        }

        if (!isSudokuValid(copyField)) {
            return false
        }

        // Anyway we try numbers to fill the cell
        for (i in 1.. SUDOKU_MAX_NUMBER) {
            val nextCell = getNextEmptyCell(copyField)

            val foundSolution = solve(copyField, nextCell, i)
            if (foundSolution) {
                return true
            }
        }
        return false
    }

    private fun isSudokuValid(field: MutableList<MutableList<Int>>): Boolean {
        return Validator.isValidByRows(field)
                && Validator.isValidByColumns(field)
                && Validator.isValidByBlocks(field)
    }

    private fun getNextEmptyCell(field: MutableList<MutableList<Int>>): Pair<Int, Int>? {
        for (i in 0 until SUDOKU_SIZE) {
            for (j in 0 until SUDOKU_SIZE) {
                if (field[i][j] == 0) {
                    return Pair(i, j)
                }
            }
        }
        return null
    }

    fun printToConsole() {
        for (i in 0 until SUDOKU_SIZE) {
            printBlockSeparatorRowPart(i)

            for (j in 0 until SUDOKU_SIZE) {
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
        const val SUDOKU_SIZE = 9
        const val BLOCK_SIZE = 3
        const val SUDOKU_MAX_NUMBER = 9
        val SET_OF_SUDOKU_NUMBERS = generateSequence(1) { it + 1 }.take(9)
    }
}