//package com.example.duan1;
//
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Looper;
//import android.widget.ImageButton;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import androidx.viewpager2.widget.ViewPager2;
//
//import com.example.duan1.model.Product;
//import com.example.duan1.model.ProductAdapter;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class MainActivity extends AppCompatActivity {
//
//    private ViewPager2 bannerViewPager;
//    private Handler handler = new Handler(Looper.getMainLooper());
//    private Runnable runnable;
//    private int currentPage = 0;
//    private List<Integer> bannerList;
//
//    private RecyclerView rvFoods;
//    private ProductAdapter adapter;
//    private List<Product> allProducts = new ArrayList<>();
//
//    // Các nút danh mục
//    private ImageButton btnBanhMi, btnCom, btnPho, btnTraSua, btnCafe;@Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        // Banner setup
//        bannerViewPager = findViewById(R.id.banner);
//        bannerList = Arrays.asList(
//                R.drawable.comsuon,
//                R.drawable.banhmy,
//                R.drawable.ic_launcher_background
//        );
//        BannerAdapter bannerAdapter = new BannerAdapter(this, bannerList);
//        bannerViewPager.setAdapter(bannerAdapter);
//        autoSlideBanner();
//
//        // RecyclerView setup
//        rvFoods = findViewById(R.id.rvFoods);
//        rvFoods.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new ProductAdapter(this, new ArrayList<>()); // ✅ Thêm context
//        rvFoods.setAdapter(adapter);
//
//        // Ánh xạ ImageButton
//        btnBanhMi = findViewById(R.id.btnBanhMi);
//        btnCom = findViewById(R.id.btnCom);
//        btnPho = findViewById(R.id.btnPho);
//        btnTraSua = findViewById(R.id.btnTraSua);
//        btnCafe = findViewById(R.id.btnCafe);
//
//        // ✅ Gọi hàm Data() thay vì loadSampleData()
//        Data();
//
//        // Sự kiện click danh mục
//        btnBanhMi.setOnClickListener(v -> filterByCategory("Bánh mì"));
//        btnCom.setOnClickListener(v -> filterByCategory("Cơm"));
//        btnPho.setOnClickListener(v -> filterByCategory("Phở"));
//        btnTraSua.setOnClickListener(v -> filterByCategory("Trà sữa"));
//        btnCafe.setOnClickListener(v -> filterByCategory("Cà phê"));
//
//        // Hiển thị tất cả món
//        adapter.setData(allProducts);
//    }
//
//    private void autoSlideBanner() {
//        runnable = () -> {
//            if (currentPage == bannerList.size()) currentPage = 0;
//            bannerViewPager.setCurrentItem(currentPage++, true);
//            handler.postDelayed(runnable, 5000);
//        };
//        handler.postDelayed(runnable, 5000);
//    }
//
//
//    private void Data() {allProducts.add(new Product("Bánh mì bò nướng phô mai", "Bánh mì", R.drawable.banhmy1, 35000));
//        allProducts.add(new Product("Bánh mì trứng xúc xích", "Bánh mì", R.drawable.banhmy2, 30000));
//        allProducts.add(new Product("Bánh mì pate chả lụa", "Bánh mì", R.drawable.banhmy3, 32000));
//        allProducts.add(new Product("Bánh mì đặc sản Hội An", "Bánh mì", R.drawable.banhmy, 40000));
//        allProducts.add(new Product("Bánh mì thịt quay giòn bì", "Bánh mì", R.drawable.banhmy, 38000));
//
//        allProducts.add(new Product("Cơm gà sốt cay Hàn Quốc", "Cơm", R.drawable.com, 45000));
//        allProducts.add(new Product("Cơm gà hấp hành", "Cơm", R.drawable.com1, 42000));
//        allProducts.add(new Product("Cơm gà xé sợi lá chanh", "Cơm", R.drawable.com2, 39000));
//        allProducts.add(new Product("Cơm gà rô ti mật ong", "Cơm", R.drawable.com3, 46000));
//
//        allProducts.add(new Product("Phở bò tái nạm", "Phở", R.drawable.pho, 50000));
//        allProducts.add(new Product("Phở bò viên sa tế", "Phở", R.drawable.pho1, 48000));
//        allProducts.add(new Product("Phở gầu gân nước trong", "Phở", R.drawable.pho2, 55000));
//        allProducts.add(new Product("Phở thập cẩm đặc biệt", "Phở", R.drawable.pho3, 60000));
//
//        allProducts.add(new Product("Trà sữa vị truyền thống", "Trà sữa", R.drawable.trasua, 35000));
//        allProducts.add(new Product("Trà sữa trân châu đường đen", "Trà sữa", R.drawable.trasua1, 39000));
//        allProducts.add(new Product("Trà sữa thái matcha", "Trà sữa", R.drawable.trasua2, 37000));
//        allProducts.add(new Product("Trà sữa Oolong kem sữa", "Trà sữa", R.drawable.trasua3, 42000));
//
//        allProducts.add(new Product("Cà phê sữa vị truyền thống", "Cà phê", R.drawable.cafe, 30000));
//        allProducts.add(new Product("Cà phê đen đá nguyên chất", "Cà phê", R.drawable.cafe1, 28000));
//        allProducts.add(new Product("Bạc xỉu đá kiểu Sài Gòn", "Cà phê", R.drawable.cafe2, 32000));
//        allProducts.add(new Product("Cà phê trứng Hà Nội", "Cà phê", R.drawable.cafe3, 35000));
//
//    }
//
//    private void filterByCategory(String category) {
//        List<Product> filtered = new ArrayList<>();
//        for (Product p : allProducts) {
//            if (p.getCategory().equals(category)) {
//                filtered.add(p);
//            }
//        }
//        adapter.setData(filtered);
//    }
//
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        handler.removeCallbacks(runnable);
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        handler.postDelayed(runnable, 5000);
//    }
//}

