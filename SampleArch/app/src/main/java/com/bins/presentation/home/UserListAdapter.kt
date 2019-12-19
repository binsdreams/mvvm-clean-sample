package com.bins.presentation.home

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bins.R
import com.bins.presentation.entity.User
import kotlinx.android.synthetic.main.user_item.view.*

class UserListAdapter : RecyclerView.Adapter<UserListAdapter.NewsViewHolder>() {

    var userlist = mutableListOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userlist.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(userlist[position])
    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(userInfo: User) {
            with(itemView) {
                userName.text = "$userInfo.fname $userInfo.lname"
            }
        }
    }

    fun updateList(list: List<User>) {
        if (list.isNotEmpty()) {
            userlist.clear()
            userlist.addAll(list)
            notifyDataSetChanged()
        }
    }
}