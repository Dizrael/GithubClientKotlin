package ru.dizraelapps.githubclientkotlin.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_single_user.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.dizraelapps.githubclientkotlin.GithubApplication
import ru.dizraelapps.githubclientkotlin.R
import ru.dizraelapps.githubclientkotlin.mvp.model.GithubUserRepo
import ru.dizraelapps.githubclientkotlin.mvp.presenter.SingleUserPresenter
import ru.dizraelapps.githubclientkotlin.mvp.view.SingleUserView
import ru.dizraelapps.githubclientkotlin.ui.navigation.BackButtonListener

class SingleUserFragment: MvpAppCompatFragment(), SingleUserView, BackButtonListener {

    companion object{
        private const val USER_KEY = "user_index"
        fun newInstance(userIndex: Int) = SingleUserFragment().apply {
            arguments = Bundle().apply {
                putInt(USER_KEY, userIndex)
            }
        }
    }

    private var userIndex = 0

    private val presenter: SingleUserPresenter by moxyPresenter {
        SingleUserPresenter(GithubUserRepo(), GithubApplication.instance.router)
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
            = View.inflate(context, R.layout.fragment_single_user, null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userIndex = arguments?.getInt(USER_KEY) ?: 0
    }

    override fun backPressed(): Boolean {
        presenter.backPressed()
        return true
    }

    override fun init() {
        user_login.text = presenter.getData(userIndex).login
    }
}