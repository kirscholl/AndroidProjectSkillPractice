import 'package:flutter/material.dart';
import 'package:flutter_module/FlutterPage/login_page.dart';

void main(){
  runApp(KirApp());
}

class KirApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Kirsch',
      theme: ThemeData(primarySwatch: Colors.blue, splashColor: Colors.transparent, highlightColor: Colors.transparent),
      home: KirLoginPage(),
      routes: {
        "/FlutterLoginPage": (context) => KirLoginPage(),
        "/HomeFlutterPage": (context) => MyHomePage(),
      },
    );
  }
}

class MyHomePage extends StatefulWidget {
  final String title = "homePge";

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  int _counter = 0;

  void _incrementCounter() {
    setState(() {
      _counter++;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(
              'You have pushed the button this many times:',
            ),
            Text(
              '$_counter',
              style: Theme.of(context).textTheme.headline4,
            ),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _incrementCounter,
        tooltip: 'Increment',
        child: Icon(Icons.add),
      ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }
}