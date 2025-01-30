package com.openclassrooms.magicgithub.utils

import androidx.recyclerview.widget.DiffUtil
import com.openclassrooms.magicgithub.model.User

class UserDiffCallback(private val newUsers: List<User>, private val oldUsers: List<User>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldUsers.size
    override fun getNewListSize(): Int = newUsers.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldUsers[oldItemPosition].id == newUsers[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldUsers[oldItemPosition] == newUsers[newItemPosition]
    }
}
