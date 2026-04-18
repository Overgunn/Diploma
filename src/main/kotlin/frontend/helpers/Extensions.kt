package frontend.helpers

import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.SelenideElement

class Extensions {
    companion object {
        fun SelenideElement.shouldBeVisible(): Boolean {
            this.shouldBe(visible)
            return this.isDisplayed
        }
        fun String.toMoney(): Double {
            return this
                .replace("[^\\d,.]".toRegex(), "")  // убираем валюту, пробелы
                .replace(",", ".")                   // на случай "1,99"
                .toDouble()
        }
    }
}