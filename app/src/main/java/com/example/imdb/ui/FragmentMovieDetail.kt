package com.example.imdb.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v4.media.session.MediaSessionCompat.Token.fromBundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.Person.fromBundle
import androidx.fragment.app.viewModels
import androidx.navigation.NavArgs
import androidx.navigation.NavArgumentBuilder
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import com.bumptech.glide.Glide
import com.example.imdb.R
import com.example.imdb.databinding.ActivityMainBinding.bind
import com.example.imdb.databinding.FargmentLoginBinding
import com.example.imdb.databinding.FragmentMovieDetailFragmentBinding
import com.example.imdb.model.Movie
import com.example.imdb.viewmodels.LoginFragmentViewModel
import com.example.imdb.viewmodels.MovieDetailsFragmentViewModel
import kotlinx.android.synthetic.main.movie_item_layout2.view.*

class FragmentMovieDetail : Fragment(R.layout.fragment_movie_detail_fragment) {
    private lateinit var binding: FragmentMovieDetailFragmentBinding
    private val viewmodel: MovieDetailsFragmentViewModel by viewModels()
lateinit var movie: Movie
      val image_url="https://image.tmdb.org/t/p/w500/aa60d07b5ba6b95ebc1e7b1148ffe894.jpg"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieDetailFragmentBinding.bind(view)
        binding.viewModel = viewmodel
        binding.lifecycleOwner = viewLifecycleOwner
    movie= FragmentMovieDetailArgs.fromBundle(requireArguments()).movieDetail
  //  Log.d("movie title : " , movie.title+"")
       binding.titleDetails.text=movie.title
      binding.ratingBar.rating=(movie.vote_average?.div(2)!!) //dividing total number of stars by 2
       binding.textView9.text= movie.overview
      Glide.with(this.requireActivity()).load(image_url +movie.poster_path ).into(binding.imageView3) //using glide library to display images

    }

}