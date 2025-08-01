package com.example.duan1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailActivity extends AppCompatActivity {

    private TextView tvName, tvCategory, tvPrice;
    private ImageView imgProduct;
    private ImageButton btnBack;
    private Button btnBuyNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_mon); // ✅ layout đúng

        // Ánh xạ view
        tvName = findViewById(R.id.tvName);
        tvCategory = findViewById(R.id.tvCategory);
        tvPrice = findViewById(R.id.tvPrice);
        imgProduct = findViewById(R.id.imgProduct);
        btnBack = findViewById(R.id.btnBack);
        btnBuyNow = findViewById(R.id.btnBuyNow);

        // Nhận dữ liệu từ intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String category = intent.getStringExtra("category");
        int imageResId = intent.getIntExtra("image", 0);
        int price = intent.getIntExtra("price", 0);

        // Gán dữ liệu vào View
        tvName.setText(name);
        tvCategory.setText("Danh mục: " + category);
        tvPrice.setText("Giá: " + price + "đ");
        imgProduct.setImageResource(imageResId);

        // Nút quay lại
        btnBack.setOnClickListener(v -> finish());

        // Nút mua ngay → sang giỏ hàng
        btnBuyNow.setOnClickListener(v -> {
            Intent cartIntent = new Intent(ProductDetailActivity.this, CartActivity.class);
            startActivity(cartIntent);
        });
    }
}
