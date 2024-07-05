package com.example.movieapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.ItemGenreBinding
import com.example.movieapp.domain.model.Genre

class GenresAdapter() : ListAdapter<Genre, GenresAdapter.GenreViewHolder>(GenreDiffUtilCallback()) {

    override fun onBindViewHolder(
        holder: GenreViewHolder,
        position: Int,
    ) = holder.bind(getItem(position))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = GenreViewHolder(
        ItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    inner class GenreViewHolder(val binding: ItemGenreBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(genre: Genre) {
            binding.tvGenre.text = genre.name
        }
    }

    class GenreDiffUtilCallback : DiffUtil.ItemCallback<Genre>() {
        override fun areItemsTheSame(oldItem: Genre, newItem: Genre) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Genre, newItem: Genre) =
            oldItem == newItem
    }
}