package ir.atefehtaheri.detailitem.repository.models

import ir.atefehtaheri.detailitem.remote.models.BackdropsitemDto


data class backdropsDataModel(
    val backdrops: List<String>
)

fun BackdropsitemDto.asBackdropsDataModel():backdropsDataModel{
    return backdropsDataModel(
        backdrops.map {
            it.file_path
        }
    )
}