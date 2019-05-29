package com.nguyendinhdoan.food.ui.welcome;

import com.nguyendinhdoan.food.ui.base.MvpPresenter;

public interface WelcomePresenter<V extends WelcomeView> extends MvpPresenter<V> {

    void isLoggedIn();
}
