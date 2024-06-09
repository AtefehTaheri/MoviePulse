package ir.atefehtaheri.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.atefehtaheri.database.dao.MovieDao
import ir.atefehtaheri.database.dao.RemoteKeyDao
import ir.atefehtaheri.database.entities.RemoteKey
import ir.atefehtaheri.database.entities.UpcomingMovieEntity


@Database(entities =[UpcomingMovieEntity::class, RemoteKey::class], version = 1)
abstract class MovieDatabase:RoomDatabase() {
    abstract val movieDao: MovieDao
    abstract val remoteKeyDao: RemoteKeyDao
}