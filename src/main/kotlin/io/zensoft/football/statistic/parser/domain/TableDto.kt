package io.zensoft.football.statistic.parser.domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TableDto(

        @SerializedName("position")
        @Expose
        val position: Long = 0,

        @SerializedName("team")
        @Expose
        val team: TeamDto,

        @SerializedName("playedGames")
        @Expose
        val playedGames: Long = 0,

        @SerializedName("won")
        @Expose
        val won: Long = 0,

        @SerializedName("draw")
        @Expose
        val draw: Long = 0,

        @SerializedName("lost")
        @Expose
        val lost: Long = 0,

        @SerializedName("points")
        @Expose
        val points: Long = 0

)