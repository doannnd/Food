package com.nguyendinhdoan.food.ui.sign_up;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nguyendinhdoan.food.R;
import com.nguyendinhdoan.food.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends BaseActivity
        implements SignUpView{

    @BindView(R.id.name_edit_text)
    TextInputEditText nameEditText;
    @BindView(R.id.phone_edit_text)
    TextInputEditText phoneEditText;
    @BindView(R.id.password_edit_text)
    TextInputEditText passwordEditText;
    @BindView(R.id.sign_up_loading)
    ProgressBar signUpLoading;

    private SignUpPresenter<SignUpView> signUpPresenter;

    public static Intent start(Context context) {
        return new Intent(context, SignUpActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setUnbinder( ButterKnife.bind(this));

        setupUI();
    }

    @Override
    public void setupUI() {
        signUpPresenter = new SignUpPresenterImpl<>();
        signUpPresenter.onAttach(this);
    }

    @Override
    public void showLoading() {
        signUpLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        signUpLoading.setVisibility(View.GONE);
    }

    @Override
    public void onError(int message) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(boolean isSignUpSuccess) {
        if (isSignUpSuccess) {
            Toast.makeText(this, "sign up success", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @OnClick(R.id.sign_up_button)
    public void handleSignUp() {
        String name = nameEditText.getText().toString();
        String phoneNumber = phoneEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        signUpPresenter.performSignUp(phoneNumber, name, password);
    }

    @Override
    protected void onDestroy() {
        signUpPresenter.onDetach();
        super.onDestroy();
    }
}
