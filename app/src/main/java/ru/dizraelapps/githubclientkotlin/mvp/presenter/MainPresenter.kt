package ru.dizraelapps.githubclientkotlin.mvp.presenter

import moxy.MvpPresenter
import ru.dizraelapps.githubclientkotlin.GithubApplication
import ru.dizraelapps.githubclientkotlin.mvp.view.MainView
import ru.dizraelapps.githubclientkotlin.ui.navigation.Screens
import ru.terrakok.cicerone.Router

class MainPresenter(private val router: Router): MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.navigateTo(Screens.UsersScreen())
    }

    fun backClicked(){
        router.exit()
    }

}