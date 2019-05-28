package com.nguyendinhdoan.food.ui.food;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.nguyendinhdoan.food.holder.FoodViewHolder;
import com.nguyendinhdoan.food.model.Food;
import com.nguyendinhdoan.food.ui.base.MvpView;

public interface FoodView extends MvpView {

    void showFoodList(FirebaseRecyclerAdapter<Food, FoodViewHolder> adapter);
}
