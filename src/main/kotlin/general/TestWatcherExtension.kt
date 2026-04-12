package general

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.TestWatcher
import java.util.Optional

class TestWatcherExtension : TestWatcher {

    override fun testSuccessful(context: ExtensionContext) {
        println("|--- TEST PASSED: ${context.displayName}")
    }

    override fun testFailed(context: ExtensionContext, cause: Throwable?) {
        println("|--- TEST FAILED: ${context.displayName}")
        println("Cause: ${cause?.message}")
    }

    override fun testAborted(context: ExtensionContext, cause: Throwable?) {
        println("|--- TEST ABORTED: ${context.displayName}")
        println("Cause: ${cause?.message}")
    }

    override fun testDisabled(context: ExtensionContext, reason: Optional<String>) {
        println("|--- TEST DISABLED: ${context.displayName} - Reason: ${reason.orElse("No reason provided")}")
    }
}