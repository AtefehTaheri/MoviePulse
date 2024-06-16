package ir.atefehtaheri.tvairing.remote.models

data class TvAiringDto(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)