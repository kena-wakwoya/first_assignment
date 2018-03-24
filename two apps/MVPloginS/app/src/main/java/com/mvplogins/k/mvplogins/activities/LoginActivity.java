package com.mvplogins.k.mvplogins.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mvplogins.k.mvplogins.R;
import com.mvplogins.k.mvplogins.model.PresenterImplements;
import com.mvplogins.k.mvplogins.presenter.LoginPresenter;
import com.mvplogins.k.mvplogins.view.LoginView;

public class LoginActivity extends Activity implements View.OnClickListener,LoginView {
    EditText etusername,etpassword;
    TextView txvLogin;
    LoginPresenter mLoginPresenter; //this is the presenter contract that is used to connect the presenter and the view together


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etusername = findViewById(R.id.etusername);
        etpassword = findViewById(R.id.etpassword);
        txvLogin = findViewById(R.id.txtvLogin);
        txvLogin.setOnClickListener(this);
        mLoginPresenter = new PresenterImplements(LoginActivity.this);
    }
    public void onClick(View v) {
        String username=  etusername.getText().toString();
        String password = etpassword.getText().toString();
        mLoginPresenter.performLogin(username,password);
    }
    public void loginValidations(){
        Toast.makeText(getApplicationContext(),"please enter username and password!",Toast.LENGTH_LONG).show();
    }



    public void loginSuccess(){
        Toast.makeText(getApplicationContext(),"login success",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this,IntentActivity.class);
        startActivity(intent);

    }
    public void loginError(){
        Toast.makeText(getApplicationContext(),"login failure",Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(),"username or password is incorrect!",Toast.LENGTH_LONG).show();
    }

}
