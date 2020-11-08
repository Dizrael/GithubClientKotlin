package ru.dizraelapps.githubclientkotlin.mvp.presenter.list

import ru.dizraelapps.githubclientkotlin.mvp.view.IItemView
import ru.dizraelapps.githubclientkotlin.mvp.view.UserItemView

interface IListPresenter<V: IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

interface IUserListPresenter: IListPresenter<UserItemView>