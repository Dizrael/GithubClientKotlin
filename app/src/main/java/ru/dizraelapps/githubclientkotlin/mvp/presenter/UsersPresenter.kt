package ru.dizraelapps.githubclientkotlin.mvp.presenter

import android.util.Log
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
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

    private val TAG: String = UsersPresenter::class.java.simpleName

    val usersListPresenter = UsersListPresenter()
    private val usersListObserver = object : Observer<GithubUser>{
        override fun onSubscribe(d: Disposable?) {
            Log.i(TAG, "onSubscribe")
        }

        override fun onNext(users: GithubUser) {
            Log.i(TAG, "onNext")
            users.run {
                usersListPresenter.users.add(this)
                viewState.updateList()
            }
        }

        override fun onError(e: Throwable?) {
            Log.i(TAG, "onError: " + e?.message)
        }

        override fun onComplete() {
            Log.i(TAG, "onComplete")
        }

    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = {
            router.navigateTo(Screens.SingleUserScreen(it.pos))
        }
    }

    private fun loadData() {
        usersRepo.getUsersRx().subscribe(usersListObserver)
    }

    class UsersListPresenter : IUserListPresenter {
        var users = mutableListOf<GithubUser>()

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