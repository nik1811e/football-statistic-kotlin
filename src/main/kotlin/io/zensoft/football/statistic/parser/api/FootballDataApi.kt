package io.zensoft.football.statistic.parser.api

import com.google.gson.Gson
import io.zensoft.football.statistic.parser.domain.StandingDto
import io.zensoft.football.statistic.parser.domain.StandingType
import io.zensoft.football.statistic.parser.domain.response.StandingsResponse
import io.zensoft.football.statistic.parser.exception.ApiException
import org.slf4j.LoggerFactory
import java.net.HttpURLConnection
import java.net.HttpURLConnection.HTTP_OK
import java.net.URL

class FootballDataApi(private val accessKey: String) {

    fun getStandings(competitionId: Long): Map<StandingType, StandingDto> {
        if (competitionId !in AVAILABLE_COMPETITIONS) {
            throw IllegalArgumentException("Competition #$competitionId is not supported.")
        }

        val statistics = getForType("$API_BASE/competitions/$competitionId/standings", StandingsResponse::class.java)
        return statistics.standings.associateBy { it.type }
    }

    fun getAvailableCompetitionsIds(): List<Long> {
        return AVAILABLE_COMPETITIONS.toList()
    }

    private fun <T> getForType(requestUrl: String, type: Class<T>): T {
        try {
            val connection = URL(requestUrl).openConnection() as HttpURLConnection
            connection.setRequestProperty(HEADER_ACCEPT, MEDIA_TYPE_JSON)
            connection.setRequestProperty(HEADER_AUTH, accessKey)

            // handling error
            if (connection.responseCode != HTTP_OK) {
                val errorResponse = connection.errorStream.bufferedReader().use {
                    it.readText()
                }
                throw ApiException(errorResponse)
            }

            // --
            val successResponse = connection.inputStream.bufferedReader().use {
                it.readText()
            }

            return Gson().fromJson(successResponse, type)
        } catch (e: Exception) {
            log.error("Exception during request to $requestUrl", e)
            throw e
        }
    }

    companion object {
        private val log = LoggerFactory.getLogger(FootballDataApi::class.java)

        private const val HEADER_ACCEPT = "Accept"
        private const val HEADER_AUTH = "X-Auth-Token"

        private const val MEDIA_TYPE_JSON = "application/json"

        private const val API_BASE = "http://api.football-data.org/v2"

        private val AVAILABLE_COMPETITIONS = listOf(2000L, 2001L, 2002L, 2003L, 2013L, 2014L, 2015L, 2016L, 2017L, 2018L, 2019L, 2021L)
    }

}