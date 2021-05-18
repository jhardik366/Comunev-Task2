package com.example.comunev_task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RandomUserAdapter() : RecyclerView.Adapter<UserViewHolder>() {

    private val users: ArrayList<User> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        val currentItem = users[position]
        holder.title.text = currentItem.title
        holder.firstName.text = currentItem.firstName
        holder.lastName.text = currentItem.lastName
    }

    override fun getItemCount(): Int {

        return users.size
    }

    fun updateUsers(updatedStats: ArrayList<User>) {
        users.clear()
        users.addAll(updatedStats)
        notifyDataSetChanged()
    }
}

class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val title: TextView = itemView.findViewById(R.id.title)
    val firstName: TextView = itemView.findViewById(R.id.firstName)
    val lastName: TextView = itemView.findViewById(R.id.lastName)
}