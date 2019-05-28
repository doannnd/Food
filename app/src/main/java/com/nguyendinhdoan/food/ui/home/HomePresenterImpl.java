package com.nguyendinhdoan.food.ui.home;

import android.util.Log;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.nguyendinhdoan.food.R;
import com.nguyendinhdoan.food.common.Common;
import com.nguyendinhdoan.food.holder.MenuViewHolder;
import com.nguyendinhdoan.food.listener.OnItemClickListener;
import com.nguyendinhdoan.food.model.Category;
import com.nguyendinhdoan.food.ui.base.BasePresenter;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class HomePresenterImpl<V extends HomeView> extends BasePresenter<V> implements HomePresenter<V> {

    private static final String TAG = HomePresenterImpl.class.getSimpleName();

    private FirebaseRecyclerAdapter<Category, MenuViewHolder> adapter;

    /**
     * load food menu to recycler view
     */
    @Override
    public void loadFoodMenu() {
        getMvpView().showLoading();

        adapter = new FirebaseRecyclerAdapter<Category, MenuViewHolder>(
                Category.class,
                R.layout.item_menu,
                MenuViewHolder.class,
                Common.getCategoryObject()
        ) {
            @Override
            protected void populateViewHolder(MenuViewHolder viewHolder, final Category category, int position) {
                loadMenuImage(viewHolder, category.getImage());
                loadMenuName(viewHolder, category.getName());
                addEventMenu(viewHolder);
            }
        };

//      ---- set data for recycler view and hide menu loading
        getMvpView().showMenuList(adapter);
        getMvpView().hideLoading();
    }

    /**
     * display image of food
     * @param viewHolder menu view holder
     * @param menuImageUrl image url of food
     */
    private void loadMenuImage(final MenuViewHolder viewHolder, String menuImageUrl) {
        viewHolder.menuItemLoading.setVisibility(View.VISIBLE);
        Picasso.get()
                .load(menuImageUrl)
                .into(viewHolder.menuIntroImageView, new Callback() {
            @Override
            public void onSuccess() {
                viewHolder.menuItemLoading.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {
                Log.e(TAG, "onError: load image error" + e.getMessage());
                viewHolder.menuItemLoading.setVisibility(View.GONE);
            }
        });
    }

    /**
     * display menu name
     * @param viewHolder menu view holder
     * @param menuName name of food
     */
    private void loadMenuName(MenuViewHolder viewHolder, String menuName) {
        viewHolder.menuNameTextView.setText(menuName);
    }

    /**
     * add event for food menu
     * @param viewHolder menu view holder
     */
    private void addEventMenu(MenuViewHolder viewHolder) {
        viewHolder.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                getMvpView().onFoodMenuClicked(adapter.getRef(position).getKey());
            }
        });
    }
}
