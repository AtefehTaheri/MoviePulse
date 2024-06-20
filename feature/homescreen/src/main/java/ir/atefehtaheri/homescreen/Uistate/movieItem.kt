package ir.atefehtaheri.homescreen.Uistate

import ir.atefehtaheri.common.models.Type

data class movieItem(
    val title: String,
    val imgurl: String?,
    val id : Int,
    val vote_average: Double,
    val type: Type
)
