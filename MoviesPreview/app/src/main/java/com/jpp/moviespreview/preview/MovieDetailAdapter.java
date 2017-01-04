package com.jpp.moviespreview.preview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jpp.moviespreview.R;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Adapter for the details of the movie.
 * <p>
 * Created by jpperetti on 6/12/16.
 */
/*default*/ class MovieDetailAdapter extends RecyclerView.Adapter<MovieDetailAdapter.MovieDetailViewHolder> {

    private final List<MoviePreviewItemDetail> mData;

    MovieDetailAdapter(@NonNull List<MoviePreviewItemDetail> data) {
        mData = data;
    }

    @Override
    public MovieDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_preview_detail_item, parent, false);
        return new MovieDetailViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MovieDetailViewHolder holder, int position) {
        MoviePreviewItemDetail item = mData.get(position);
        holder.bindDetail(item);
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (mData != null) {
            count = mData.size();
        }
        return count;
    }

    static class MovieDetailViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.txt_movie_preview_detail_title)
        TextView txtTitle;
        @InjectView(R.id.txt_movie_preview_body)
        TextView txtBody;

        public MovieDetailViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }


        void bindDetail(@NonNull MoviePreviewItemDetail detail) {
            txtTitle.setText(detail.getTitle());
            txtBody.setText(detail.getBody());
        }
    }

}
