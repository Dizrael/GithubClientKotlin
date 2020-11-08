package ru.dizraelapps.githubclientkotlin.ui.navigation

import ru.dizraelapps.githubclientkotlin.ui.fragments.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class UsersScreen: SupportAppScreen(){
        override fun getFragment() = UsersFragment.newInstance()
    }
}