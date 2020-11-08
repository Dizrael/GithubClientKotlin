package ru.dizraelapps.githubclientkotlin.mvp.model

import ru.dizraelapps.githubclientkotlin.mvp.model.entity.GithubUser

class GithubUserRepo {
    private val repositories = listOf(
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5")
    )

    fun getUsers(): List<GithubUser>{
        return repositories
    }

    fun getSingleUser(userIndex: Int): GithubUser{
        return repositories[userIndex]
    }
}