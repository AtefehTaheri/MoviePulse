package ir.atefehtaheri.detailscreen.UiState

import ir.atefehtaheri.detailitem.repository.models.TvShowDetailDataModel


data class DetailTvShowState(
    val tvShowDetailDataModel: TvShowDetailDataModel? = null,
    val loading: Boolean = true,
)
