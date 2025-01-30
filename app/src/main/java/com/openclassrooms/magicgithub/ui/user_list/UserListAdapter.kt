package com.openclassrooms.magicgithub.ui.user_list

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.openclassrooms.magicgithub.databinding.ItemListUserBinding
import com.openclassrooms.magicgithub.model.User

class UserAdapter(
    private var users: MutableList<User>,
    private val listener: Listener
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    interface Listener {
        fun onClickDelete(user: User)
    }

    class UserViewHolder(val binding: ItemListUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemListUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]

        // Set user name
        holder.binding.itemListUserUsername.text = user.login

        // Load avatar with Glide
        Glide.with(holder.itemView.context)
            .load(user.avatarUrl)
            .into(holder.binding.itemListUserAvatar)

        // Background color depends on isActive
        holder.binding.root.setBackgroundColor(
            if (user.isActive) Color.WHITE else Color.RED
        )

        // Delete
        holder.binding.itemListUserDeleteButton.setOnClickListener {
            listener.onClickDelete(user)
        }
    }

    override fun getItemCount() = users.size

    fun updateUsers(newUsers: List<User>) {
        users.clear()
        users.addAll(newUsers)
        notifyDataSetChanged()
    }

    fun moveUser(fromPosition: Int, toPosition: Int) {
        if (fromPosition < 0 || toPosition < 0 || fromPosition >= users.size || toPosition >= users.size) return
        val movedUser = users.removeAt(fromPosition)
        users.add(toPosition, movedUser)
        notifyItemMoved(fromPosition, toPosition)
    }

    fun getUserAt(position: Int): User {
        return users[position]
    }
}
