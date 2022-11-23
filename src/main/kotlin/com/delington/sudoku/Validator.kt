package com.delington.sudoku

import mu.KotlinLogging


class Validator {

    companion object {
        private val logger = KotlinLogging.logger {}

        fun isValidByRow(field: MutableList<MutableList<Int>>, rowIndex: Int, candidateNumber: Int): Boolean {
            for (j in 0 until field[0].size) {
                if (field[rowIndex][j] == candidateNumber) {
                    return false
                }
            }
            return true
        }

        fun isValidByColumn(field: MutableList<MutableList<Int>>, columnIndex: Int, candidateNumber: Int): Boolean {
            for (i in 0 until field.size) {
                if (field[i][columnIndex] == candidateNumber) {
                    return false
                }
            }
            return true
        }

        fun isValidByBlock(field: MutableList<MutableList<Int>>, rowIndex: Int, columnIndex: Int, candidateNumber: Int): Boolean {
            val baseRowIndex = getBaseIndex(rowIndex)
            val baseColumnIndex = getBaseIndex(columnIndex)

            for (i in baseRowIndex until baseRowIndex + (SudokuGrid.BLOCK_SIZE - 1)) {
                for (j in baseColumnIndex until baseColumnIndex + (SudokuGrid.BLOCK_SIZE - 1)) {
                    if (field[i][j] == candidateNumber) {
                        return false
                    }
                }
            }
            return true
        }

        private fun getBaseIndex(number: Int): Int {
            val times = number / Sudoku2.BLOCK_SIZE
            return times * Sudoku2.BLOCK_SIZE
        }

        fun isValidByRows(field: MutableList<MutableList<Int>>): Boolean {
            for (i in 0 until field.size) {
                for (j in 0 until field[0].size) {
                    for (k in j + 1 until field[0].size) {
                        if (field[i][j] == field[i][k] && field[i][j] != 0) {
                            logger.warn("Something went wrong at row: $i, column: $j, next-column: $k")
                            return false
                        }
                    }
                }
            }
            return true
        }

        fun isValidByColumns(field: MutableList<MutableList<Int>>): Boolean {
            for (j in 0 until field[0].size) {
                for (i in 0 until field.size) {
                    for (k in i + 1 until field.size) {
                        if (field[i][j] == field[k][j] && field[i][j] != 0) {
                            return false
                        }
                    }
                }
            }
            return true
        }

        fun isValidByBlocks(field: MutableList<MutableList<Int>>): Boolean {
            var fieldWithRows = mutableListOf<MutableList<Int>>()

            for (k in 0..6 step 3) {
                for (l in 0..6 step 3) {
                    var blockList = mutableListOf<Int>()
                    for (i in 0..2) {
                        for (j in 0..2) {
                            blockList.add(field[k + i][l + j])
                        }
                    }
                    fieldWithRows.add(blockList)
                }
            }

            return isValidByRows(fieldWithRows)
        }
    }
}