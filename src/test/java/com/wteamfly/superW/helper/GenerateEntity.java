/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.superW.helper;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.wteamfly.superW.entity.po.TablePo;
import com.wteamfly.superW.testhelper.TestDBHelper;

/**
 * 自动生成实体类（预留）.
 * 
 * @author 殷梓淞
 * @since 1.0.0
 */
public final class GenerateEntity {

    /**
     * 这里配置实体类的目录.
     */
    private static String entitySrcPath = "E:/eclipseWorkSpace/SuperW/src/"
            + "main/java/com/wteamfly/superW/entity";

    /**
     * 获取所有表.
     * 
     * @return List<String> 数据表名集合.
     */
    public static List<String> getTables() {
        String sql = "show tables;";
        List<String> tableList = new ArrayList<String>();

        ResultSet resultSet = TestDBHelper.executeQuery(sql);
        try {
            while (resultSet.next()) {

                tableList.add(resultSet.getString("Tables_in_superw"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableList;
    }

    /**
     * 获取所有字段.
     * 
     * @param tableName
     *            表名.
     * @return List<TablePo> 表字段集合.
     */
    public static List<TablePo> getColumns(final String tableName) {
        String sql = "show full columns from " + tableName + ";";
        List<TablePo> tableList = new ArrayList<TablePo>();
        TablePo tablePo;
        ResultSet resultSet = TestDBHelper.executeQuery(sql);
        try {
            while (resultSet.next()) {
                tablePo = new TablePo();
                tablePo.setField(resultSet.getString("Field"));
                tablePo.setComment(resultSet.getString("Comment"));
                tablePo.setIsNull(resultSet.getString("Null"));
                tablePo.setKey(resultSet.getString("Key"));
                tablePo.setType(resultSet.getString("Type"));
                tableList.add(tablePo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableList;
    }

    /**
     * 首字母转小写.
     * 
     * @param s
     *            字符串.
     * @return 字符串.
     */
    public static String toLowerCaseFirstOne(final String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder())
                    .append(Character.toLowerCase(s.charAt(0)))
                    .append(s.substring(1)).toString();
        }

    }

    /**
     * 首字母转大写.
     * 
     * @param s
     *            字符串.
     * @return 字符串.
     */
    public static String toUpperCaseFirstOne(final String s) {
        if (Character.isUpperCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder())
                    .append(Character.toUpperCase(s.charAt(0)))
                    .append(s.substring(1)).toString();
        }
    }

    /**
     * main方法.
     * 
     * @param args
     *            参数.
     */
    public static void main(final String[] args) {
        // List<String> list = GenerateEntity.getTables();
        // for (String string : list) {
        // GenerateEntity.getColumns(string);
        // }

        //判断文件是否存在
        File file = new File(entitySrcPath + "/vo/UserVo2.java");
        System.out.println(file.exists()); 

    }
}
