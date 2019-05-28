package com.nguyendinhdoan.food.ui.food;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.nguyendinhdoan.food.R;
import com.nguyendinhdoan.food.holder.FoodViewHolder;
import com.nguyendinhdoan.food.model.Food;
import com.nguyendinhdoan.food.ui.base.BaseActivity;
import com.nguyendinhdoan.food.utils.ConstantUtils;

public class FoodActivity extends BaseActivity implements FoodView{

    private RecyclerView foodRecyclerView;
    private ProgressBar foodLoading;

    private FoodPresenter<FoodView> foodPresenter;

    public static Intent start(Context context) {
        return new Intent(context, FoodActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        initViews();
        setupUI();
        addEvents();
    }

    @Override
    public void showLoading() {
        foodLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        foodLoading.setVisibility(View.GONE);
    }

    @Override
    public void initViews() {
        foodRecyclerView = findViewById(R.id.food_recycler_view);
        foodLoading = findViewById(R.id.food_loading);
    }

    @Override
    public void setupUI() {
        foodPresenter = new FoodPresenterImpl<>();
        foodPresenter.onAttach(this);

        String categoryId = null;
        if (getIntent() != null) {
           categoryId = getIntent().getStringExtra(ConstantUtils.CATEGORY_ID_KEY);
        }

        if (categoryId != null) {
            foodPresenter.loadFoodByCategoryId(categoryId);
        }
    }

    @Override
    public void addEvents() {

    }

    @Override
    protected void onDestroy() {
        foodPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void showFoodList(FirebaseRecyclerAdapter<Food, FoodViewHolder> adapter) {
        foodRecyclerView.setHasFixedSize(true);
        foodRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        foodRecyclerView.setAdapter(adapter);
    }
}
