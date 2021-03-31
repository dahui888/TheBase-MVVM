# TheBase-MVVM

#### base

以[QMUI](https://github.com/Tencent/QMUI_Android)+Jetpack封装的一个MVVM基础框架。

主要提供了[BaseQMUIFragment](https://gitee.com/theoneee/the-base-mvvm/blob/master/base/src/main/java/com/theone/mvvm/base/fragment/BaseQMUIFragment.kt)、[BaseVmFragment](https://gitee.com/theoneee/the-base-mvvm/blob/master/base/src/main/java/com/theone/mvvm/base/fragment/BaseVmFragment.kt)和[BaseVmDbFragment](https://gitee.com/theoneee/the-base-mvvm/blob/master/base/src/main/java/com/theone/mvvm/base/fragment/BaseVmDbFragment.kt)三个基类和QMUI一些组件的扩展封装。


### core 

在base的基础上再一次进行封装。
1.添加默认的状态管理器。
2.对[列表](https://gitee.com/theoneee/the-base-mvvm/blob/master/core/src/main/java/com/theone/mvvm/core/fragment/BasePullRefreshRcPagerFragment.kt)、[Tab](https://gitee.com/theoneee/the-base-mvvm/blob/master/core/src/main/java/com/theone/mvvm/core/fragment/BaseTabFragment.kt)类型Fragment进行多级封装。
3.引入[RxHttp](https://github.com/liujingxing/okhttp-RxHttp)网络请求框架,对网络请求进行一些默认封装。
4.[BaseRequestViewModel](https://gitee.com/theoneee/the-base-mvvm/blob/master/core/src/main/java/com/theone/mvvm/core/viewmodel/BaseRequestViewModel.kt)和[BaseListViewModel](https://gitee.com/theoneee/the-base-mvvm/blob/master/core/src/main/java/com/theone/mvvm/core/viewmodel/BaseListViewModel.kt)可以更方便的进行网络请求的处理。

#### common

一些公用的工具和Widge.


#### app 

以[core]为基础，创建的一个[玩Android](http://wanandroid.com/)APP。

[点击下载](https://gitee.com/theoneee/the-base-mvvm/raw/master/apks/demo_release.apk)