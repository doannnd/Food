package com.nguyendinhdoan.food.ui.welcome;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nguyendinhdoan.food.R;
import com.nguyendinhdoan.food.ui.base.BaseActivity;
import com.nguyendinhdoan.food.ui.sign_in.SignInActivity;
import com.nguyendinhdoan.food.ui.sign_up.SignUpActivity;

public class WelcomeActivity extends BaseActivity implements View.OnClickListener {

    private Button signInButton, signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initViews();
        addEvents();
    }

    @Override
    public void addEvents() {
        signUpButton.setOnClickListener(this);
        signInButton.setOnClickListener(this);
    }

    @Override
    public void initViews() {
        signInButton = findViewById(R.id.sign_in_button);
        signUpButton = findViewById(R.id.sign_up_button);
    }

    @Override
    public void setupUI() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button: {
                startActivity(SignInActivity.start(this));
                finish();
                break;
            }
            case R.id.sign_up_button: {
                startActivity(SignUpActivity.start(this));
                break;
            }
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
