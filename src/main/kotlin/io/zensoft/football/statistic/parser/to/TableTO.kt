package io.zensoft.football.statistic.parser.to

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TableTO {
    @SerializedName("position")
    @Expose
    var position: Long = 0
    @SerializedName("team")
    @Expose
    lateinit var team: TeamTO
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

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     * @param draw
     * @param position
     * @param lost
     * @param points
     * @param won
     * @param team
     */
    constructor(position: Long, team: TeamTO, playedGames: Long, won: Long, draw: Long, lost: Long,
                points: Long) : super() {
        this.position = position
        this.team = team
        this.playedGames = playedGames
        this.won = won
        this.draw = draw
        this.lost = lost
        this.points = points
    }
}