package com.pugerp.movieapp.ui._core.base;

import android.app.ProgressDialog;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.pugerp.movieapp.R;
import com.pugerp.movieapp.databinding.ToolbarBinding;
import com.pugerp.movieapp.di.components.ActivityComponent;
import com.pugerp.movieapp.di.components.DaggerActivityComponent;
import com.pugerp.movieapp.root.MovieApp;
import com.pugerp.movieapp.ui._core.mvp.MvpView;
import com.yatatsu.autobundle.AutoBundle;

import javax.inject.Inject;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements MvpView, BaseFragment.BaseFragmentListener {

    public ToolbarBinding bindingToolbar;

    public ProgressDialog progressDialog;

    private ActivityComponent activityComponent;

    @Inject
    protected P presenter;

    protected abstract View inflateLayout();

    protected abstract void setup(Bundle saveInstanceState);

    protected abstract void setComponent(@NonNull ActivityComponent component);

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            AutoBundle.bind(this, savedInstanceState);
        } else {
            AutoBundle.bind(this);
        }


        setContentView(inflateLayout());
        createComponent();
        onComponentCreated(activityComponent);
        setup(savedInstanceState);

    }

    private void onComponentCreated(ActivityComponent activityComponent) {
        setComponent(activityComponent);
    }

    private void createComponent() {
        this.activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(((MovieApp) this.getApplicationContext()).getComponent())
                .build();
    }

    @NonNull
    public ActivityComponent getActivityComponent() {return activityComponent;}

    @Override
    public void onFragmentAttach() {

    }

    @Override
    public void onFragmentDetach() {

    }

    @Override
    public void showLoading() {
        if (progressDialog != null && progressDialog.isShowing())
            hideLoading();

        progressDialog = ProgressDialog.show(this, null, "Loading, Please wait...", true);
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.hide();
    }
}
