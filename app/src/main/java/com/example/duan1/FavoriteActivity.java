package com.example.duan1;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavoriteActivity extends AppCompatActivity {

    private RecyclerView rvFavorite;
    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        setTitle("Món ăn yêu thích");

        rvFavorite = findViewById(R.id.rvFavorite);
        rvFavorite.setLayoutManager(new LinearLayoutManager(this));

        loadFavorites();

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                finish();
            }
        });
    }

    private void loadFavorites() {
        List<Product> favorites = FavoriteManager.getFavorites();
        if (favorites.isEmpty()) {
            Toast.makeText(this, "Chưa có món yêu thích nào", Toast.LENGTH_SHORT).show();
        }

        adapter = new ProductAdapter(this, favorites, true);
        rvFavorite.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadFavorites();
    }
}
