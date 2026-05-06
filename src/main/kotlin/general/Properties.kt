package general

import java.util.*

object Config {
    private val DEFAULT_PROP_FILE = "/dev.properties"

    val get: Props by lazy {
        val fileName = System.getProperty("env_config", DEFAULT_PROP_FILE)

        val properties = Properties().apply {
            val stream = Config::class.java.getResourceAsStream(fileName)
                ?: throw IllegalStateException("Properties file '$fileName' not found")
            stream.use { load(it) }
        }


        Props(
            browserName = properties.getProperty("browser.name"),
            browserVersion = properties.getProperty("browser.version"),
            frontendUrl = properties.getProperty("frontend.url"),
            backendUrl = properties.getProperty("backend.url"),
            backendApiVersion = properties.getProperty("backend.api.version"),
            moonHost = properties.getProperty("moon.host")
        )
    }

    data class Props(
        val browserName: String,
        val browserVersion: String,
        val frontendUrl: String,
        val backendUrl: String,
        val backendApiVersion: String,
        val moonHost: String,
    )
}