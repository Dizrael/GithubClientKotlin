package ru.dizraelapps.githubclientkotlin.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_users.*
import kotlinx.android.synthetic.main.fragment_users.view.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.dizraelapps.githubclientkotlin.GithubApplication
import ru.dizraelapps.githubclientkotlin.R
import ru.dizraelapps.githubclientkotlin.mvp.model.GithubUserRepo
import ru.dizraelapps.githubclientkotlin.mvp.presenter.MainPresenter
import ru.dizraelapps.githubclientkotlin.mvp.presenter.UsersPresenter
import ru.dizraelapps.githubclientkotlin.mvp.view.UsersView
import ru.dizraelapps.githubclientkotlin.ui.adapter.UserRVAdapter
import ru.dizraelapps.githubclientkotlin.ui.navigation.BackButtonListener

class UsersFragment: MvpAppCompatFragment(), UsersView, BackButtonListener {

    companion object{
        fun newInstance() = UsersFragment()
    }

    private val usersPresenter: UsersPresenter by moxyPresenter {
        UsersPresenter(GithubUserRepo(), GithubApplication.instance.router)
    }

    private val adapter by lazy {
        UserRVAdapter(usersPresenter.usersListPresenter)
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
            = View.inflate(context, R.layout.fragment_users, null)

    override fun init() {
        rv_users.adapter = adapter
        rv_users.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun updateList() {
        adapter.notifyDataSetChanged()
    }

    override fun backPressed() = usersPresenter.backPressed()


}