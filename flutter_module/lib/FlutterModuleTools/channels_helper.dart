import 'package:flutter/services.dart';

//设定唯一标识
const MethodChannel _methodChannel = MethodChannel('plugins.flutter.io/goToNative_method');

class SkipPluginUtil {
  static void skipPage() {
    //设定方法名
    _methodChannel.invokeMethod("goNativePage");
  }
}

class PluginManager {
  static const MethodChannel _channel = const MethodChannel('plugin_demo');

  static Future<String> pushFirstActivity(Map params) async {
    String resultStr = await _channel.invokeMethod('jumpToActivity', params);
    return resultStr;
  }
}