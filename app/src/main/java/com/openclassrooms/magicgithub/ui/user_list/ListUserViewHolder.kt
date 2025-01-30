package com.openclassrooms.magicgithub.ui.user_list

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.openclassrooms.magicgithub.databinding.ItemListUserBinding
import com.openclassrooms.magicgithub.model.User

class ListUserViewHolder(
    private val binding: ItemListUserBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User, callback: UserAdapter.Listener) {
        Glide.with(binding.root.context)
            .load(user.avatarUrl)
            .apply(RequestOptions.circleCropTransform())
            .into(binding.itemListUserAvatar)

        binding.itemListUserUsername.text = user.login


        ///set background color depending on isActive
        binding.root.setBackgroundColor(
            if (user.isActive) Color.WHITE else Color.RED
        )

        // Delete button
        binding.itemListUserDeleteButton.setOnClickListener {
            callback.onClickDelete(user)
        }
    }
}
