package com.nguyendinhdoan.food.ui.base;

import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity implements MvpView {

    public abstract void initViews();

    public abstract void setupUI();

    public abstract void addEvents();
}
