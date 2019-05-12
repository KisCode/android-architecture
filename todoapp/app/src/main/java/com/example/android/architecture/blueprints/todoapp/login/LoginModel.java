package com.example.android.architecture.blueprints.todoapp.login;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import androidx.annotation.UiThread;

import java.util.Random;

/***
 * 登录业务逻辑
 */
public class LoginModel {

    public void loging(String username, String password, final LoginCallBack callBack) {
        //模拟登录业务逻辑
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int time = new Random().nextInt(5) + 1;
                    try {
                        Thread.sleep(time * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    boolean loginResult = new Random().nextBoolean();
                    if (loginResult) {
                        // mock userDdata
                        UserData userData =new UserData();
                        userData.setEmail("keno@126.com");
                        userData.setUserName("keno");
                        callBack.onSuccess(userData);
                    } else {
                        callBack.onFailure("登录失败");
                    }
                }
            }).start();

           
        }
    }

    interface LoginCallBack {
        void onFailure(String erroInfo);

        void onSuccess(UserData userData);
    }
}
