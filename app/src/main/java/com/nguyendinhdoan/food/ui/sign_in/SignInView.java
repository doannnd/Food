package com.nguyendinhdoan.food.ui.sign_in;

import android.support.annotation.StringRes;

public interface SignInView {

    void showLoading();

    void hideLoading();

    void onError(@StringRes int message);

    void onSuccess(boolean isLoginSuccess);
}
