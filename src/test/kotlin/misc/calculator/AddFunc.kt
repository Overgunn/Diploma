package misc.calculator

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test


class AddFunc {

    @Test
    @Tags(Tag("misc"),Tag("calculator"))
    @DisplayName("Addition function")
    fun additionOfTwoNumbers() {
        val a = 1
        val b = 1

        val expectedResult = 2
        val actualResult = a + b
        println("$a + $b = $actualResult")
        expectedResult shouldBe actualResult
    }
}