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
        setContentView(R.layout.activity_dangnhap); // Layout đăng nhập

        // Ánh xạ các thành phần
        txtUser = findViewById(R.id.txtuser);
        txtPass = findViewById(R.id.txtpass);
        btnLogin = findViewById(R.id.btnlogin);
        btnSignIn = findViewById(R.id.btnsignin);
        btnDoiMatKhau = findViewById(R.id.btnDoiMatKhau);
        ckMatKhau = findViewById(R.id.ckMatkhau);

        // Đọc lại tài khoản đã ghi nhớ nếu có
        SharedPreferences preferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        boolean remember = preferences.getBoolean("remember", false);
        if (remember) {
            txtUser.setText(preferences.getString("username", ""));
            txtPass.setText(preferences.getString("password", ""));
            ckMatKhau.setChecked(true);
        }

        // Xử lý đăng nhập
        btnLogin.setOnClickListener(v -> {
            String inputUsername = txtUser.getText().toString().trim();
            String inputPassword = txtPass.getText().toString().trim();

            if (inputUsername.isEmpty() || inputPassword.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            // Lấy dữ liệu từ SharedPreferences của RegisterActivity
            SharedPreferences prefs = getSharedPreferences("UserData", MODE_PRIVATE);
            String savedUsername = prefs.getString("username", "");
            String savedPassword = prefs.getString("password", "");
            String savedEmail = prefs.getString("email", "user@example.com");
            String savedPhone = prefs.getString("sdt", "Chưa có số");
            String savedAddress = prefs.getString("diachi", "Chưa có địa chỉ");

            // So sánh thông tin nhập vào
            if (inputUsername.equals(savedUsername) && inputPassword.equals(savedPassword)) {
                Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();

                // Lưu thông tin người dùng vào USER_FILE cho AccountActivity
                SharedPreferences userPrefs = getSharedPreferences("USER_FILE", MODE_PRIVATE);
                SharedPreferences.Editor userEditor = userPrefs.edit();
                userEditor.putString("USERNAME", inputUsername);
                userEditor.putString("EMAIL", savedEmail);
                userEditor.putString("PHONE", savedPhone);
                userEditor.putString("ADDRESS", savedAddress);
                userEditor.apply();

                // Ghi nhớ đăng nhập nếu được chọn
                SharedPreferences.Editor loginEditor = getSharedPreferences("loginPrefs", MODE_PRIVATE).edit();
                if (ckMatKhau.isChecked()) {
                    loginEditor.putBoolean("remember", true);
                    loginEditor.putString("username", inputUsername);
                    loginEditor.putString("password", inputPassword);
                } else {
                    loginEditor.clear();
                }
                loginEditor.apply();

                // Chuyển sang MainActivity
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();

            } else {
                Toast.makeText(this, "Sai tên đăng nhập hoặc mật khẩu", Toast.LENGTH_SHORT).show();
            }
        });

        // Chuyển sang màn đăng ký
        btnSignIn.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });

        // Nút đổi mật khẩu (chưa làm)
        btnDoiMatKhau.setOnClickListener(v -> {
            startActivity(new Intent(this, ChangePasswordActivity.class));
        });

    }
}