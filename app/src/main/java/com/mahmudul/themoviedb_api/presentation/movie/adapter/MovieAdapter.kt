package com.mahmudul.themoviedb_api.presentation.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.mahmudul.themoviedb_api.databinding.RowMovieBinding
import com.mahmudul.themoviedb_api.domain.model.Movie

class MovieAdapter : PagingDataAdapter<Movie, MovieViewHolder>(ITunesDifferCallback()) {
    var onItemClickListener: ((Movie) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = RowMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position)!!, onItemClickListener)
    }
}

class ITunesDifferCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie) =
        oldItem == newItem

}