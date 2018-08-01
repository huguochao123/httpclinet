package com.shls.db.service;

/**
 * Created by song on 2017/11/5.
 */
public class PoNotFoundException extends RuntimeException
{
    public PoNotFoundException(String poName, long id)
    {
        super(poName + "不存在: " + id);
    }
}
