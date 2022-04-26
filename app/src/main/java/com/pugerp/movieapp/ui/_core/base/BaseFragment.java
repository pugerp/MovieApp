package com.pugerp.movieapp.ui._core.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pugerp.movieapp.di.components.DaggerFragmentComponent;
import com.pugerp.movieapp.di.components.FragmentComponent;
import com.pugerp.movieapp.di.modules.FragmentModule;
import com.pugerp.movieapp.ui._core.mvp.MvpView;
import com.yatatsu.autobundle.AutoBundle;

import javax.inject.Inject;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements MvpView {

    @Inject
    protected P presenter;

    public BaseActivity activity;
    private FragmentComponent fragmentComponent;

    protected abstract int layout();

    protected abstract void setup(View view);

    protected abstract void setComponent(@NonNull FragmentComponent component);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            AutoBundle.bind(this, savedInstanceState);
        } else {
            AutoBundle.bind(this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = getActivity().getLayoutInflater().inflate(layout(), container, false);

        createComponent();
        onCreateComponent(fragmentComponent);
        setup(v);

        return v;
    }

    private void onCreateComponent(FragmentComponent fragmentComponent) {
        setComponent(fragmentComponent);
    }


    private void createComponent() {
        fragmentComponent = DaggerFragmentComponent.builder()
                .activityComponent(((BaseActivity) getActivity()).getActivityComponent())
                .fragmentModule(new FragmentModule(this))
                .build();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            this.activity = (BaseActivity) context;
            activity.onFragmentAttach();
        }
    }

    @Override
    public void onDetach() {
        activity.onFragmentDetach();
        super.onDetach();
    }

    public interface BaseFragmentListener {
        void onFragmentAttach();
        void onFragmentDetach();
    }

}
