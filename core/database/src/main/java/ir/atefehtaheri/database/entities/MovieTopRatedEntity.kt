package ir.atefehtaheri.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MovieTopRated")
data class MovieTopRatedEntity(
    @PrimaryKey(autoGenerate = true) val id_auto: Int = 0,
    val id: Int,
    val backdrop_path: String?,
    val title: String,
    val overview: String,
    val poster_path: String?,
    val release_date: String,
    val vote_average: Double
)