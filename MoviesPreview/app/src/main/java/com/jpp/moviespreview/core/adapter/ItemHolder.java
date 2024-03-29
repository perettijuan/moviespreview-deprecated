package com.jpp.moviespreview.core.adapter;

import android.support.annotation.NonNull;

/**
 * Holder to decouple business objects with items that are presented in a list.
 * Created by jpperetti on 2/4/16.
 */
public class ItemHolder<T> {

    private final T model;

    public ItemHolder(@NonNull T model) {
        this.model = model;
    }

    @NonNull
    public T getModel() {
        return model;
    }
}