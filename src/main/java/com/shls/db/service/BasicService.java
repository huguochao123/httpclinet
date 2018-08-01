package com.shls.db.service;

import com.shls.db.query.*;
import org.apache.commons.collections.map.HashedMap;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by song on 16-11-11.
 */
public abstract class BasicService<P extends BasicPo, T extends BasicDao>
{
    public static final Map<Object, Object> STATUS_MAP = new HashedMap();

    protected T dao;

    /** 保存 **/
    public static final int STATUS_SAVE = 0;
    /** 无效 **/
    public static final int STATUS_INVALID = -1;
    /** 审核中 **/
    public static final int STATUS_AUDITING = 1;
    /** 打回修改 **/
    public static final int STATUS_RETURN_EDIT = 2;
    /** 审核通过 **/
    public static final int STATUS_PASS = 3;
    /** 进行中 **/
    public static final int STATUS_IN_PROGRESS = 4;
    /** 合同审核中 **/
    public static final int STATUS_CONTRACT_AUDITING = 5;
    /** 合同审核打回修 **/
    public static final int STATUS_CONTRACT_RETURN_EDIT = 11;
    /** 合同审核通过 **/
    public static final int STATUS_CONTRACT_PASS = 6;
    /** 等待用印 **/
    public static final int STATUS_PREPARE_SEAL = 12;
    /** 用印审核中 **/
    public static final int STATUS_SEAL_AUDITING = 7;
    /** 用印打回修改 **/
    public static final int STATUS_SEAL_RETURN_EDIT = 8;
    /** 用印审核通过 **/
    public static final int STATUS_SEAL_PASS = 9;
    /** 资料待补 **/
    public static final int STATUS_DATA_NOT_COMPLETE = 10;
    /** 等待投放 **/
    public static final int STATUS_PREPARE_DELIVERY = 99;
    /** 已投放 **/
    public static final int STATUS_DELIVERY_COMPLETE = 100;
    /** 完成 **/
    public static final int STATUS_FINISHED = 101;
    /** 归档审核中 **/
    public static final int STATUS_ARCHIVE_AUDITING = 198;
    /** 归档打回修改 **/
    public static final int STATUS_ARCHIVE_RETURN_EDIT = 199;
    /** 归档 **/
    public static final int STATUS_ARCHIVED = 200;


    static
    {
        STATUS_MAP.put(BasicService.STATUS_SAVE, "save");
        STATUS_MAP.put(BasicService.STATUS_AUDITING, "auditing");
        STATUS_MAP.put(BasicService.STATUS_CONTRACT_AUDITING, "contractAuditing");
        STATUS_MAP.put(BasicService.STATUS_RETURN_EDIT, "returnEdit");
        STATUS_MAP.put(BasicService.STATUS_CONTRACT_PASS, "contractPass");
        STATUS_MAP.put(BasicService.STATUS_PASS, "pass");
        STATUS_MAP.put(BasicService.STATUS_SEAL_AUDITING, "sealAuditing");
        STATUS_MAP.put(BasicService.STATUS_SEAL_RETURN_EDIT, "sealReturnEdit");
        STATUS_MAP.put(BasicService.STATUS_SEAL_PASS, "sealPass");
        STATUS_MAP.put(BasicService.STATUS_FINISHED, "finished");
        STATUS_MAP.put(BasicService.STATUS_DELIVERY_COMPLETE, "assetsFormed");
        STATUS_MAP.put(BasicService.STATUS_IN_PROGRESS, "inProgress");
        STATUS_MAP.put(BasicService.STATUS_INVALID, "invalid");
        STATUS_MAP.put(BasicService.STATUS_ARCHIVED, "archived");
        STATUS_MAP.put(BasicService.STATUS_DATA_NOT_COMPLETE, "dataNotComplete");
        STATUS_MAP.put(BasicService.STATUS_PREPARE_DELIVERY, "prepareDelivery");
        STATUS_MAP.put(BasicService.STATUS_CONTRACT_RETURN_EDIT, "contractReturnEdit");
        STATUS_MAP.put(BasicService.STATUS_PREPARE_SEAL, "prepareSeal");
        STATUS_MAP.put(BasicService.STATUS_ARCHIVE_AUDITING, "archiveAuditing");
        STATUS_MAP.put(BasicService.STATUS_ARCHIVE_RETURN_EDIT, "archiveReturnEdit");



        STATUS_MAP.put("save", BasicService.STATUS_SAVE);
        STATUS_MAP.put("auditing", BasicService.STATUS_AUDITING);
        STATUS_MAP.put("contractAuditing", BasicService.STATUS_CONTRACT_AUDITING);
        STATUS_MAP.put("pass", BasicService.STATUS_PASS);
        STATUS_MAP.put("sealAuditing", BasicService.STATUS_SEAL_AUDITING);
        STATUS_MAP.put("sealReturnEdit", BasicService.STATUS_SEAL_RETURN_EDIT);
        STATUS_MAP.put("sealPass", BasicService.STATUS_SEAL_PASS);
        STATUS_MAP.put("finished", BasicService.STATUS_FINISHED);
        STATUS_MAP.put("assetsFormed", BasicService.STATUS_DELIVERY_COMPLETE);
        STATUS_MAP.put("inProgress", BasicService.STATUS_IN_PROGRESS);
        STATUS_MAP.put("invalid", BasicService.STATUS_INVALID);
        STATUS_MAP.put("returnEdit", BasicService.STATUS_RETURN_EDIT);
        STATUS_MAP.put("contractPass", BasicService.STATUS_CONTRACT_PASS);
        STATUS_MAP.put("archiveAuditing", BasicService.STATUS_ARCHIVE_AUDITING);
        STATUS_MAP.put("archiveReturnEdit", BasicService.STATUS_ARCHIVE_RETURN_EDIT);
        STATUS_MAP.put("archived", BasicService.STATUS_ARCHIVED);
        STATUS_MAP.put("dataNotComplete", BasicService.STATUS_DATA_NOT_COMPLETE);
        STATUS_MAP.put("prepareDelivery", BasicService.STATUS_PREPARE_DELIVERY);
        STATUS_MAP.put("contractReturnEdit", BasicService.STATUS_CONTRACT_RETURN_EDIT);
        STATUS_MAP.put("prepareSeal", BasicService.STATUS_PREPARE_SEAL);
    }

