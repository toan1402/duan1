package com.example.duan1;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 bannerViewPager;
    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable runnable;
    private int currentPage = 0;
    private List<Integer> bannerList;

    private RecyclerView rvFoods;
    private FoodAdapter foodAdapter;
    private List<Food> foodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup banner
        bannerViewPager = findViewById(R.id.banner);
        bannerList = Arrays.asList(
                R.drawable.comsuon,
                R.drawable.banhmy,
                R.drawable.ic_launcher_background
        );
        BannerAdapter adapter = new BannerAdapter(this, bannerList);
        bannerViewPager.setAdapter(adapter);
        autoSlideBanner();

        // Setup RecyclerView
        rvFoods = findViewById(R.id.rvFoods);
        rvFoods.setLayoutManager(new LinearLayoutManager(this));

        // Dummy data
        foodList = Arrays.asList(
                new Food("Cơm sườn", "35.000đ", "https://cdn.tgdd.vn/2020/10/CookProduct/5-cach-lam-com-suon-nuong-thom-ngon-dam-da-hap-dan-202205111152426991.jpg"),
                new Food("Bánh mì trứng", "15.000đ", "https://cdn.tgdd.vn/Files/2021/08/13/1376210/lam-banh-mi-op-la-thom-ngon-gion-rum-don-gian-tai-nha-202108131720515657.jpg"),
                new Food("Trà đào", "20.000đ", "https://cdn.tgdd.vn/Files/2021/08/24/1378783/cach-lam-tra-dao-thom-ngon-mat-lanh-don-gian-tai-nha-202108241027126660.jpg")
        );

        foodAdapter = new FoodAdapter(this, foodList);
        rvFoods.setAdapter(foodAdapter);
    }

    private void autoSlideBanner() {
        runnable = () -> {
            if (currentPage == bannerList.size()) currentPage = 0;
            bannerViewPager.setCurrentItem(currentPage++, true);
            handler.postDelayed(runnable, 5000);
        };
        handler.postDelayed(runnable, 5000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 5000);
    }
}
