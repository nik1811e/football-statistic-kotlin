package io.zensoft.football.statistic.parser.util

import org.slf4j.LoggerFactory
import java.util.*

object PropertyUtil {

    private val log = LoggerFactory.getLogger(PropertyUtil::class.java)

    private const val CONFIG_BASE_PATH = "configs"


    fun readValue(propertyFilename: String, key: String): String {
        try {
            val prop = Properties()
            prop.load(javaClass.classLoader.getResourceAsStream("$CONFIG_BASE_PATH/$propertyFilename.properties"))
            return prop.getProperty(key)
        } catch (ex: Exception) {
            log.error("Exception during fetching property by key #$key from $propertyFilename.properties file.", ex)
            throw ex
        }
    }

}