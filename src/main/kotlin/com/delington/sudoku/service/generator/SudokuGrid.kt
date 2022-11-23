package com.delington.sudoku.service.generator

import com.delington.sudoku.constants.SUDOKU_MAX_NUMBER
import com.delington.sudoku.constants.SUDOKU_SIZE
import com.delington.sudoku.service.dealer.Dealer
import com.delington.sudoku.service.printer.SudokuPrinter
import com.delington.sudoku.service.validator.Validator

fun MutableList<MutableList<Int>>.copy() = map { it.toMutableList() }.toMutableList()

class SudokuGrid(
    private var field: MutableList<MutableList<Int>>,
    private val dealer: Dealer,
    private val printer: SudokuPrinter
) {

    fun generate() {
        dealer.dealRandomNumbers(field)
        printer.printToConsole(field)
        solve(field, Pair(0, 0), -1)
        printer.printToConsole(field)
    }

    private fun solve(startField: MutableList<MutableList<Int>>, currentEmptyCell: Pair<Int, Int>?, value: Int): Boolean {
        // There are no more cell to fill
        if (currentEmptyCell == null) {
            field = startField
            return true
        }
        val copyField = startField.copy()

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
}