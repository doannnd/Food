package com.nguyendinhdoan.food.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.nguyendinhdoan.food.R;
import com.nguyendinhdoan.food.common.Common;
import com.nguyendinhdoan.food.holder.MenuViewHolder;
import com.nguyendinhdoan.food.model.Category;
import com.nguyendinhdoan.food.ui.base.BaseActivity;
import com.nguyendinhdoan.food.ui.food.FoodActivity;
import com.nguyendinhdoan.food.utils.ConstantUtils;

public class HomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, HomeView {

    private static final String TAG = HomeActivity.class.getSimpleName();

    private Toolbar toolbar;
    private FloatingActionButton fab;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ProgressBar menuLoading;
    private RecyclerView menuRecyclerView;

    private HomePresenter<HomeView> homePresenter;

    public static Intent start(Context context) {
        return new Intent(context, HomeActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initViews();
        setupUI();
        addEvents();
    }

    @Override
    public void addEvents() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void setupUI() {
        setupToolbar();
        setupNavigationView();

        homePresenter = new HomePresenterImpl<>();
        homePresenter.onAttach(this);
        homePresenter.loadFoodMenu();
    }

    private void setupNavigationView() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

//      ---- display name of user in navigation view
        View viewNav = navigationView.getHeaderView(0);
        TextView fullNameTextView = viewNav.findViewById(R.id.full_name_text_view);

        fullNameTextView.setText(Common.currentUser.getName());
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.toolbar_title));
        }
    }

    @Override
    public void initViews() {
        toolbar = findViewById(R.id.toolbar);
        fab = findViewById(R.id.fab);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        menuLoading = findViewById(R.id.menu_loading);
        menuRecyclerView = findViewById(R.id.menu_recycler_view);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void showLoading() {
        menuLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        menuLoading.setVisibility(View.GONE);
    }

    @Override
    public void showMenuList(FirebaseRecyclerAdapter<Category, MenuViewHolder> adapter) {
        menuRecyclerView.setHasFixedSize(true);
        menuRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        menuRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onFoodMenuClicked(String categoryId) {
        Log.d(TAG, "onFoodMenuClicked: category id" + categoryId);
        Intent foodIntent = FoodActivity.start(this);
        foodIntent.putExtra(ConstantUtils.CATEGORY_ID_KEY, categoryId);
        startActivity(foodIntent);
    }

    @Override
    protected void onDestroy() {
        homePresenter.onDetach();
        super.onDestroy();
    }
}
