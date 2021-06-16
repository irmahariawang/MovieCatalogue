package com.sekarlangitstudio.moviecatalogue.ui.detail

import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.sekarlangitstudio.moviecatalogue.R
import com.sekarlangitstudio.moviecatalogue.data.source.local.entity.MovieEntity
import com.sekarlangitstudio.moviecatalogue.data.source.local.entity.TelevisionEntity
import com.sekarlangitstudio.moviecatalogue.databinding.ActivityDetailBinding
import com.sekarlangitstudio.moviecatalogue.databinding.ContentDetailBinding
import com.sekarlangitstudio.moviecatalogue.viewmodel.ViewModelFactory

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_SESSION = "extra_session"
    }

    private lateinit var detailContentDetailBinding: ContentDetailBinding
    lateinit var title: String
    private var isFavorite = false
    lateinit var movieEntity: MovieEntity
    lateinit var televisionEntity: TelevisionEntity
    lateinit var session: String
    lateinit var extraSession: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(activityDetailBinding.root)
        detailContentDetailBinding = activityDetailBinding.detailContent

        setSupportActionBar(activityDetailBinding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        activityDetailBinding.progressBar.visibility = View.VISIBLE
        detailContentDetailBinding.btnAddFavorite.visibility = View.VISIBLE

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val extraId = extras.getString(EXTRA_ID)
            extraSession = extras.getString(EXTRA_SESSION)!!

            if (extraSession != null) {
                if (extraId != null) {

                    viewModel.setSelectedId(extraId)


                    when (extraSession) {
                        resources.getString(R.string.movies) -> viewModel.detailMovie.observe(
                            this,
                            { movie ->
                                setDetailMovie(movie)
                                checkFavMovie(movie)
                                movieEntity = movie

                            })

                        resources.getString(R.string.televisions) -> viewModel.detailTv.observe(
                            this,
                            { television ->
                                setDetailTv(television)
                                checkFavTv(television)
                                televisionEntity = television

                            })

                    }

                    activityDetailBinding.progressBar.visibility = View.GONE
                    activityDetailBinding.content.visibility = View.VISIBLE


                }
            }

        }

        detailContentDetailBinding.btnAddFavorite.setOnClickListener {
            when (extraSession) {
                resources.getString(R.string.movies) -> {
                    viewModel.setFavoriteMovie(movieEntity)
                    setFavoriteState(true)
                }
                resources.getString(R.string.televisions) -> {
                    viewModel.setFavoriteTv(televisionEntity)
                    setFavoriteState(true)
                }
            }
        }


        detailContentDetailBinding.btnRemoveFavorite.setOnClickListener {
            when (extraSession) {
                resources.getString(R.string.movies) -> {
                    viewModel.setFavoriteMovie(movieEntity)
                    setFavoriteState(false)
                }
                resources.getString(R.string.televisions) -> {
                    viewModel.setFavoriteTv(televisionEntity)
                    setFavoriteState(false)
                }
            }
        }



        activityDetailBinding.fab.setOnClickListener {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle(resources.getString(R.string.share_title))
                .setText(resources.getString(R.string.share_text, title))
                .startChooser()
        }
    }

    private fun checkFavMovie(movie: MovieEntity?) {
        if (movie!!.favorite == true) {
            setFavoriteState(true)
        } else {
            setFavoriteState(false)
        }
    }


    private fun checkFavTv(television: TelevisionEntity?) {
        if (television!!.favorite == true) {
            setFavoriteState(true)
        } else {
            setFavoriteState(false)
        }
    }

    private fun setDetailMovie(movieEntity: MovieEntity) {
        title = movieEntity.title
        detailContentDetailBinding.tvDetailTitle.text = movieEntity.title
        detailContentDetailBinding.tvDetailDate.text = movieEntity.airingDate
        detailContentDetailBinding.tvDetailGenre.text = movieEntity.genre
        detailContentDetailBinding.tvDetailScore.text =
            resources.getString(R.string.userScore, movieEntity.score)
        detailContentDetailBinding.textDescription.text = movieEntity.description
        detailContentDetailBinding.textDuration.text = movieEntity.duration
        detailContentDetailBinding.textDirector.text = movieEntity.director
        detailContentDetailBinding.textCasting.text = movieEntity.casting

        Glide.with(this)
            .load(Uri.parse("file:///android_asset/" + movieEntity.imagePath + ".jpg"))
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(detailContentDetailBinding.imgDetailPoster)
    }

    private fun setDetailTv(tvEntity: TelevisionEntity) {
        title = tvEntity.title
        detailContentDetailBinding.tvDetailTitle.text = tvEntity.title
        detailContentDetailBinding.tvDetailDate.text = tvEntity.airingDate
        detailContentDetailBinding.tvDetailGenre.text = tvEntity.genre
        detailContentDetailBinding.tvDetailScore.text =
            resources.getString(R.string.userScore, tvEntity.score)
        detailContentDetailBinding.textDescription.text = tvEntity.description
        detailContentDetailBinding.textDuration.text = tvEntity.duration
        detailContentDetailBinding.textDirector.text = tvEntity.director
        detailContentDetailBinding.textCasting.text = tvEntity.casting

        Glide.with(this)
            .load(Uri.parse("file:///android_asset/" + tvEntity.imagePath + ".jpg"))
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(detailContentDetailBinding.imgDetailPoster)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFavoriteState(state: Boolean) {
        if (state) {
            detailContentDetailBinding.btnAddFavorite.visibility = View.GONE
            detailContentDetailBinding.btnRemoveFavorite.visibility = View.VISIBLE
        } else {
            detailContentDetailBinding.btnAddFavorite.visibility = View.VISIBLE
            detailContentDetailBinding.btnRemoveFavorite.visibility = View.GONE
        }
    }

    /*
    private fun checkFavoriteMovie(extraId: String): Boolean {
        val state: Boolean


     return state
    }

     */

}