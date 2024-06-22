package com.example.movieapp.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.common.extentions.loadImage
import com.example.movieapp.databinding.ItemMoviesBinding
import com.example.movieapp.domain.model.Movie

class HomeAdapter(
    private val onMovieClick: (Int) -> Unit,
) : PagingDataAdapter<Movie, HomeAdapter.HomeViewHolder>(
    HomeDiffUtilCallBack()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HomeViewHolder(
        ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.firstOrNull() == ITEM_CHANGE) {
            getItem(position)?.let {
                holder.bind(it)
            }
        } else {
            super.onBindViewHolder(holder, position, payloads)
        }
    }

    inner class HomeViewHolder(private val binding: ItemMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movie) {
            with(binding) {
                ivMovie.loadImage(item.posterPath)
                tvMovieName.text = item.name
                tvMovieDate.text = item.firstAirDate
                tvMovieRate.text = item.voteAverageFormat
                scoreCircleView.setScore(item.voteAverage)
                root.setOnClickListener {
                    onMovieClick(item.id)
                }
            }
        }
    }

    class HomeDiffUtilCallBack : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(
            oldItem: Movie,
            newItem: Movie,
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Movie,
            newItem: Movie,
        ) = oldItem == newItem

        override fun getChangePayload(oldItem: Movie, newItem: Movie): Any? {
            return if (oldItem.id != newItem.id) {
                ITEM_CHANGE
            } else {
                super.getChangePayload(oldItem, newItem)
            }
        }
    }
}

private const val ITEM_CHANGE = "itemChange"