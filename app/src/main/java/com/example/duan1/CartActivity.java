package com.example.duan1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duan1.Product;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    private int quantity = 1;
    private final int unitPrice = 40000;
    private LinearLayout cartList;
    private TextView tvQuantity, tvTotalPrice;
    private View itemView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Ánh xạ view
        Button btnCheckout = findViewById(R.id.btn_checkout);
        ImageButton btnBack = findViewById(R.id.btn_back); // ✅ chỉ khai báo 1 lần
        tvTotalPrice = findViewById(R.id.tv_total_price);
        cartList = findViewById(R.id.cart_list);

        itemView = cartList.getChildAt(0); // lấy món ăn đầu tiên

        tvQuantity = itemView.findViewById(R.id.tv_quantity);
        ImageButton btnIncrease = itemView.findViewById(R.id.btn_increase);
        ImageButton btnDecrease = itemView.findViewById(R.id.btn_decrease);
        ImageButton btnDelete = itemView.findViewById(R.id.btn_delete);

        updateTotalPrice();

        // Tăng
        btnIncrease.setOnClickListener(v -> {
            quantity++;
            tvQuantity.setText(String.valueOf(quantity));
            updateTotalPrice();
        });

        // Giảm
        btnDecrease.setOnClickListener(v -> {
            if (quantity > 1) {
                quantity--;
                tvQuantity.setText(String.valueOf(quantity));
                updateTotalPrice();
            }
        });

        // Xoá
        btnDelete.setOnClickListener(v -> {
            cartList.removeView(itemView);
            quantity = 0;
            updateTotalPrice();
        });
        btnCheckout.setOnClickListener(v -> {
            List<Product> cartItems = CartManager.getCartItems();

            // Nếu không có sản phẩm thì thoát sớm


            // Nếu có sản phẩm thì chuyển sang màn xác nhận
            Intent intent = new Intent(CartActivity.this, XacNhanThanhToanActivity.class);
            startActivity(intent);
            Toast.makeText(CartActivity.this, "Đặt hàng thành công!", Toast.LENGTH_SHORT).show();


            new android.os.Handler().postDelayed(() -> {
                Intent mainIntent = new Intent(CartActivity.this, MainActivity.class);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(mainIntent);
                finish(); // Đóng CartActivity nếu cần
            }, 3000); // 3 giây
        });




        // Quay lại
        btnBack.setOnClickListener(v -> finish()); // ✅ không lặp lại
    }

    private void updateTotalPrice() {
        int total = quantity * unitPrice;
        tvTotalPrice.setText("Tổng: " + total + "đ");
    }
}