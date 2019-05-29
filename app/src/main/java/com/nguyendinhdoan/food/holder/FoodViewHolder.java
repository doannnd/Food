package com.nguyendinhdoan.food.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nguyendinhdoan.food.R;
import com.nguyendinhdoan.food.listener.OnItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.food_intro_image_view)
    public ImageView foodIntroImageView;
    @BindView(R.id.food_name_text_view)
    public TextView foodNameTextView;
    @BindView(R.id.food_item_loading)
    public ProgressBar foodItemLoading;

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public FoodViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

//        ------------ set event when click into menu recycler view item
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onItemClickListener.onItemClicked(getAdapterPosition());
    }
}
