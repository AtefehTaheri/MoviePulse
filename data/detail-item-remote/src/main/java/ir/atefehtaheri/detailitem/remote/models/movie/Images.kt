package ir.atefehtaheri.detailitem.remote.models.movie

data class Images(
    val backdrops: List<Backdrop>,
    val logos: List<Logo>,
    val posters: List<Poster>
)