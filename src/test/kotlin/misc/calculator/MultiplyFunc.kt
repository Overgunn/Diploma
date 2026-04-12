package misc.calculator

import io.kotest.matchers.shouldBe
import io.qameta.allure.Feature
import io.qameta.allure.Story
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test

@Feature("Simple calc operations")
@Story("multiply function")
@Tags(Tag("calculator"),Tag("multiply"),Tag("regression"))

class MultiplyFunc {

    @Test
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