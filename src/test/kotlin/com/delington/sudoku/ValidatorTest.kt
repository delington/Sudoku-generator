package com.delington.sudoku

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream


internal class ValidatorTest {

    @Test
    fun isValidByRow() {
        val field = mutableListOf(
            mutableListOf(1, 2, 3),
            mutableListOf(4, 5, 6),
            mutableListOf(7, 0, 9)
        )
        assertFalse(Validator.isValidByRow(field, 0, 3))
        assertTrue(Validator.isValidByRow(field, 2, 8))
    }

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

    @Test
    fun isValidByColumn() {
        val field = mutableListOf(
            mutableListOf(1, 2, 3),
            mutableListOf(4, 5, 6),
            mutableListOf(7, 8, 9)
        )
        assertFalse(Validator.isValidByColumn(field, 0, 4))
        assertTrue(Validator.isValidByColumn(field, 2, 7))
    }

    @Test
    fun isValidByBlock() {
        val field = mutableListOf(
            mutableListOf(1, 2, 3),
            mutableListOf(4, 5, 6),
            mutableListOf(7, 0, 9)
        )
        assertFalse(Validator.isValidByBlock(field, 1, 1, 5))
        assertFalse(Validator.isValidByBlock(field, 2, 1, 5))
        assertTrue(Validator.isValidByBlock(field, 0, 1, 8))
        assertTrue(Validator.isValidByBlock(field, 2, 1, 8))
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