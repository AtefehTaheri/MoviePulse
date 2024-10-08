package ir.atefehtaheri.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TvAiring")
data class TvAiringEntity(
    @PrimaryKey(autoGenerate = true) val id_auto: Int = 0,
    val id: Int,
    val backdrop_path: String?,
    val name: String,
    val overview: String,
    val poster_path: String?,
    val first_air_date: String,
    val vote_average: Double
)