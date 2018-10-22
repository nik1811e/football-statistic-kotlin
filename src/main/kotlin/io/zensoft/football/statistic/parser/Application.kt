package io.zensoft.football.statistic.parser

import io.zensoft.football.statistic.parser.api.FootballDataApi
import io.zensoft.football.statistic.parser.domain.StandingType
import io.zensoft.football.statistic.parser.processor.InputProcessor
import io.zensoft.football.statistic.parser.util.PropertyUtil
fun main(args: Array<String>) {
    private const val DEFAULT_COMPETITION_ID = 2021L
    InputHandler().perform()
    fun main(args: Array<String>) {
        val competitionId = if (args.isEmpty()) {
            DEFAULT_COMPETITION_ID
        } else {
            args[0].toLong()
        }
    }
    val accessKey = PropertyUtil.readValue("api", "accessKey")
    val api = FootballDataApi(accessKey)
    val standing = api.getStandings(competitionId)[StandingType.TOTAL]!!
    val inputProcessor = InputProcessor(standing)
    inputProcessor.start()
}