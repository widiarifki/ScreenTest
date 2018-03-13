package com.widiarifki.screentest.presentation.home

/**
 * Created by widiarifki on 27/02/2018.
 */

class HomePresenter(private val mView: IHomeView) {

    fun checkPalindrome(name: String) {
        mView.showDialogue(name, if (isPalindrome(name)) "is Palindrome" else "No Palindrome")
    }

    private fun isPalindrome(name: String): Boolean {
        val word =  name.replace(" ", "")
        val revWord = StringBuilder(word).reverse().toString()
        return word == revWord
    }
}