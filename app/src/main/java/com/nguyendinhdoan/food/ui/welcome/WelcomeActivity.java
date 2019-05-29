package com.nguyendinhdoan.food.ui.welcome;

import android.os.Bundle;
import android.view.View;

import com.nguyendinhdoan.food.R;
import com.nguyendinhdoan.food.ui.base.BaseActivity;
import com.nguyendinhdoan.food.ui.sign_in.SignInActivity;
import com.nguyendinhdoan.food.ui.sign_up.SignUpActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        setUnbinder(ButterKnife.bind(this));
    }

    @Override
    public void setupUI() {
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @OnClick({R.id.sign_up_button, R.id.sign_in_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sign_up_button:
                startActivity(SignUpActivity.start(this));
                break;
            case R.id.sign_in_button:
                startActivity(SignInActivity.start(this));
                finish();
                break;
        }
    }
}
