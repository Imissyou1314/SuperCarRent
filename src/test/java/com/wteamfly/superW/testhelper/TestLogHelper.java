/**
 * Copyright (c) 2014 Wteamfly.  All rights reserved. 网飞公司 版权所有.
 * 请勿修改或删除版权声明及文件头部.
 */
package com.wteamfly.superW.testhelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * 测试用日志操作工具类.
 * 
 * @author 侯骏雄
 * @since 3.0.0
 */
public final class TestLogHelper {

    /**
     * 数据库操作工具类的私有构造方法，其作用是为了防止用户显式生成工具类的实例对象.
     * 
     */
    private TestLogHelper() {
    }

    /**
     * 清空日志文件.
     */
    public static void clearLog() {
        File file = new File("../webapps/SuperW/WEB-INF/logs/app.log");
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write("");
            fileWriter.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * 检查日志文件是否包含指定语句.
     * 
     * @param content
     *            指定语句
     * @return true 包含 false 不包含
     */
    public static boolean checklog(final String content) {
        File file = new File("../webapps/SuperW/WEB-INF/logs/app.log");
        Reader reader = null;
        StringBuffer logContent = new StringBuffer();
        try {
            reader = new InputStreamReader(new FileInputStream(file));
            int tempchar;
            while ((tempchar = reader.read()) != -1) {
                if (((char) tempchar) != '\r') {
                    logContent.append((char) tempchar);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean result = -1 != logContent.toString().indexOf(content);
        return result;
    }
}
