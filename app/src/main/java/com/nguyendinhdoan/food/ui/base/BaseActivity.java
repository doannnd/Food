package com.nguyendinhdoan.food.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity implements MvpView {

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setUnbinder(Unbinder unbinder) {
        this.unbinder = unbinder;
    }

    public abstract void setupUI();

    @Override
    protected void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroy();
    }
}
