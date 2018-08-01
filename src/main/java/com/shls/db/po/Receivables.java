package com.shls.db.po; 

import com.shls.db.query.BasicPo;

public class Receivables extends BasicPo {

    /** 基础交易合同名称 **/
    private String dealContractName;

    /** 基础交易合同编号 **/
    private String dealContractSerialNumber;

    /** 基础交易项目名称 **/
    private String dealProjectName;

    /** 基础交易合同金额 **/
    private double dealContractAmount;

    /** 基础交易起始日期 **/
    private java.sql.Date dealBeginDate;

    /** 基础交易完工日期 **/
    private java.sql.Date dealEndDate;

    /** 项目ID **/
    private long projectId;

    /** 供应商ID **/
    private long supplierId;

    /** 申请表编号 **/
    private String pmtApplyFormNumber;

    /** 申请表确认金额 **/
    private double applyFormConfirmAmount;

    /** 至本次申请累计进度款占工程造价% **/
    private double applyAmountInConstCost;

    /** 进度支付审批表编号 **/
    private String progPmtApprFormNumber;

    /** 确认应收帐金额 **/
    private double confirmedReceivablesAmount;

    /** 所属区域 **/
    private long region;

    /** 保理合同有效期 **/
    private int contractValidityPeriod;

    /** 保理合同签订日期 **/
    private java.sql.Date contractSignDate;

    /** 应收账款检查 **/
    private long receivablesInChecked;

    /** 创建用户（业务部门用户） **/
    private long creator;

    /** 买方ID **/
    private long buyerId;

    /** 应收款到期日 **/
    private java.sql.Date paymentDueDate;

    /** 买方地址 **/
    private String buyerAddress;

    /** 保理合同名称 **/
    private String contractName;

    /** 保理合同到期日 **/
    private java.sql.Date contractDueDate;

    /** 受让应收款金额 **/
    private double transferReceivablesAmount;

    /** 保理融资金额 **/
    private double factoringFinancingAmount;

    /** 应收款起始日 **/
    private java.sql.Date receivablesBeginDate;

    /** 已付账款 **/
    private double paidAmount;

    /** 定金 **/
    private double earnest;

    /** 供应商地址 **/
    private String supplierAddress;

    /** 担保方ID **/
    private long guarantorId;

    /** 交易对手, 1:SUPPLIER, 2:TRANSFEROR, 3:CONTRACTOR, 4:PURCHASING_CENTER **/
    private int counterpartyType;

    /** 交易对手ID **/
    private long counterpartyId;

    /** 尾款ID **/
    private long balancePaymentId;

    /** 折价率, 默认与项目折价率相同 **/
    private double discountRate;

    /** 债务人付款确认书编号 **/
    private String debtorPaymentCfmNumber;

    /** 债务人主体付款确认书编号 **/
    private String debtorSubPaymentCfmNumber;

    /** 融资金额,如果录入/导入是有融资金额,则以录入/导入的为准;否则按照公式计算 **/
    private double financingAmount;

    /** 是否利息内扣, 若内扣,则投放金额=融资金额-利息;若不内扣,投放金额=融资金额 **/
    private boolean interestDeducted;

    /** 保理天数,如果有录入/导入,则以录入/导入为准;否则保理天数为“应收款到期日-应收款起始日 **/
    private int factoringDays;

    /** 费率,百分比 **/
    private double rate;

    /** 融资利息 **/
    private double financingInterest;

    /** 基础交易类型 **/
    private String basicDealType;

    /** 银行账号ID **/
    private long bankAccountId;

    /** 供应商是否提交审核 **/
    private boolean supplierSubmitted;

    /** 项目公司是否提交审核 **/
    private boolean projectCompanySubmitted;



    /** 获取 基础交易合同名称 **/
    public String getDealContractName() {
        return dealContractName;
   }

    /** 设置 基础交易合同名称 **/
    public void setDealContractName(String dealContractName) {
        this.dealContractName = dealContractName;
   }

