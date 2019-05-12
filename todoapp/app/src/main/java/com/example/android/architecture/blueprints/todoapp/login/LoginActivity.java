package com.example.android.architecture.blueprints.todoapp.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.architecture.blueprints.todoapp.R;
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity;

import static com.google.common.base.Preconditions.checkNotNull;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginContract.View {

    private LoginPresenter presenter;

    private ProgressDialog progressDialog;
    private EditText etUsernameLogin;
    private EditText etPasswordLogin;
    private Button btnLoginLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        presenter = new LoginPresenter(this, new LoginModel());
        setPresenter(presenter);
    }

    private void initView() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loadding");

        etUsernameLogin = (EditText) findViewById(R.id.et_username_login);
        etPasswordLogin = (EditText) findViewById(R.id.et_password_login);
        btnLoginLogin = (Button) findViewById(R.id.btn_login_login);

        btnLoginLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login_login:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        String userName = etUsernameLogin.getText().toString().trim();
        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(this, "input username", Toast.LENGTH_SHORT).show();
            return;
        }

        String password = etPasswordLogin.getText().toString().trim();
        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(this, "input password", Toast.LENGTH_SHORT).show();
            return;
        }
        presenter.login(userName, password);

    }

    @Override
    public void showLoadding() {
        progressDialog.show();
    }

    @Override
    public void dissmissLoadding() {
        progressDialog.dismiss();
    }

    @Override
    public void loginSuccess(UserData data) {
        Toast.makeText(this, "Login Success", Toast.LENGTH_LONG).show();
        startActivity(new Intent(this, TasksActivity.class));
    }

    @Override
    public void loginFailure(String erro) {
        Toast.makeText(this, "Login Failure:"+erro, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setPresenter(LoginContract.Presesenter presenter) {
        presenter = checkNotNull(presenter);
    }
}
