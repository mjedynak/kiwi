package io.github.fromsource.kiwi.core

interface BeComparable<T : Comparable<T>, R : BeComparable<T, R>> {
    fun actual(): T

    infix fun beLessThan(expected: T): R {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this as R
    }

    infix fun beGreaterThan(expected: T): R {
        val message = "${actual()} should be > $expected"
        assert(actual() > expected) { message }
        return this as R
    }
}