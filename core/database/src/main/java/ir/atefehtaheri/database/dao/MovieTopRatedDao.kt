package ir.atefehtaheri.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ir.atefehtaheri.database.entities.MovieTopRatedEntity

@Dao
interface MovieTopRatedDao {

    @Insert
    fun insertMovieTopRatedAll(movieTopRated:List<MovieTopRatedEntity>)

    @Query("SELECT * FROM MovieTopRated")
    fun pagingSourceMovieTopRated():PagingSource<Int, MovieTopRatedEntity>

    @Query("DELETE FROM MovieTopRated")
    fun clearAllMovieTopRated()
}
