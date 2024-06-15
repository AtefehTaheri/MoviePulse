package ir.atefehtaheri.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import ir.atefehtaheri.database.entities.UpcomingMovieEntity

@Dao
interface UpcomingMovieDao {

    @Upsert
    fun upsertUpcomingAll(upcoming:List<UpcomingMovieEntity>)
    @Query("SELECT * FROM Upcoming")
    fun pagingSourceUpcoming():PagingSource<Int, UpcomingMovieEntity>

    @Query("DELETE FROM Upcoming")
    fun clearAllUpcoming()
}
