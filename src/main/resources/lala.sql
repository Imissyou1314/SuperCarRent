   drop database if exists superw ;
   create database superw;
   use superw;
   
   
    drop table if exists t_menuitem;
    drop table if exists t_permission;
    drop table if exists t_role;
    drop table if exists t_rolemenuitemmap;
    drop table if exists t_rolepermissionmap;
    drop table if exists t_user;
    drop table if exists t_userrolemap;
    drop table if exists t_carinfo;
    
    create table t_menuitem (
        MenuItemId bigint not null auto_increment,
        CreateTime datetime,
        CreatorId bigint,
        EditeTime datetime,
        EditorId bigint,
        IsDelete bit,
        IsLockUp bit,
        MenuItemCtrl varchar(255),
        MenuItemIcon varchar(255),
        MenuItemName varchar(255),
        MenuItemOrder bigint,
        MenuItemParentId bigint,
        MenuItemRouteUrl varchar(255),
        MenuItemUrl varchar(255),
        MenuName varchar(255),
        Version bigint,
        primary key (MenuItemId)
    ) ENGINE=InnoDB;
        create table t_permission (
        PermissionId bigint not null auto_increment,
        PermissionName varchar(255),
        CreateTime datetime,
        CreatorId bigint,
        EditeTime datetime,
        EditorId bigint,
        IsDelete bit,
        IsLockUp bit,
        Version bigint,
        primary key (PermissionId)
    ) ENGINE=InnoDB;
    insert into t_permission (
        PermissionName
    )value(
    'addPermission'
    );
        create table t_role (
        RoleId bigint not null auto_increment,
        CreateTime datetime,
        CreatorId bigint,
        EditeTime datetime,
        EditorId bigint,
        IsDelete bit,
        IsLockUp bit,
        RoleName varchar(255),
        Version bigint,
        primary key (RoleId)
    ) ENGINE=InnoDB;;
    
    insert into t_role(RoleName)value( 'lala');
        create table t_rolemenuitemmap (
        RoleMenuItemMapId bigint not null auto_increment,
        CreateTime datetime ,
        CreatorId bigint,
        EditeTime datetime ,
        EditorId bigint,
        IsDelete bit,
        IsLockUp bit,
        menuItemId bigint,
        RoleId bigint,
        Version bigint,
        primary key (RoleMenuItemMapId)
    ) ENGINE=InnoDB;
        create table t_rolepermissionmap (
        RolePermissionMapId bigint not null auto_increment,
        CreateTime datetime ,
        CreatorId bigint,
        EditeTime datetime ,
        EditorId bigint,
        IsDelete bit,
        IsLockUp bit,
        PermissionId bigint,
        RoleId bigint,
        Version bigint,
        primary key (RolePermissionMapId)
    ) ENGINE=InnoDB;
    
    insert into t_rolepermissionmap(roleid,permissionid)value(1,1);
    
        create table t_user (
        UserId bigint not null auto_increment,
        LoginName varchar(255),
        Password varchar(255),
        CreateTime datetime ,
        CreatorId bigint,
        EditeTime datetime ,
        EditorId bigint,
        IsDelete bit,
        IsLockUp bit,
        
        Version bigint,
        primary key (UserId)
    ) ENGINE=InnoDB;
    
    insert into t_user(
    	LoginName,
    	Password
    )value(
    'user1',
    'AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA'
    );
        create table t_userrolemap (
        UserRoleMapId bigint not null auto_increment,
        CreateTime datetime ,
        CreatorId bigint,
        EditeTime datetime ,
        EditorId bigint,
        IsDelete bit,
        IsLockUp bit,
        RoleId bigint,
        UserId bigint,
        Version bigint,
        primary key (UserRoleMapId)
    ) ENGINE=InnoDB;
    
    insert into t_userrolemap(roleid,userid)value(1,1);
    
    create table t_carinfo (
    CarInfoId bigint not null auto_increment, 
    CarBrand varchar(255), 
    CarInfoName varchar(255), 
    CarState integer, 
    CarType varchar(255), 
    CreateTime datetime, 
    CreatorId bigint, 
    EditeTime datetime, 
    EditorId bigint, 
    IsDelete bit, 
    Version bigint, 
    primary key (CarInfoId)
    ) ENGINE=InnoDB;
    
    create table t_orders (
    OrdersId bigint not null auto_increment, 
    BackCarTime datetime, 
    CarBrand varchar(255), 
    CarInfoId bigint, 
    CarInfoName varchar(255), 
    CarState integer, 
    CarType varchar(255), 
    CreateTime datetime, 
    CreatorId bigint, 
    EditeTime datetime, 
    EditorId bigint, 
    GetCarTime datetime, 
    IsDelete bit, 
    OrderState integer, 
    TotalCost bigint, 
    UsedTime integer, 
    UserId bigint, 
    Version bigint, 
    primary key (OrdersId)
    ) ENGINE=InnoDB;
    
    create table t_server (
    ServerId bigint not null auto_increment, 
    CreateTime datetime, 
    CreatorId bigint, 
    EditeTime datetime, 
    EditorId bigint, 
    IsDelete bit, 
    ServerName varchar(255), 
    Servercost integer, 
    serverdescribe varchar(255), 
    Version bigint, primary key (ServerId)
    ) ENGINE=InnoDB;
    
    create table t_orderdetail (
    OrderDatailId bigint not null auto_increment, 
    CreateTime datetime, 
    CreatorId bigint, 
    EditeTime datetime, 
    EditorId bigint, 
    IsDelete bit, 
    necessary bit, 
    orderId bigint, 
    ServerId bigint, 
    ServerName varchar(255), 
    servercost integer, 
    servercounttype varchar(255), 
    serverdescribe varchar(255), 
    servernum integer, Version bigint, 
    primary key (OrderDatailId)
    ) ENGINE=InnoDB;
    
    
    