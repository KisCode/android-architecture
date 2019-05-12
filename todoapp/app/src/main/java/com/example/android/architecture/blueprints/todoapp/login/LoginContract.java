package com.example.android.architecture.blueprints.todoapp.login;

import com.example.android.architecture.blueprints.todoapp.BasePresenter;
import com.example.android.architecture.blueprints.todoapp.BaseView;

public interface LoginContract {
    interface View extends BaseView<Presesenter> {

        void showLoadding();

        void dissmissLoadding();

        void loginSuccess(UserData data);

        void loginFailure(String erro);
    }

    interface Presesenter extends BasePresenter {
        void login(String username, String password);

    }
}
