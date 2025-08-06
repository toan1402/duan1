package com.example.duan1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private List<Order> orderList;

    public OrderAdapter(List<Order> orders) {
        this.orderList = orders;
    }

    public void setData(List<Order> orders) {
        this.orderList = orders;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orderList.get(position);

        // Gộp danh sách sản phẩm thành 1 chuỗi
        StringBuilder productInfo = new StringBuilder();
        int totalPrice = 0;
        for (CartItem item : order.getItems()) {
            productInfo.append("- ")
                    .append(item.getName())
                    .append(" (x").append(item.getQuantity()).append("): ")
                    .append(item.getPrice()).append(" VND\n");
            totalPrice += item.getPrice() * item.getQuantity();
        }

        holder.txtProductName.setText(productInfo.toString().trim());
        holder.txtQuantity.setText(""); // Không dùng nữa nếu gộp rồi
        holder.txtPrice.setText("Tổng giá: " + totalPrice + " VND");
        holder.txtCustomer.setText("Khách: " + order.getCustomerName());
        holder.txtPhone.setText("SĐT: " + order.getPhoneNumber());
        holder.txtAddress.setText("Địa chỉ: " + order.getAddress());

        String formattedDate = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                .format(new Date(order.getTimestamp()));
        holder.txtTimestamp.setText("Thời gian: " + formattedDate);
    }


    @Override
    public int getItemCount() {
        return orderList != null ? orderList.size() : 0;
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView txtProductName, txtQuantity, txtPrice, txtTimestamp, txtCustomer, txtPhone, txtAddress;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            txtProductName = itemView.findViewById(R.id.tv_product_info);
            txtQuantity = itemView.findViewById(R.id.tv_quantity);
            txtPrice = itemView.findViewById(R.id.tv_price);
            txtTimestamp = itemView.findViewById(R.id.tv_date);
            txtCustomer = itemView.findViewById(R.id.tv_customer_name);
            txtPhone = itemView.findViewById(R.id.tv_phone);
            txtAddress = itemView.findViewById(R.id.tv_address);
        }
    }
}
