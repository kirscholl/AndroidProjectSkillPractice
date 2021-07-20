package com.example.hurricaneapp.MaterialDesignPractice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.example.hurricaneapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MaterialDesignPracticeActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private Fruit[] fruits = {
            new Fruit("Apple",R.drawable.apple),
            new Fruit("Banana",R.drawable.banana),
            new Fruit("Pear",R.drawable.pear),
            new Fruit("Pineapple",R.drawable.pineapple),
            new Fruit("Grape",R.drawable.grape),
            new Fruit("Strawberry",R.drawable.strawberry),
            new Fruit("Cherry",R.drawable.cherry),
            new Fruit("Watermelon",R.drawable.watermelon)
    };
    private List<Fruit> fruitList = new ArrayList<>();
    private FruitAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    private void initFruits(){
        fruitList.clear();
        for (int i = 0; i < 50; i++){
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
        }
    }

    private void refreshFruits(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                //开启一个线程,将线程沉睡2s以观察刷新
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //使用runOnUiThread切回主线程
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //重新生成数据
                        initFruits();
                        //通知数据发生变化
                        adapter.notifyDataSetChanged();
                        //setRefreshing(false)表示结束刷新
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //滑动菜单是通过修改activity_material_design.xml添加DrawerLayout完成的
        //在java代码中绑定在此activity
        setContentView(R.layout.activity_material_design);

        //将ActionBar设置为Toolbar
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //ActionBar Flutter中的leading按钮,通过在actionBar中设定Indicator
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        //NavigationView
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigator_view);
        navigationView.setCheckedItem(R.id.navigator_call);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

        //悬浮按钮
        FloatingActionButton floatingButton = findViewById(R.id.floating_action_button);
        floatingButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                //SnackBar
                Snackbar.make(v,"Floating Button Clicked", Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener(){

                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MaterialDesignPracticeActivity.this,
                                        "Floating Button Clicked Toast",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });

        //FruitRecycleView
        initFruits();
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);

        //由于RecyclerView和ToolBar都是在CoordinatorLayout中,CoordinatorLayout相当于FrameLayout
        //FrameLayout中没有明确指定位置,所有的View都是默认呈现在布局的左上角
        //AppBarLayout
        //AppBarLayout是一个垂直方向上的LinearLayout

        //SwipeRefreshLayout
        //添加具体的刷新逻辑
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        //设置刷新loading的颜色
        swipeRefreshLayout.setColorSchemeColors(getResources()
                .getColor(R.color.design_default_color_primary));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruits();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //将toolbar_menu绑定到ActionBar(Toolbar)上,相当于Flutter中的actions[]
        getMenuInflater().inflate(R.menu.material_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.backup:
                Intent intent = new Intent(MaterialDesignPracticeActivity.this,
                        LoginPageActivity.class);
                startLoginPageActivity(intent);
                break;
            case R.id.delete:
                Toast.makeText(this, "Clicked Delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this, "Clicked Settings", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

    private void startLoginPageActivity(Intent intent){
        startActivity(intent);
    }
}