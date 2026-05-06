package misc.arrays

import io.kotest.matchers.collections.shouldBeEmpty
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test

class ArrayEmptyTest {

    @Test
    @Tags(Tag("misc"), Tag("arrays"))
    @DisplayName("Arrays is empty")
    fun arrayEmptyCheck() {
        val intArray: Array<String> = arrayOf()

        intArray.shouldBeEmpty()
    }
}