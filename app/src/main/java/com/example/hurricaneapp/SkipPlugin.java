package com.example.hurricaneapp;

import android.content.Intent;

import androidx.annotation.NonNull;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugins.GeneratedPluginRegistrant;

//public class SkipPlugin implements MethodChannel.MethodCallHandler {
//
//    private Activity activity;
//    //取到唯一标识
//    private static final String CHANNEL_METHOD = "plugins.flutter.io/goToNative_method";
//    private SkipPlugin(Activity context) {
//        activity = context;
//    }
//
//    static void registerWith(PluginRegistry.Registrar registrar) {
//        MethodChannel channel = new MethodChannel(registrar.messenger(), CHANNEL_METHOD);
//        SkipPlugin skipPlugin = new SkipPlugin(registrar.activity());
//        channel.setMethodCallHandler(skipPlugin);
//    }
//
//    @Override
//    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
//        try {
//            switch (methodCall.method) {
//                //找到对应方法
//                case "goNativePage": {
//                    activity.startActivity(new Intent(activity, FlutterSwitchNativeTestActivity.class));
//                    break;
//                }
//            }
//        } catch (Exception e) {
//            result.error("IOException encountered", methodCall.method, e);
//        }
//    }
//}


class MainActivity extends FlutterActivity {
    private static final String CHANNEL = "plugin_demo";

    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        GeneratedPluginRegistrant.registerWith(flutterEngine);
        new MethodChannel(flutterEngine.getDartExecutor(), CHANNEL).setMethodCallHandler(
                new MethodChannel.MethodCallHandler() {
                    @Override
                    public void onMethodCall(MethodCall call, MethodChannel.Result result) {
                        if (call.method.equals("jumpToActivity")) {
                            // 参数
                            String params = call.argument("key");

                            // 跳转
                            Intent intent = new Intent(MainActivity.this, FlutterSwitchNativeTestActivity.class);
                            intent.putExtra("activityKey", params);
                            startActivity(intent);

                            result.success(params);
                        } else {
                            result.notImplemented();
                        }
                    }
                });
    }
}



