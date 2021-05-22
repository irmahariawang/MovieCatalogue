package com.sekarlangitstudio.moviecatalogue.ui.television

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sekarlangitstudio.moviecatalogue.R
import com.sekarlangitstudio.moviecatalogue.data.source.local.entity.TelevisionEntity
import com.sekarlangitstudio.moviecatalogue.databinding.ItemCardViewBinding
import com.sekarlangitstudio.moviecatalogue.ui.detail.DetailActivity

class TelevisionAdapter : RecyclerView.Adapter<TelevisionAdapter.TvViewHolder>() {

    private var listTv = ArrayList<TelevisionEntity>()

    fun setTv(televisions: List<TelevisionEntity>?) {
        if (televisions.isNullOrEmpty()) return
        this.listTv.clear()
        this.listTv.addAll(televisions)
    }


    class TvViewHolder(private val binding: ItemCardViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(television: TelevisionEntity) {
            with(binding) {
                tvItemTitle.text = television.title
                tvItemDate.text =
                    itemView.resources.getString(R.string.airing_date, television.airingDate)
                tvItemScore.text =
                    itemView.resources.getString(R.string.user_score, television.score)
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_ID, television.televisionId)
                    intent.putExtra(
                        DetailActivity.EXTRA_SESSION,
                        itemView.context.resources.getString(R.string.televisions)
                    )
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(Uri.parse("file:///android_asset/" + television.imagePath + ".jpg"))
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(imgPoster)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        val itemsCardViewBinding =
            ItemCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvViewHolder(itemsCardViewBinding)
    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        val television = listTv[position]
        holder.bind(television)
    }

    override fun getItemCount(): Int = listTv.size

}