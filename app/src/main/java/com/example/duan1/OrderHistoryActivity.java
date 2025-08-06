package com.example.duan1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
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
            View itemView = inflater.inflate(R.layout.item_order, orderList, false);

            TextView tvProducts = itemView.findViewById(R.id.tv_product_info);
            TextView tvTotalPrice = itemView.findViewById(R.id.tv_price);
            TextView tvDate = itemView.findViewById(R.id.tv_date);
            TextView tvCustomer = itemView.findViewById(R.id.tv_customer_name);
            TextView tvPhone = itemView.findViewById(R.id.tv_phone);
            TextView tvAddress = itemView.findViewById(R.id.tv_address);

            // Tạo chuỗi danh sách sản phẩm + tính tổng tiền
            StringBuilder productInfo = new StringBuilder();
            int totalPrice = 0;
            for (CartItem item : order.getItems()) {
                productInfo.append("- ")
                        .append(item.getName())
                        .append(" x").append(item.getQuantity())
                        .append(" (").append(item.getPrice()).append("đ)\n");

                totalPrice += item.getPrice() * item.getQuantity();
            }

            tvProducts.setText(productInfo.toString().trim());
            tvTotalPrice.setText("Tổng tiền: " + totalPrice + "đ");
            tvDate.setText("Ngày: " + sdf.format(new Date(order.getTimestamp())));
            tvCustomer.setText("Khách hàng: " + order.getCustomerName());
            tvPhone.setText("SĐT: " + order.getPhoneNumber());
            tvAddress.setText("Địa chỉ: " + order.getAddress());

            orderList.addView(itemView);
        }
    }
}
