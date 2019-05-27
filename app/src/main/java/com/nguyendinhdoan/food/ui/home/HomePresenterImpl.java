package com.nguyendinhdoan.food.ui.home;

import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nguyendinhdoan.food.R;
import com.nguyendinhdoan.food.holder.MenuViewHolder;
import com.nguyendinhdoan.food.model.Category;
import com.nguyendinhdoan.food.utils.CommonUtils;
import com.squareup.picasso.Picasso;

public class HomePresenterImpl implements HomePresenter {

    private static final String TAG = HomePresenterImpl.class.getSimpleName();

    private HomeView homeView;

    public HomePresenterImpl(HomeView homeView) {
        this.homeView = homeView;
    }

    @Override
    public void loadFoodMenu() {
        homeView.showLoading();

        final DatabaseReference categoriesObject = FirebaseDatabase
                .getInstance().getReference(CommonUtils.CATEGORIES_TABLE_NAME);
        final FirebaseRecyclerAdapter<Category, MenuViewHolder> adapter = new FirebaseRecyclerAdapter<Category, MenuViewHolder>(
                Category.class, R.layout.item_menu, MenuViewHolder.class, categoriesObject
        ) {
            @Override
            protected void populateViewHolder(MenuViewHolder viewHolder, Category category, int position) {
                viewHolder.menuItemLoading.setVisibility(View.VISIBLE);

//              ------------- Load infor menu
                viewHolder.menuNameTextView.setText(category.getName());
                Picasso.get().load(category.getImage()).into(viewHolder.menuIntroImageView);

                viewHolder.menuItemLoading.setVisibility(View.GONE);
            }
        };

        homeView.showMenuList(adapter);
        homeView.hideLoading();
    }
}
