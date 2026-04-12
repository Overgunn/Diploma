package misc.arrays

import io.kotest.matchers.collections.shouldNotBeEmpty
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import io.qameta.allure.Feature
import io.qameta.allure.Story
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags

@Feature("Array")
@Story("Basic array checks: array is not empty")
@Tags(Tag("array"))


class ArrayNotEmptyTest {
    @Test
    @DisplayName("Arrays is not empty")
    fun arrayNotEmptyCheck() {
        val intArray = arrayOf(1, 2, 3)
        val stringArray = arrayOf("abc", "def", "ghi")

        intArray.shouldNotBeEmpty()
        stringArray.shouldNotBeEmpty()
    }
}