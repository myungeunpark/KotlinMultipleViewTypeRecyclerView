package com.mepark.kotlinmultiviewlist

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DataAdaptor(val context : Context, list : MutableList<DataModel>) : RecyclerView.Adapter<DataAdaptor.DataViewHolder>() {

    private val datList = list

    companion object {

        val TYPE_MOVIE = 0
        val TYPE_DIRECTOR = 1
        val TYPE_HEADER = 2
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {

        val layout = when(viewType){

            TYPE_MOVIE -> R.layout.layout_movie
            TYPE_DIRECTOR -> R.layout.layout_director
            TYPE_HEADER -> R.layout.layout_header
            else -> throw IllegalArgumentException("Wrong argument")
        }
        val view = LayoutInflater.from(context).inflate(layout, parent, false)

        return DataViewHolder(view)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(context, datList[position])
    }

    override fun getItemCount(): Int {
        return datList.size
    }

    override fun getItemViewType(position: Int): Int {

        return when(datList[position]){

            is DataModel.Movie -> TYPE_MOVIE
            is DataModel.Director -> TYPE_DIRECTOR
            is DataModel.Header -> TYPE_HEADER
            else -> throw IllegalArgumentException("Wrong argument")
        }
    }

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(context:Context, dat : DataModel){

            when(dat){

                is DataModel.Movie -> bindMovie(context, dat)
                is DataModel.Director -> bindDirector(context, dat)
                is DataModel.Header -> bindHeader(context, dat)
            }
        }

        fun bindMovie(context:Context, dat : DataModel.Movie){

            // movie
            val mov_img  : ImageView = itemView.findViewById(R.id.movie_img)
            val mov_title : TextView = itemView.findViewById(R.id.movie_title)
            val mov_intro : TextView = itemView.findViewById(R.id.movie_intro)

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(dat.img)
                .into(mov_img)
            mov_title.text = dat.title
            mov_intro.text = dat.des
        }

        fun bindDirector(context:Context, dat : DataModel.Director){

            // director
            val dir_photo : ImageView = itemView.findViewById(R.id.dir_img)
            val dir_name : TextView = itemView.findViewById(R.id.dir_name)
            val dir_intro : TextView = itemView.findViewById(R.id.dir_intro)

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(dat.photo)
                .into(dir_photo)
            dir_name.text = dat.name
            dir_intro.text = dat.des
        }

        fun bindHeader(context:Context, dat : DataModel.Header){

            // header
            val header : TextView = itemView.findViewById(R.id.headerTextView)
            header.text = dat.name
        }
    }


}