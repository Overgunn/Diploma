package database

import database.helpers.ExposedHelper
import database.helpers.JDBCHelper
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test

class DbProductsTest {

    @Test
    @DisplayName("Test fetching all products from the with database with basic JDBC")
    @Tags(Tag("DB"),Tag("regress"),Tag("products"))
    fun testGetAllProducts() {
        val jdbcClient = JDBCHelper()

        val products = jdbcClient.getProducts()
        println(products)
    }

    @Test
    @DisplayName("Test fetching all products from the database with kotlin JDBC")
    @Tags(Tag("DB"),Tag("regress"),Tag("products"))
    fun testGetAllProductsNew() {
        val jdbcClient = JDBCHelper()

        val products = jdbcClient.getProductNew()
        println(products)
    }

    @Test
    @DisplayName("Test fetching all products from the database with Exposed helper JDBC")
    @Tags(Tag("DB"),Tag("regress"),Tag("products"))
    fun testGetAllProductsExposed() {
        val exposedHelper = ExposedHelper()

        val products = exposedHelper.getAllProductsExposed()
        println(products)
    }
}