package io.zensoft.football.statistic.parser.handlers

import io.zensoft.football.statistic.parser.api.FootballDataApi
import io.zensoft.football.statistic.parser.domain.StatisticDTO
import io.zensoft.football.statistic.parser.domain.TableDTO
import io.zensoft.football.statistic.parser.utils.Utils
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import java.util.regex.Pattern

class InputHandler {

    companion object {
        private var to: StatisticDTO? = null
        private var map: Map<Int, String> = HashMap()
        private val HELP = "help"
        private val EXIT = "exit"

        init {
            to = FootballDataApi().call("http://api.football-data.org/v2/competitions/"
                    + Utils().readPropertiesValue("id") + "/standings")
            var index = 1
            for (table: TableDTO in to!!.standings[0].table) {
                (map as HashMap<Int, String>)[index++] = table.team.name
            }
        }
    }

    fun perform() {
        input()
    }

    private fun handler(word: String) {
        if (!word.isEmpty()) {
            if (HELP.equals(word.trim(), true)) {
                outputHelp()
            } else if (EXIT.equals(word.trim(), true)) {
                println("Thank you for attention\nBy Nikita Eliseenko")
            } else if (!HELP.equals(word.trim(), true)) {
                handleInputTeam(word)
            } else {
                println("Please, enter the correct data.")
                input()
            }
        } else {
            System.err.println("You didn't enter anything.")
            input()
        }
    }

    private fun handleInputTeam(word: String) {
        if (Pattern.compile("\\d+").matcher(word).matches()) {
            if (Integer.parseInt(word) <= map.size) {
                println(searchResultByName(map[Integer.parseInt(word)]))
            } else {
                println("Invalid number $word entered.")
            }
            input()
        } else {
            println(searchResultByName(word))
            input()
        }
    }

    private fun outputHelp() {
        var index = 1
        for (table in to!!.standings[0].table) {
            println("${index++} ${table.team.name}")
        }
        input()
    }

    private fun searchResultByName(name: String?): String {
        var r = ""
        for (table in to!!.standings[0].table) {
            if (table.team.name.equals(name, true)) {
                r = ("\n" + name + "\nTotal\tWon\tDraw\tLost\tPoints\n" + table.playedGames + "\t"
                        + table.won + "\t" + table.draw + "\t" + table.lost + "\t"
                        + table.points)
                break
            }
        }
        return r
    }

    private fun input() {
        try {
            println(("_____________________________________________________\n" +
                    "Enter the team name (number) or " + HELP +
                    "\nFor exit: " + EXIT))
            val standardInput = BufferedReader(InputStreamReader(System.`in`))
            handler(standardInput.readLine())
        } catch (e: Exception) {
            println("ERROR: " + e.message)
        }
    }
}