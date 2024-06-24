package ir.atefehtaheri.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.atefehtaheri.database.dao.MovieTopRatedDao
import ir.atefehtaheri.database.dao.NowPlayingMovieDao
import ir.atefehtaheri.database.dao.UpcomingMovieDao
import ir.atefehtaheri.database.dao.RemoteKeyDao
import ir.atefehtaheri.database.dao.TvAiringDao
import ir.atefehtaheri.database.dao.TvTopRatedDao
import ir.atefehtaheri.database.entities.MovieTopRatedEntity
import ir.atefehtaheri.database.entities.NowPlayingMovieEntity
import ir.atefehtaheri.database.entities.RemoteKey
import ir.atefehtaheri.database.entities.TvAiringEntity
import ir.atefehtaheri.database.entities.TvTopRatedEntity
import ir.atefehtaheri.database.entities.UpcomingMovieEntity


@Database(entities =[
    UpcomingMovieEntity::class,
    RemoteKey::class, TvAiringEntity::class,
    NowPlayingMovieEntity::class,
    TvTopRatedEntity::class,
    MovieTopRatedEntity::class
    ], version = 1)
abstract class MovieDatabase:RoomDatabase() {
    abstract val upcomingMovieDao: UpcomingMovieDao
    abstract val remoteKeyDao: RemoteKeyDao
    abstract val nowPlayingMovieDao: NowPlayingMovieDao
    abstract val tvAiringDao: TvAiringDao
    abstract val tvTopRatedDao: TvTopRatedDao
    abstract val movieTopRatedDao: MovieTopRatedDao

}