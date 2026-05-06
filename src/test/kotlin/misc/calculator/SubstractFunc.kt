package misc.calculator

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test

class SubtractFunc {

    @Test
    @Tags(Tag("misc"), Tag("calculator"))
    @DisplayName("Substraction function")
    fun subtractionOfTwoNumbers() {
        val a = 2
        val b = 1


        val expectedResult = 1
        val actualResult = a - b
        println("$a - $b = $actualResult")
        expectedResult shouldBe actualResult
    }
}