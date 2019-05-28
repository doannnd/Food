package com.nguyendinhdoan.food.ui.food;

import com.nguyendinhdoan.food.ui.base.MvpPresenter;

public interface FoodPresenter<V extends FoodView> extends MvpPresenter<V> {
    void loadFoodByCategoryId(String categoryId);
}
