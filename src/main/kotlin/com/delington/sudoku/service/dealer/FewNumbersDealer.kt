package com.delington.sudoku.service.dealer

import com.delington.sudoku.constants.SET_OF_SUDOKU_NUMBERS
import com.delington.sudoku.constants.SUDOKU_MAX_NUMBER
import kotlin.random.Random

class FewNumbersDealer : Dealer {

    override fun dealRandomNumbers(field: MutableList<MutableList<Int>>) {
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
}