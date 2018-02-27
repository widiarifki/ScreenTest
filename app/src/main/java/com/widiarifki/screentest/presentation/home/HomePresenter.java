package com.widiarifki.screentest.presentation.home;

/**
 * Created by widiarifki on 27/02/2018.
 */

public class HomePresenter {
    private IHomeView mView;

    public HomePresenter(IHomeView view){
        mView = view;
    }

    public void checkPalindrome(String name) {
        mView.showDialogue(name, isPalindrome(name) ? "is Palindrome" : "No Palindrome");
    }

    private boolean isPalindrome(String name) {
        String word = name.replace(" ", "");
        String revWord = new StringBuilder(word).reverse().toString();
        return word.equals(revWord);
    }
}