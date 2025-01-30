package com.openclassrooms.magicgithub.di

import com.openclassrooms.magicgithub.api.FakeApiService
import com.openclassrooms.magicgithub.repository.UserRepository

object Injection {
    private var repository: UserRepository? = null

    fun getRepository(): UserRepository {
        if (repository == null) {
            repository = UserRepository(FakeApiService())
        }
        return repository!!
    }

    fun resetRepository() {
        repository = null
    }
}
