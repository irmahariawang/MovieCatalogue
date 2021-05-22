package com.sekarlangitstudio.moviecatalogue.utils

import android.content.Context
import com.sekarlangitstudio.moviecatalogue.data.source.remote.response.MovieResponse
import com.sekarlangitstudio.moviecatalogue.data.source.remote.response.TelevisionResponse
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private val context: Context) {

    private fun parsingFileToString(fileName: String): String? {
        return try {
            val `is` = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    fun loadMovies(): List<MovieResponse> {
        val list = ArrayList<MovieResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("MovieResponses.json").toString())
            val listArray = responseObject.getJSONArray("movies")
            for (i in 0 until listArray.length()) {
                val movie = listArray.getJSONObject(i)

                val id = movie.getString("id")
                val title = movie.getString("title")
                val description = movie.getString("description")
                val airingDate = movie.getString("airingDate")
                val score = movie.getString("score")
                val genre = movie.getString("genre")
                val duration = movie.getString("duration")
                val director = movie.getString("director")
                val casting = movie.getString("casting")
                val imagePath = movie.getString("imagePath")

                val movieResponse = MovieResponse(
                    id,
                    title,
                    description,
                    airingDate,
                    score,
                    genre,
                    duration,
                    director,
                    casting,
                    imagePath
                )
                list.add(movieResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }

    fun loadTelevisions(): List<TelevisionResponse> {
        val list = ArrayList<TelevisionResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("TvResponses.json").toString())
            val listArray = responseObject.getJSONArray("televisions")
            for (i in 0 until listArray.length()) {
                val tv = listArray.getJSONObject(i)

                val id = tv.getString("id")
                val title = tv.getString("title")
                val description = tv.getString("description")
                val airingDate = tv.getString("airingDate")
                val score = tv.getString("score")
                val genre = tv.getString("genre")
                val duration = tv.getString("duration")
                val director = tv.getString("director")
                val casting = tv.getString("casting")
                val imagePath = tv.getString("imagePath")

                val tvResponse = TelevisionResponse(
                    id,
                    title,
                    description,
                    airingDate,
                    score,
                    genre,
                    duration,
                    director,
                    casting,
                    imagePath
                )
                list.add(tvResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }
}