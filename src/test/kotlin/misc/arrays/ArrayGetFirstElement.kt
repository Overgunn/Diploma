package misc.arrays

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test

class ArrayGetFirstElement {

    @Test
    @Tags(Tag("misc"),Tag("arrays"))
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