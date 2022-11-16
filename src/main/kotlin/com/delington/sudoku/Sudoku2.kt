package com.delington.sudoku

import java.util.concurrent.BlockingDeque

class Sudoku2 (
    private val missingNumberCount: Int,
) {
    private var field: MutableList<MutableList<Cell>> = MutableList(SIZE) {
        MutableList(SIZE) { Cell() }
    }

    fun printToConsole() {
        for (i in 0 until SIZE) {
            printBlockSeparatorRowPart(i)

            for (j in 0 until SIZE) {
                printBlockSeparatorColumnPart(j)
                printElement(field[i][j].number)
            }
            println()
        }
    }

    private fun printElement(element: Int) = if (element == 0) {
        print(".  ")
    } else {
        print("$element  ")
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

    fun fillValues() {
//        fillDiagonal()
        fillRemaining()
    }

    private fun fillRemaining() {
        for (i in 0 until SIZE) {
            for (j in 0 until SIZE) {
                val cell = field[i][j]

                if (cell.candidates.isNotEmpty() && cell.number == 0) {
                    val randomElement = cell.candidates.random()
                    cell.number = randomElement
                    removeCandidate(randomElement, i, j)
                }
            }
        }
    }

    private fun fillDiagonal() {
        for (i in 0..SIZE - BLOCK_SIZE step 3) {
            randomizeDiagonalBlock(i)
        }
    }

    private fun randomizeDiagonalBlock(index: Int) {
        for (i in 0 until BLOCK_SIZE) {
            for (j in 0 until BLOCK_SIZE) {
                val cell = field[index + i][index + j]
                val randomElement = cell.candidates.random()

                cell.number = randomElement
                removeCandidate(randomElement, i, j, index)
            }
        }
    }

    private fun removeCandidate(randomElement: Int, i: Int, j: Int) {
        removeCandidateFromRow(randomElement, i)
        removeCandidateFromColumn(randomElement, j)
        removeCandidateFromBox(randomElement, i, j)
    }

    private fun removeCandidate(randomElement: Int, i: Int, j: Int, index: Int) {
        removeCandidateFromRow(randomElement, i)
        removeCandidateFromColumn(randomElement, j)
        removeCandidateFromBox(randomElement, index + i, index + j)
    }

    private fun removeCandidateFromBox(randomElement: Int, i: Int, j: Int) {
        val baseRow = getBaseNumber(i)
        val baseColumn = getBaseNumber(j)

        for (k in 0 until BLOCK_SIZE) {
            for (l in 0 until BLOCK_SIZE) {
                removeElementCandidate(baseRow + k, baseColumn + l, randomElement)
            }
        }
    }

    private fun removeElementCandidate(i: Int, j: Int, randomElement: Int) {
        val currentElement = field[i][j]
        if (currentElement.candidates.contains(randomElement)) {
            currentElement.candidates.remove(randomElement)
        }
    }

    private fun getBaseNumber(number: Int): Int {
        val times = number/ BLOCK_SIZE
        return times * BLOCK_SIZE
    }

    private fun removeCandidateFromColumn(randomElement: Int, j: Int) {
        for (i in 0 until SIZE) {
            removeElementCandidate(i, j, randomElement)
        }
    }

    private fun removeCandidateFromRow(randomElement: Int, i: Int) {
        for (j in 0 until SIZE) {
            removeElementCandidate(i, j, randomElement)
        }
    }

    companion object {
        const val SIZE = 9
        const val BLOCK_SIZE = 3
        val SET_OF_SUDOKU_NUMBERS = generateSequence(1) { it + 1 }.take(9)
    }
}