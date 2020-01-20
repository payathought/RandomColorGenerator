package com.example.randomcolorgenerator.model

import com.google.gson.annotations.SerializedName

data class ColorSchemeModel(val schemes : List<Scheme>)

data class Scheme(
    val timeStamp : String? = "",
    val colors : Array<String>,
    val id : String,
    val tags : List<Tags>

)

data class Tags (
    val id: String,
    val name : String
)
