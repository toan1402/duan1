package com.example.duan1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ProductDetailActivity extends AppCompatActivity {

    private TextView tvName, tvCategory, tvPrice;
    private ImageView imgProduct;
    private ImageButton btnBack;
    private Button btnBuyNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_mon);

        tvName = findViewById(R.id.tvName);
        tvCategory = findViewById(R.id.tvCategory);
        tvPrice = findViewById(R.id.tvPrice);
        imgProduct = findViewById(R.id.imgProduct);
        btnBack = findViewById(R.id.btnBack);
        btnBuyNow = findViewById(R.id.btnBuyNow);

        // Lấy dữ liệu truyền từ Intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String category = intent.getStringExtra("category");
        int imageResId = intent.getIntExtra("image", 0);
        int price = intent.getIntExtra("price", 0);

        // Gán dữ liệu lên view
        tvName.setText(name);
        tvCategory.setText("Danh mục: " + category);
        tvPrice.setText("Giá: " + price + "đ");
        imgProduct.setImageResource(imageResId);

        btnBack.setOnClickListener(v -> finish());

        btnBuyNow.setOnClickListener(v -> {
            // Tạo list chỉ chứa sản phẩm này
            ArrayList<CartItem> singleItemList = new ArrayList<>();
            singleItemList.add(new CartItem(name, price, 1, imageResId));  // tạo sản phẩm trực tiếp, không thêm vào CartManager

            // Lấy thông tin người dùng
            SharedPreferences prefs = getSharedPreferences("USER_FILE", MODE_PRIVATE);
            String userName = prefs.getString("USERNAME", "Không có tên");
            String phone = prefs.getString("PHONE", "Chưa có số");
            String address = prefs.getString("ADDRESS", "Chưa có địa chỉ");

            // Tạo đơn hàng mới với sản phẩm này
            Order newOrder = new Order(userName, phone, address, singleItemList, System.currentTimeMillis());

            // Lưu đơn hàng
            OrderManager.addOrder(newOrder);

            // Chuyển đến màn hình xác nhận thanh toán
            Intent confirmIntent = new Intent(ProductDetailActivity.this, XacNhanThanhToanActivity.class);
            startActivity(confirmIntent);
            finish();
        });

    }
}
