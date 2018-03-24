package com.mvplogins.k.mvplogins.view;

/**
 * Created by k on 3/18/2018.
 * this is the contract between the view{@link com.mvplogins.k.mvplogins.activities.LoginActivity} and
 * the presenter{@link com.mvplogins.k.mvplogins.presenter.LoginPresenter}
 */




public interface LoginView {
    void loginValidations();
    void loginSuccess();
    void loginError();
}
