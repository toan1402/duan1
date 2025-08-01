package com.example.duan1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private List<Product> productList;
    private Context context;
    private boolean isFavoriteMode;

    public ProductAdapter(Context context, List<Product> products, boolean isFavoriteMode) {
        this.context = context;
        this.productList = products;
        this.isFavoriteMode = isFavoriteMode;
    }

    public void setData(List<Product> newList) {
        productList = newList;
        notifyDataSetChanged();
    }

    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductAdapter.ViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.txtName.setText(product.getName());
        holder.img.setImageResource(product.getImageResId());
        holder.txtGia.setText("Giá: " + product.getPrice() + "đ");

        // Hiển thị icon yêu thích
        if (FavoriteManager.isFavorite(product)) {
            holder.btnFavorite.setImageResource(R.drawable.ic_heart_filled);
        } else {
            holder.btnFavorite.setImageResource(R.drawable.ic_heart_outline);
        }

        // Xử lý nút yêu thích
        holder.btnFavorite.setOnClickListener(v -> {
            boolean isFav = FavoriteManager.isFavorite(product);
            if (isFav) {
                FavoriteManager.remove(product);
                holder.btnFavorite.setImageResource(R.drawable.ic_heart_outline);
                if (isFavoriteMode) {
                    int pos = holder.getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION && pos < productList.size()) {
                        productList.remove(pos);
                        notifyItemRemoved(pos);
                    }
                }
            } else {
                FavoriteManager.add(product);
                holder.btnFavorite.setImageResource(R.drawable.ic_heart_filled);
            }
        });

        // Chi tiết sản phẩm
        holder.img.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductDetailActivity.class);
            intent.putExtra("name", product.getName());
            intent.putExtra("category", product.getCategory());
            intent.putExtra("price", product.getPrice());
            intent.putExtra("image", product.getImageResId());
            context.startActivity(intent);
        });

        // Mua ngay
        holder.btnBuyNow.setOnClickListener(v -> {
            Intent intent = new Intent(context, CartActivity.class);
            context.startActivity(intent);
        });

        // Xóa trong chế độ yêu thích
        holder.btnDelete.setVisibility(isFavoriteMode ? View.VISIBLE : View.GONE);
        holder.btnDelete.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();
            if (pos != RecyclerView.NO_POSITION && pos < productList.size()) {
                Product p = productList.get(pos);
                FavoriteManager.remove(p);
                productList.remove(pos);
                notifyItemRemoved(pos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtGia;
        ImageView img;
        Button btnBuyNow, btnDelete;
        ImageButton btnFavorite;

        public ViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtProductName);
            txtGia = itemView.findViewById(R.id.txtGia);
            img = itemView.findViewById(R.id.imgProduct);
            btnBuyNow = itemView.findViewById(R.id.btnBuyNow);
            btnFavorite = itemView.findViewById(R.id.btnFavorite);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
