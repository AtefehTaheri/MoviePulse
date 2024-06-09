package ir.atefehtaheri.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import ir.atefehtaheri.database.entities.UpcomingMovieEntity

@Dao
interface MovieDao {

    @Upsert
    fun upsertUpcomingAll(upcoming:List<UpcomingMovieEntity>)
    @Query("SELECT * FROM upcoming")
    fun pagingSourceUpcoming():PagingSource<Int, UpcomingMovieEntity>

    @Query("DELETE FROM upcoming")
    fun clearAllUpcoming()
}
