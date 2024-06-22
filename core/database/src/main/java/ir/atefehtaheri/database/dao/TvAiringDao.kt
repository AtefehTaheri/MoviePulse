package ir.atefehtaheri.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ir.atefehtaheri.database.entities.TvAiringEntity

@Dao
interface TvAiringDao {

    @Insert
    fun insertTvAiringAll(tvAiring:List<TvAiringEntity>)

    @Query("SELECT * FROM TvAiring")
    fun pagingSourceTvAiring():PagingSource<Int, TvAiringEntity>

    @Query("DELETE FROM TvAiring")
    fun clearAllTvAiring()
}
