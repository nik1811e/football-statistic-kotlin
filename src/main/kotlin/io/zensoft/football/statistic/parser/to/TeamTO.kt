package io.zensoft.football.statistic.parser.to

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TeamTO {
    @SerializedName("id")
    @Expose
    var id: Long = 0
    @SerializedName("name")
    @Expose
    lateinit var name: String
    @SerializedName("crestUrl")
    @Expose
    lateinit var crestUrl: String

    constructor() {}

    /**
     * @param id
     * @param crestUrl
     * @param name
     */
    constructor(id: Long, name: String, crestUrl: String) : super() {
        this.id = id
        this.name = name
        this.crestUrl = crestUrl
    }
}