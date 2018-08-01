package com.shls.db.po;

import com.shls.db.query.BasicPo;

public class Client extends BasicPo
{

    /** 客户名称 **/
    private String name;

    /** 客户简称 **/
    private String shortName;

    /** 客户登陆系统密码 **/
    private String loginPassword;

    /** 创建人 **/
    private long creator;

    /** 客户类型, 1:供应商; 2:转让方; 3:总包方; 4:集采中心; 5:买方; 6:担保方 7:地产商 **/
    private int clientType;

    /** 统一社会信用代码 **/
    private String uscc;

    /** 注册号 **/
    private String registrationNumber;

    /** 英文名 **/
    private String englishName;

    /** 系统登录账户 **/
    private String loginAccount;

    /** 客户认证进度 **/
    private String authProgress;

    /** 企业证照类型编码 **/
    private String identTyoeCode;

    /** 企业证照编号 **/
    private String identNumber;



    /** 获取 客户名称 **/
    public String getName() {
        return name;
   }

    /** 设置 客户名称 **/
    public void setName(String name) {
        this.name = name;
   }

    /** 获取 客户简称 **/
    public String getShortName() {
        return shortName;
   }

    /** 设置 客户简称 **/
    public void setShortName(String shortName) {
        this.shortName = shortName;
   }

    /** 获取 客户登陆系统密码 **/
    public String getLoginPassword() {
        return loginPassword;
   }

    /** 设置 客户登陆系统密码 **/
    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
   }

    /** 获取 创建人 **/
    public long getCreator() {
        return creator;
   }

    /** 设置 创建人 **/
    public void setCreator(long creator) {
        this.creator = creator;
   }

    /** 获取 客户类型, 1:供应商; 2:转让方; 3:总包方; 4:集采中心; 5:买方; 6:担保方 7:地产商 **/
    public int getClientType() {
        return clientType;
   }

    /** 设置 客户类型, 1:供应商; 2:转让方; 3:总包方; 4:集采中心; 5:买方; 6:担保方 7:地产商 **/
    public void setClientType(int clientType) {
        this.clientType = clientType;
   }

    /** 获取 统一社会信用代码 **/
    public String getUscc() {
        return uscc;
   }

    /** 设置 统一社会信用代码 **/
    public void setUscc(String uscc) {
        this.uscc = uscc;
   }

    /** 获取 注册号 **/
    public String getRegistrationNumber() {
        return registrationNumber;
   }

    /** 设置 注册号 **/
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
   }

    /** 获取 英文名 **/
    public String getEnglishName() {
        return englishName;
   }

    /** 设置 英文名 **/
    public void setEnglishName(String englishName) {
        this.englishName = englishName;
   }

    /** 获取 系统登录账户 **/
    public String getLoginAccount() {
        return loginAccount;
   }

    /** 设置 系统登录账户 **/
    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
   }

    /** 获取 客户认证进度 **/
    public String getAuthProgress() {
        return authProgress;
   }

    /** 设置 客户认证进度 **/
    public void setAuthProgress(String authProgress) {
        this.authProgress = authProgress;
   }

    /** 获取 企业证照类型编码 **/
    public String getIdentTyoeCode() {
        return identTyoeCode;
   }

    /** 设置 企业证照类型编码 **/
    public void setIdentTyoeCode(String identTyoeCode) {
        this.identTyoeCode = identTyoeCode;
   }

    /** 获取 企业证照编号 **/
    public String getIdentNumber() {
        return identNumber;
   }

    /** 设置 企业证照编号 **/
    public void setIdentNumber(String identNumber) {
        this.identNumber = identNumber;
   }

    public String toString() {
        return "Client{id = " + id + ", "
             + "name = " + name + ", "
             + "shortName = " + shortName + ", "
             + "loginPassword = " + loginPassword + ", "
             + "isDelete = " + isDelete + ", "
             + "status = " + status + ", "
             + "creator = " + creator + ", "
             + "createdAt = " + createdAt + ", "
             + "updatedAt = " + updatedAt + ", "
             + "clientType = " + clientType + ", "
             + "uscc = " + uscc + ", "
             + "registrationNumber = " + registrationNumber + ", "
             + "englishName = " + englishName + ", "
             + "loginAccount = " + loginAccount + ", "
             + "authProgress = " + authProgress + ", "
             + "identTyoeCode = " + identTyoeCode + ", "
             + "identNumber = " + identNumber + ", " + "}";
    }

}

