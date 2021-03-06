package com.sekarlangitstudio.moviecatalogue.ui.favorite.favmovie

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sekarlangitstudio.moviecatalogue.R
import com.sekarlangitstudio.moviecatalogue.data.source.local.entity.MovieEntity
import com.sekarlangitstudio.moviecatalogue.databinding.ItemCardViewBinding
import com.sekarlangitstudio.moviecatalogue.ui.detail.DetailActivity

class FavMovieAdapter :
    PagedListAdapter<MovieEntity, FavMovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.movieId == newItem.movieId
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val itemsFavoriteBinding =
            ItemCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemsFavoriteBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }

    class MovieViewHolder(private val binding: ItemCardViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding) {
                tvItemTitle.text = movie.title
                tvItemDate.text =
                    itemView.resources.getString(R.string.airing_date, movie.airingDate)
                tvItemScore.text = itemView.resources.getString(R.string.user_score, movie.score)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_ID, movie.movieId)
                    intent.putExtra(
                        DetailActivity.EXTRA_SESSION,
                        itemView.context.resources.getString(R.string.movies)
                    )
                    itemView.context.startActivity(intent)
                }

                Glide.with(itemView.context)
                    .load(Uri.parse("file:///android_asset/" + movie.imagePath + ".jpg"))
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgPoster)
            }
        }
    }


    //override fun getItemCount(): Int = listMovies.size

    fun getSwipedData(swipedPosition: Int): MovieEntity? = getItem(swipedPosition)

}