package ir.atefehtaheri.toprated.remote.models

data class TopRatedTvShowDto(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)