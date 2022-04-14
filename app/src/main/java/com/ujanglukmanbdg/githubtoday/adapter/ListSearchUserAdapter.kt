package com.ujanglukmanbdg.githubtoday.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ujanglukmanbdg.githubtoday.data.ItemsItem
import com.ujanglukmanbdg.githubtoday.databinding.ItemListSearchUserBinding

class ListSearchUserAdapter: RecyclerView.Adapter<ListSearchUserAdapter.ListViewSearchHolder>() {


    private val listSearchUsers =  ArrayList<ItemsItem>()
    private var onItemClickCallback: OnItemClickCallback? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setUser(items: ArrayList<ItemsItem>) {
        listSearchUsers.clear()
        listSearchUsers.addAll(items)
        this.notifyDataSetChanged()
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewSearchHolder(private val binding: ItemListSearchUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: ItemsItem) {
            with(binding) {
                Glide.with(this.imgItemPhotoSearch)
                    .load(user.avatarUrl)
                    .circleCrop()
                    .into(imgItemPhotoSearch)
                tvItemUsernameSearch.text = user.login
                tvUrlAddress.text = user.htmlUrl
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewSearchHolder {
        val binding = ItemListSearchUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewSearchHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewSearchHolder, position: Int) {
        holder.bind(listSearchUsers[position])
        holder.itemView.setOnClickListener { onItemClickCallback?.onItemClicked(listSearchUsers[position]) }
    }

    override fun getItemCount(): Int = listSearchUsers.size

    interface OnItemClickCallback {
        fun onItemClicked(user: ItemsItem)
    }

}