# TheBase-MVVM

### 别问，问就是抄我鸡哥的代码 - [JetpackMvvm](https://gitee.com/hegaojian/JetpackMvvm)

### base

以[QMUI](https://github.com/Tencent/QMUI_Android)+Jetpack组件封装的一个MVVM基础框架。

主要提供了[BaseQMUIFragment](https://gitee.com/theoneee/the-base-mvvm/blob/master/base/src/main/java/com/theone/mvvm/base/fragment/BaseQMUIFragment.kt)、[BaseVmFragment](https://gitee.com/theoneee/the-base-mvvm/blob/master/base/src/main/java/com/theone/mvvm/base/fragment/BaseVmFragment.kt)和[BaseVmDbFragment](https://gitee.com/theoneee/the-base-mvvm/blob/master/base/src/main/java/com/theone/mvvm/base/fragment/BaseVmDbFragment.kt)三个基类和QMUI一些组件的扩展封装。


### core 

##### 在base的基础上再一次进行封装。
1.添加默认的状态管理器。

2.对[列表](https://gitee.com/theoneee/the-base-mvvm/blob/master/core/src/main/java/com/theone/mvvm/core/base/fragment/BasePagerRecyclerViewFragment.kt)、[Tab](https://gitee.com/theoneee/the-base-mvvm/blob/master/core/src/main/java/com/theone/mvvm/core/base/fragment/BaseTabFragment.kt)类型Fragment进行多级封装。

3.引入[RxHttp](https://github.com/liujingxing/okhttp-RxHttp)网络请求框架,对网络请求进行一些默认封装。

4.[BaseRequestViewModel](https://gitee.com/theoneee/the-base-mvvm/blob/master/core/src/main/java/com/theone/mvvm/core/base/viewmodel/BaseRequestViewModel.kt)和[BaseListViewModel](https://gitee.com/theoneee/the-base-mvvm/blob/master/core/src/main/java/com/theone/mvvm/core/base/viewmodel/BaseListViewModel.kt)可以更方便的进行网络请求的处理。

### common

##### 一些公用的工具和组件.


### app 

以[core](https://gitee.com/theoneee/the-base-mvvm/tree/master/core/src/main/java/com/theone/mvvm/core)为基础，创建的一个 [玩Android](http://wanandroid.com/) App。

[点击下载](https://gitee.com/theoneee/the-base-mvvm/raw/master/apks/demo_release.apk)

![首页](https://images.gitee.com/uploads/images/2021/0331/102531_dd1b7778_2286054.png "S10331-10005279(1).png")

![项目](https://images.gitee.com/uploads/images/2021/0331/102553_02e4fe39_2286054.png "S10331-10010060(1).png")

![广场](https://images.gitee.com/uploads/images/2021/0331/102613_f127785c_2286054.png "S10331-10010786(1).png")

![体系](https://images.gitee.com/uploads/images/2021/0331/102637_bc67da88_2286054.png "S10331-10011338(1).png")

![公众号](https://images.gitee.com/uploads/images/2021/0331/102652_f1253db5_2286054.png "S10331-10011856(1).png")

![我的](https://images.gitee.com/uploads/images/2021/0331/102714_bd4b64e2_2286054.png "S10331-10012315(1).png")

![积分排行](https://images.gitee.com/uploads/images/2021/0331/102733_4430f4b7_2286054.png "S10331-10013191(1).png")

![积分记录](https://images.gitee.com/uploads/images/2021/0331/102748_1e0b447a_2286054.png "S10331-10013685(1).png")

![搜索](https://images.gitee.com/uploads/images/2021/0331/102759_3025f5e8_2286054.png "S10331-10015798(1).png")
