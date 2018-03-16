package com.widiarifki.screentest.presentation.home

import com.widiarifki.screentest.model.User
import io.realm.Realm

/**
 * Created by widiarifki on 27/02/2018.
 */

class HomePresenter(private val mView: IHomeView) {

    fun getPalindromeMessage(name: String) : String {
        return name + " " + (if (isPalindrome(name)) "is Palindrome" else "No Palindrome")
    }

    private fun isPalindrome(name: String): Boolean {
        val word =  name.replace(" ", "")
        val revWord = StringBuilder(word).reverse().toString()
        return word == revWord
    }

    fun saveUserData(username: String){
        val user = User(username)
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        realm.copyToRealmOrUpdate(user)
        realm.commitTransaction()
    }
}