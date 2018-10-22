package io.zensoft.football.statistic.parser.domain


import com.google.gson.annotations.SerializedName

class StandingTO {
    @SerializedName("table")
    var table: List<TableTO> = emptyList()
}