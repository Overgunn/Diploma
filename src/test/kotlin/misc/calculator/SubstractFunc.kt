package misc.calculator

import io.kotest.matchers.shouldBe
import io.qameta.allure.Feature
import io.qameta.allure.Story
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test

@Feature("Simple calc operations")
@Story("substraction function")
@Tags(Tag("misc"))

class SubtractFunc {

    @Test
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