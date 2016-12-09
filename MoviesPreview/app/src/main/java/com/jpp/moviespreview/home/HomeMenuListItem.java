package com.jpp.moviespreview.home;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.jpp.moviespreview.core.adapter.ItemHolder;
import com.jpp.moviespreview.core.flow.sections.ApplicationSection;

/**
 * ItemHolder to represent the items in the home menu.
 * <p>
 * Created by jpperetti on 9/12/16.
 */
public class HomeMenuListItem extends ItemHolder<ApplicationSection> {


    public HomeMenuListItem(@NonNull ApplicationSection model) {
        super(model);
    }

    @NonNull
    @Override
    public ApplicationSection getModel() {
        return super.getModel();
    }

    @StringRes
    public int getTitle() {
        return getModel().getName();
    }


    public boolean isSelected() {
        return getModel().isSelected();
    }

}
