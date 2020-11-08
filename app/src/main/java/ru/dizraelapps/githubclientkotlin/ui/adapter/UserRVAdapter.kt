package ru.dizraelapps.githubclientkotlin.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_user.view.*
import ru.dizraelapps.githubclientkotlin.R
import ru.dizraelapps.githubclientkotlin.mvp.presenter.list.IUserListPresenter
import ru.dizraelapps.githubclientkotlin.mvp.view.UserItemView

class UserRVAdapter(private val presenter: IUserListPresenter) :
    RecyclerView.Adapter<UserRVAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        holder.containerView.setOnClickListener {
            presenter.itemClickListener?.invoke(holder)
        }
        presenter.bindView(holder)
    }


    override fun getItemCount() = presenter.getCount()


    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), UserItemView, LayoutContainer {

        override var pos: Int = -1
        override fun setLogin(text: String) = with(containerView) {
            tv_login.text = text
        }
    }

}