package com.jpp.moviespreview.search;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.jpp.moviespreview.R;
import com.jpp.moviespreview.core.toolbar.TransformingToolbar;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * {@link TransformingToolbar} implementation for the search screen
 * <p>
 * Will provide an implementation with a search icon and a cancel button, an edit text
 * to enter the search query.
 * <p>
 * Created by jpperetti on 19/12/16.
 */
public class SearchToolbar extends TransformingToolbar {

    @InjectView(R.id.et_search_toolbar)
    EditText etSearch;

    private SearchActionListener mSearchActionListener;

    public SearchToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundResource(R.drawable.search_box_background);
        setNavigationIcon(R.drawable.ic_action_back);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        inflate(getContext(), R.layout.search_toolbar, this);
        ButterKnife.inject(this);

        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (mSearchActionListener != null) {
                        mSearchActionListener.onSearchEntered(etSearch.getText().toString());
                    }
                    return true;
                }
                return false;
            }
        });
    }


    /**
     * Requests the focus on the search box.
     */
    public void requestFocusOnSearch() {
        etSearch.requestFocus();
    }


    public void setActionListener(@NonNull SearchActionListener listener) {
        mSearchActionListener = listener;
    }

    public interface SearchActionListener {
        void onSearchEntered(@NonNull String query);
    }
}
