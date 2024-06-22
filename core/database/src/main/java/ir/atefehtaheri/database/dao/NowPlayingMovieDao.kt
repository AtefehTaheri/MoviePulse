package ir.atefehtaheri.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import ir.atefehtaheri.database.entities.NowPlayingMovieEntity
import ir.atefehtaheri.database.entities.UpcomingMovieEntity

@Dao
interface NowPlayingMovieDao {

    @Insert
    fun insertAllNowPlayingMovie(nowplaying:List<NowPlayingMovieEntity>)

    @Query("SELECT * FROM NowPlayingMovie")
    fun pagingSourceNowPlayingMovie():PagingSource<Int, NowPlayingMovieEntity>

    @Query("DELETE FROM NowPlayingMovie")
    fun clearAllNowPlayingMovie()
}