package com.example.duan1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.duan1.Product;
import com.example.duan1.ProductAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 bannerViewPager;
    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable runnable;
    private int currentPage = 0;
    private List<Integer> bannerList;

    private RecyclerView rvFoods;
    private ProductAdapter adapter;
    private List<Product> allProducts = new ArrayList<>();

    // Các nút danh mục
    private ImageButton btnBanhMi, btnCom, btnPho, btnTraSua, btnCafe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Banner setup
        bannerViewPager = findViewById(R.id.banner);
        bannerList = Arrays.asList(
                R.drawable.cafe1,
                R.drawable.trasua1,
                R.drawable.banhmy3,
                R.drawable.pho1
        );
        BannerAdapter bannerAdapter = new BannerAdapter(this, bannerList);
        bannerViewPager.setAdapter(bannerAdapter);
        autoSlideBanner();

        // RecyclerView setup
        rvFoods = findViewById(R.id.rvFoods);
        rvFoods.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProductAdapter(this, new ArrayList<>(), false);
        rvFoods.setAdapter(adapter);

        // Ánh xạ ImageButton
        btnBanhMi = findViewById(R.id.btnBanhMi);
        btnCom = findViewById(R.id.btnCom);
        btnPho = findViewById(R.id.btnPho);
        btnTraSua = findViewById(R.id.btnTraSua);
        btnCafe = findViewById(R.id.btnCafe);

        // Load dữ liệu
        Data();

        // Gán toàn bộ sản phẩm cho Adapter
        adapter.setData(allProducts);

        // Bắt sự kiện lọc theo danh mục
        btnBanhMi.setOnClickListener(v -> filterByCategory("Bánh mì"));
        btnCom.setOnClickListener(v -> filterByCategory("Cơm"));
        btnPho.setOnClickListener(v -> filterByCategory("Phở"));
        btnTraSua.setOnClickListener(v -> filterByCategory("Trà sữa"));
        btnCafe.setOnClickListener(v -> filterByCategory("Cà phê"));

        // 👉 Bottom Navigation xử lý chuyển màn bằng if-else
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home) {
                // Đã ở Home
                return true;
            } else if (itemId == R.id.nav_favorite) {
                startActivity(new Intent(MainActivity.this, FavoriteActivity.class));
                return true;
            } else if (itemId == R.id.nav_notification) {
                startActivity(new Intent(MainActivity.this, NotificationActivity.class));
                return true;
            } else if (itemId == R.id.donhang) {
                startActivity(new Intent(MainActivity.this, CartActivity.class));
                return true;
            } else if (itemId == R.id.nav_account) {
                startActivity(new Intent(MainActivity.this, AccountActivity.class));
                return true;
            }

            return false;
        });
    }

    private void autoSlideBanner() {
        runnable = () -> {
            if (currentPage == bannerList.size()) currentPage = 0;
            bannerViewPager.setCurrentItem(currentPage++, true);
            handler.postDelayed(runnable, 5000);
        };
        handler.postDelayed(runnable, 5000);
    }

    private void Data() {
        allProducts.add(new Product("Bánh mì bò nướng phô mai", "Bánh mì", R.drawable.banhmy1, 35000));
        allProducts.add(new Product("Bánh mì trứng xúc xích", "Bánh mì", R.drawable.banhmy2, 30000));
        allProducts.add(new Product("Bánh mì pate chả lụa", "Bánh mì", R.drawable.banhmy3, 32000));
        allProducts.add(new Product("Bánh mì đặc sản Hội An", "Bánh mì", R.drawable.banhmy, 40000));
        allProducts.add(new Product("Bánh mì thịt quay giòn bì", "Bánh mì", R.drawable.banhmy, 38000));

        allProducts.add(new Product("Cơm gà sốt cay Hàn Quốc", "Cơm", R.drawable.com, 45000));
        allProducts.add(new Product("Cơm gà hấp hành", "Cơm", R.drawable.com1, 42000));
        allProducts.add(new Product("Cơm gà xé sợi lá chanh", "Cơm", R.drawable.com2, 39000));
        allProducts.add(new Product("Cơm gà rô ti mật ong", "Cơm", R.drawable.com3, 46000));

        allProducts.add(new Product("Phở bò tái nạm", "Phở", R.drawable.pho, 50000));
        allProducts.add(new Product("Phở bò viên sa tế", "Phở", R.drawable.pho1, 48000));
        allProducts.add(new Product("Phở gầu gân nước trong", "Phở", R.drawable.pho2, 55000));
        allProducts.add(new Product("Phở thập cẩm đặc biệt", "Phở", R.drawable.pho3, 60000));

        allProducts.add(new Product("Trà sữa vị truyền thống", "Trà sữa", R.drawable.trasua, 35000));
        allProducts.add(new Product("Trà sữa trân châu đường đen", "Trà sữa", R.drawable.trasua1, 39000));
        allProducts.add(new Product("Trà sữa thái matcha", "Trà sữa", R.drawable.trasua2, 37000));
        allProducts.add(new Product("Trà sữa Oolong kem sữa", "Trà sữa", R.drawable.trasua3, 42000));

        allProducts.add(new Product("Cà phê sữa vị truyền thống", "Cà phê", R.drawable.cafe, 30000));
        allProducts.add(new Product("Cà phê đen đá nguyên chất", "Cà phê", R.drawable.cafe1, 28000));
        allProducts.add(new Product("Bạc xỉu đá kiểu Sài Gòn", "Cà phê", R.drawable.cafe2, 32000));
        allProducts.add(new Product("Cà phê trứng Hà Nội", "Cà phê", R.drawable.cafe3, 35000));
    }

    private void filterByCategory(String category) {
        List<Product> filtered = new ArrayList<>();
        for (Product p : allProducts) {
            if (p.getCategory().equals(category)) {
                filtered.add(p);
            }
        }
        adapter.setData(filtered);
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
