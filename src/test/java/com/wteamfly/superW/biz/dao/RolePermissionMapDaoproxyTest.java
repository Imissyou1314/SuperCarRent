package com.wteamfly.superW.biz.dao;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.dbunit.dataset.DefaultDataSet;
import org.dbunit.dataset.DefaultTable;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wteamfly.superW.testhelper.TestDBHelper;
import com.wteamfly.supercarrent.biz.dao.RolePermissionMapDaoproxy;
import com.wteamfly.supercarrent.entity.po.PaginationPo;
import com.wteamfly.supercarrent.entity.po.PermissionPo;
import com.wteamfly.supercarrent.entity.po.RolePermissionMapPo;
import com.wteamfly.supercarrent.entity.po.RolePo;

/**
 * 行为权限映射DaoProxy测试类.
 * 
 * @author 殷梓淞.
 * @since 3.0.0
 */
public class RolePermissionMapDaoproxyTest {

    /**
     * 测试启动前方法.
     * 
     * @throws java.lang.Exception
     *             普通异常.
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        // 获取原数据库内容
        IDataSet backupDataset = TestDBHelper.backupDataBase();
        // 构建测试用的数据
        // 指定测试用到的表
        DefaultTable userTable = new DefaultTable("t_user", backupDataset
                .getTable("t_user").getTableMetaData().getColumns());
        // 指定测试用到的表
        DefaultTable rolePermissionMapTable = new DefaultTable(
                "t_rolepermissionmap", backupDataset
                        .getTable("t_rolepermissionmap").getTableMetaData()
                        .getColumns());

        // 写入数据
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .parse("2012-01-12 23:30:20");
        Object[] userData1 = { Long.valueOf("1"), "user1",
                "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", date, Long.valueOf("1"),
                null, null, Byte.valueOf("0"), Byte.valueOf("0"),
                Long.valueOf("0") };
        userTable.addRow(userData1);

        Object[] rolePermissionMapData1 = { Long.valueOf("1"),
                Long.valueOf("2"), Long.valueOf("20"), date, Long.valueOf("1"),
                null, null, Byte.valueOf("0"), Byte.valueOf("0"),
                Long.valueOf("0") };
        rolePermissionMapTable.addRow(rolePermissionMapData1);
        Object[] rolePermissionMapData2 = { Long.valueOf("2"),
                Long.valueOf("2"), Long.valueOf("21"), date, Long.valueOf("1"),
                null, null, Byte.valueOf("0"), Byte.valueOf("0"),
                Long.valueOf("0") };
        rolePermissionMapTable.addRow(rolePermissionMapData2);
        Object[] rolePermissionMapData3 = { Long.valueOf("3"),
                Long.valueOf("2"), Long.valueOf("22"), date, Long.valueOf("1"),
                null, null, Byte.valueOf("0"), Byte.valueOf("0"),
                Long.valueOf("0") };
        rolePermissionMapTable.addRow(rolePermissionMapData3);
        Object[] rolePermissionMapData4 = { Long.valueOf("4"),
                Long.valueOf("2"), Long.valueOf("23"), date, Long.valueOf("1"),
                null, null, Byte.valueOf("0"), Byte.valueOf("0"),
                Long.valueOf("0") };
        rolePermissionMapTable.addRow(rolePermissionMapData4);

        // testAddRolePermissionMap使用
        // Object[] rolePermissionMapData5 = { Long.valueOf("5"),
        // Long.valueOf("2"),
        // Long.valueOf("1"), date, Long.valueOf("1"), null, null,
        // Byte.valueOf("0"), Byte.valueOf("0"), Long.valueOf("0") };
        // rolePermissionMapTable.addRow(rolePermissionMapData5);
        // testDeleteRolePermissionMap使用
        Object[] rolePermissionMapData6 = { Long.valueOf("6"),
                Long.valueOf("2"), Long.valueOf("1"), date, Long.valueOf("1"),
                null, null, Byte.valueOf("0"), Byte.valueOf("0"),
                Long.valueOf("0") };
        rolePermissionMapTable.addRow(rolePermissionMapData6);
        // testEditRolePermissionMap使用
        Object[] rolePermissionMapData7 = { Long.valueOf("7"),
                Long.valueOf("2"), Long.valueOf("1"), date, Long.valueOf("1"),
                null, null, Byte.valueOf("0"), Byte.valueOf("0"),
                Long.valueOf("0") };
        rolePermissionMapTable.addRow(rolePermissionMapData7);
        // testPageRolePermissionMap使用
        Object[] rolePermissionMapData8 = { Long.valueOf("8"),
                Long.valueOf("2"), Long.valueOf("1"), date, Long.valueOf("1"),
                null, null, Byte.valueOf("0"), Byte.valueOf("0"),
                Long.valueOf("0") };
        rolePermissionMapTable.addRow(rolePermissionMapData8);

        // 组合表
        DefaultTable[] tables = { userTable, rolePermissionMapTable };
        // 创建测试数据库的引用以准备写入数据库中
        IDataSet tempDataSet = new DefaultDataSet(tables);
        // 写入数据库
        DatabaseOperation.CLEAN_INSERT.execute(TestDBHelper.getConn(),
                tempDataSet);
    }

    /**
     * 测试结束方法.
     * 
     * @throws java.lang.Exception
     *             普通异常.
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        TestDBHelper.restoreDataBase();
    }

    /**
     * 查询角色未拥有行为列表单元测试.
     * @throws Exception 普通异常.
     */
    @Test
    public final void testPageNotHasRolePermission() throws Exception {
        RolePermissionMapDaoproxy daoproxy = RolePermissionMapDaoproxy
                .getInstance();
        PaginationPo pageInfo = new PaginationPo();
        pageInfo.setIndexPageNum(1);
        pageInfo.setSize(1);
        RolePermissionMapPo rolePermissionMapPo = new RolePermissionMapPo();
        rolePermissionMapPo.setRoleId(2L);
        PaginationPo pageResult = daoproxy.pageNotHasRolePermission(pageInfo,
                rolePermissionMapPo);
        
        boolean rs = ((PermissionPo) pageResult.getList().get(0)).getPermissionId() == Long
                .valueOf("2");
        assertTrue("查询分页成功时失败", rs);
    }
    
    /**
     * 查询角色拥有行为列表.
     * @throws Exception 普通异常.
     */
    @Test
    public final void testPageRolePermission() throws Exception {
        RolePermissionMapDaoproxy daoproxy = RolePermissionMapDaoproxy
                .getInstance();
        PaginationPo pageInfo = new PaginationPo();
        pageInfo.setIndexPageNum(1);
        pageInfo.setSize(1);
        RolePermissionMapPo rolePermissionMapPo = new RolePermissionMapPo();
        rolePermissionMapPo.setRoleId(2L);
        PaginationPo pageResult = daoproxy.pageRolePermission(pageInfo,
                rolePermissionMapPo);
        
        boolean rs = ((PermissionPo) pageResult.getList().get(0)).getPermissionId() == Long
                .valueOf("20");
        assertTrue("查询分页成功时失败", rs);
    }
}
