package com.example.duan1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AccountActivity extends AppCompatActivity {

    TextView tvUsername, tvEmail, tvPhone, tvAddress;
    Button btnLogout, btnEditInfo;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        setTitle("Tài khoản của tôi");

        // Ánh xạ view
        tvUsername = findViewById(R.id.tvUsername);
        tvEmail = findViewById(R.id.tvEmail);
        tvPhone = findViewById(R.id.tvPhone);
        tvAddress = findViewById(R.id.tvAddress);
        btnLogout = findViewById(R.id.btnLogout);
        btnEditInfo = findViewById(R.id.btnEditInfo);
        ImageButton btnBack = findViewById(R.id.btnBack); // ánh xạ nút back

        // Khởi tạo SharedPreferences
        sharedPreferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);

        // Xử lý nút "Chỉnh sửa thông tin"
        btnEditInfo.setOnClickListener(v -> {
            Intent intent = new Intent(AccountActivity.this, EditAccountActivity.class);
            intent.putExtra("username", sharedPreferences.getString("USERNAME", ""));
            intent.putExtra("email", sharedPreferences.getString("EMAIL", ""));
            intent.putExtra("phone", sharedPreferences.getString("PHONE", ""));
            intent.putExtra("address", sharedPreferences.getString("ADDRESS", ""));
            startActivity(intent);
        });

        // Xử lý nút "Đăng xuất"
        btnLogout.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();

            Intent intent = new Intent(AccountActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        // Xử lý nút "Quay lại MainActivity"
        btnBack.setOnClickListener(v -> {
            finish(); // Quay về MainActivity (nếu AccountActivity được mở từ đó)
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        String username = sharedPreferences.getString("USERNAME", "Không có tên");
        String email = sharedPreferences.getString("EMAIL", "Không có email");
        String phone = sharedPreferences.getString("PHONE", "Chưa có số điện thoại");
        String address = sharedPreferences.getString("ADDRESS", "Chưa có địa chỉ");

        tvUsername.setText("Tên người dùng: " + username);
        tvEmail.setText("Email: " + email);
        tvPhone.setText("Số điện thoại: " + phone);
        tvAddress.setText("Địa chỉ: " + address);
    }
}
