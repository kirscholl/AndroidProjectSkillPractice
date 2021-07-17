import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class LoginWay {
  const LoginWay({this.way, this.loginPrompt, this.iconData, this.loginField});
  final String way;
  final String loginPrompt;
  final IconData iconData;
  final Widget loginField;
}

List<LoginWay> loginWays = <LoginWay>[
  LoginWay(way: "手机号", loginPrompt: "请输入你的手机号", iconData: Icons.phone, loginField: _byPhoneField()),
  LoginWay(way: "邮箱", loginPrompt: "请输入你的邮箱", iconData: Icons.email, loginField: _byEmailField())
];

Widget _byEmailField() {
  FocusNode focusNode = new FocusNode();
  TextEditingController _loginEmailWayController = new TextEditingController();

  return Container(
      width: 360,
      child: TextField(
        controller: _loginEmailWayController,
        autofocus: true,
        focusNode: focusNode,
        decoration: InputDecoration(
          hintText: "请输入邮箱账号",
        ),
      ));
}

Widget _byPhoneField() {
  TextEditingController _loginPhoneWayController = new TextEditingController();
  return Container(
    child: Row(
      children: [
        Container(
          padding: EdgeInsets.only(left: 5),
          child: Row(
            children: [
              Text("+86"),
              Theme(
                  data: ThemeData(
                    splashColor: Colors.transparent,
                  ),
                  child: Container(
                    child: CupertinoButton(
                      child: Icon(
                        Icons.arrow_drop_down,
                        size: 20,
                      ),
                      onPressed: () {
                        //todo 实现上弹的国家选择菜单,###菜单上方搜索按钮,左边字典序首字母筛选列表###
                      },
                    ),
                  )),
              Container(
                  width: 280,
                  child: TextField(
                    controller: _loginPhoneWayController,
                    autofocus: true,
                    decoration: InputDecoration(
                      hintText: "请输入手机号",
                    ),
                  )),
            ],
          ),
        )
      ],
    ),
  );
}

class HurricaneLoginTabBar extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return HurricaneLoginTabBarState();
  }
}

class HurricaneLoginTabBarState extends State<HurricaneLoginTabBar> with SingleTickerProviderStateMixin {
  TabController _tabController;

  @override
  void initState() {
    super.initState();
    _tabController =
    new TabController(vsync: this, length: loginWays.length); //todo SingleTickerProviderStateMixin => vsync ???
  }

  @override
  void dispose() {
    _tabController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.only(top: 10),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [_loginTabBar(), _loginTabBarView()],
      ),
    );
  }

  Widget _loginTabBar() {
    return Theme(
      data: ThemeData(
        splashColor: Colors.transparent,
        highlightColor: Colors.transparent,
      ),
      child: TabBar(
          controller: _tabController,
          labelColor: Colors.lightBlue,
          labelStyle: TextStyle(fontSize: 15),
          isScrollable: true,
          unselectedLabelColor: Colors.black,
          indicatorColor: Colors.lightBlue,
          tabs: loginWays.map((LoginWay loginWay) {
            //todo trial list.map toList() ???
            return Tab(
              text: loginWay.way,
            );
          }).toList()),
    );
  }

  Widget _loginTabBarView() {
    return Container(
      // padding: EdgeInsets.only(top: 10),
      height: 60,
      child: TabBarView(
          controller: _tabController,
          children: loginWays.map((LoginWay loginWay) {
            return Row(
              children: [
                Container(
                  padding: EdgeInsets.only(right: 5),
                  child: Icon(
                    loginWay.iconData,
                    color: Colors.lightBlue,
                  ),
                ),
                loginWay.loginField,
              ],
            );
          }).toList()),
    );
  }
}
