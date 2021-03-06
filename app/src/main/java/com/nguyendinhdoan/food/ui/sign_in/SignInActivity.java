package com.nguyendinhdoan.food.ui.sign_in;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.nguyendinhdoan.food.R;
import com.nguyendinhdoan.food.ui.base.BaseActivity;
import com.nguyendinhdoan.food.ui.home.HomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInActivity extends BaseActivity
        implements SignInView {

    @BindView(R.id.phone_edit_text)
    TextInputEditText phoneEditText;
    @BindView(R.id.password_edit_text)
    TextInputEditText passwordEditText;
    @BindView(R.id.sign_in_loading)
    ProgressBar signInLoading;

    private SignInPresenter<SignInView> signInPresenter;

    public static Intent start(Context context) {
        return new Intent(context, SignInActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        setUnbinder( ButterKnife.bind(this));

        setupUI();
    }

    @Override
    public void setupUI() {
        signInPresenter = new SignInPresenterImpl<>();
        signInPresenter.onAttach(this);
    }

    @Override
    public void showLoading() {
        signInLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        signInLoading.setVisibility(View.GONE);
    }

    @Override
    public void onError(int message) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(boolean isLoginSuccess) {
        if (isLoginSuccess) {
            startActivity(HomeActivity.start(this));
            finish();
        }
    }

    @OnClick(R.id.sign_in_button)
    public void handleSignIn() {
        String phoneNumber = phoneEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        signInPresenter.performSignIn(phoneNumber, password);
    }

    @Override
    protected void onDestroy() {
        signInPresenter.onDetach();
        super.onDestroy();
    }
}
