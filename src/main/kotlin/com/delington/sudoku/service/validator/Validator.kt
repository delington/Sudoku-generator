package com.delington.sudoku.service.validator

import mu.KotlinLogging

class Validator {

    companion object {
        private val logger = KotlinLogging.logger {}

        fun isValidByRows(field: MutableList<MutableList<Int>>): Boolean {
            for (i in 0 until field.size) {
                for (j in 0 until field[0].size) {
                    for (k in j + 1 until field[0].size) {
                        if (field[i][j] == field[i][k] && field[i][j] != 0) {
                            logger.debug("Something went wrong at row: $i, column: $j, next-column: $k")
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