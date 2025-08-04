package com.example.duan1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;

public class CartActivity extends AppCompatActivity {

    private LinearLayout cartList;
    private TextView tvTotalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        ImageButton btnBack = findViewById(R.id.btn_back);
        Button btnCheckout = findViewById(R.id.btn_checkout);
        tvTotalPrice = findViewById(R.id.tv_total_price);
        cartList = findViewById(R.id.cart_list);

        loadCartItems();

        btnCheckout.setOnClickListener(v -> {
            if (CartManager.getCartItems().isEmpty()) {
                Toast.makeText(this, "Giỏ hàng đang trống!", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(this, "Đặt hàng thành công!", Toast.LENGTH_SHORT).show();
            CartManager.clearCart();

            new android.os.Handler().postDelayed(() -> {
                Intent intent = new Intent(CartActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }, 3000);
        });

        btnBack.setOnClickListener(v -> finish());
    }

    private void loadCartItems() {
        cartList.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(this);

        for (Map.Entry<Product, Integer> entry : CartManager.getCartMap().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            View itemView = inflater.inflate(R.layout.item_cart, cartList, false);

            TextView name = itemView.findViewById(R.id.tv_food_name);
            TextView price = itemView.findViewById(R.id.tv_food_price);
            TextView tvQty = itemView.findViewById(R.id.tv_quantity);
            ImageView img = itemView.findViewById(R.id.img_food);
            ImageButton btnInc = itemView.findViewById(R.id.btn_increase);
            ImageButton btnDec = itemView.findViewById(R.id.btn_decrease);
            ImageButton btnDelete = itemView.findViewById(R.id.btn_delete);

            name.setText("Tên: " + product.getName());
            price.setText("Giá: " + product.getPrice() + "đ");
            img.setImageResource(product.getImageResId());
            tvQty.setText(String.valueOf(quantity));

            btnInc.setOnClickListener(v -> {
                CartManager.updateQuantity(product, CartManager.getQuantity(product) + 1);
                tvQty.setText(String.valueOf(CartManager.getQuantity(product)));
                updateTotalPrice();
            });

            btnDec.setOnClickListener(v -> {
                int newQty = CartManager.getQuantity(product) - 1;
                if (newQty > 0) {
                    CartManager.updateQuantity(product, newQty);
                    tvQty.setText(String.valueOf(newQty));
                } else {
                    CartManager.removeFromCart(product);
                    cartList.removeView(itemView);
                }
                updateTotalPrice();
            });

            btnDelete.setOnClickListener(v -> {
                CartManager.removeFromCart(product);
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
