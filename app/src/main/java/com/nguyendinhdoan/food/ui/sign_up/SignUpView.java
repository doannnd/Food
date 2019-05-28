package com.nguyendinhdoan.food.ui.sign_up;

import android.support.annotation.StringRes;

import com.nguyendinhdoan.food.ui.base.MvpView;

public interface SignUpView extends MvpView {

    void onError(@StringRes int message);

    void onSuccess(boolean isSignUpSuccess);
}
