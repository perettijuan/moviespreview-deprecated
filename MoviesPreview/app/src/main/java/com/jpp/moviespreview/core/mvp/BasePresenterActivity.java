package com.jpp.moviespreview.core.mvp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

/**
 * Base class for all Activities in the manager application. This base class contains all the logic
 * related to managing the MVP lifecycle.
 * <p>
 * Created by jpperetti on 8/2/16.
 */
public abstract class BasePresenterActivity<V extends PresentingView, T extends BasePresenter<V>>
        extends AppCompatActivity implements PresentingView {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StateFragment stateFragment = createStateFragmentIfNeeded();

        // noinspection unchecked
        T presenter = (T) stateFragment.getPresenter();

        if (presenter == null) {
            presenter = createPresenter();
            presenter.init(getIntent().getExtras());
        }

        // noinspection unchecked
        stateFragment.setPresenter(presenter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getPresenter().linkView(getView());
    }

    @Override
    protected void onStop() {
        getPresenter().unlinkView(getView());
        super.onStop();
    }


    /**
     * Creates the StateFragment whenever is needed.
     */
    private StateFragment createStateFragmentIfNeeded() {
        FragmentManager fManager = getSupportFragmentManager();
        StateFragment stateFragment = getStateFragment();
        if (stateFragment == null) {
            stateFragment = StateFragment.newInstance();
            FragmentTransaction tr = fManager.beginTransaction();
            tr.add(stateFragment, StateFragment.TAG);
            tr.commit();
        }
        return stateFragment;
    }


    @Nullable
    private StateFragment getStateFragment() {
        FragmentManager fManager = getSupportFragmentManager();
        return (StateFragment) fManager.findFragmentByTag(StateFragment.TAG);
    }


    // --- flow step

    @Override
    public void executeNextStep(@NonNull Intent intent, boolean maintainCurrent) {
        startActivity(intent);
        if (!maintainCurrent) {
            finish();
        }
    }


    // --- PresentingView

    @Override
    public void showSnackbarError(@StringRes int errorMessage, @StringRes int recoverMessage, @Nullable BasePresenterCommand command) {
        showSnackbarError(getString(errorMessage), getString(recoverMessage), command);
    }

    @Override
    public void showSnackbarError(@NonNull String errorMessage, @Nullable String recoverMessage, @Nullable final BasePresenterCommand command) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                errorMessage, command == null ? Snackbar.LENGTH_LONG : Snackbar.LENGTH_INDEFINITE);
        View backgroundView = snackbar.getView();
        backgroundView.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_red_dark));
        if (command != null && recoverMessage != null) {
            snackbar.setAction(recoverMessage, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    command.executeCommand();
                }
            });
        }
        Button snackbarAction = (Button) backgroundView.findViewById(android.support.design.R.id.snackbar_action);
        snackbarAction.setTextColor(ContextCompat.getColor(this, android.R.color.white));
        snackbar.show();
    }

    @Override
    public void showSnackbarError(@StringRes int errorMessage) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), getString(errorMessage), Snackbar.LENGTH_LONG);
        View backgroundView = snackbar.getView();
        backgroundView.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_red_dark));
        snackbar.show();
    }

    @Override
    public void showSnackbarWarning(@StringRes int errorMessage) {
        Snackbar.make(findViewById(android.R.id.content), getString(errorMessage), Snackbar.LENGTH_LONG).show();
    }


    // --- Presenter

    /**
     * Gets a view for the presenter.
     *
     * @return an instance of a view
     */
    @NonNull
    protected abstract V getView();

    /**
     * Creates a presenter for this view.
     *
     * @return an instance of a presenter
     */
    @NonNull
    protected abstract T createPresenter();

    /**
     * Get this view presenter.
     *
     * @return a view presenter
     */
    @NonNull
    protected T getPresenter() {
        StateFragment fragment = getStateFragment();
        if (fragment == null) {
            throw new IllegalStateException("StateFragment not created yet");
        }
        // noinspection unchecked
        T presenter = (T) fragment.getPresenter();
        if (presenter == null) {
            throw new IllegalStateException("Presenter not attached yet");
        }
        return presenter;
    }


    //-- Utility

    /**
     * Hides the soft keyboard from the UI.
     */
    protected void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

    }


    /**
     * Shows the soft keyboard on top of the current UI.
     */
    protected void showKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
    }
}
