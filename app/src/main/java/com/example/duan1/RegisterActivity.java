package com.example.duan1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText txtMa, txtTen, soDienThoai, edtDiaChi, txtPass;
    Button btnDangKy, btnBack;

    // Giả lập lưu thông tin đơn giản (thay thế database thật)
    public static String savedUsername = "";
    public static String savedPassword = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_kyactivity); // tên layout đăng ký của bạn

        txtMa = findViewById(R.id.txtma);
        txtTen = findViewById(R.id.txtten);
        soDienThoai = findViewById(R.id.sodienthoai);
        edtDiaChi = findViewById(R.id.edtDiaChi);
        txtPass = findViewById(R.id.txtpass);
        btnDangKy = findViewById(R.id.btndk);
        btnBack = findViewById(R.id.back);

        btnDangKy.setOnClickListener(v -> {
            String username = txtMa.getText().toString().trim();
            String password = txtPass.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ tên đăng nhập và mật khẩu", Toast.LENGTH_SHORT).show();
                return;
            }

            // Lưu thông tin tạm (bạn có thể thay bằng database, Firebase, SQLite, ...)
            savedUsername = username;
            savedPassword = password;

            Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();

            // Chuyển về màn hình đăng nhập
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

        btnBack.setOnClickListener(v -> finish()); // Trở lại màn hình trước
    }
}

