package com.nguyendinhdoan.food.ui.home;

import com.nguyendinhdoan.food.ui.base.MvpPresenter;

public interface HomePresenter<V extends HomeView> extends MvpPresenter<V> {
    void loadFoodMenu();
}
