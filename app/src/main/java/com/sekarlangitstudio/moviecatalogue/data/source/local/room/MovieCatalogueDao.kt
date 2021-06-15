package com.sekarlangitstudio.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sekarlangitstudio.moviecatalogue.data.source.local.entity.MovieEntity
import com.sekarlangitstudio.moviecatalogue.data.source.local.entity.TelevisionEntity

@Dao
interface MovieCatalogueDao {

    @Query("SELECT * FROM movieentities")
    fun getMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movieentities WHERE favorite = 1")
    fun getFavoriteMovie(): LiveData<List<MovieEntity>>

    @Transaction
    @Query("SELECT * FROM movieentities WHERE movieId = :movieId")
    fun getMovieById(movieId: String): LiveData<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)


    @Query("SELECT * FROM televisionentities")
    fun getTvs(): LiveData<List<TelevisionEntity>>

    @Query("SELECT * FROM televisionentities WHERE favorite = 1")
    fun getFavoriteTv(): LiveData<List<TelevisionEntity>>

    @Transaction
    @Query("SELECT * FROM televisionentities WHERE televisionId = :tvId")
    fun getTelevisionById(tvId: String): LiveData<TelevisionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvs(tvs: List<TelevisionEntity>)

    @Update
    fun updateTelevision(tv: TelevisionEntity)

}