package misc.arrays

import io.kotest.matchers.shouldBe
import io.qameta.allure.Feature
import io.qameta.allure.Story
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags

@Feature("Array")
@Story("Basic array checks: get 1st element from array")
@Tags(Tag("misc"))

class ArrayGetFirstElement {
    @Test
    @DisplayName("Checks first element in given arrays")
    fun arrayGetFirstElement() {
        val numbers = arrayOf(1, 2, 3)
        val firstNumber = numbers.first()

        val itemsString = arrayOf("abc", "def", "ghi")
        val firstString = itemsString.first()

        firstNumber shouldBe 1
        firstString shouldBe "abc"

    }
}