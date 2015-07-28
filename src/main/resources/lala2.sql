    drop database if exists supercarrent;
   
    create database supercarrent;
    
    use supercarrent;
   
   	/*新添加*/
    drop table if exists t_carinfo;
    drop table if exists t_menuitem;
  	/*新添加*/
    drop table if exists t_orderdetail;
   	/*新添加*/
    drop table if exists t_orders;
    drop table if exists t_permission;
    drop table if exists t_role;
    drop table if exists t_rolemenuitemmap;
    drop table if exists t_rolepermissionmap;
   	/*新添加*/
    drop table if exists t_server;
   	/*新添加*/
    drop table if exists t_tmporders;
    drop table if exists t_user;
    drop table if exists t_userrolemap;
    
    
    
    
        create table t_carinfo (
        /*车辆信息的id*/
        CarInfoId bigint not null auto_increment,
        /*车辆品牌*/
        CarBrand varchar(255),
        /*车辆状态*/
        CarState integer,
        /*车辆类型（三厢）*/
        CarType varchar(255),
        CarAccriage bigint,
        CarCarriage varchar(255),
        carGear varchar(255),
        carInsurance bigint,
        carPrice bigint,
        carVersion varchar(255),
        /**/
        CreateTime timestamp NOT NULL default CURRENT_TIMESTAMP,
        /**/
        CreatorId bigint,
        /*NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP*/
        EditeTime datetime ,
        /**/
        EditorId bigint,
        /**/
        IsDelete bit,
        /**/
        Version bigint,
        primary key (CarInfoId)
    ) ENGINE=InnoDB;
    
    
   insert into t_carinfo (CarBrand ,CarState ,CarType ,carInsurance ,CarPrice,CreatorId,Version) values('宝马',0,'三厢',40,199,1,0);
       
    
    create table t_menuitem (
        /**/
        MenuItemId bigint not null auto_increment,
        /**/
        CreateTime datetime,
        /**/
        CreatorId bigint,
        /**/
        EditeTime datetime,
        /**/
        EditorId bigint,
        /**/
        IsDelete bit,
        /**/
        IsLockUp bit,
        /**/
        MenuItemCtrl varchar(255),
        /**/
        MenuItemIcon varchar(255),
        /**/
        MenuItemName varchar(255),
        /**/
        MenuItemOrder bigint,
        /**/
        MenuItemParentId bigint,
        /**/
        MenuItemRouteUrl varchar(255),
        /**/
        MenuItemUrl varchar(255),
        /**/
        MenuName varchar(255),
        /**/
        Version bigint,
        primary key (MenuItemId)
    ) ENGINE=InnoDB;
    
     create table t_orderdetail (
        /*订单详情id*/
        OrderDatailId bigint not null auto_increment,
        /**/
        CreateTime datetime,
        /**/
        CreatorId bigint,
        /**/
        EditeTime datetime,
        /**/
        EditorId bigint,
        /*外键id*/
        foreignId bigint,
        /*外键类型（标志外键id是carid还是ordersid）*/
        foreignIdtype varchar(255),
        /**/
        IsDelete bit,
        /*标志该订单明细是否是必须的，对于车辆来说*/
        necessary bit,
        /*服务id*/
        ServerId bigint,
        /*服务名称*/
        ServerName varchar(255),
        /*服务费用*/
        servercost integer,
        /*服务单位（天，次）*/
        servercounttype varchar(255),
        /*服务的描述*/
        serverdescribe varchar(255),
        /*服务数量*/
        ServerNum integer,
        serverType varchar(255),
        /**/
        Version bigint,
        primary key (OrderDatailId)
    ) ENGINE=InnoDB;
    
     create table t_orders (
     	/*订单id*/
        OrdersId bigint not null auto_increment,
     	/*还车时间*/
        BackCarTime datetime,
        carAccriage bigint,
     	/*车品牌*/
        CarBrand varchar(255),
        carPrice bigint,
        carGear varchar(255),
     	/*车辆id*/
        CarInfoId bigint,
        carInsurance bigint,
     	/*车辆的类型*/
        CarType varchar(255),
        carVersion varchar(255),
     	/**/
        CreateTime datetime,
     	/**/
        CreatorId bigint,
        /**/
        EditeTime datetime,
     	/**/
        EditorId bigint,
     	/*取车时间*/
        GetCarTime datetime,
     	/**/
        IsDelete bit,
     	/**/
        OrderState integer,
     	/*订单总费用*/
        TotalCost bigint,
     	/*使用时长*/
        UsedTime integer,
     	/*用户id*/
        UserId bigint,
     	/**/
        Version bigint,
        primary key (OrdersId)
    ) ENGINE=InnoDB;
    
     create table t_permission (
     	/**/
        PermissionId bigint not null auto_increment,
     	/**/
        CreateTime datetime,
     	/**/
        CreatorId bigint,
     	/**/
        EditeTime datetime,
     	/**/
        EditorId bigint,
     	/**/
        IsDelete bit,
     	/**/
        IsLockUp bit,
     	/**/
        PermissionName varchar(255),
     	/**/
        Version bigint,
        primary key (PermissionId)
    ) ENGINE=InnoDB;
    
     insert into t_permission (PermissionName)value('addPermission');
     
     /*tmpOrders权限*/
     insert into t_permission (PermissionName)value('addTmpOrders');
     insert into t_permission (PermissionName)value('deleteTmpOrders');
     insert into t_permission (PermissionName)value('editTmpOrders');
     insert into t_permission (PermissionName)value('pageTmpOrders');
     insert into t_permission (PermissionName)value('getAllTmpOrders');
     insert into t_permission (PermissionName)value('addOrders');
     insert into t_permission (PermissionName)value('getOrderDetailByTmpOId');
     /*车辆管理*/
     insert into t_permission (PermissionName)value('addCar');
     insert into t_permission (PermissionName)value('deleteCar');
     insert into t_permission (PermissionName)value('editCar');
     insert into t_permission (PermissionName)value('pageCar');
     insert into t_permission (PermissionName)value('getAllCars');
     insert into t_permission (PermissionName)value('getCarsByBrand');
     insert into t_permission (PermissionName)value('orderCarsByMoney');
     insert into t_permission (PermissionName)value('getCarsByType');
     insert into t_permission (PermissionName)value('getOrderDetailByCarId');
     insert into t_permission (PermissionName)value('getAllOrderDetailByCarId');
    
    create table t_role (
    	/**/
        RoleId bigint not null auto_increment,
     	/**/
        CreateTime datetime,
     	/**/
        CreatorId bigint,
     	/**/
        EditeTime datetime,
     	/**/
        EditorId bigint,
     	/**/
        IsDelete bit,
     	/**/
        IsLockUp bit,
     	/**/
        RoleName varchar(255),
     	/**/
        Version bigint,
        primary key (RoleId)
    ) ENGINE=InnoDB;
    
    
     insert into t_role(RoleName)value( 'lala');
     insert into t_role(RoleName)value( 'lala2');
     insert into t_role(RoleName)value( 'lala3');
     insert into t_role(RoleName)value( 'lala4');
     insert into t_role(RoleName)value( 'lala5');
     
     create table t_rolemenuitemmap (
        RoleMenuItemMapId bigint not null auto_increment,
     	/**/
        CreateTime datetime,
     	/**/
        CreatorId bigint,
     	/**/
        EditeTime datetime,
     	/**/
        EditorId bigint,
     	/**/
        IsDelete bit,
     	/**/
        IsLockUp bit,
     	/**/
        menuItemId bigint,
     	/**/
        RoleId bigint,
     	/**/
        Version bigint,
        primary key (RoleMenuItemMapId)
    ) ENGINE=InnoDB;
    
    
    
     create table t_rolepermissionmap (
     	/**/
        RolePermissionMapId bigint not null auto_increment,
     	/**/
        CreateTime datetime,
     	/**/
        CreatorId bigint,
     	/**/
        EditeTime datetime,
     	/**/
        EditorId bigint,
     	/**/
        IsDelete bit,
     	/**/
        IsLockUp bit,
     	/**/
        PermissionId bigint,
     	/**/
        RoleId bigint,
     	/**/
        Version bigint,
        primary key (RolePermissionMapId)
    ) ENGINE=InnoDB;
 /*    访客权限
    insert into t_rolepermissionmap(RoleId,PermissionId) values(1,1);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(1,2);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(1,3);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(1,4);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(1,5);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(1,6);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(1,7);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(1,8);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(1,9);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(1,10);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(1,11);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(1,12);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(1,13);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(1,14);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(1,15);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(1,16);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(1,17);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(1,18);
    */
    insert into t_rolepermissionmap(RoleId,PermissionId) values(2,1);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(2,2);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(2,3);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(2,4);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(2,5);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(2,6);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(2,7);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(2,8);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(2,9);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(2,10);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(2,11);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(2,12);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(2,13);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(2,14);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(2,15);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(2,16);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(2,17);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(2,18);
    
    insert into t_rolepermissionmap(RoleId,PermissionId) values(3,1);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(3,2);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(3,3);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(3,4);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(3,5);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(3,6);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(3,7);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(3,8);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(3,9);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(3,10);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(3,11);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(3,12);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(3,13);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(3,14);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(3,15);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(3,16);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(3,17);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(3,18);
    
    insert into t_rolepermissionmap(RoleId,PermissionId) values(4,1);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(4,2);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(4,3);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(4,4);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(4,5);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(4,6);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(4,7);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(4,8);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(4,9);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(4,10);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(4,11);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(4,12);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(4,13);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(4,14);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(4,15);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(4,16);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(4,17);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(4,18);
    
    insert into t_rolepermissionmap(RoleId,PermissionId) values(5,1);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(5,2);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(5,3);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(5,4);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(5,5);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(5,6);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(5,7);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(5,8);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(5,9);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(5,10);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(5,11);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(5,12);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(5,13);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(5,14);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(5,15);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(5,16);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(5,17);
    insert into t_rolepermissionmap(RoleId,PermissionId) values(5,18);
    
     create table t_server (
     	/*服务id*/
        ServerId bigint not null auto_increment,
     	/**/
        CreateTime datetime,
     	/**/
        CreatorId bigint,
     	/**/
        EditeTime datetime,
     	/**/
        EditorId bigint,
     	/**/
        IsDelete bit,
     	/*服务名称*/
        ServerName varchar(255),
        serverType varchar(255),
     	/*服务费用*/
        ServerCost integer,
     	/*服务描述*/
        ServerDescribe varchar(255),
     	/**/
        Version bigint,
        primary key (ServerId)
    ) ENGINE=InnoDB;
    
    insert into t_server(serverName,serverType,serverCost,serverDescribe) values(
    	'车险费用','其他类型',199,'这是一次性的增值服务，没有价值，千万不要买这个服务'
    );
    insert into t_server(serverName,serverType,serverCost,serverDescribe) values(
    	'车险费用2','其他类型',200,'这是一次性的增值服务，没有价值，千万不要买这个服务2'
    );
    insert into t_server(serverName,serverType,serverCost,serverDescribe) values(
    	'车险费用3','其他类型',201,'这是一次性的增值服务，没有价值，千万不要买这个服务3'
    );
    
    
     create table t_tmporders (
     	/*tmp订单id*/
        TmpOrdersId bigint not null auto_increment,
     	/*还车时间*/
        BackCarTime datetime,
        carAccriage bigint,
     	/*车辆品牌*/
        CarBrand varchar(255),
        CarPrice bigint,
        carGear varchar(255),
     	/*车辆id*/
        CarInfoId bigint,
        carInsurance bigint,
     	/*车辆类型*/
        CarType varchar(255),
        carVersion varchar(255),
     	/**/
        CreateTime datetime,
     	/**/
        CreatorId bigint,
     	/**/
        EditeTime datetime,
     	/**/
        EditorId bigint,
     	/*取车时间*/
        GetCarTime datetime,
     	/**/
        IsDelete bit,
     	/*总费用*/
        TotalCost bigint,
     	/*使用时长*/
        UsedTime integer,
     	/*用户id*/
        UserId bigint,
     	/**/
        Version bigint,
     	/**/
        primary key (TmpOrdersId)
    ) ENGINE=InnoDB;
    
    create table t_user (
     	/**/
        UserId bigint not null auto_increment,
        cardNumber bigint,
        cardType varchar(255),
     	/**/
        CreateTime datetime,
     	/**/
        CreatorId bigint,
     	/**/
        EditeTime datetime,
     	/**/
        EditorId bigint,
        email varchar(255),
     	/**/
        IsDelete bit,
     	/**/
        IsLockUp bit,
     	/**/
        LoginName varchar(255),
     	/**/
        Password varchar(255),
        realName varchar(255),
     	/**/
        Version bigint,
     	/**/
        primary key (UserId)
    ) ENGINE=InnoDB;
    
    insert into t_user(LoginName,Password)value
    ('user1','AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA');
    insert into t_user(LoginName,Password)value
    ('易临风','123456');
    
    
     create table t_userrolemap (
     	/**/
        UserRoleMapId bigint not null auto_increment,
     	/**/
        CreateTime datetime,
     	/**/
        CreatorId bigint,
     	/**/
        EditeTime datetime,
     	/**/
        EditorId bigint,
     	/**/
        IsDelete bit,
     	/**/
        IsLockUp bit,
     	/**/
        RoleId bigint,
     	/**/
        UserId bigint,
     	/**/
        Version bigint,
     	/**/
        primary key (UserRoleMapId)
    ) ENGINE=InnoDB;

  	insert into t_userrolemap(UserId,RoleId) values(1,1);
    insert into t_userrolemap(UserId,RoleId) values(1,2);
    insert into t_userrolemap(UserId,RoleId) values(1,3);
    insert into t_userrolemap(UserId,RoleId) values(1,4);
    insert into t_userrolemap(UserId,RoleId) values(1,5);
    
    insert into t_userrolemap(UserId,RoleId) values(2,1);
    insert into t_userrolemap(UserId,RoleId) values(2,2);
    insert into t_userrolemap(UserId,RoleId) values(2,3);
    insert into t_userrolemap(UserId,RoleId) values(2,4);
    insert into t_userrolemap(UserId,RoleId) values(2,5);

     
    select * from t_carinfo;
   	select * from t_menuitem;
    select * from t_orderdetail;
    select * from t_orders;
    select * from t_permission;
    select * from t_role;
    select * from t_rolemenuitemmap;
    select * from t_rolepermissionmap;
    select * from t_server;
    select * from t_tmporders;
    select * from t_user;
    select * from t_userrolemap;
    

    