    /** 获取 基础交易合同编号 **/
    public String getDealContractSerialNumber() {
        return dealContractSerialNumber;
   }

    /** 设置 基础交易合同编号 **/
    public void setDealContractSerialNumber(String dealContractSerialNumber) {
        this.dealContractSerialNumber = dealContractSerialNumber;
   }

    /** 获取 基础交易项目名称 **/
    public String getDealProjectName() {
        return dealProjectName;
   }

    /** 设置 基础交易项目名称 **/
    public void setDealProjectName(String dealProjectName) {
        this.dealProjectName = dealProjectName;
   }

    /** 获取 基础交易合同金额 **/
    public double getDealContractAmount() {
        return dealContractAmount;
   }

    /** 设置 基础交易合同金额 **/
    public void setDealContractAmount(double dealContractAmount) {
        this.dealContractAmount = dealContractAmount;
   }

    /** 获取 基础交易起始日期 **/
    public java.sql.Date getDealBeginDate() {
        return dealBeginDate;
   }

    /** 设置 基础交易起始日期 **/
    public void setDealBeginDate(java.sql.Date dealBeginDate) {
        this.dealBeginDate = dealBeginDate;
   }

    /** 获取 基础交易完工日期 **/
    public java.sql.Date getDealEndDate() {
        return dealEndDate;
   }

    /** 设置 基础交易完工日期 **/
    public void setDealEndDate(java.sql.Date dealEndDate) {
        this.dealEndDate = dealEndDate;
   }

    /** 获取 项目ID **/
    public long getProjectId() {
        return projectId;
   }

    /** 设置 项目ID **/
    public void setProjectId(long projectId) {
        this.projectId = projectId;
   }

    /** 获取 供应商ID **/
    public long getSupplierId() {
        return supplierId;
   }

    /** 设置 供应商ID **/
    public void setSupplierId(long supplierId) {
        this.supplierId = supplierId;
   }

    /** 获取 申请表编号 **/
    public String getPmtApplyFormNumber() {
        return pmtApplyFormNumber;
   }

    /** 设置 申请表编号 **/
    public void setPmtApplyFormNumber(String pmtApplyFormNumber) {
        this.pmtApplyFormNumber = pmtApplyFormNumber;
   }

    /** 获取 申请表确认金额 **/
    public double getApplyFormConfirmAmount() {
        return applyFormConfirmAmount;
   }

    /** 设置 申请表确认金额 **/
    public void setApplyFormConfirmAmount(double applyFormConfirmAmount) {
        this.applyFormConfirmAmount = applyFormConfirmAmount;
   }

    /** 获取 至本次申请累计进度款占工程造价% **/
    public double getApplyAmountInConstCost() {
        return applyAmountInConstCost;
   }

    /** 设置 至本次申请累计进度款占工程造价% **/
    public void setApplyAmountInConstCost(double applyAmountInConstCost) {
        this.applyAmountInConstCost = applyAmountInConstCost;
   }

    /** 获取 进度支付审批表编号 **/
    public String getProgPmtApprFormNumber() {
        return progPmtApprFormNumber;
   }

    /** 设置 进度支付审批表编号 **/
    public void setProgPmtApprFormNumber(String progPmtApprFormNumber) {
        this.progPmtApprFormNumber = progPmtApprFormNumber;
   }

    /** 获取 确认应收帐金额 **/
    public double getConfirmedReceivablesAmount() {
        return confirmedReceivablesAmount;
   }

    /** 设置 确认应收帐金额 **/
    public void setConfirmedReceivablesAmount(double confirmedReceivablesAmount) {
        this.confirmedReceivablesAmount = confirmedReceivablesAmount;
   }

    /** 获取 所属区域 **/
    public long getRegion() {
        return region;
   }

    /** 设置 所属区域 **/
    public void setRegion(long region) {
        this.region = region;
   }

    /** 获取 保理合同有效期 **/
    public int getContractValidityPeriod() {
        return contractValidityPeriod;
   }

