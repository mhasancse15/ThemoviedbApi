package com.mahmudul.themoviedb_api.domain.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.ErrorResult
import coil.request.ImageRequest
import com.mahmudul.themoviedb_api.R
import com.mahmudul.themoviedb_api.common.Constants
import com.mahmudul.themoviedb_api.data.model.movie.Movie
import com.mahmudul.themoviedb_api.databinding.RowMovieBinding

class MoviePagingDataAdapter() :
    PagingDataAdapter<Movie, MoviePagingDataAdapter.SearchImageViewHolder>(Diff) {
    companion object {
        val Diff = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(
                oldItem: Movie,
                newItem: Movie
            ): Boolean {
                return oldItem.id == newItem.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(
                oldItem: Movie,
                newItem: Movie
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchImageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return SearchImageViewHolder(
            RowMovieBinding.inflate(layoutInflater, parent, false)
        )
    }

    class SearchImageViewHolder(val binding: RowMovieBinding) :
        RecyclerView.ViewHolder(binding.root)

    @SuppressLint("CheckResult", "SetTextI18n")
    override fun onBindViewHolder(holder: SearchImageViewHolder, position: Int) {
        try {
            val movie = getItem(position)
            holder.itemView.apply {
                with(holder.binding) {
                    if (movie != null) {
                        movieNameTextView.text = movie.title
                        if (movie.poster_path.isNotBlank()) {
                            imageView.load(Constants.POSTER_PATH + movie.poster_path) {
                                placeholder(R.drawable.ic_launcher_background)

                                listener(
                                    onSuccess = { _, _ ->
                                        Log.d(
                                            "imageIssue",
                                            "Success image Url = " + movie.poster_path
                                        )
                                    },
                                    onError = { request: ImageRequest, error: ErrorResult ->
                                        request.error
                                        imageView.load(R.drawable.ic_launcher_background)
                                        Log.d(
                                            "imageIssue",
                                            "Exception image Url = " + movie.poster_path + " Error $error"
                                        )
                                    }
                                )
                            }
                        } else {
                            imageView.load(R.drawable.ic_launcher_background)
                        }
                        setOnClickListener {
                            onItemClickListener?.let { it(movie) }
                            Log.d("TAG", "${movie.id}")
                        }
                    }
                }
            }
            /* val movie = getItem(position)
             if (movie != null) {
                 with(holder.binding) {
                     movieNameTextView.text = movie.title
                     if (movie.poster_path.isNotBlank()) {
                         imageView.load(Constants.POSTER_PATH+movie.poster_path) {
                             placeholder(R.drawable.ic_launcher_background)

                             listener(
                                 onSuccess = { _, _ ->
                                     Log.d(
                                         "imageIssue",
                                         "Success image Url = " + movie.poster_path
                                     )
                                 },
                                 onError = { request: ImageRequest, error: ErrorResult ->
                                     request.error
                                     imageView.load(R.drawable.ic_launcher_background)
                                     Log.d(
                                         "imageIssue",
                                         "Exception image Url = " + movie.poster_path + " Error $error"
                                     )
                                 }
                             )
                         }
                     } else {
                         imageView.load(R.drawable.ic_launcher_background)
                     }
                     imageView.setOnClickListener {
                         onMovieItemClickListener.
                     }
                 }
             }*/
        } catch (ex: Exception) {
            ex.message
        }
    }


    private var onItemClickListener: ((Movie) -> Unit)? = null

    fun setOnItemClickListener(listener: (Movie) -> Unit) {
        onItemClickListener = listener

    }
}