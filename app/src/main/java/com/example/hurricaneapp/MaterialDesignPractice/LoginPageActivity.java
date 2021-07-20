package com.example.hurricaneapp.MaterialDesignPractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hurricaneapp.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginPageActivity extends AppCompatActivity {
    private static final String EMAIL_PATTERN =
            "^\\s*\\w+(?:\\.?[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);


        TextInputLayout textLayoutUsername = findViewById(R.id.tl_username);
        TextInputLayout textLayoutPassword = findViewById(R.id.tl_password);

//        EditText editTextUsername = findViewById(R.id.et_username);
//        EditText editTextPassword = findViewById(R.id.et_password);

        Button login_btn = findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                login(textLayoutUsername, textLayoutPassword);
            }
        });
    }

    private boolean validateUsername(String username){
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }

    private boolean validatePassword(String password){
        return password.length() > 6;
    }

    private void login(TextInputLayout textLayoutUsername, TextInputLayout textLayoutPassword){
        String username = textLayoutUsername.getEditText().getText().toString();
        String password = textLayoutPassword.getEditText().getText().toString();

        if(!validateUsername(username)){
            textLayoutUsername.setErrorEnabled(true);
            textLayoutUsername.setError("请输入正确的邮箱地址");
        } else if(!validatePassword(password)) {
            textLayoutPassword.setErrorEnabled(true);
            textLayoutPassword.setError("密码长度过短");
        } else {
            textLayoutUsername.setErrorEnabled(false);
            textLayoutPassword.setErrorEnabled(false);
            Toast.makeText(this,"登陆成功",Toast.LENGTH_SHORT);
        }
    }
}