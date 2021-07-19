import 'package:flutter/cupertino.dart';
import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:flutter_module/FlutterWidget/login_tab_bar.dart';

class HurricaneLoginPage extends StatelessWidget {
  final loginTitle = "Login Hurricane";

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      behavior: HitTestBehavior.translucent,
      onTap: () {
        FocusScope.of(context).requestFocus(FocusNode());
      },
      child: Scaffold(
        body: Padding(
          padding: EdgeInsets.fromLTRB(10, 30, 10, 0),
          child: Column(crossAxisAlignment: CrossAxisAlignment.start, children: [
            _languageSelection(),
            _loginTitle(loginTitle),
            _loginWarningText(),
            HurricaneLoginTabBar(),
            _toNextButton(context),
            _registrationPrompt()
          ]),
        )
      ),
    );
  }

  Widget _languageSelection() {
    return Container(
      alignment: Alignment.topRight,
      padding: EdgeInsets.only(bottom: 25),
      child: TextButton(
          style: ButtonStyle(overlayColor: MaterialStateProperty.all(Colors.transparent)),
          child: Container(
            width: 95,
            child: Row(mainAxisAlignment: MainAxisAlignment.end, children: [
              Text(
                "简体中文",
                style: TextStyle(fontSize: 15, color: Colors.black54),
              ),
              Icon(
                Icons.arrow_drop_down,
                color: Colors.black54,
              ),
            ]),
          ),
          onPressed: () {
            //todo 实现中英文切换,弹出菜单样式应是,选择语言则勾选选择的语言,点击完成完成选择
            //todo 后续国际化所有Text
          }),
    );
  }

  Widget _loginTitle(String title) {
    return Container(
      child: Text(
        loginTitle,
        style: TextStyle(
          fontSize: 35,
          fontWeight: FontWeight.w600,
          color: Colors.black,
        ),
      ),
    );
  }

  Widget _loginWarningText() {
    return Container(
        padding: EdgeInsets.only(top: 10),
        child: Text.rich(TextSpan(children: [
          _loginWarningTextFragment("点击登陆代表你同意", Colors.black54),
          _loginWarningTextFragment("服务协议", Colors.blue),
          _loginWarningTextFragment("和", Colors.black54),
          _loginWarningTextFragment("隐私政策", Colors.blue),
        ])));
  }

  _loginWarningTextFragment(String text, Color color, [GestureRecognizer gestureRecognizer]) {
    return TextSpan(
        text: text,
        style: TextStyle(
          fontWeight: FontWeight.w400,
          fontSize: 12,
          color: color,
        ),
        recognizer: gestureRecognizer);
  }

  Widget _toNextButton(BuildContext context) {
    return Container(
      margin: EdgeInsets.only(top: 20),
      height: 40,
      width: double.infinity,
      child: FlatButton(
        splashColor: Colors.transparent,
        color: Colors.lightBlue,
        child: Text(
          "下一步",
          style: TextStyle(color: Colors.white, fontWeight: FontWeight.normal, fontSize: 17),
        ),
        onPressed: () async {
          //todo 跳转到原生主界面
        },
      ),
    );
  }

  Widget _registrationPrompt() {
    return Container(
      margin: EdgeInsets.only(top: 20),
      child: Text.rich(
        TextSpan(children: [
          TextSpan(
            text: "还没有账号?",
            style: TextStyle(fontSize: 14, color: Colors.black54),
          ),
          TextSpan(
            text: "立即注册",
            style: TextStyle(fontSize: 14, color: Colors.lightBlue),
          )
        ]),
      ),
    );
  }
}
