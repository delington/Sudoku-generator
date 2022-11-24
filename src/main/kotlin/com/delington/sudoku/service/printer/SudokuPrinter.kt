package com.delington.sudoku.service.printer

import com.delington.sudoku.constants.SUDOKU_SIZE

class SudokuPrinter {

    fun printToConsole(field: MutableList<MutableList<Int>>) {
        println("----------------------------------")
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
}