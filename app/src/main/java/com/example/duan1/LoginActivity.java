package com.example.duan1;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText txtUser, txtPass;
    Button btnLogin, btnSignIn, btnDoiMatKhau;
    CheckBox ckMatKhau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap); // tên layout đăng nhập của bạn

        txtUser = findViewById(R.id.txtuser);
        txtPass = findViewById(R.id.txtpass);
        btnLogin = findViewById(R.id.btnlogin);
        btnSignIn = findViewById(R.id.btnsignin);
        btnDoiMatKhau = findViewById(R.id.btnDoiMatKhau);
        ckMatKhau = findViewById(R.id.ckMatkhau);

        SharedPreferences preferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        boolean remember = preferences.getBoolean("remember", false);

        if (remember) {
            txtUser.setText(preferences.getString("username", ""));
            txtPass.setText(preferences.getString("password", ""));
            ckMatKhau.setChecked(true);
        }

        btnLogin.setOnClickListener(v -> {
            String username = txtUser.getText().toString().trim();
            String password = txtPass.getText().toString().trim();

            if (username.equals(RegisterActivity.savedUsername) && password.equals(RegisterActivity.savedPassword)) {
                Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                // Lưu thông tin nếu nhớ mật khẩu
                if (ckMatKhau.isChecked()) {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("username", username);
                    editor.putString("password", password);
                    editor.putBoolean("remember", true);
                    editor.apply();
                } else {
                    preferences.edit().clear().apply();
                }

                // Vào trang chính hoặc trang chào mừng
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Tên đăng nhập hoặc mật khẩu sai!", Toast.LENGTH_SHORT).show();
            }
        });

        btnSignIn.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });

        btnDoiMatKhau.setOnClickListener(v -> {
            Toast.makeText(this, "Chức năng đang phát triển...", Toast.LENGTH_SHORT).show();
        });
    }
}

