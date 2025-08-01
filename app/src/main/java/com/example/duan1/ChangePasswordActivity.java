package com.example.duan1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ChangePasswordActivity extends AppCompatActivity {

    EditText edtOldPass, edtNewPass, edtConfirmPass;
    Button btnChangePass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        setTitle("Đổi mật khẩu");

        edtOldPass = findViewById(R.id.edtOldPass);
        edtNewPass = findViewById(R.id.edtNewPass);
        edtConfirmPass = findViewById(R.id.edtConfirmPass);
        btnChangePass = findViewById(R.id.btnChangePass);

        btnChangePass.setOnClickListener(v -> {
            String oldPass = edtOldPass.getText().toString().trim();
            String newPass = edtNewPass.getText().toString().trim();
            String confirmPass = edtConfirmPass.getText().toString().trim();

            // Lấy mật khẩu đã lưu trong SharedPreferences
            SharedPreferences prefs = getSharedPreferences("UserData", MODE_PRIVATE);
            String savedPass = prefs.getString("password", "");

            if (!oldPass.equals(savedPass)) {
                Toast.makeText(this, "Mật khẩu cũ không đúng", Toast.LENGTH_SHORT).show();
                return;
            }

            if (newPass.isEmpty() || confirmPass.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!newPass.equals(confirmPass)) {
                Toast.makeText(this, "Mật khẩu xác nhận không khớp", Toast.LENGTH_SHORT).show();
                return;
            }

            // Cập nhật lại mật khẩu mới
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("password", newPass);
            editor.apply();

            Toast.makeText(this, "Đổi mật khẩu thành công!", Toast.LENGTH_SHORT).show();
            finish(); // Quay về màn trước
        });
    }
}