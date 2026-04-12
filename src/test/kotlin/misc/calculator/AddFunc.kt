package misc.calculator

import io.kotest.matchers.shouldBe
import io.qameta.allure.Feature
import io.qameta.allure.Story
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test

@Feature("Simple calc operations")
@Story("addition function")
@Tags(Tag("calculator"),Tag("addition"),Tag("regression"))

class AddFunc {

    @Test
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