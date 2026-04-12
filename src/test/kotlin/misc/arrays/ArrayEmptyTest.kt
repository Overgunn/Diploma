package misc.arrays

import io.kotest.matchers.collections.shouldBeEmpty
import io.qameta.allure.Feature
import io.qameta.allure.Story
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test

@Feature("Array")
@Story("Basic array checks: array is empty")
@Tags(Tag("array"))

class ArrayEmptyTest{
    @Test
    @DisplayName("Arrays is empty")
    fun arrayEmptyCheck() {
        val intArray: Array<String> = arrayOf()

        intArray.shouldBeEmpty()
    }
}