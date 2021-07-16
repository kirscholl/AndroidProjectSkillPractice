package com.example.hurricaneapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import io.flutter.embedding.android.FlutterActivity;


public class NativeSwitchFlutterTestActivity extends AppCompatActivity {
    FlutterEngineHelper flutterEngineHelper = new FlutterEngineHelper();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.native_switch_flutter_activity_layout);

        Button nativeFlutterSwitch = findViewById(R.id.nativeFlutterSwitchButton);

//        String flutterRoute = "/HomeFlutterPage";
        String flutterRoute = "FlutterLoginPage";

        flutterEngineHelper.initFlutterEngine(NativeSwitchFlutterTestActivity.this, flutterRoute);

        nativeFlutterSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //跳转到Flutter中的main入口的方法中 两种写法并无不同
                //1.
//                startActivity(new Intent(NativeFlutterSwitchTestActivity.this,
//                        FlutterActivity.class));
                //2.
//                startActivity(FlutterActivity.createDefaultIntent(
//                        NativeFlutterSwitchTestActivity.this));

                //

                //创建新的flutterEngine并跳转到指定路由的Flutter页面
                //todo 打开Flutter页面时,白屏,在主线程中执行了太多任务
//                startActivity(FlutterActivity
//                                        .withNewEngine()
//                                        .initialRoute("/HomeFlutterPage")
//                                        .build(NativeFlutterSwitchTestActivity.this));


                //todo flutter引擎的学习 http://gityuan.com/2019/06/22/flutter_booting/
                startActivity(FlutterActivity.
                        withCachedEngine("engine_id").
                        build(NativeSwitchFlutterTestActivity.this));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        flutterEngineHelper.destroyEngine();
    }
}


//todo 使用 FlutterBoost 尝试跳转 https://blog.csdn.net/u013894711/article/details/103482120


