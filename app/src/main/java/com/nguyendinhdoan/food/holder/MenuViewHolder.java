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

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.menu_intro_image_view)
    public ImageView menuIntroImageView;
    @BindView(R.id.menu_name_text_view)
    public TextView menuNameTextView;
    @BindView(R.id.menu_item_loading)
    public ProgressBar menuItemLoading;

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public MenuViewHolder(@NonNull View itemView) {
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
