package com.example.duan1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    private LinearLayout cartList;
    private TextView tvTotalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        ImageButton btnBack = findViewById(R.id.btnBack);
        Button btnCheckout = findViewById(R.id.btn_checkout);
        tvTotalPrice = findViewById(R.id.tv_total_price);
        cartList = findViewById(R.id.cart_list);

        loadCartItems();

        btnCheckout.setOnClickListener(v -> {
            if (CartManager.getCartItems().isEmpty()) {
                Toast.makeText(this, "Giỏ hàng đang trống!", Toast.LENGTH_SHORT).show();
                return;
            }

            CartManager.clearCart();

            Intent intent = new Intent(CartActivity.this, XacNhanThanhToanActivity.class);
            startActivity(intent);
            finish();
        });

        btnBack.setOnClickListener(v -> finish());
    }

    private void loadCartItems() {
        cartList.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(this);

        List<CartItem> items = CartManager.getCartItems();
        for (CartItem item : items) {
            View itemView = inflater.inflate(R.layout.item_cart, cartList, false);

            TextView name = itemView.findViewById(R.id.tv_food_name);
            TextView price = itemView.findViewById(R.id.tv_food_price);
            TextView tvQty = itemView.findViewById(R.id.tv_quantity);
            ImageView img = itemView.findViewById(R.id.img_food);
            ImageButton btnInc = itemView.findViewById(R.id.btn_increase);
            ImageButton btnDec = itemView.findViewById(R.id.btn_decrease);
            ImageButton btnDelete = itemView.findViewById(R.id.btn_delete);

            name.setText("Tên: " + item.getName());
            price.setText("Giá: " + item.getPrice() + "đ");
            tvQty.setText(String.valueOf(item.getQuantity()));
            img.setImageResource(item.getImageResId());

            btnInc.setOnClickListener(v -> {
                item.setQuantity(item.getQuantity() + 1);
                tvQty.setText(String.valueOf(item.getQuantity()));
                updateTotalPrice();
            });

            btnDec.setOnClickListener(v -> {
                int newQty = item.getQuantity() - 1;
                if (newQty > 0) {
                    item.setQuantity(newQty);
                    tvQty.setText(String.valueOf(newQty));
                } else {
                    CartManager.removeFromCart(item.getName());
                    cartList.removeView(itemView);
                }
                updateTotalPrice();
            });

            btnDelete.setOnClickListener(v -> {
                CartManager.removeFromCart(item.getName());
                cartList.removeView(itemView);
                updateTotalPrice();
            });

            cartList.addView(itemView);
        }

        updateTotalPrice();
    }

    private void updateTotalPrice() {
        int total = CartManager.getTotalPrice();
        tvTotalPrice.setText("Tổng: " + total + "đ");
    }
}
