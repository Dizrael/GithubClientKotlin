package ru.dizraelapps.githubclientkotlin.mvp.presenter

import moxy.MvpPresenter
import ru.dizraelapps.githubclientkotlin.mvp.model.GithubUserRepo
import ru.dizraelapps.githubclientkotlin.mvp.view.SingleUserView
import ru.dizraelapps.githubclientkotlin.ui.navigation.BackButtonListener
import ru.terrakok.cicerone.Router

class SingleUserPresenter(private val usersRepo: GithubUserRepo, private val router: Router): MvpPresenter<SingleUserView>(),
 BackButtonListener{

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun getData(userIndex: Int) = usersRepo.getSingleUser(userIndex)

    override fun backPressed(): Boolean {
        router.exit()
        return true
    }
}