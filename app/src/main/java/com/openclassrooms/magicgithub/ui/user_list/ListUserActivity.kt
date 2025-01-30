package com.openclassrooms.magicgithub.ui.user_list

import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.openclassrooms.magicgithub.databinding.ActivityListUserBinding
import com.openclassrooms.magicgithub.di.Injection
import com.openclassrooms.magicgithub.model.User

class ListUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListUserBinding
    private lateinit var adapter: UserAdapter
    private val userRepository = Injection.getRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Use ViewBinding
        binding = ActivityListUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        // Floating Action Button that adds a random user
        binding.activityListUserFab.setOnClickListener {
            userRepository.addRandomUser()
            adapter.updateUsers(userRepository.getUsers())
        }
    }

    private fun setupRecyclerView() {
        adapter = UserAdapter(
            userRepository.getUsers().toMutableList(),
            object : UserAdapter.Listener {
                override fun onClickDelete(user: User) {
                    userRepository.deleteUser(user)
                    adapter.updateUsers(userRepository.getUsers())
                }
            }
        )

        binding.activityListUserRv.layoutManager = LinearLayoutManager(this)
        binding.activityListUserRv.adapter = adapter

        // ItemTouchHelper for drag (UP/DOWN) & swipe (LEFT/RIGHT)
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val from = viewHolder.adapterPosition
                val to = target.adapterPosition
                if (from == RecyclerView.NO_POSITION || to == RecyclerView.NO_POSITION) {
                    return false
                }
                // Move item in our adapter
                adapter.moveUser(from, to)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                if (position == RecyclerView.NO_POSITION) return

                val user = adapter.getUserAt(position)
                // Toggle active/inactive
                user.isActive = !user.isActive
                adapter.notifyItemChanged(position)

                // Show a small message
                val status = if (user.isActive) "Activated" else "Deactivated"
                Snackbar.make(binding.root, "${user.login} $status", Snackbar.LENGTH_SHORT).show()
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    // Turn background red while swiping
                    viewHolder.itemView.setBackgroundColor(
                        if (dX != 0f) Color.RED else Color.WHITE
                    )
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
        })

        itemTouchHelper.attachToRecyclerView(binding.activityListUserRv)
    }
}
