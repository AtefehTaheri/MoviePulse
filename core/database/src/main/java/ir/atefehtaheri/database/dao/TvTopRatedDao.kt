package ir.atefehtaheri.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ir.atefehtaheri.database.entities.TvTopRatedEntity

@Dao
interface TvTopRatedDao {

    @Insert
    fun insertTvTopRatedAll(tvTopRated:List<TvTopRatedEntity>)

    @Query("SELECT * FROM TvTopRated")
    fun pagingSourceTvTopRated():PagingSource<Int, TvTopRatedEntity>

    @Query("DELETE FROM TvTopRated")
    fun clearAllTvTopRated()
}
