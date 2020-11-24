package ru.dizraelapps.githubclientkotlin.mvp.model

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.dizraelapps.githubclientkotlin.mvp.model.entity.GithubUser

class GithubUserRepo {
    private val repositories = listOf(
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5")
    )

    fun getUsers(): List<GithubUser>{
        return repositories
    }

    fun getUsersRx(): Observable<GithubUser>{
        return Observable.create<GithubUser>{ emitter ->
            try {
                val list = repositories
                list.forEach {
                    emitter.onNext(it)
                }
            } catch (e: Throwable){
                emitter.onError(e)
            }
            emitter.onComplete()
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    fun getSingleUser(userIndex: Int): GithubUser{
        return repositories[userIndex]
    }

    fun getSingleUserRx(userIndex: Int): Observable<GithubUser> =
        Observable.just(repositories[userIndex])
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}