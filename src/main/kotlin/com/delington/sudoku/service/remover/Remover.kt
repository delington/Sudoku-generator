package com.delington.sudoku.service.remover

import com.delington.sudoku.constants.SUDOKU_MAX_NUMBER
import kotlin.random.Random

interface Remover {
    val removableNumbersCount: Int

    fun removeNumbers(field: MutableList<MutableList<Int>>)
}

class FewNumbersRemover(
    override val removableNumbersCount: Int
) : Remover {

    override fun removeNumbers(field: MutableList<MutableList<Int>>) {
        val setOfCoords = mutableSetOf<Pair<Int, Int>>()

        for (i in 0..removableNumbersCount) {
            val randomCoords = getRandomIndexes()
            setOfCoords.add(randomCoords)

            field[randomCoords.first][randomCoords.second] = 0
        }
    }

    private fun getRandomIndexes(): Pair<Int, Int> {
        val randomRow = Random.nextInt(0, SUDOKU_MAX_NUMBER)
        val randomColumn = Random.nextInt(0, SUDOKU_MAX_NUMBER)

        return Pair(randomRow, randomColumn)
    }
}