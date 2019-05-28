package com.nguyendinhdoan.food.ui.sign_in;

import android.support.annotation.StringRes;

import com.nguyendinhdoan.food.ui.base.MvpView;

public interface SignInView extends MvpView {

    void onError(@StringRes int message);

    void onSuccess(boolean isLoginSuccess);
}
