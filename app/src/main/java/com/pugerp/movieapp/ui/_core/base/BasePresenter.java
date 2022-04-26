package com.pugerp.movieapp.ui._core.base;

import android.util.Log;

import androidx.annotation.NonNull;

import com.pugerp.movieapp.network.NetworkCallback;
import com.pugerp.movieapp.network.NetworkInterface;
import com.pugerp.movieapp.ui._core.mvp.MvpView;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter<V extends MvpView, K> {

    private CompositeSubscription compositeSubscription;
    private Subscriber subscriber;
    protected V view;
    protected K component;

    @Inject
    public NetworkInterface networkInterface;

    public abstract void setComponent(@NonNull K component);

    public void onAttach(V view) {
        this.view = view;
    }

    public void onDetach() {
        view = null;
    }

    public void onUnsubscribe() {
        if (compositeSubscription != null && compositeSubscription.hasSubscriptions()) {
            if (subscriber != null) {
                subscriber.unsubscribe();
            }
            compositeSubscription.unsubscribe();
            Log.e("TAG", "onUnsubscribe...");
        }
    }

    protected <T> void addSubscribe(Observable<T> observable, Subscriber<T> subscriber) {
        this.subscriber = subscriber;
        if (compositeSubscription == null) {
            compositeSubscription = new CompositeSubscription();
        }
        compositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }

    public void stop() {
        if (subscriber != null) {
            subscriber.unsubscribe();
        }
    }
}
