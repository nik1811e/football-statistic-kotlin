package io.zensoft.football.statistic.parser.domain

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TeamDto(

        @SerializedName("id")
        @Expose
        val id: Long = 0,

        @SerializedName("name")
        @Expose
        val name: String,

        @SerializedName("crestUrl")
        @Expose
        val crestUrl: String

)