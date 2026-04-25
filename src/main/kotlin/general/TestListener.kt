package general

import backend.controllers.Controllers
import backend.helpers.AuthorizationHelper
import backend.helpers.GarbageCollector
import com.codeborne.selenide.Selenide
import org.junit.platform.engine.TestExecutionResult
import org.junit.platform.launcher.TestExecutionListener
import org.junit.platform.launcher.TestIdentifier
import org.junit.platform.launcher.TestPlan

class TestListener: Controllers(), TestExecutionListener {

    private val authHelper = AuthorizationHelper()

    override fun testPlanExecutionStarted(testPlan: TestPlan) {
        Config.get
        println("|------ Starting test cycle -----|")
    }

    override fun executionStarted(testIdentifier: TestIdentifier) {
        if (!testIdentifier.isTest) return
        println("|--- TEST STARTED: ${testIdentifier.displayName}")
    }

    override fun executionFinished(
        testIdentifier: TestIdentifier,
        testExecutionResult: TestExecutionResult
    ) {}

    override fun executionSkipped(testIdentifier: TestIdentifier, reason: String) {
        if (!testIdentifier.isTest) return
        println("|--- TEST SKIPPED: ${testIdentifier.displayName} - Reason: $reason")
    }

    override fun testPlanExecutionFinished(testPlan: TestPlan) {
        println("|------ Test cycle finished -----|")
        Selenide.closeWebDriver()
        println("|------ Garbage collector -------|")
        GarbageCollector.user.forEach { id ->
            users.deleteUserById(token = authHelper.getAdminToken(), id = id)
                .also {println("Deleted user: $id")}
        }

        GarbageCollector.products.forEach { id ->
            products.deleteProductById(token = authHelper.getAdminToken(), id = id)
                .also { println("Deleted product: $id") }
        }

        GarbageCollector.order.forEach { id ->
            orders.deleteOrder(token = authHelper.getAdminToken(), id = id)
                .also { println("Deleted order: $id") }
        }
    }
}