    public static final String CREATOR_TYPE_HEXIN = "HEXIN";

    public static final String CREATOR_TYPE_CLIENT = "CLIENT";


    @Resource
    public void setDao(T dao)
    {
        this.dao = dao;
    }

    public int insert(P basicPo)
    {
        basicPo.setUpdatedAt(System.currentTimeMillis());
        basicPo.setCreatedAt(System.currentTimeMillis());

        return dao.insert(basicPo);
    }

    public int update(P basicPo)
    {
        basicPo.setUpdatedAt(System.currentTimeMillis());
        return dao.update(basicPo);
    }

    public int updateIgnoreNull(P basicPo)
    {
        basicPo.setUpdatedAt(System.currentTimeMillis());
        return dao.updateIgnoreNull(basicPo);
    }

    public int delete(P basicPo)
    {
        return dao.delete(basicPo);
    }

    public int deleteById(long id)
    {
        return dao.deleteById(id);
    }

    public int deleteAll()
    {
        return dao.deleteAll();
    }

    public P findById(long id)
    {
        return (P) dao.findById(id);
    }

    /**
     * 根据ID查找对象, 如果对象不存在, 则抛出{@link PoNotFoundException}
     */
    public P findExistingById(long id)
    {
        P po = findById(id);
        if (po == null)
        {
            throw new PoNotFoundException("对象", id);
        }

        return po;
    }

    /**
     * 判断指定ID的对象是否存在
     *
     * @param throwExceptionOnNotExist 当对象不存在时是否抛出异常
     * @throws PoNotFoundException 当对象不存在, throwExceptionOnNotExist == true时
     */
    public boolean exist(Long id, boolean throwExceptionOnNotExist)
    {
        if (id == null)
        {
            throw new PoNotFoundException("对象", 0);
        }

        boolean exist = new SimpleQuery(dao).eq("id", id).exist();

        if (!exist && throwExceptionOnNotExist)
        {
            throw new PoNotFoundException("对象", id);
        }

        return exist;
    }


    public boolean insertOrUpdate(P po, boolean ignoreNull)
    {
        if (po.getId() > 0)
        {
            return ignoreNull ? updateIgnoreNull(po) > 0 : update(po) > 0;
        }
        else
        {
            return insert(po) > 0;
        }
    }

    public boolean insertOrUpdate(BasicPo po, BasicDao dao)
    {
        if (po != null)
        {
            po.setUpdatedAt(System.currentTimeMillis());
            if (po.getId() > 0)
            {
                return dao.update(po) > 0;
            }
            else
            {
                po.setCreatedAt(System.currentTimeMillis());
                return dao.insert(po) > 0;
            }
        }
        return false;
    }

    public void updateStatus(long id, int status)
    {
        BasicPo po = dao.findById(id);
        if (po != null)
        {
            po.setStatus(status);
            dao.updateIgnoreNull(po);
        }
        else
        {
            throw new RuntimeException("PO不存在: " + id);
        }
    }

    /**
     * 创建查询对象
     */
    public SimpleQuery createQuery()
    {
        return new SimpleQuery(dao);
    }

    public SimpleQuery createQuery(boolean unDelete)
    {
        return new SimpleQuery(dao, unDelete);
    }

    /**
     * 分页查找PO列表
     *
     * @param page page < 0, 返回所有数据
     */
    protected List findPoListByPage(Query query, int page, int pageLength)
    {
        List<? extends BasicPo> list;

        if (page > 0)
        {
            list = query.orderBy("id", Order.DESC).listPage((page - 1) * pageLength, pageLength);
        }
        else
        {
            list = query.orderBy("id", Order.DESC).list();
        }

        return list;
    }

}
