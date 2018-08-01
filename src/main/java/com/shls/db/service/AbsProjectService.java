package com.shls.db.service;

import com.shls.db.dao.product.AbsProjectDao;
import com.shls.db.po.AbsProject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
 * Created by song on 11/08/2017.
 */
@Service
public class AbsProjectService extends BasicService<AbsProject, AbsProjectDao>
{
    @Resource
    ClientService clientService;

    /**
     * 项目类型: NORMAL
     */
    public static final String TYPE_NORMAL = "NORMAL";

    /**
     * 项目类型: 尾款
     */
    public static final String TYPE_BALANCE_PAYMENT = "BALANCE_PAYMENT";


    /**
     * 根据项目名查找项目
     */
    public AbsProject findByName(String name)
    {
        return (AbsProject) createQuery().eq("name", name).singleResult();
    }
}