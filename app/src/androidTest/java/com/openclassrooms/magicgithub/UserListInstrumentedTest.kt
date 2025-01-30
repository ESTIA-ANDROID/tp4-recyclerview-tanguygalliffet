package com.openclassrooms.magicgithub

import com.openclassrooms.magicgithub.api.FakeApiServiceGenerator
import com.openclassrooms.magicgithub.di.Injection
import com.openclassrooms.magicgithub.model.User
import com.openclassrooms.magicgithub.repository.UserRepository
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class UserRepositoryTest {

    private lateinit var userRepository: UserRepository

    @Before
    fun setup() {
        Injection.resetRepository()
        userRepository = Injection.getRepository()
    }

    @Test
    fun getUsersWithSuccess() {
        val usersActual = userRepository.getUsers()
        val usersExpected = FakeApiServiceGenerator.FAKE_USERS
        assertEquals(usersExpected.size, usersActual.size)
    }

    @Test
    fun activateAndDeactivateUser() {
        val user = userRepository.getUsers()[0]
        assertTrue(user.isActive)

        // Deactivate
        user.isActive = false
        assertFalse(user.isActive)

        // Reactivate
        user.isActive = true
        assertTrue(user.isActive)
    }

    @Test
    fun reorderUsersWithSuccess() {
        val users = userRepository.getUsers().toMutableList()
        val user1 = users[0]
        val user2 = users[1]

        users.removeAt(0)
        users.add(1, user1)

        assertEquals(user2, users[0])
        assertEquals(user1, users[1])
    }
}
