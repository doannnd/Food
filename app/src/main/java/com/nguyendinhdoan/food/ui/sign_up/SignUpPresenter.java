package com.nguyendinhdoan.food.ui.sign_up;

import com.nguyendinhdoan.food.ui.base.MvpPresenter;
import com.nguyendinhdoan.food.ui.base.MvpView;

public interface SignUpPresenter<V extends SignUpView> extends MvpPresenter<V> {

    void performSignUp(String phoneNumber, String name, String password);
}
