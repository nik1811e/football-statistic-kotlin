package io.zensoft.football.statistic.parser.to

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StatisticTO {
    @SerializedName("standings")
    @Expose
    var standings: List<StandingTO> = emptyList()
}