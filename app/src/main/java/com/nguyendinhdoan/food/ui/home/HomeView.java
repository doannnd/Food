package com.nguyendinhdoan.food.ui.home;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.nguyendinhdoan.food.holder.MenuViewHolder;
import com.nguyendinhdoan.food.model.Category;

public interface HomeView {
    void showLoading();

    void hideLoading();

    void showMenuList(FirebaseRecyclerAdapter<Category, MenuViewHolder> adapter);
}
