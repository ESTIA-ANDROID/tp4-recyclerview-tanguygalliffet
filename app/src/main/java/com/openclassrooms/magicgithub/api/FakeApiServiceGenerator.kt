package com.openclassrooms.magicgithub.api

import com.openclassrooms.magicgithub.model.User

object FakeApiServiceGenerator {

    private fun getAvatarUrl(name: String): String {
        return "https://ui-avatars.com/api/?name=${name.replace(" ", "+")}&size=512"
    }

    @JvmField
    val FAKE_USERS: List<User> = listOf(
        User("001", "Jake", getAvatarUrl("Jake")),
        User("002", "Paul", getAvatarUrl("Paul")),
        User("003", "Phil", getAvatarUrl("Phil")),
        User("004", "Guillaume", getAvatarUrl("Guillaume")),
        User("005", "Francis", getAvatarUrl("Francis")),
        User("006", "George", getAvatarUrl("George")),
        User("007", "Louis", getAvatarUrl("Louis")),
        User("008", "Mateo", getAvatarUrl("Mateo")),
        User("009", "April", getAvatarUrl("April")),
        User("010", "Louise", getAvatarUrl("Louise")),
        User("011", "Elodie", getAvatarUrl("Elodie")),
        User("012", "Helene", getAvatarUrl("Helene")),
        User("013", "Fanny", getAvatarUrl("Fanny")),
        User("014", "Laura", getAvatarUrl("Laura")),
        User("015", "Gertrude", getAvatarUrl("Gertrude")),
        User("016", "Chloé", getAvatarUrl("Chloé")),
        User("017", "April", getAvatarUrl("April2")),
        User("018", "Marie", getAvatarUrl("Marie")),
        User("019", "Henri", getAvatarUrl("Henri")),
        User("020", "Rémi", getAvatarUrl("Rémi"))
    )

    @JvmField
    val FAKE_USERS_RANDOM = mutableListOf(
        User("021", "Lea", getAvatarUrl("Lea")),
        User("022", "Geoffrey", getAvatarUrl("Geoffrey")),
        User("023", "Simon", getAvatarUrl("Simon")),
        User("024", "André", getAvatarUrl("André")),
        User("025", "Leopold", getAvatarUrl("Leopold"))
    )
}
