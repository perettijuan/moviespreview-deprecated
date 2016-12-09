package com.jpp.moviespreview.home.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jpp.moviespreview.R;
import com.jpp.moviespreview.home.HomeMenuListItem;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * RecyclerView.Adapter for the home screen section menu.
 * <p>
 * Created by jpperetti on 9/12/16.
 */
public class HomeMenuRecyclerViewAdapter extends RecyclerView.Adapter<HomeMenuRecyclerViewAdapter.HomeMenuViewHolder> {

    private List<HomeMenuListItem> mData;


    public void updateData(@NonNull List<HomeMenuListItem> data) {
        int currentSize = getItemCount();
        mData = data;
        notifyDataSetChanged();
    }


    public List<HomeMenuListItem> getData() {
        return mData;
    }

    public HomeMenuListItem getItemAtPosition(int position) {
        return mData.get(position);
    }

    @Override
    public HomeMenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_screen_menu_item, parent, false);
        return new HomeMenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeMenuViewHolder holder, int position) {
        holder.bindItem(mData.get(position));
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (mData != null) {
            count = mData.size();
        }
        return count;
    }

    static class HomeMenuViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.txt_home_screen_menu_item)
        TextView txtItem;
        @InjectView(R.id.iv_home_screen_menu_item)
        ImageView ivSelected;

        HomeMenuViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }


        void bindItem(@NonNull HomeMenuListItem item) {
            txtItem.setText(item.getTitle());
            ivSelected.setVisibility(item.isSelected() ? View.VISIBLE : View.INVISIBLE);
        }

    }

}
