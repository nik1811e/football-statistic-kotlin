package io.zensoft.football.statistic.parser.domain


import com.google.gson.annotations.SerializedName

data class StandingDto(

        @SerializedName("stage")
        val stage: String,

        @SerializedName("type")
        val type: StandingType,

        @SerializedName("table")
        val table: List<TableDto> = emptyList()

)