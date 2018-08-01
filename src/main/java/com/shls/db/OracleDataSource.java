package com.shls.db;

import org.apache.commons.dbcp.BasicDataSource;

/**
 * Created by song on 14/09/2017.
 */
public class OracleDataSource extends BasicDataSource
{
    public OracleDataSource()
    {
        // 不加这个属性无法获取字段注释
        addConnectionProperty("remarksReporting", "true");
    }

}
