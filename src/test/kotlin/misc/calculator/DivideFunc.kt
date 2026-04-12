package misc.calculator

import io.kotest.matchers.shouldBe
import io.qameta.allure.Feature
import io.qameta.allure.Story
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test

@Feature("Simple calc operations")
@Story("divide function")
@Tags(Tag("calculator"),Tag("divide"),Tag("regression"))

class DivideFunc {

    @Test
    @DisplayName("Dividing function")
    fun divideTwoNumbers() {
        val a = 9
        val b = 3

        val expectedResult = 3
        val actualResult = a / b
        println("$a / $b = $actualResult")
        expectedResult shouldBe actualResult
    }
}