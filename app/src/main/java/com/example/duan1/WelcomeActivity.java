package com.example.duan1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome); // layout bạn gửi

        // Chuyển sang màn hình đăng nhập sau 3 giây
        new Handler().postDelayed(() -> {
            startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
            finish(); // Đóng welcome
        }, 3000); // 3000 ms = 3 giây
    }
}
