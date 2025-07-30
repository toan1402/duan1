package com.example.duan1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private Context context;
    private List<Food> foodList;

    public FoodAdapter(Context context, List<Food> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_food, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Food food = foodList.get(position);

        holder.tvName.setText(food.getName());
        holder.tvPrice.setText(food.getPrice());

        // Kiểm tra URL ảnh và load bằng Glide
        if (food.getImageUrl() != null && !food.getImageUrl().isEmpty()) {
            Glide.with(context)
                    .load(food.getImageUrl())
                    .placeholder(R.drawable.ic_launcher_background) // ảnh mặc định khi đang tải
                    .error(R.drawable.ic_launcher_foreground) // ảnh khi lỗi tải ảnh
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.imgFood);
        } else {
            // Nếu không có URL, dùng ảnh mặc định
            holder.imgFood.setImageResource(R.drawable.comsuon);
        }
    }

    @Override
    public int getItemCount() {
        return foodList != null ? foodList.size() : 0;
    }

    public static class FoodViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFood;
        TextView tvName, tvPrice;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFood = itemView.findViewById(R.id.imgFood);
            tvName = itemView.findViewById(R.id.tvFoodName);
            tvPrice = itemView.findViewById(R.id.tvFoodPrice);
        }
    }
}
