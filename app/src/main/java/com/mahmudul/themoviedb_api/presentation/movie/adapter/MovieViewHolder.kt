package com.mahmudul.themoviedb_api.presentation.movie.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.request.ErrorResult
import coil.request.ImageRequest
import com.mahmudul.themoviedb_api.databinding.RowMovieBinding
import com.mahmudul.themoviedb_api.domain.model.Movie


class MovieViewHolder(private val binding: RowMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie, onItemClickListener: ((Movie) -> Unit)? = null) {
        binding.apply {

            movieNameTextView.text = movie.title
            if (movie.poster_path?.isNotBlank() == true) {
                imageView.load(com.mahmudul.themoviedb_api.common.Constants.POSTER_PATH + movie.poster_path) {
                    placeholder(com.mahmudul.themoviedb_api.R.drawable.ic_launcher_background)

                    listener(
                        onSuccess = { _, _ ->
                            android.util.Log.d(
                                "imageIssue",
                                "Success image Url = " + movie.poster_path
                            )
                        },
                        onError = { request: ImageRequest, error: ErrorResult ->
                            request.error
                            imageView.load(com.mahmudul.themoviedb_api.R.drawable.ic_launcher_background)
                            android.util.Log.d(
                                "imageIssue",
                                "Exception image Url = " + movie.poster_path + " Error $error"
                            )
                        }
                    )
                }
            } else {
                imageView.load(com.mahmudul.themoviedb_api.R.drawable.ic_launcher_background)
            }

            itemView.setOnClickListener {
                onItemClickListener?.invoke(movie)
            }
        }
    }
}
