package misc.arrays

import io.kotest.matchers.collections.shouldNotBeEmpty
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test

class ArrayNotEmptyTest {

    @Test
    @Tags(Tag("misc"),Tag("arrays"))
    @DisplayName("Arrays is not empty")
    fun arrayNotEmptyCheck() {
        val intArray = arrayOf(1, 2, 3)
        val stringArray = arrayOf("abc", "def", "ghi")

        intArray.shouldNotBeEmpty()
        stringArray.shouldNotBeEmpty()
    }
}