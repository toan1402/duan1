package com.example.duan1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ChiTietActivity extends AppCompatActivity {

    private ImageView imgProduct;
    private TextView tvName, tvPrice, tvCategory;
    private Button btnBuyNow;
    private ImageButton btnBack;

    private String name;
    private int price;
    private String category;
    private int imageRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_mon);

        imgProduct = findViewById(R.id.imgProduct);
        tvName = findViewById(R.id.tvName);
        tvPrice = findViewById(R.id.tvPrice);
        tvCategory = findViewById(R.id.tvCategory);
        btnBuyNow = findViewById(R.id.btnBuyNow);
        btnBack = findViewById(R.id.btnBack);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        price = intent.getIntExtra("price", 0);
        category = intent.getStringExtra("category");
        imageRes = intent.getIntExtra("imageRes", R.drawable.banhmy);
        tvName.setText(name);
        tvPrice.setText("Giá: " + price + "đ");
        tvCategory.setText("Danh mục: " + category);
        imgProduct.setImageResource(imageRes);

        btnBack.setOnClickListener(view -> finish());

        btnBuyNow.setOnClickListener(v -> {
            CartItem item = new CartItem(name, price, 1, imageRes);
            CartManager.addToCart(item);
            Toast.makeText(this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();

            Intent cartIntent = new Intent(ChiTietActivity.this, CartActivity.class);
            startActivity(cartIntent);
        });
    }
}
