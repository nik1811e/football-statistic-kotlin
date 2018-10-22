package io.zensoft.football.statistic.parser.domain.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.zensoft.football.statistic.parser.domain.StandingDto

data class StandingsResponse(

        @SerializedName("standings")
        @Expose
        val standings: List<StandingDto> = emptyList()

)