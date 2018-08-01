package com.shls.db.service;

import com.shls.db.dao.product.ClientDao;
import com.shls.db.po.Client;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by song on 12/12/2017.
 */
@Service
public class ClientService extends BasicService<Client, ClientDao>
{
    public static final int SUPPLIER = 1;
    public static final int TRANSFEROR = 2;
    public static final int CONTRACTOR = 3;
    public static final int PURCHASING_CENTER = 4;
    public static final int BUYER = 5;
    public static final int GUARANTOR = 6;
    public static final int CORE_ENTERPRISE = 7;
    public static final int FACTORING = 8;
    public static final int SECURITIES_TRADER = 9;
    public static final int HOUSE_OWNER = 10;

    /** 律事事务所 */
    public static final int LAW_FIRM = 11;

    /** 评级机构 */
    public static final int RATING_AGENCY = 12;

    /** 资产管理方 */
    public static final int ASSET_MANAGER = 13;

    /** 会计师事务所 */
    public static final int ACCOUNTING_FIRM = 14;


    public static final Map<Object, Object> CLIENT_TYPE_MAP;
    public static final Map<Integer, String> CLIENT_NAME_MAP;

    static
    {
        CLIENT_TYPE_MAP = new HashMap<>();
        CLIENT_TYPE_MAP.put("SUPPLIER", SUPPLIER);
        CLIENT_TYPE_MAP.put("TRANSFEROR", TRANSFEROR);
        CLIENT_TYPE_MAP.put("CONTRACTOR", CONTRACTOR);
        CLIENT_TYPE_MAP.put("PURCHASING_CENTER", PURCHASING_CENTER);
        CLIENT_TYPE_MAP.put("BUYER", BUYER);
        CLIENT_TYPE_MAP.put("PROJECT_COMPANY", BUYER);
        CLIENT_TYPE_MAP.put("GUARANTOR", GUARANTOR);
        CLIENT_TYPE_MAP.put("CORE_ENTERPRISE", CORE_ENTERPRISE);
        CLIENT_TYPE_MAP.put("FACTORING", FACTORING);
        CLIENT_TYPE_MAP.put("SECURITIES_TRADER", SECURITIES_TRADER);
        CLIENT_TYPE_MAP.put("HOUSE_OWNER", HOUSE_OWNER);
        CLIENT_TYPE_MAP.put("LAW_FIRM", LAW_FIRM);
        CLIENT_TYPE_MAP.put("RATING_AGENCY", RATING_AGENCY);
        CLIENT_TYPE_MAP.put("ASSET_MANAGER", ASSET_MANAGER);
        CLIENT_TYPE_MAP.put("ACCOUNTING_FIRM", ACCOUNTING_FIRM);


        CLIENT_TYPE_MAP.put(SUPPLIER, "SUPPLIER");
        CLIENT_TYPE_MAP.put(TRANSFEROR, "TRANSFEROR");
        CLIENT_TYPE_MAP.put(CONTRACTOR, "CONTRACTOR");
        CLIENT_TYPE_MAP.put(PURCHASING_CENTER, "PURCHASING_CENTER");
        CLIENT_TYPE_MAP.put(BUYER, "BUYER");
        CLIENT_TYPE_MAP.put(GUARANTOR, "GUARANTOR");
        CLIENT_TYPE_MAP.put(CORE_ENTERPRISE, "CORE_ENTERPRISE");
        CLIENT_TYPE_MAP.put(FACTORING, "FACTORING");
        CLIENT_TYPE_MAP.put(SECURITIES_TRADER, "SECURITIES_TRADER");
        CLIENT_TYPE_MAP.put(HOUSE_OWNER, "HOUSE_OWNER");
        CLIENT_TYPE_MAP.put(LAW_FIRM, "LAW_FIRM");
        CLIENT_TYPE_MAP.put(RATING_AGENCY, "RATING_AGENCY");
        CLIENT_TYPE_MAP.put(ASSET_MANAGER, "ASSET_MANAGER");
        CLIENT_TYPE_MAP.put(ACCOUNTING_FIRM, "ACCOUNTING_FIRM");

        CLIENT_NAME_MAP = new HashMap<>();
        CLIENT_NAME_MAP.put(SUPPLIER, "供应商");
        CLIENT_NAME_MAP.put(TRANSFEROR, "转让方");
        CLIENT_NAME_MAP.put(CONTRACTOR, "总包方");
        CLIENT_NAME_MAP.put(PURCHASING_CENTER, "集采中心");
        CLIENT_NAME_MAP.put(BUYER, "项目公司");
        CLIENT_NAME_MAP.put(GUARANTOR, "担保方");
        CLIENT_NAME_MAP.put(CORE_ENTERPRISE, "核心企业");
        CLIENT_NAME_MAP.put(FACTORING, "保理商");
        CLIENT_NAME_MAP.put(SECURITIES_TRADER, "券商");
        CLIENT_NAME_MAP.put(HOUSE_OWNER, "业主");
        CLIENT_TYPE_MAP.put(LAW_FIRM, "律事事务所");
        CLIENT_TYPE_MAP.put(RATING_AGENCY, "评级机构");
        CLIENT_TYPE_MAP.put(ASSET_MANAGER, "资产管理方");
        CLIENT_TYPE_MAP.put(ACCOUNTING_FIRM, "会计师事务所");

    }


    public Client findByName(String name)
    {
        return (Client) createQuery().eq("name", name).singleResult();
    }
}
