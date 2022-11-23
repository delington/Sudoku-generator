package com.delington.sudoku.service.dealer

import com.delington.sudoku.constants.BLOCK_SIZE
import com.delington.sudoku.constants.SET_OF_SUDOKU_NUMBERS
import com.delington.sudoku.constants.SUDOKU_SIZE

class OrthogonalMatrixDealer : Dealer {

    override fun dealRandomNumbers(field: MutableList<MutableList<Int>>) {
        for (i in 0..SUDOKU_SIZE - BLOCK_SIZE step 3) {
            randomizeDiagonalBlock(i, field)
        }
    }

    private fun randomizeDiagonalBlock(index: Int, field: MutableList<MutableList<Int>>) {
        val simpleSet = SET_OF_SUDOKU_NUMBERS.toMutableSet()

        for (i in 0 until BLOCK_SIZE) {
            for (j in 0 until BLOCK_SIZE) {
                val randomElement: Int = simpleSet.random()
                field[index + i][index + j] = randomElement
                simpleSet.remove(randomElement)
            }
        }
    }
}