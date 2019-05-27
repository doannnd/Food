package com.nguyendinhdoan.food.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nguyendinhdoan.food.R;
import com.nguyendinhdoan.food.listener.OnItemClickListener;

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ImageView menuIntroImageView;
    public TextView menuNameTextView;
    public ProgressBar menuItemLoading;

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public MenuViewHolder(@NonNull View itemView) {
        super(itemView);

        menuIntroImageView = itemView.findViewById(R.id.menu_intro_image_view);
        menuNameTextView = itemView.findViewById(R.id.menu_name_text_view);
        menuItemLoading = itemView.findViewById(R.id.menu_item_loading);

//        ------------ set event when click into menu recycler view item
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onItemClickListener.onItemClicked(getAdapterPosition());
    }

}
