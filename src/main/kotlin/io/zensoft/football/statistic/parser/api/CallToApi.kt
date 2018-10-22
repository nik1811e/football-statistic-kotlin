package io.zensoft.football.statistic.parser.api

import com.google.gson.Gson
import io.zensoft.football.statistic.parser.to.StatisticTO
import io.zensoft.football.statistic.parser.utils.Utils
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class CallToApi {
    fun call(requestUrl: String): StatisticTO? {
        try {
            var output: String?
            val url = URL(requestUrl)
            val conn = url.openConnection() as HttpURLConnection

            conn.requestMethod = Utils().readPropertiesValue("method")
            conn.setRequestProperty("Accept", Utils().readPropertiesValue("contenttype"))
            conn.setRequestProperty("X-Auth-Token", Utils().readPropertiesValue("apikey"))

            if (conn.responseCode != 200) {
                throw RuntimeException("Failed : HTTP error code : " + conn.responseCode)
            }

            var br = BufferedReader(InputStreamReader((conn.inputStream)))

            var to: StatisticTO? = null
            output = br.readLine()
            while (output != null) {
                to = Gson().fromJson(output, StatisticTO::class.java)
                output = br.readLine()
            }
            conn.disconnect()
            return to
        } catch (e: Exception) {
            return null
        }
    }
}