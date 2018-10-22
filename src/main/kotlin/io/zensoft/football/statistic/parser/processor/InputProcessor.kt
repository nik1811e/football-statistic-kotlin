package io.zensoft.football.statistic.parser.processor

import io.zensoft.football.statistic.parser.domain.StandingDto
import io.zensoft.football.statistic.parser.domain.TableDto
import java.util.*

class InputProcessor(standing: StandingDto) {

    private val tableByName = TreeMap<String, TableDto>()
    private val tableByNumber = TreeMap<Int, TableDto>()

    init {
        var counter = 0
        for (tableDto in standing.table) {
            tableByNumber[++counter] = tableDto
            tableByName[tableDto.team.name] = tableDto
        }
    }


    fun start() {
        Scanner(System.`in`).use {
            do {
                printPrompt()

                val command = it.nextLine().trim()
                if (command.isBlank()) {
                    continue
                }

                handle(command)
            } while (!command.equals(COMMAND_EXIT, true))
        }
    }

    private fun handle(command: String) {
        when (command.toLowerCase()) {
            COMMAND_EXIT -> printGoodBye()
            COMMAND_HELP -> printHelp()
            in REGEX_NUMBER -> searchByNumber(Integer.parseInt(command))
            else -> searchByName(command)
        }
    }

    private fun printGoodBye() {
        println("Thank you for attention")
        println("By Nikita Eliseenko")
    }

    private fun printLine() {
        System.out.println("----------------------------------")
    }

    private fun printPrompt() {
        printLine()
        println("Enter the team name (number) or $COMMAND_HELP")
        println("For exit: $COMMAND_EXIT")
    }

    private fun printHelp() {
        for (entry in tableByNumber) {
            System.out.printf("%2d. %s", entry.key, entry.value.team.name)
            System.out.println()
        }
    }

    private fun printTable(table: TableDto) {
        System.out.println(table.team.name)
        printLine()

        System.out.println("Total | Won | Draw | Lost | Points")
        printLine()
        System.out.printf(
                "%5d | %3d | %4d | %4d | %6d",
                table.playedGames,
                table.won,
                table.draw,
                table.lost,
                table.points
        )
        System.out.println()
    }

    private fun searchByNumber(number: Int) {
        val table = tableByNumber[number]
        if (table == null) {
            println("Can not find statistics for team #$number.")
            return
        }

        printTable(table)
    }

    private fun searchByName(name: String) {
        val table = tableByName[name]
        if (table == null) {
            println("Can not find statistics for team [$name]")
            return
        }

        printTable(table)
    }

    companion object {
        private const val COMMAND_HELP = "help"
        private const val COMMAND_EXIT = "exit"

        private val REGEX_NUMBER = Regex("\\d+")
    }
}

operator fun Regex.contains(text: CharSequence): Boolean = this.matches(text)
