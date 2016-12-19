package com.jpp.moviespreview.search;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import com.jpp.moviespreview.R;
import com.jpp.moviespreview.core.toolbar.TransformingToolbar;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * {@link TransformingToolbar} implementation for the search screen
 * <p>
 * Created by jpperetti on 19/12/16.
 */
public class SearchToolbar extends TransformingToolbar {

    @InjectView(R.id.et_search_toolbar)
    EditText etSearch;

    public SearchToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(context.getResources().getColor(android.R.color.white));
        setNavigationIcon(R.drawable.ic_action_back);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        inflate(getContext(), R.layout.search_toolbar, this);
        ButterKnife.inject(this);
    }

    @Override
    public void showContent() {
        super.showContent();
        etSearch.requestFocus();
    }
}
