package com.example.imdb.ui
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imdb.Adapter.Adapter
import com.example.imdb.databinding.FragmentHomeBinding
import com.example.imdb.model.Movie
import com.example.imdb.model.Response
import com.example.imdb.viewmodels.HomeFragmentViewModel
import com.example.imdb.requests.APIServicee
import com.example.imdb.requests.MovieINterface
import kotlinx.android.synthetic.main.movie_item_layout_1.*
import retrofit2.Call
import retrofit2.Callback
import com.google.android.material.navigation.NavigationView

import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController

import androidx.navigation.NavDirections
import com.example.imdb.R
import kotlinx.android.synthetic.main.toolbar_layout.*


class FragmentHome : Fragment(com.example.imdb.R.layout.fragment_home) {

    fun getMovieData(callback: (List<Movie>) -> Unit) {
        val apiService = APIServicee.getInstance().create(MovieINterface::class.java)
        apiService.getMovieList().enqueue(object : Callback<Response> {
            override fun onResponse(
                call: Call<Response>,
                response: retrofit2.Response<Response>
            ) {
                return callback(response.body()!!.movie)
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }

    private lateinit var binding: FragmentHomeBinding
    private val viewmodel: HomeFragmentViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        binding.viewModel3 = viewmodel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.pouplarMovieRv.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        binding.pouplarMovieRv.setHasFixedSize(true)

        getMovieData { movies: List<Movie> ->
            binding.pouplarMovieRv.adapter = Adapter(movies)
        }


    }

    fun showDetails(pos: Int, movies: List<Movie>)
    {
        val action: NavDirections = FragmentHomeDirections.actionFragmentHomeToFragmentMovieDetail2(getSelectedMovie(pos, movies))
        findNavController().navigate(action)



    }

    private fun getSelectedMovie(pos:Int, movies: List<Movie>): Movie {
        if(movies!=null)
        {
            if(movies.size>0)
            {
                return movies.get(pos)

            }
        }
        return movies.get(pos)
    }





}






