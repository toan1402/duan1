package com.example.duan1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText txtMa, txtTen, soDienThoai, edtDiaChi, txtPass;
    Button btnDangKy, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_kyactivity);

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

            // ✅ Lưu thông tin bằng SharedPreferences
            SharedPreferences preferences = getSharedPreferences("user_data", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("username", username);
            editor.putString("password", password);
            editor.apply();

            Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

        btnBack.setOnClickListener(v -> finish());
    }
}
