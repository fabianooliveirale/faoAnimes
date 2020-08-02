package com.fabiano.faoanime.screens.animes.adapter

import android.util.Log
import android.util.Log.d
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.fabiano.faoanime.R
import com.fabiano.faoanime.databinding.AdapterAnimesBinding
import com.fabiano.faoanime.models.Anime
import com.fabiano.faoanime.utils.ViewAnimation
import com.squareup.picasso.Picasso

class AnimesAdapter : RecyclerView.Adapter<AnimesAdapter.AnimesViewHolder>() {
    private var animes: ArrayList<Anime> = ArrayList()
    val animation = ViewAnimation()

    inner class AnimesViewHolder(
        val adapterAnimesBinding: AdapterAnimesBinding
    ) : RecyclerView.ViewHolder(adapterAnimesBinding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AnimesAdapter.AnimesViewHolder {
        val binding = DataBindingUtil.inflate<AdapterAnimesBinding>(
            LayoutInflater.from(parent.context),
            R.layout.adapter_animes,
            parent,
            false
        )
        return AnimesViewHolder(binding)
    }

    override fun getItemCount(): Int = animes.count()

    override fun onBindViewHolder(holder: AnimesAdapter.AnimesViewHolder, position: Int) {
        holder.adapterAnimesBinding.anime = animes[position]

        if (position % 2 == 0)
            animation.fadeInLeft(holder.adapterAnimesBinding.mainView, durationAnimation = 960)
        else
            animation.fadeInRight(holder.adapterAnimesBinding.mainView, durationAnimation = 960)
    }

    fun replace(animes: ArrayList<Anime>?) {
        this.animes.clear()
        this.animes = animes ?: arrayListOf()
        notifyDataSetChanged()
    }
}



