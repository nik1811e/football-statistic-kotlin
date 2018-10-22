package io.zensoft.football.statistic.parser.to

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TeamTO {
    @SerializedName("name")
    @Expose
    lateinit var name: String
}