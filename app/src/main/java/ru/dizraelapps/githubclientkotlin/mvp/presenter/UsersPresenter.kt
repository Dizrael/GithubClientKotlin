package ru.dizraelapps.githubclientkotlin.mvp.presenter

import moxy.MvpPresenter
import ru.dizraelapps.githubclientkotlin.GithubApplication
import ru.dizraelapps.githubclientkotlin.mvp.model.GithubUserRepo
import ru.dizraelapps.githubclientkotlin.mvp.model.entity.GithubUser
import ru.dizraelapps.githubclientkotlin.mvp.presenter.list.IUserListPresenter
import ru.dizraelapps.githubclientkotlin.mvp.view.UserItemView
import ru.dizraelapps.githubclientkotlin.mvp.view.UsersView
import ru.dizraelapps.githubclientkotlin.ui.fragments.UsersFragment
import ru.dizraelapps.githubclientkotlin.ui.navigation.Screens
import ru.terrakok.cicerone.Router

class UsersPresenter(private val usersRepo: GithubUserRepo, private val router: Router) : MvpPresenter<UsersView>() {

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = {
            router.navigateTo(Screens.SingleUserScreen(it.pos))
        }
    }

    private fun loadData() {
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }

    fun backPressed(): Boolean{
        router.exit()
        return true
    }
}