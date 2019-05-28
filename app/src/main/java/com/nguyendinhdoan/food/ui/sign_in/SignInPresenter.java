package com.nguyendinhdoan.food.ui.sign_in;

import com.nguyendinhdoan.food.ui.base.BasePresenter;
import com.nguyendinhdoan.food.ui.base.MvpPresenter;

public interface SignInPresenter<V extends SignInView> extends MvpPresenter<V> {

    void performSignIn(String phoneNumber, String password);
}
