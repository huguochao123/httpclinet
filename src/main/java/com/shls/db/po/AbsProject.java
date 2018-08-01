package com.shls.db.po;

import com.shls.db.query.BasicPo;

public class AbsProject extends BasicPo
{

    /** ABS项目编号 **/
    private String serialNumber;

    /** ABS项目名称 **/
    private String name;

    /** ABS项目描述 **/
    private String description;

    /** 抄送单位 **/
    private String ccUnitName;

    /** 创建者ID **/
    private long creator;

    /** 项目负责人 **/
    private long projectManager;

    /** 合同自增编号 **/
    private int contractIndex;

    /** 项目类型, NORMAL:普通项目; BALANCE_PAYMENT:尾款项目 **/
    private String projectType;

    /** 交易对手, 1:SUPPLIER, 2:TRANSFEROR, 3:CONTRACTOR, 4:PURCHASING_CENTER **/
    private int counterparty;

    /** 保理信息ID **/
    private long factoringInfoId;

    /** 资管/信托计划ID **/
    private long assetMgtPlanId;



    /** 获取 ABS项目编号 **/
    public String getSerialNumber() {
        return serialNumber;
   }

    /** 设置 ABS项目编号 **/
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
   }

    /** 获取 ABS项目名称 **/
    public String getName() {
        return name;
   }

    /** 设置 ABS项目名称 **/
    public void setName(String name) {
        this.name = name;
   }

    /** 获取 ABS项目描述 **/
    public String getDescription() {
        return description;
   }

    /** 设置 ABS项目描述 **/
    public void setDescription(String description) {
        this.description = description;
   }

    /** 获取 抄送单位 **/
    public String getCcUnitName() {
        return ccUnitName;
   }

    /** 设置 抄送单位 **/
    public void setCcUnitName(String ccUnitName) {
        this.ccUnitName = ccUnitName;
   }

    /** 获取 创建者ID **/
    public long getCreator() {
        return creator;
   }

    /** 设置 创建者ID **/
    public void setCreator(long creator) {
        this.creator = creator;
   }

    /** 获取 项目负责人 **/
    public long getProjectManager() {
        return projectManager;
   }

    /** 设置 项目负责人 **/
    public void setProjectManager(long projectManager) {
        this.projectManager = projectManager;
   }

    /** 获取 合同自增编号 **/
    public int getContractIndex() {
        return contractIndex;
   }

    /** 设置 合同自增编号 **/
    public void setContractIndex(int contractIndex) {
        this.contractIndex = contractIndex;
   }

    /** 获取 项目类型, NORMAL:普通项目; BALANCE_PAYMENT:尾款项目 **/
    public String getProjectType() {
        return projectType;
   }

    /** 设置 项目类型, NORMAL:普通项目; BALANCE_PAYMENT:尾款项目 **/
    public void setProjectType(String projectType) {
        this.projectType = projectType;
   }

    /** 获取 交易对手, 1:SUPPLIER, 2:TRANSFEROR, 3:CONTRACTOR, 4:PURCHASING_CENTER **/
    public int getCounterparty() {
        return counterparty;
   }

    /** 设置 交易对手, 1:SUPPLIER, 2:TRANSFEROR, 3:CONTRACTOR, 4:PURCHASING_CENTER **/
    public void setCounterparty(int counterparty) {
        this.counterparty = counterparty;
   }

    /** 获取 保理信息ID **/
    public long getFactoringInfoId() {
        return factoringInfoId;
   }

    /** 设置 保理信息ID **/
    public void setFactoringInfoId(long factoringInfoId) {
        this.factoringInfoId = factoringInfoId;
   }

    /** 获取 资管/信托计划ID **/
    public long getAssetMgtPlanId() {
        return assetMgtPlanId;
   }

    /** 设置 资管/信托计划ID **/
    public void setAssetMgtPlanId(long assetMgtPlanId) {
        this.assetMgtPlanId = assetMgtPlanId;
   }

    public String toString() {
        return "AbsProject{id = " + id + ", "
             + "serialNumber = " + serialNumber + ", "
             + "name = " + name + ", "
             + "description = " + description + ", "
             + "ccUnitName = " + ccUnitName + ", "
             + "creator = " + creator + ", "
             + "isDelete = " + isDelete + ", "
             + "status = " + status + ", "
             + "projectManager = " + projectManager + ", "
             + "createdAt = " + createdAt + ", "
             + "updatedAt = " + updatedAt + ", "
             + "contractIndex = " + contractIndex + ", "
             + "projectType = " + projectType + ", "
             + "counterparty = " + counterparty + ", "
             + "factoringInfoId = " + factoringInfoId + ", "
             + "assetMgtPlanId = " + assetMgtPlanId + ", " + "}";
    }

}

