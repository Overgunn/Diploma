package misc.calculator

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test

class DivideFunc {

    @Test
    @Tags(Tag("misc"),Tag("calculator"))
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