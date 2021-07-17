import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class BatteryPage extends StatefulWidget{
  @override
  State<StatefulWidget> createState() {
    return _BatteryPageState();
  }
}

class _BatteryPageState extends State<BatteryPage> {

  //todo 从flutter中使用channel跳转到原生,未解决报错
  //todo Unhandled Exception: MissingPluginException(No implementation found for method ... on channel ...)

  static const platform = const MethodChannel('samples.flutter.dev/battery');

// Get battery level.

  String _batteryLevel = 'Unknown battery level.';

  Future<void> _getBatteryLevel() async {
    String batteryLevel;
    try {
      final int result = await platform.invokeMethod('getBatteryLevel');
      batteryLevel = 'Battery level at $result % .';
    } on PlatformException catch (e) {
      batteryLevel = "Failed to get battery level: '${e.message}'.";
    }

    setState(() {
      _batteryLevel = batteryLevel;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Material(
      child: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: [
            ElevatedButton(
              child: Text('Get Battery Level'),
              onPressed: _getBatteryLevel,
            ),
            Text(_batteryLevel),
          ],
        ),
      ),
    );
  }
}

//todo 使用pigeon生成双端接口