package com.nguyendinhdoan.food.ui.home;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.nguyendinhdoan.food.holder.MenuViewHolder;
import com.nguyendinhdoan.food.model.Category;
import com.nguyendinhdoan.food.ui.base.MvpView;

public interface HomeView extends MvpView {
    void showMenuList(FirebaseRecyclerAdapter<Category, MenuViewHolder> adapter);

    void onFoodMenuClicked(String name);
}
