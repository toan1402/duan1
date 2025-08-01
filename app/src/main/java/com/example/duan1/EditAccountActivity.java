package com.example.duan1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EditAccountActivity extends AppCompatActivity {

    EditText edtUsername, edtEmail, edtPhone, edtAddress;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
        setTitle("Chỉnh sửa thông tin");

        edtUsername = findViewById(R.id.edtUsername);
        edtEmail = findViewById(R.id.edtEmail);
        edtPhone = findViewById(R.id.edtPhone);
        edtAddress = findViewById(R.id.edtAddress);
        btnSave = findViewById(R.id.btnSave);

        // Nhận dữ liệu từ Intent
        edtUsername.setText(getIntent().getStringExtra("username"));
        edtEmail.setText(getIntent().getStringExtra("email"));
        edtPhone.setText(getIntent().getStringExtra("phone"));
        edtAddress.setText(getIntent().getStringExtra("address"));

        btnSave.setOnClickListener(v -> {
            String username = edtUsername.getText().toString();
            String email = edtEmail.getText().toString();
            String phone = edtPhone.getText().toString();
            String address = edtAddress.getText().toString();

            // Lưu lại vào SharedPreferences
            SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("USERNAME", username);
            editor.putString("EMAIL", email);
            editor.putString("PHONE", phone);
            editor.putString("ADDRESS", address);
            editor.apply();

            Toast.makeText(EditAccountActivity.this, "Đã lưu thông tin", Toast.LENGTH_SHORT).show();
            finish(); // Quay lại AccountActivity
        });
    }
}
