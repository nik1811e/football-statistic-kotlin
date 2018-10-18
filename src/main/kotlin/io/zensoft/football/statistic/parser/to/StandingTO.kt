package io.zensoft.football.statistic.parser.to


import com.google.gson.annotations.SerializedName

class StandingTO {
    @SerializedName("stage")
    lateinit var stage: String
    @SerializedName("type")
    lateinit var type: String
    @SerializedName("group")
    lateinit var group: Any
    @SerializedName("table")
    var table: List<TableTO> = emptyList()

    /**
     * No args constructor for use in serialization
     */
    constructor() {}

    /**
     * @param table
     * @param group
     * @param type
     * @param stage
     */

    constructor(stage: String, type: String, group: Any, table: List<TableTO>) : super() {
        this.stage = stage
        this.type = type
        this.group = group
        this.table = table
    }
}