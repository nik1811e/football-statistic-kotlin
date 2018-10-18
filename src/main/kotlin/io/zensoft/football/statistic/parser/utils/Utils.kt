package io.zensoft.football.statistic.parser.utils

import org.slf4j.LoggerFactory
import java.util.*

class Utils {
    private val LOGGER = LoggerFactory.getLogger(Utils::class.java)
    private val CONFIG_PROPERTIES = "configs/config.properties"

    fun readPropertiesValue(key: String): String {
        val prop = Properties()
        try {
            prop.load(
                    Utils::class.java.classLoader.getResourceAsStream(CONFIG_PROPERTIES))
            return prop.getProperty(key)
        } catch (ex: Exception) {
            LOGGER.error(Arrays.toString(ex.stackTrace))
        }
        return ""
    }
}