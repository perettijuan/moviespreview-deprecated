package com.jpp.moviespreview.preview;

import android.support.annotation.NonNull;

/**
 * Represents an item in the detail.
 * <p>
 * Created by jpperetti on 6/12/16.
 */
/*package*/ class MoviePreviewItemDetail {


    private final String mTitle;
    private final String mBody;


    MoviePreviewItemDetail(@NonNull String title, @NonNull String body) {
        mTitle = title;
        mBody = body;
    }

    @NonNull
    String getTitle() {
        return mTitle;
    }


    @NonNull
    String getBody() {
        return mBody;
    }
}
