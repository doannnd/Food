package com.nguyendinhdoan.food.ui.sign_up;

import android.support.annotation.StringRes;

public interface SignUpView {

    void showLoading();

    void hideLoading();

    void onError(@StringRes int message);

    void onSuccess(boolean isSignUpSuccess);
}
