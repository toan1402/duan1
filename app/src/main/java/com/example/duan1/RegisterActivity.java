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
            String ten = txtTen.getText().toString().trim();
            String sdt = soDienThoai.getText().toString().trim();
            String diaChi = edtDiaChi.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty() || ten.isEmpty() || sdt.isEmpty() || diaChi.isEmpty()) {
                Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            // Lưu thông tin người dùng vào SharedPreferences
            SharedPreferences prefs = getSharedPreferences("UserData", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("username", username);
            editor.putString("password", password);
            editor.putString("ten", ten);
            editor.putString("sdt", sdt);
            editor.putString("diachi", diaChi);
            editor.apply();

            Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();

            // Chuyển sang LoginActivity
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });


        btnBack.setOnClickListener(v -> finish()); // Trở lại màn hình trước
    }
}

