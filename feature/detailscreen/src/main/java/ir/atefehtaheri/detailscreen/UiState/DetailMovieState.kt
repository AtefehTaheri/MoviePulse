package ir.atefehtaheri.detailscreen.UiState

import ir.atefehtaheri.detailitem.repository.models.MovieDetailDataModel


data class DetailMovieState(
    val MovieDetailDataModel: MovieDetailDataModel?=null,
    val loading: Boolean = true,
)
