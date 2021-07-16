package com.example.hurricaneapp;

import android.content.Context;
import android.util.Log;

import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.embedding.engine.dart.DartExecutor;

//todo 实现单个Engine管理多个入口???   https://www.jianshu.com/p/0b6674c3a3ad
public class FlutterEngineHelper {
    private String TAG = "FlutterEngineHelper";
    private FlutterEngine flutterEngine;

    void initFlutterEngine(Context context, String flutterRoute){
        flutterEngine = new FlutterEngine(context);
        Log.d(TAG,"Init flutter engine successful");

        //缓存并跳转到指定路由的Flutter页面
        flutterEngine.getNavigationChannel().setInitialRoute(flutterRoute);

        //通过engine_id唯一标识来缓存
        flutterEngine.getDartExecutor().executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault());
        FlutterEngineCache
                .getInstance()
                .put("engine_id", flutterEngine);
    }

    void destroyEngine(){
        if(flutterEngine != null){
            flutterEngine.destroy();
            Log.d(TAG,"Destroy flutter engine successful");
        }
    }
}
