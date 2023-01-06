package com.mahmudul.themoviedb_api.presentation.movie_details

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.load
import coil.request.ErrorResult
import coil.request.ImageRequest
import com.mahmudul.themoviedb_api.R
import com.mahmudul.themoviedb_api.common.Constants
import com.mahmudul.themoviedb_api.common.Resource
import com.mahmudul.themoviedb_api.data.model.movie_details.MovieDetailsResponse
import com.mahmudul.themoviedb_api.databinding.FragmentMovieDetailsBinding
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

@AndroidEntryPoint
class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {

    private val viewModel: MovieDetailsViewModel by viewModels()
    private val binding by viewBinding(FragmentMovieDetailsBinding::bind)
    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

      //  val data = args.movieId

        initViewCollect()
    }

    @SuppressLint("SetTextI18n")
    private fun initViewCollect() {
        with(viewModel) {
            with(binding) {
                getMovieDetails(args.movieId.toString(), Constants.TOKEN)
                viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                    state.collect { response ->
                        when (response) {
                            is Resource.Loading -> {
                                Log.e("MoiveResponse", "Loading")
                            }
                            is Resource.Success -> {
                                if (response.data.poster_path.isNotBlank()) {
                                    imageView.load(Constants.POSTER_PATH + response.data.poster_path) {
                                        placeholder(R.drawable.ic_launcher_background)

                                        listener(
                                            onSuccess = { _, _ ->
                                                Log.d(
                                                    "imageIssue",
                                                    "Success image Url = " + response.data.poster_path
                                                )
                                            },
                                            onError = { request: ImageRequest, error: ErrorResult ->
                                                request.error
                                                imageView.load(R.drawable.ic_launcher_background)
                                                Log.d(
                                                    "imageIssue",
                                                    "Exception image Url = " + response.data.poster_path + " Error $error"
                                                )
                                            }
                                        )
                                    }
                                } else {
                                    imageView.load(R.drawable.ic_launcher_background)
                                }

                                movieTitle.text = response.data.title
                                releasedDate.text = "Released Date : ${response.data.release_date}"
                                time.text = "Time : ${response.data.runtime}"
                                rated.text = "Vote average : ${response.data.vote_count}"
                                boxoffice.text = "Response : ${response.data.revenue}"
                                plot.text = response.data.overview

                                Log.e("MoiveResponse", response.data.id.toString())
                                // binding.result.text = response.data.toString()
                            }
                            is Resource.Error -> {
                                MotionToast.createColorToast(
                                    requireActivity(),
                                    "Error",
                                    response.throwable.localizedMessage ?: "Error",
                                    MotionToastStyle.ERROR,
                                    MotionToast.GRAVITY_TOP or MotionToast.GRAVITY_CENTER,
                                    MotionToast.LONG_DURATION,
                                    null
                                )
                                Log.e("MoiveResponse", response.throwable.localizedMessage ?: "Error")
                            }
                            else -> {
                                Log.e("MoiveResponse", "Unknown Error")
                            }
                        }
                    }
                }
            }
        }
    }

}