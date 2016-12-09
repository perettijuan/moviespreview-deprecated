package com.jpp.moviespreview.home.delegate;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.jpp.moviespreview.core.MoviesContext;
import com.jpp.moviespreview.core.flow.sections.ApplicationSection;
import com.jpp.moviespreview.core.mvp.PresenterDelegate;
import com.jpp.moviespreview.home.HomeMenuListItem;
import com.jpp.moviespreview.home.HomeView;

import java.util.ArrayList;
import java.util.List;

/**
 * PresenterDelegate to show the list of sections in the home screen.
 * <p>
 * Created by jpperetti on 9/12/16.
 */
public class HomePresenterDelegate implements PresenterDelegate<HomeView> {


    @Override
    public void linkView(@NonNull HomeView view, @NonNull MoviesContext context) {

        if (context.getSections() == null || context.getSections().isEmpty()) {
            // first time -> setup
            context.setSections(ApplicationSection.newInstance());
        }

        List<HomeMenuListItem> items = getHomeMenuList(context.getSections());
        view.showHomeMenu(items);
        view.setTitle(getSelectedSectionName(items));
    }

    @Override
    public void updateView(@NonNull HomeView view, @NonNull MoviesContext context) {
        //nothing for the moment.
    }


    public void onItemSelected(@NonNull HomeMenuListItem selected, @NonNull List<HomeMenuListItem> items, @NonNull HomeView view) {
        if (!selected.isSelected()) {
            @StringRes int name = -1;
            for (HomeMenuListItem item : items) {
                item.getModel().toggleSelected();
                if(item.isSelected()) {
                    name = item.getTitle();
                }
            }
            view.showHomeMenu(items);
            view.clearPage();
            view.setTitle(name);
        }
    }


    private List<HomeMenuListItem> getHomeMenuList(@NonNull List<ApplicationSection> sections) {
        List<HomeMenuListItem> menu = new ArrayList<>();
        for (ApplicationSection section : sections) {
            menu.add(new HomeMenuListItem(section));
        }
        return menu;
    }

    @StringRes
    private int getSelectedSectionName(List<HomeMenuListItem> items) {
        @StringRes int name = -1;
        for (HomeMenuListItem item : items) {
            if (item.isSelected()) {
                name = item.getTitle();
                break;
            }
        }
        return name;
    }
}
