package com.jpp.moviespreview.core.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by jpperetti on 2/12/16.
 */

public class StateFragment<T extends BasePresenter<?>> extends Fragment {

    static final String TAG = StateFragment.class.getName();


    private BasePresenter<?> mPresenter;

    public static StateFragment newInstance() {
        return new StateFragment();
    }

    /**
     * Class constructor
     */
    public StateFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    public void setPresenter(@NonNull BasePresenter<?> presenter) {
        mPresenter = presenter;
    }


    @Nullable
    public BasePresenter<?> getPresenter() {
        return mPresenter;
    }

}
