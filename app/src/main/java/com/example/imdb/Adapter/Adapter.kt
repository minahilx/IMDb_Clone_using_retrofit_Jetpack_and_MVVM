package com.example.imdb.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imdb.R
import com.example.imdb.model.Movie
import com.example.imdb.ui.FragmentHome
import kotlinx.android.synthetic.main.movie_item_layout2.view.*


class Adapter (
    val movies: List<Movie>
):RecyclerView.Adapter<Adapter.MovieViewHolder>()

{

    class MovieViewHolder(view: View): RecyclerView.ViewHolder(view)
{    val image_url="https://image.tmdb.org/t/p/w500/"
    fun bindMovie(movie: Movie)

{
    itemView.movie_title.text=movie.title
    itemView.movie_duration.text= movie.original_language
    itemView.movie_category.text=movie.release
    itemView.rating_bar.rating=(movie.vote_average?.div(2)!!)//dividing total number of stars by 2
    Glide.with(itemView).load(image_url +movie.poster_path ).into(itemView.movie_img_id) //using glide library to display images
}


}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
       return MovieViewHolder(
           LayoutInflater.from(parent.context).inflate(R.layout.movie_item_layout2, parent, false)
       )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindMovie(movies.get(position))
        holder.itemView.cardViewItem.setOnClickListener {
       //     Log.d("adapter", "position =  $position")

                val obj= FragmentHome()
               obj.showDetails(position, movies)

        }


        }



    override fun getItemCount(): Int = movies.size



}


