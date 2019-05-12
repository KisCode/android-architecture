package com.example.android.architecture.blueprints.todoapp.login;

import android.os.Handler;
import android.os.Message;

/**
 * Login
 */
public class LoginPresenter implements LoginContract.Presesenter {

    private LoginContract.View loginView;
    private LoginModel loginModel;

    public LoginPresenter(LoginContract.View loginView, LoginModel loginModel) {
        this.loginView = loginView;
        this.loginModel = loginModel;
    }

    @Override
    public void login(String username, String password) {
        loginView.showLoadding();
        loginModel.loging(username, password, new LoginModel.LoginCallBack() {
            @Override
            public void onFailure(String erroInfo) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        loginView.dissmissLoadding();
                        loginView.loginFailure("Login Failure");
                    }
                });
            }

            @Override
            public void onSuccess(final UserData userData) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        loginView.dissmissLoadding();
                        loginView.loginSuccess(userData);
                    }
                });
            }
        });
    }

    @Override
    public void start() {

    }


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };
}
