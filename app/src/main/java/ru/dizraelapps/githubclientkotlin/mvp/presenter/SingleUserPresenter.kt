package ru.dizraelapps.githubclientkotlin.mvp.presenter

import android.util.Log
import moxy.MvpPresenter
import ru.dizraelapps.githubclientkotlin.mvp.model.GithubUserRepo
import ru.dizraelapps.githubclientkotlin.mvp.model.entity.GithubUser
import ru.dizraelapps.githubclientkotlin.mvp.view.SingleUserView
import ru.dizraelapps.githubclientkotlin.ui.navigation.BackButtonListener
import ru.terrakok.cicerone.Router

class SingleUserPresenter(private val usersRepo: GithubUserRepo, private val router: Router): MvpPresenter<SingleUserView>(),
 BackButtonListener{

    private val TAG = SingleUserPresenter::class.java.simpleName

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData()
    }

    lateinit var userData: GithubUser
    var index: Int? = null
    private fun loadData() {
        index?.run {
            usersRepo.getSingleUserRx(this)
                .subscribe(
                    { data -> userData = data },
                    { e -> Log.e(TAG, "loadData: "+ e.message ) }
                ) ?: { Log.e(TAG, "loadData: ERROR", )}
        }

    }

    fun getData(userIndex: Int) = usersRepo.getSingleUser(userIndex)

    override fun backPressed(): Boolean {
        router.exit()
        return true
    }
}