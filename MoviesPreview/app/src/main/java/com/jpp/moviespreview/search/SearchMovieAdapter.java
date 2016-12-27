package com.jpp.moviespreview.search;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jpp.moviespreview.R;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * RecyclerView.Adapter implementation for the search screen.
 * <p>
 * Created by jpperetti on 22/12/16.
 */
/*package*/ class SearchMovieAdapter extends RecyclerView.Adapter<SearchMovieAdapter.SearchMovieViewHolder> {

    private List<SearchMovieListItem> mData;


    /**
     * Swipes the entire data in the adapter.
     *
     * @param data - the model to be rendered.
     */
    void swipeData(@NonNull List<SearchMovieListItem> data) {
        int currentSize = getItemCount();
        mData = data;
        notifyItemRangeChanged(currentSize, getItemCount());
    }

    @Override
    public SearchMovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_movie_item, parent, false);
        return new SearchMovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SearchMovieViewHolder holder, int position) {
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

    //-- ViewHolder
    static class SearchMovieViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.iv_search_movie_poster)
        SimpleDraweeView ivMoviePoster;
        @InjectView(R.id.txt_search_movie_title)
        TextView txtMovieTitle;
        @InjectView(R.id.txt_search_movie_overview)
        TextView txtSearchMovieOverview;

        SearchMovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }


        void bindItem(SearchMovieListItem item) {
            Uri posterUri = Uri.parse(item.getPosterUrl());
            ivMoviePoster.setImageURI(posterUri);
            txtMovieTitle.setText(item.getTitle());
            txtSearchMovieOverview.setText(item.getOverview());
        }
    }

}
