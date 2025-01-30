package com.openclassrooms.magicgithub.api

import com.openclassrooms.magicgithub.api.FakeApiServiceGenerator.FAKE_USERS
import com.openclassrooms.magicgithub.model.User

class FakeApiService : ApiService {
    private val _users = FAKE_USERS.toMutableList() // Fixed: Removed duplicate initialization

    /**
     * Return a list of [User]
     */
    override fun getUsers(): List<User> {
        return _users.toList() // Return an immutable copy
    }

    /**
     * Generate a random [User] and add it to the [_users] list.
     */
    override fun addRandomUser() {
        val randomUser = FakeApiServiceGenerator.FAKE_USERS_RANDOM.randomOrNull()
        randomUser?.let { _users.add(it) }
    }

    /**
     * Delete a [User] from the [_users] list.
     */
    override fun deleteUser(user: User) {
        _users.remove(user)
    }
}
