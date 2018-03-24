package com.mvplogins.k.mvplogins.model;

import android.text.TextUtils;

import com.mvplogins.k.mvplogins.presenter.LoginPresenter;
import com.mvplogins.k.mvplogins.view.LoginView;

/**
 * Created by k on 3/18/2018.
 */


/*
This  part is the model part of the code
 */
public class PresenterImplements implements LoginPresenter {
    LoginView mLoginView;
    public PresenterImplements(LoginView loginView){
        this.mLoginView = loginView;
    }
    @Override
    public void performLogin(String username, String password) {
    if(TextUtils.isEmpty(username)||TextUtils.isEmpty(password)){
        mLoginView.loginValidations();

    }
    else{
        if(username.equals("kena") && password.equals("kena")||username.equals("mike") &&password.equals("mike") || username.equals("sena") && password.equals("sena")){
            mLoginView.loginSuccess();
        }
        else{
            mLoginView.loginError();
        }
    }
    }
}
