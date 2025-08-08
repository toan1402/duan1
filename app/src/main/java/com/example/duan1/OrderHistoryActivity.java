package com.example.duan1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class OrderHistoryActivity extends AppCompatActivity {

    private LinearLayout orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_don_hang);

        orderList = findViewById(R.id.orderHistoryContainer);
        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        List<Order> orders = OrderManager.getOrderHistory();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());

        LayoutInflater inflater = LayoutInflater.from(this);

        for (Order order : orders) {
            View orderView = inflater.inflate(R.layout.item_order, orderList, false);

            LinearLayout itemContainer = orderView.findViewById(R.id.item_container);
            TextView tvTotalPrice = orderView.findViewById(R.id.tv_price);
            TextView tvDate = orderView.findViewById(R.id.tv_date);
            TextView tvCustomer = orderView.findViewById(R.id.tv_customer_name);
            TextView tvPhone = orderView.findViewById(R.id.tv_phone);
            TextView tvAddress = orderView.findViewById(R.id.tv_address);

            int totalPrice = 0;

            for (CartItem item : order.getItems()) {
                View productView = inflater.inflate(R.layout.item_order_product, itemContainer, false);

                ImageView imgProduct = productView.findViewById(R.id.img_product);
                TextView tvProductInfo = productView.findViewById(R.id.tv_product_info);

                imgProduct.setImageResource(item.getImageResId());
                tvProductInfo.setText("• " + item.getName() + " x" + item.getQuantity() + " (" + item.getPrice() + "đ)");

                totalPrice += item.getPrice() * item.getQuantity();
                itemContainer.addView(productView);
            }

            tvTotalPrice.setText("Tổng tiền: " + totalPrice + "đ");
            tvDate.setText("Ngày: " + sdf.format(new Date(order.getTimestamp())));
            tvCustomer.setText("Khách hàng: " + order.getCustomerName());
            tvPhone.setText("SĐT: " + order.getPhoneNumber());
            tvAddress.setText("Địa chỉ: " + order.getAddress());

            orderList.addView(orderView);
        }


    }
}
