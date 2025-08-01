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
        setContentView(R.layout.activity_dangnhap); // tên layout đăng nhập

        txtUser = findViewById(R.id.txtuser);
        txtPass = findViewById(R.id.txtpass);
        btnLogin = findViewById(R.id.btnlogin);
        btnSignIn = findViewById(R.id.btnsignin);
        btnDoiMatKhau = findViewById(R.id.btnDoiMatKhau);
        ckMatKhau = findViewById(R.id.ckMatkhau);

        // Đọc SharedPreferences lưu thông tin ghi nhớ đăng nhập
        SharedPreferences loginPrefs = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        boolean remember = loginPrefs.getBoolean("remember", false);

        if (remember) {
            txtUser.setText(loginPrefs.getString("username", ""));
            txtPass.setText(loginPrefs.getString("password", ""));
            ckMatKhau.setChecked(true);
        }

        btnLogin.setOnClickListener(v -> {
            String inputUsername = txtUser.getText().toString().trim();
            String inputPassword = txtPass.getText().toString().trim();

            // 🔒 Đọc tài khoản đã đăng ký từ SharedPreferences "user_data"
            SharedPreferences userPrefs = getSharedPreferences("user_data", MODE_PRIVATE);
            String savedUsername = userPrefs.getString("username", "");
            String savedPassword = userPrefs.getString("password", "");

            if (inputUsername.equals(savedUsername) && inputPassword.equals(savedPassword)) {
                Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();

                // Ghi nhớ tài khoản nếu chọn
                if (ckMatKhau.isChecked()) {
                    SharedPreferences.Editor editor = loginPrefs.edit();
                    editor.putString("username", inputUsername);
                    editor.putString("password", inputPassword);
                    editor.putBoolean("remember", true);
                    editor.apply();
                } else {
                    loginPrefs.edit().clear().apply();
                }

                // Chuyển sang MainActivity
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
