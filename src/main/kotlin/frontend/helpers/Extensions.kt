package frontend.helpers

class Extensions {
    companion object {
        fun String.toMoney(): Double {
            return this
                .replace("[^\\d,.]".toRegex(), "")  // убираем валюту, пробелы
                .replace(",", ".")                   // на случай "1,99"
                .toDouble()
        }
    }
}