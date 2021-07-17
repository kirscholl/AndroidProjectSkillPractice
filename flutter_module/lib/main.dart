import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_module/FlutterPage/login_page.dart';

import 'FlutterPage/flutter_switch_native_test.dart';

void main(){
  // runApp(HurricaneApp());
  runApp(_getMaterialAppForRoute("/BatteryPage"));
}


Widget _getMaterialAppForRoute(String route){
  return MaterialApp(
    home: Scaffold(
      body: _getRouteWidget(route),
    ),
  );
}

Widget _getRouteWidget(String route){
  switch (route){
    case "/FlutterLoginPage":
      return HurricaneApp();
    case "/BatteryPage":
      return BatteryPage();
    default:
      return Center(
        child: Text('Unknown route: $route',
          // textDirection: TextDecoration.lineThrough,
        ),
      );
  }
}

class HurricaneApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Kirsch',
      theme: ThemeData(primarySwatch: Colors.blue, splashColor: Colors.transparent, highlightColor: Colors.transparent),
      home: HurricaneLoginPage(),
      routes: {
        "/FlutterLoginPage": (context) => HurricaneLoginPage(),
        "/BatteryPage": (context) => BatteryPage(),
      },
    );
  }
}