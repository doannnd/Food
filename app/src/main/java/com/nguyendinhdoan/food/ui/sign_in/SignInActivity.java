package com.nguyendinhdoan.food.ui.sign_in;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.nguyendinhdoan.food.R;
import com.nguyendinhdoan.food.ui.home.HomeActivity;

public class SignInActivity extends AppCompatActivity
        implements SignInView, View.OnClickListener {

    private TextInputEditText phoneEditText, passwordEditText;
    private Button signInButton;
    private ProgressBar signInLoading;

    private SignInPresenter signInPresenter;

    public static Intent start(Context context) {
        return new Intent(context, SignInActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        initViews();
        setupUI();
        addEvents();
    }

    private void setupUI() {
        signInPresenter = new SignInPresenterImpl(this);
    }

    private void addEvents() {
        signInButton.setOnClickListener(this);
    }

    private void initViews() {
        phoneEditText = findViewById(R.id.phone_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
        signInButton = findViewById(R.id.sign_in_button);
        signInLoading = findViewById(R.id.sign_in_loading);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.sign_in_button) {
            String phoneNumber = phoneEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            signInPresenter.performSignIn(phoneNumber, password);
        }
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
}
