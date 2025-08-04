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
        holder.bind(product, isFavoriteMode);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
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

        public void bind(Product product, boolean isFavoriteMode) {
            txtName.setText(product.getName());
            txtGia.setText("Giá: " + product.getPrice() + "đ");
            img.setImageResource(product.getImageResId());

            // Hiển thị trạng thái yêu thích
            btnFavorite.setImageResource(FavoriteManager.isFavorite(product)
                    ? R.drawable.ic_heart_filled
                    : R.drawable.ic_heart_outline);

            // Xử lý yêu thích
            btnFavorite.setOnClickListener(v -> {
                boolean isFav = FavoriteManager.isFavorite(product);
                if (isFav) {
                    FavoriteManager.remove(product);
                    btnFavorite.setImageResource(R.drawable.ic_heart_outline);
                    if (isFavoriteMode) {
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION && pos < productList.size()) {
                            productList.remove(pos);
                            notifyItemRemoved(pos);
                        }
                    }
                } else {
                    FavoriteManager.add(product);
                    btnFavorite.setImageResource(R.drawable.ic_heart_filled);
                }
            });

            // Chi tiết sản phẩm
            img.setOnClickListener(v -> {
                Intent intent = new Intent(context, ChiTietActivity.class);
                intent.putExtra("name", product.getName());
                intent.putExtra("category", product.getCategory());
                intent.putExtra("price", product.getPrice());
                intent.putExtra("imageRes", product.getImageResId()); // key đúng là "imageRes"
                context.startActivity(intent);
            });

            // Mua ngay → thêm vào giỏ + mở giỏ
            btnBuyNow.setOnClickListener(v -> {
                CartItem cartItem = new CartItem(
                        product.getName(),
                        product.getPrice(),
                        1,
                        product.getImageResId()
                );
                CartManager.addToCart(cartItem);
                Intent intent = new Intent(context, CartActivity.class);
                context.startActivity(intent);
            });


            // Nút xóa (chỉ hiện trong danh sách yêu thích)
            btnDelete.setVisibility(isFavoriteMode ? View.VISIBLE : View.GONE);
            btnDelete.setOnClickListener(v -> {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION && pos < productList.size()) {
                    Product p = productList.get(pos);
                    FavoriteManager.remove(p);
                    productList.remove(pos);
                    notifyItemRemoved(pos);
                }
            });
        }
    }
}
