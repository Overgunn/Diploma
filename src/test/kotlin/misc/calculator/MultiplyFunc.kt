package misc.calculator

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test

class MultiplyFunc {

    @Test
    @Tags(Tag("misc"),Tag("calculator"))
    @DisplayName("Multiply function")
    fun multiplicationOfTwoNumbers() {
        val a = 2
        val b = 2

        val expectedResult = 4
        val actualResult = a * b
        println("$a * $b = $actualResult")
        expectedResult shouldBe actualResult
    }
}