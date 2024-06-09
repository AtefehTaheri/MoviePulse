package ir.atefehtaheri.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "upcoming")
data class UpcomingMovieEntity(
    @PrimaryKey
    val id: Int,
    val backdrop_path: String?,
    val original_title: String,
    val overview: String,
    val poster_path: String?,
    val release_date: String,
    val vote_average: Double
)