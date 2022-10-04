package com.example.flixster

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flixter2.R

public const val BASE_URL = "https://image.tmdb.org/t/p/w500/"


class ShowsRVA (
    private val shows: List<Show>,
    private val mListener: OnListFragmentInteractionListener?
        ) : RecyclerView.Adapter<ShowsRVA.ShowVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_show_item, parent, false)
        return ShowVH(view)
    }

    inner class ShowVH(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: Show? = null
        val mName: TextView = mView.findViewById<View>(R.id.tvName) as TextView
        val mPoster = mView.findViewById<ImageView>(R.id.tvPreview)

        override fun toString(): String {
            return mName.toString()
        }

    }

    override fun onBindViewHolder(holder: ShowVH, position: Int) {
        val show = shows[position]

        holder.mItem = show
        holder.mName.text = show.showName


        Glide.with(holder.mView)
            .load(BASE_URL + show.showPoster)
            .centerInside()
            .into(holder.mPoster)

        holder.mView.setOnClickListener {
            holder.mItem?.let { show ->
                mListener?.onItemClick(show)
            }
        }
    }

    override fun getItemCount(): Int {
        return shows.size
    }
}


