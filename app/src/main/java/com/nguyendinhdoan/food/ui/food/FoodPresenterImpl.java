package com.nguyendinhdoan.food.ui.food;

import android.util.Log;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.nguyendinhdoan.food.R;
import com.nguyendinhdoan.food.common.Common;
import com.nguyendinhdoan.food.holder.FoodViewHolder;
import com.nguyendinhdoan.food.model.Food;
import com.nguyendinhdoan.food.ui.base.BasePresenter;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class FoodPresenterImpl<V extends FoodView> extends BasePresenter<V> implements FoodPresenter<V> {

    private static final String TAG = FoodPresenterImpl.class.getSimpleName();
    private static final String MENU_ID = "menu_id";

    private FirebaseRecyclerAdapter<Food, FoodViewHolder> adapter;

    @Override
    public void loadFoodByCategoryId(String categoryId) {
        getMvpView().showLoading();

        adapter = new FirebaseRecyclerAdapter<Food, FoodViewHolder>(
                Food.class,
                R.layout.item_food,
                FoodViewHolder.class,
                Common.getFoodObject().orderByChild(MENU_ID).equalTo(categoryId)
        ) {
            @Override
            protected void populateViewHolder(FoodViewHolder viewHolder, Food food, int position) {
                loadFoodName(viewHolder, food.getName());
                loadFoodImage(viewHolder, food.getImage());
            }
        };

        //      ---- set data for recycler view and hide food loading
        getMvpView().showFoodList(adapter);
        getMvpView().hideLoading();
    }

    private void loadFoodName(FoodViewHolder viewHolder, String foodName) {
        viewHolder.foodNameTextView.setText(foodName);
    }

    private void loadFoodImage(final FoodViewHolder viewHolder, String foodImageUrl) {
        viewHolder.foodItemLoading.setVisibility(View.VISIBLE);
        Picasso.get()
                .load(foodImageUrl)
                .into(viewHolder.foodIntroImageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        viewHolder.foodItemLoading.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Exception e) {
                        viewHolder.foodItemLoading.setVisibility(View.GONE);
                        Log.e(TAG, "onError: load food image error" + e.getMessage());
                    }
                });
    }
}
