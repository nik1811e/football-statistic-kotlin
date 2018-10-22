package io.zensoft.football.statistic.parser.domain


import com.google.gson.annotations.SerializedName

class StandingDTO {
    @SerializedName("table")
    var table: List<TableDTO> = emptyList()
}