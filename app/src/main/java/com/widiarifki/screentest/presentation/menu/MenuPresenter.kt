package com.widiarifki.screentest.presentation.menu

import com.widiarifki.screentest.model.User
import io.realm.Realm

/**
 * Created by widiarifki on 27/02/2018.
 */

class MenuPresenter(internal var mView: IMenuView) {

    fun getUsername() : String?{
        val realm = Realm.getDefaultInstance()
        val realmQuery = realm.where(User::class.java)
        val user: User? = realmQuery.findFirst()
        return user?.name
    }
}
