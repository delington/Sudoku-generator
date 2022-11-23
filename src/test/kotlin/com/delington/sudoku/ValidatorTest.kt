package com.delington.sudoku

import com.delington.sudoku.service.validator.Validator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream


internal class ValidatorTest {

    @ParameterizedTest
    @MethodSource("provideMatrixesForRowCheck")
    fun shouldBeValidByRows(field: MutableList<MutableList<Int>>, expectedBoolean: Boolean) {
        assertEquals(expectedBoolean, Validator.isValidByRows(field))
    }

    @ParameterizedTest
    @MethodSource("provideMatrixesForColumnCheck")
    fun shouldBeValidByColumns(field: MutableList<MutableList<Int>>, expectedBoolean: Boolean) {
        assertEquals(expectedBoolean, Validator.isValidByColumns(field))
    }

    @ParameterizedTest
    @MethodSource("provideMatrixesForBlockCheck")
    fun shouldBeValidByBlocks(field: MutableList<MutableList<Int>>, expectedBoolean: Boolean) {
        assertEquals(expectedBoolean, Validator.isValidByBlocks(field))
    }

    companion object {
        @JvmStatic
        fun provideMatrixesForRowCheck(): Stream<Arguments> =
            Stream.of(
                Arguments.of(mutableListOf(
                    mutableListOf(1, 2, 3, 0, 0),
                    mutableListOf(4, 5, 6, 0, 0),
                    mutableListOf(7, 0, 9, 0, 0)
                ), true),
                Arguments.of(mutableListOf(
                    mutableListOf(1, 2, 2, 0, 0),
                    mutableListOf(4, 5, 6, 0, 0),
                    mutableListOf(7, 0, 9, 0, 0)
                ), false)
            )

        @JvmStatic
        fun provideMatrixesForColumnCheck(): Stream<Arguments> =
            Stream.of(
                Arguments.of(mutableListOf(
                    mutableListOf(1, 2, 3, 0, 0),
                    mutableListOf(4, 5, 6, 0, 0),
                    mutableListOf(7, 0, 9, 0, 0),
                    mutableListOf(0, 0, 4, 0, 0)
                ), true),
                Arguments.of(mutableListOf(
                    mutableListOf(1, 2, 2, 0, 0),
                    mutableListOf(4, 5, 6, 0, 3),
                    mutableListOf(7, 0, 9, 0, 0),
                    mutableListOf(7, 0, 7, 0, 1),
                    mutableListOf(0, 0, 4, 0, 0)
                ), false)
            )

        @JvmStatic
        fun provideMatrixesForBlockCheck(): Stream<Arguments> =
            Stream.of(
                Arguments.of(mutableListOf(
                    mutableListOf(1, 2, 3, 0, 0, 0, 0, 0, 0),
                    mutableListOf(4, 5, 6, 0, 0, 0, 0, 0, 0),
                    mutableListOf(7, 0, 9, 0, 0, 0, 0, 0, 0),
                    mutableListOf(0, 0, 4, 0, 0, 0, 1, 2, 3),
                    mutableListOf(7, 0, 9, 0, 0, 0, 9, 8, 7),
                    mutableListOf(5, 0, 2, 0, 0, 0, 5, 6, 4),
                    mutableListOf(1, 0, 3, 0, 0, 0, 3, 2, 1),
                    mutableListOf(7, 5, 9, 0, 0, 0, 6, 8, 9),
                    mutableListOf(8, 2, 6, 0, 0, 0, 4, 5, 7)
                ), true),
                Arguments.of(mutableListOf(
                    mutableListOf(1, 2, 3, 0, 0, 0, 0, 0, 0),
                    mutableListOf(4, 5, 6, 0, 0, 0, 0, 0, 0),
                    mutableListOf(7, 0, 9, 0, 0, 0, 0, 0, 0),
                    mutableListOf(0, 0, 4, 0, 0, 0, 1, 2, 3),
                    mutableListOf(7, 0, 9, 0, 0, 0, 9, 8, 7),
                    mutableListOf(5, 0, 2, 0, 0, 0, 5, 6, 4),
                    mutableListOf(1, 0, 3, 0, 0, 0, 3, 2, 1),
                    mutableListOf(7, 5, 9, 0, 0, 0, 6, 8, 9),
                    mutableListOf(8, 2, 9, 0, 0, 0, 7, 5, 7)
                ), false),
            )
    }
}