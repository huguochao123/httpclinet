package com.shls.db;

/**
 * Created by song on 05/01/2018.
 */
public class SqlAdaptor
{
    private String sql;

    public String getSql()
    {
        return sql;
    }

    public void setSql(String sql)
    {
        this.sql = sql;
    }

    public SqlAdaptor(String sql)
    {
        this.sql = sql;
    }
}
