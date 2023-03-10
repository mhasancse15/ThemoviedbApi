package com.mahmudul.themoviedb_api.presentation.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.mahmudul.themoviedb_api.R
import com.mahmudul.themoviedb_api.common.Constants
import com.mahmudul.themoviedb_api.common.Resource
import com.mahmudul.themoviedb_api.data.model.movie.Movie
import com.mahmudul.themoviedb_api.databinding.FragmentMovieBinding
import com.mahmudul.themoviedb_api.domain.adapter.MoviePagingDataAdapter
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle


@AndroidEntryPoint
class MovieFragment : Fragment(R.layout.fragment_movie) {
    private val viewModel: MovieViewModel by viewModels()
    private val binding by viewBinding(FragmentMovieBinding::bind)
    private lateinit var moviePagingDataAdapter: MoviePagingDataAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewCollect()
        moviePagingDataAdapter.setOnItemClickListener {
            findNavController().navigate(
                R.id.action_movieFragment_to_movieDetailsFragment,
                bundleOf("movie_id" to it.id.toString())
            )
        }
    }

    private fun initViewCollect() {
        with(viewModel) {
            with(binding) {
                moviePagingDataAdapter = MoviePagingDataAdapter()
                movieRecyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
                movieRecyclerView.apply {
                    layoutManager
                    adapter = moviePagingDataAdapter
                }
                movieRecyclerView.layoutManager


               //getTopRatedMovies(Constants.TOKEN, "en-US")
                lifecycleScope.launchWhenStarted {
                    getTopRatedMovies(Constants.TOKEN,"en-US")
                    state.collect{state->
                        state.let {
                            if (it != null) {
                                moviePagingDataAdapter.submitData(
                                    lifecycle,it
                                )
                            }
                        }

                    }
                }



                /*viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                    state.collect { response ->
                        when (response) {
                            is Resource.Loading -> {
                                Log.e("MoiveResponse", "Loading")
                            }
                            is Resource.Success -> {

                                moviePagingDataAdapter.submitData(
                                    lifecycle, PagingData.from(response.data.results)
                                )
                                Log.e("MoiveResponse", response.data.total_results.toString())
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
                }*/
            }
        }
    }
}