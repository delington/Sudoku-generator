package com.delington.sudoku

import kotlin.math.sqrt


class Sudoku(
    private val missingNumberCount: Int,
    private val field: MutableList<MutableList<Int>>,
) {
    // Sudoku Generator
    fun fillValues() {
        // Fill the diagonal blocks
        fillDiagonalBlocks()

        // Fill remaining blocks
        //fillRemaining()
        //fillBlock2(0, 3, 3, SET_OF_SUDOKU_NUMBERS.toMutableSet())
        fillBlock3(0, 3, SET_OF_SUDOKU_NUMBERS.toMutableSet())

        // Remove Randomly K digits to make game
        removeKDigits()
    }

    private fun fillBlock3(startRow: Int, startColumn: Int, simpleSet: MutableSet<Int>) {
        while(simpleSet.isNotEmpty()) {
            val randomElement = simpleSet.random()
            simpleSet.remove(randomElement)

            placeElement(startRow, startColumn, randomElement)
            printToConsole()
            print("-----------${simpleSet.size}-----------")
        }
    }

    private fun placeElement(startRow: Int, startColumn: Int, randomElement: Int) {
        for (i in startRow until startRow + BLOCK_SIZE) {
            for (j in startColumn until startColumn + BLOCK_SIZE) {
                if (isNumberPlaceable(i, j, randomElement)) {
                    var currentField = field[i][j]
                    if (currentField == 0) {
                        field[i][j] = randomElement
                        return
                    } else {

                    }
                }
            }
        }
    }

    private fun fillRemaining() {
//        for (i in 0..SIZE - BLOCK_SIZE step 3) {
//            for (j in 0..SIZE - BLOCK_SIZE step 3) {
//                if (field[i][j] == 0) {
//                    fillBlock(i, j)
//                }
//            }
//        }
//        fillBlock(0, 3)
//        fillBlock(0, 6)
//        fillBlock(3, 0)
        fillBlockRecursive(0, 3, 3, SET_OF_SUDOKU_NUMBERS.toMutableSet())
    }

    private fun fillBlockRecursive(rowIndex: Int, columnIndex: Int, baseColumnIndex: Int, simpleSet: MutableSet<Int>): Unit {
        printToConsole()
        println("-------------set-size: ${simpleSet.size}----------------------")
        if (simpleSet.isEmpty()) {
            return
        }
        var i = rowIndex
        var j = columnIndex
        val blockRightMostIndex = baseColumnIndex + BLOCK_SIZE - 1
        if (columnIndex > blockRightMostIndex && rowIndex < BLOCK_SIZE - 1) {
            i = rowIndex + 1
            j = 0
        }

        val randomElement = simpleSet.random()

        if(isNumberPlaceable(rowIndex, columnIndex, randomElement)) {
            field[i][j] = randomElement
            simpleSet.remove(randomElement)
            return fillBlockRecursive(i, j + 1, baseColumnIndex, simpleSet)
        }

        return fillBlockRecursive(i, j + 1, baseColumnIndex, simpleSet)
    }

    private fun fillBlock2(rowIndex: Int, columnIndex: Int, baseColumnIndex: Int, simpleSet: MutableSet<Int>) {
        if (simpleSet.isEmpty()) {
            return
        }

        val randomElement = simpleSet.random()

        if(!isNumberPlaceable(rowIndex, columnIndex, randomElement)) {
            if(simpleSet.size > 3) {
                if (columnIndex == 2) {
                    simpleSet.add(field[rowIndex - 1][0])
                    return fillBlock2(rowIndex - 1, 0, baseColumnIndex, simpleSet)
                }
                simpleSet.add(field[rowIndex][columnIndex - 1])
                return fillBlock2(rowIndex, columnIndex - 1, baseColumnIndex, simpleSet)
            }
            if(columnIndex == baseColumnIndex + 2) {
                return fillBlock2(rowIndex - 1, baseColumnIndex, baseColumnIndex, simpleSet)
            }
            return fillBlock2(rowIndex, columnIndex - 1, baseColumnIndex, simpleSet)
        }

        field[rowIndex][columnIndex] = randomElement
        simpleSet.remove(randomElement)

        printToConsole()
        println("-------------set-size: ${simpleSet.size}----------------------")

        if (columnIndex == baseColumnIndex + 2) {
            return fillBlock2(rowIndex + 1, baseColumnIndex, baseColumnIndex, simpleSet)
        }
        return fillBlock2(rowIndex, columnIndex + 1, baseColumnIndex, simpleSet)
    }

    private fun removeKDigits() {
        //TODO("Not yet implemented")
    }

    private fun fillBlock(rowIndex: Int, columnIndex: Int) {
        val simpleSet = SET_OF_SUDOKU_NUMBERS.toMutableSet()

        for (i in 0 until BLOCK_SIZE) {
            for (j in 0 until BLOCK_SIZE) {
                printToConsole()
                println("-----------------------------------")
                var randomElement = simpleSet.random()
                val currentRowIndex = rowIndex + i
                val currentColumnIndex = columnIndex + j

                while (!isNumberPlaceable(currentRowIndex, currentColumnIndex, randomElement)) {
                    randomElement = simpleSet.random()
                }
                field[currentRowIndex][currentColumnIndex] = randomElement
                simpleSet.remove(randomElement)
            }
        }
    }

    private fun fillDiagonalBlocks() {
        for (i in 0..SIZE - BLOCK_SIZE step 3) {
            randomizeDiagonalBlock(i)
        }
    }

    fun isRowCorrect(rowIndex: Int, candidateNumber: Int): Boolean {
        for (j in 0 until SIZE) {
            if (field[rowIndex][j] == candidateNumber) {
                return false
            }
        }
        return true
    }

    fun isColumnCorrect(columnIndex: Int, candidateNumber: Int): Boolean {
        for (i in 0 until SIZE) {
            if (field[i][columnIndex] == candidateNumber) {
                return false
            }
        }
        return true
    }

    private fun isNumberPlaceable(rowIndex: Int, columnIndex: Int, candidateNumber: Int): Boolean =
        isRowCorrect(rowIndex, candidateNumber) && isColumnCorrect(columnIndex, candidateNumber)

    private fun randomizeDiagonalBlock(index: Int) {
        val simpleSet = SET_OF_SUDOKU_NUMBERS.toMutableSet()

        for (i in 0 until BLOCK_SIZE) {
            for (j in 0 until BLOCK_SIZE) {
                val randomElement: Int = simpleSet.random()
                field[index + i][index + j] = randomElement
                simpleSet.remove(randomElement)
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
        const val BLOCK_SIZE = 3
        val SET_OF_SUDOKU_NUMBERS = generateSequence(1) { it + 1 }.take(9)
    }
}