    /** 设置 保理合同有效期 **/
    public void setContractValidityPeriod(int contractValidityPeriod) {
        this.contractValidityPeriod = contractValidityPeriod;
   }

    /** 获取 保理合同签订日期 **/
    public java.sql.Date getContractSignDate() {
        return contractSignDate;
   }

    /** 设置 保理合同签订日期 **/
    public void setContractSignDate(java.sql.Date contractSignDate) {
        this.contractSignDate = contractSignDate;
   }

    /** 获取 应收账款检查 **/
    public long getReceivablesInChecked() {
        return receivablesInChecked;
   }

    /** 设置 应收账款检查 **/
    public void setReceivablesInChecked(long receivablesInChecked) {
        this.receivablesInChecked = receivablesInChecked;
   }

    /** 获取 创建用户（业务部门用户） **/
    public long getCreator() {
        return creator;
   }

    /** 设置 创建用户（业务部门用户） **/
    public void setCreator(long creator) {
        this.creator = creator;
   }

    /** 获取 买方ID **/
    public long getBuyerId() {
        return buyerId;
   }

    /** 设置 买方ID **/
    public void setBuyerId(long buyerId) {
        this.buyerId = buyerId;
   }

    /** 获取 应收款到期日 **/
    public java.sql.Date getPaymentDueDate() {
        return paymentDueDate;
   }

    /** 设置 应收款到期日 **/
    public void setPaymentDueDate(java.sql.Date paymentDueDate) {
        this.paymentDueDate = paymentDueDate;
   }

    /** 获取 买方地址 **/
    public String getBuyerAddress() {
        return buyerAddress;
   }

