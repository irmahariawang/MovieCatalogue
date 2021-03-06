package com.sekarlangitstudio.moviecatalogue.ui.favorite.favtv

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
import com.sekarlangitstudio.moviecatalogue.data.source.local.entity.TelevisionEntity
import com.sekarlangitstudio.moviecatalogue.databinding.ItemCardViewBinding
import com.sekarlangitstudio.moviecatalogue.ui.detail.DetailActivity

class FavTvAdapter : PagedListAdapter<TelevisionEntity, FavTvAdapter.TvViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TelevisionEntity>() {
            override fun areItemsTheSame(
                oldItem: TelevisionEntity,
                newItem: TelevisionEntity
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: TelevisionEntity,
                newItem: TelevisionEntity
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TvViewHolder {
        val itemsFavoriteBinding =
            ItemCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvViewHolder(itemsFavoriteBinding)
    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        val tv = getItem(position)
        if (tv != null) {
            holder.bind(tv)
        }
    }

    inner class TvViewHolder(private val binding: ItemCardViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tv: TelevisionEntity) {
            with(binding) {
                tvItemTitle.text = tv.title
                tvItemDate.text = itemView.resources.getString(R.string.airing_date, tv.airingDate)
                tvItemScore.text = itemView.resources.getString(R.string.user_score, tv.score)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_ID, tv.televisionId)
                    intent.putExtra(
                        DetailActivity.EXTRA_SESSION,
                        itemView.context.resources.getString(R.string.televisions)
                    )
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(Uri.parse("file:///android_asset/" + tv.imagePath + ".jpg"))
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgPoster)
            }

        }
    }

    fun getSwipedData(swipedPosition: Int): TelevisionEntity? = getItem(swipedPosition)
}