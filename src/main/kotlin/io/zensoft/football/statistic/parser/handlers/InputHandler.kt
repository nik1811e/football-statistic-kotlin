package io.zensoft.football.statistic.parser.handlers

import io.zensoft.football.statistic.parser.api.CallToApi
import io.zensoft.football.statistic.parser.to.StatisticTO
import io.zensoft.football.statistic.parser.to.TableTO
import io.zensoft.football.statistic.parser.utils.Utils
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import java.util.regex.Pattern

class InputHandler {

    private val HELP = "help"
    private val EXIT = "exit"

    companion object {
        private var to: StatisticTO? = null
        private var map: Map<Int, String> = emptyMap()
        init {
            to = CallToApi().call("http://api.football-data.org/v2/competitions/"
                    + Utils().readPropertiesValue("id") + "/standings")
            var index = 1
            map = HashMap()
            for (table: TableTO in to!!.standings[0].table) {
                (map as HashMap<Int, String>)[index++] = table.team.name
            }
        }
    }

    private fun handler(word: String) {
        if (!word.isEmpty()) {
            if (HELP.equals(word.trim(),true)) {
                outputHelp()
            } else if (EXIT.equals(word.trim(),true)) {
                System.err.println("Thank you for attention\nBy Nikita Eliseenko")
            } else if (!HELP.equals(word.trim(),true)) {
                handleInputTeam(word)
            } else {
                System.err.println("Please, enter the correct data.")
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
                System.out.println(searchResultByName(map.get(Integer.parseInt(word))))
            } else {
                System.err.println("Invalid number $word entered.")
            }
            input()
        } else {
            System.out.println(searchResultByName(word))
            input()
        }
    }

    private fun outputHelp() {
        var index = 1
        for (table in to!!.standings!!.get(0).table!!) {
            println("${index++} ${table.team.name}")
        }
        input()
    }

    private fun searchResultByName(name: String?): String {
        var r = ""
        for (table in to!!.standings!!.get(0).table!!) {
            if (table.team.name.equals(name,true)) {
                r = ("\n" + name + "\nTotal\tWon\tDraw\tLost\tPoints\n" + table.playedGames + "\t"
                        + table.won + "\t" + table.draw + "\t" + table.lost + "\t"
                        + table.points)
                break
            }
        }
        return r
    }

    private fun input() {
        try
        {
            println(("_____________________________________________________\n" +
                    "Enter the team name (number) or " + HELP +
                    "\nFor exit: " + EXIT))
            val standardInput = BufferedReader(InputStreamReader(System.`in`))
            handler(standardInput.readLine())
        }
        catch (e:Exception) {
            System.err.println("ERROR: " + e.message)
        }
    }
    fun perform() {
        input()
    }
}