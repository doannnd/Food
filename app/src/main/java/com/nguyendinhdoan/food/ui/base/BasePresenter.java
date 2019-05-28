package com.nguyendinhdoan.food.ui.base;

public class BasePresenter<V extends MvpView> implements MvpPresenter<V>{

    private V view;

    @Override
    public void onAttach(V mvpView) {
        this.view = mvpView;
    }

    @Override
    public void onDetach() {
        this.view = null;
    }

    public boolean isViewAttached() {
        return view != null;
    }

    public V getMvpView() {
        return view;
    }

}
