我暂时在shiroIntercepter中修改了权限认证系统，现在暂时是什么角色都能有超级权限



1.通过对象的非空属性来生成查询条件   SuperDaoProxy 中的queryList方法

2.查看指定用户所有订单
3.查看指定订单的订单明细
4.获得空闲状态的车辆
5.通过车辆id获得对应的车辆服务       orderdetail中存在车辆id，方便查找

6.将选中的订单明细发送给后台，并将外键设置成订单

6.通过指定条件搜索车辆

7.后台有给车辆添加服务，就是给车辆添加服务套餐，订单明细

8.用户登录系统
9.


修改订单明细表，修改结构为， 编号，编号类型



1，car			该订单明细是属于车的，属于1号车的


2,orders		该订单明细是属于订单的，2号订单
