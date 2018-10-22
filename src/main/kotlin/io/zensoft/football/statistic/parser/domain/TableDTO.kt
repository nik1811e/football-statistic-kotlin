package io.zensoft.football.statistic.parser.domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TableDTO {
    @SerializedName("team")
    @Expose
    lateinit var team: TeamDTO
    @SerializedName("playedGames")
    @Expose
    var playedGames: Long = 0
    @SerializedName("won")
    @Expose
    var won: Long = 0
    @SerializedName("draw")
    @Expose
    var draw: Long = 0
    @SerializedName("lost")
    @Expose
    var lost: Long = 0
    @SerializedName("points")
    @Expose
    var points: Long = 0

}