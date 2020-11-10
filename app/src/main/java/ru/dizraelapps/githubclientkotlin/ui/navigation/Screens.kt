package ru.dizraelapps.githubclientkotlin.ui.navigation

import androidx.fragment.app.Fragment
import ru.dizraelapps.githubclientkotlin.ui.fragments.SingleUserFragment
import ru.dizraelapps.githubclientkotlin.ui.fragments.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class UsersScreen: SupportAppScreen(){
        override fun getFragment() = UsersFragment.newInstance()
    }

    class SingleUserScreen(private var userIndex: Int): SupportAppScreen(){
        override fun getFragment() = SingleUserFragment.newInstance(userIndex)
    }
}