    /** 设置 买方地址 **/
    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
   }

    /** 获取 保理合同名称 **/
    public String getContractName() {
        return contractName;
   }

    /** 设置 保理合同名称 **/
    public void setContractName(String contractName) {
        this.contractName = contractName;
   }

    /** 获取 保理合同到期日 **/
    public java.sql.Date getContractDueDate() {
        return contractDueDate;
   }

    /** 设置 保理合同到期日 **/
    public void setContractDueDate(java.sql.Date contractDueDate) {
        this.contractDueDate = contractDueDate;
   }

    /** 获取 受让应收款金额 **/
    public double getTransferReceivablesAmount() {
        return transferReceivablesAmount;
   }

    /** 设置 受让应收款金额 **/
    public void setTransferReceivablesAmount(double transferReceivablesAmount) {
        this.transferReceivablesAmount = transferReceivablesAmount;
   }

    /** 获取 保理融资金额 **/
    public double getFactoringFinancingAmount() {
        return factoringFinancingAmount;
   }

    /** 设置 保理融资金额 **/
    public void setFactoringFinancingAmount(double factoringFinancingAmount) {
        this.factoringFinancingAmount = factoringFinancingAmount;
   }

    /** 获取 应收款起始日 **/
    public java.sql.Date getReceivablesBeginDate() {
        return receivablesBeginDate;
   }

    /** 设置 应收款起始日 **/
    public void setReceivablesBeginDate(java.sql.Date receivablesBeginDate) {
        this.receivablesBeginDate = receivablesBeginDate;
   }

    /** 获取 已付账款 **/
    public double getPaidAmount() {
        return paidAmount;
   }

    /** 设置 已付账款 **/
    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
   }

    /** 获取 定金 **/
    public double getEarnest() {
        return earnest;
   }

    /** 设置 定金 **/
    public void setEarnest(double earnest) {
        this.earnest = earnest;
   }

    /** 获取 供应商地址 **/
    public String getSupplierAddress() {
        return supplierAddress;
   }

    /** 设置 供应商地址 **/
    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
   }

    /** 获取 担保方ID **/
    public long getGuarantorId() {
        return guarantorId;
   }

    /** 设置 担保方ID **/
    public void setGuarantorId(long guarantorId) {
        this.guarantorId = guarantorId;
   }

    /** 获取 交易对手, 1:SUPPLIER, 2:TRANSFEROR, 3:CONTRACTOR, 4:PURCHASING_CENTER **/
    public int getCounterpartyType() {
        return counterpartyType;
   }

    /** 设置 交易对手, 1:SUPPLIER, 2:TRANSFEROR, 3:CONTRACTOR, 4:PURCHASING_CENTER **/
    public void setCounterpartyType(int counterpartyType) {
        this.counterpartyType = counterpartyType;
   }

    /** 获取 交易对手ID **/
    public long getCounterpartyId() {
        return counterpartyId;
   }

    /** 设置 交易对手ID **/
    public void setCounterpartyId(long counterpartyId) {
        this.counterpartyId = counterpartyId;
   }

    /** 获取 尾款ID **/
    public long getBalancePaymentId() {
        return balancePaymentId;
   }

    /** 设置 尾款ID **/
    public void setBalancePaymentId(long balancePaymentId) {
        this.balancePaymentId = balancePaymentId;
   }

    /** 获取 折价率, 默认与项目折价率相同 **/
    public double getDiscountRate() {
        return discountRate;
   }

    /** 设置 折价率, 默认与项目折价率相同 **/
    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
   }

    /** 获取 债务人付款确认书编号 **/
    public String getDebtorPaymentCfmNumber() {
        return debtorPaymentCfmNumber;
   }

    /** 设置 债务人付款确认书编号 **/
    public void setDebtorPaymentCfmNumber(String debtorPaymentCfmNumber) {
        this.debtorPaymentCfmNumber = debtorPaymentCfmNumber;
   }

    /** 获取 债务人主体付款确认书编号 **/
    public String getDebtorSubPaymentCfmNumber() {
        return debtorSubPaymentCfmNumber;
   }

    /** 设置 债务人主体付款确认书编号 **/
    public void setDebtorSubPaymentCfmNumber(String debtorSubPaymentCfmNumber) {
        this.debtorSubPaymentCfmNumber = debtorSubPaymentCfmNumber;
   }

    /** 获取 融资金额,如果录入/导入是有融资金额,则以录入/导入的为准;否则按照公式计算 **/
    public double getFinancingAmount() {
        return financingAmount;
   }

    /** 设置 融资金额,如果录入/导入是有融资金额,则以录入/导入的为准;否则按照公式计算 **/
    public void setFinancingAmount(double financingAmount) {
        this.financingAmount = financingAmount;
   }

    /** 获取 是否利息内扣, 若内扣,则投放金额=融资金额-利息;若不内扣,投放金额=融资金额 **/
    public boolean isInterestDeducted() {
        return interestDeducted;
   }

    /** 设置 是否利息内扣, 若内扣,则投放金额=融资金额-利息;若不内扣,投放金额=融资金额 **/
    public void setInterestDeducted(boolean interestDeducted) {
        this.interestDeducted = interestDeducted;
   }

    /** 获取 保理天数,如果有录入/导入,则以录入/导入为准;否则保理天数为“应收款到期日-应收款起始日 **/
    public int getFactoringDays() {
        return factoringDays;
   }

    /** 设置 保理天数,如果有录入/导入,则以录入/导入为准;否则保理天数为“应收款到期日-应收款起始日 **/
    public void setFactoringDays(int factoringDays) {
        this.factoringDays = factoringDays;
   }

    /** 获取 费率,百分比 **/
    public double getRate() {
        return rate;
   }

    /** 设置 费率,百分比 **/
    public void setRate(double rate) {
        this.rate = rate;
   }

    /** 获取 融资利息 **/
    public double getFinancingInterest() {
        return financingInterest;
   }

    /** 设置 融资利息 **/
    public void setFinancingInterest(double financingInterest) {
        this.financingInterest = financingInterest;
   }

    /** 获取 基础交易类型 **/
    public String getBasicDealType() {
        return basicDealType;
   }

    /** 设置 基础交易类型 **/
    public void setBasicDealType(String basicDealType) {
        this.basicDealType = basicDealType;
   }

    /** 获取 银行账号ID **/
    public long getBankAccountId() {
        return bankAccountId;
   }

    /** 设置 银行账号ID **/
    public void setBankAccountId(long bankAccountId) {
        this.bankAccountId = bankAccountId;
   }

    /** 获取 供应商是否提交审核 **/
    public boolean isSupplierSubmitted() {
        return supplierSubmitted;
   }

    /** 设置 供应商是否提交审核 **/
    public void setSupplierSubmitted(boolean supplierSubmitted) {
        this.supplierSubmitted = supplierSubmitted;
   }

    /** 获取 项目公司是否提交审核 **/
    public boolean isProjectCompanySubmitted() {
        return projectCompanySubmitted;
   }

    /** 设置 项目公司是否提交审核 **/
    public void setProjectCompanySubmitted(boolean projectCompanySubmitted) {
        this.projectCompanySubmitted = projectCompanySubmitted;
   }

    public String toString() {
        return "Receivables{id = " + id + ", "
             + "dealContractName = " + dealContractName + ", "
             + "dealContractSerialNumber = " + dealContractSerialNumber + ", "
             + "dealProjectName = " + dealProjectName + ", "
             + "dealContractAmount = " + dealContractAmount + ", "
             + "dealBeginDate = " + dealBeginDate + ", "
             + "dealEndDate = " + dealEndDate + ", "
             + "projectId = " + projectId + ", "
             + "supplierId = " + supplierId + ", "
             + "pmtApplyFormNumber = " + pmtApplyFormNumber + ", "
             + "applyFormConfirmAmount = " + applyFormConfirmAmount + ", "
             + "applyAmountInConstCost = " + applyAmountInConstCost + ", "
             + "progPmtApprFormNumber = " + progPmtApprFormNumber + ", "
             + "confirmedReceivablesAmount = " + confirmedReceivablesAmount + ", "
             + "region = " + region + ", "
             + "contractValidityPeriod = " + contractValidityPeriod + ", "
             + "contractSignDate = " + contractSignDate + ", "
             + "receivablesInChecked = " + receivablesInChecked + ", "
             + "isDelete = " + isDelete + ", "
             + "status = " + status + ", "
             + "creator = " + creator + ", "
             + "createdAt = " + createdAt + ", "
             + "updatedAt = " + updatedAt + ", "
             + "buyerId = " + buyerId + ", "
             + "paymentDueDate = " + paymentDueDate + ", "
             + "buyerAddress = " + buyerAddress + ", "
             + "contractName = " + contractName + ", "
             + "contractDueDate = " + contractDueDate + ", "
             + "transferReceivablesAmount = " + transferReceivablesAmount + ", "
             + "factoringFinancingAmount = " + factoringFinancingAmount + ", "
             + "receivablesBeginDate = " + receivablesBeginDate + ", "
             + "paidAmount = " + paidAmount + ", "
             + "earnest = " + earnest + ", "
             + "supplierAddress = " + supplierAddress + ", "
             + "guarantorId = " + guarantorId + ", "
             + "counterpartyType = " + counterpartyType + ", "
             + "counterpartyId = " + counterpartyId + ", "
             + "balancePaymentId = " + balancePaymentId + ", "
             + "discountRate = " + discountRate + ", "
             + "debtorPaymentCfmNumber = " + debtorPaymentCfmNumber + ", "
             + "debtorSubPaymentCfmNumber = " + debtorSubPaymentCfmNumber + ", "
             + "financingAmount = " + financingAmount + ", "
             + "interestDeducted = " + interestDeducted + ", "
             + "factoringDays = " + factoringDays + ", "
             + "rate = " + rate + ", "
             + "financingInterest = " + financingInterest + ", "
             + "basicDealType = " + basicDealType + ", "
             + "bankAccountId = " + bankAccountId + ", "
             + "supplierSubmitted = " + supplierSubmitted + ", "
             + "projectCompanySubmitted = " + projectCompanySubmitted + ", " + "}";
    }

}

