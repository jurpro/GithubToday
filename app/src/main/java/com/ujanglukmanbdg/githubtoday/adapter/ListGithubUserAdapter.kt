package com.ujanglukmanbdg.githubtoday.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ujanglukmanbdg.githubtoday.data.GithubUser
import com.ujanglukmanbdg.githubtoday.R

class ListGithubUserAdapter(private val listGithubUser: ArrayList<GithubUser>): RecyclerView.Adapter<ListGithubUserAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_list_github_user, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, location, activeSince, photo) = listGithubUser[position]
        Glide.with(holder.itemView.context)
            .load(photo)
            .circleCrop()
            .into(holder.imgPhoto)
        holder.tvName.text = name
        holder.tvLocation.text = location
        holder.tvActiveSince.text = activeSince
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listGithubUser[position])
        }
    }

    override fun getItemCount(): Int = listGithubUser.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        var tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        var tvLocation: TextView = itemView.findViewById(R.id.tv_item_location)
        var tvActiveSince: TextView = itemView.findViewById(R.id.tv_item_active_since)
    }

    interface OnItemClickCallback {
        fun onItemClicked(datagithub: GithubUser)
    }
}