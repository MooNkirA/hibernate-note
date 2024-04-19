package com.moon.hibernate.entity;

/**
 * 实体类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2024-03-09 13:11
 * @description
 */
public class Linkman {
    private Long lkmId; // bigint(32) NOT NULL AUTO_INCREMENT COMMENT '联系人编号(主键)'
    private String lkmName; // varchar(16) DEFAULT NULL COMMENT '联系人姓名'
    private Long lkmCustid; // bigint(32) NOT NULL COMMENT '客户id'
    private String lkmGender; // char(1) DEFAULT NULL COMMENT '联系人性别'
    private String lkmPhone; // varchar(16) DEFAULT NULL COMMENT '联系人办公电话'
    private String lkmMobile; // varchar(16) DEFAULT NULL COMMENT '联系人手机'
    private String lkmEmail; // varchar(64) DEFAULT NULL COMMENT '联系人邮箱'
    private String lkmQq; // varchar(16) DEFAULT NULL COMMENT '联系人qq'
    private String lkmPosition; // varchar(16) DEFAULT NULL COMMENT '联系人职位'
    private String lkmMemo; // varchar(512) DEFAULT NULL COMMENT '联系人备注'

    // 一个客户对应多个联系人
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Linkman() {
    }

    public Linkman(Long lkmId, String lkmName, Long lkmCustid, String lkmGender, String lkmPhone, String lkmMobile,
                   String lkmEmail, String lkmQq, String lkmPosition, String lkmMemo, Customer customer) {
        super();
        this.lkmId = lkmId;
        this.lkmName = lkmName;
        this.lkmCustid = lkmCustid;
        this.lkmGender = lkmGender;
        this.lkmPhone = lkmPhone;
        this.lkmMobile = lkmMobile;
        this.lkmEmail = lkmEmail;
        this.lkmQq = lkmQq;
        this.lkmPosition = lkmPosition;
        this.lkmMemo = lkmMemo;
        this.customer = customer;
    }

    public Long getLkmId() {
        return lkmId;
    }

    public void setLkmId(Long lkmId) {
        this.lkmId = lkmId;
    }

    public String getLkmName() {
        return lkmName;
    }

    public void setLkmName(String lkmName) {
        this.lkmName = lkmName;
    }

    public Long getLkmCustid() {
        return lkmCustid;
    }

    public void setLkmCustid(Long lkmCustid) {
        this.lkmCustid = lkmCustid;
    }

    public String getLkmGender() {
        return lkmGender;
    }

    public void setLkmGender(String lkmGender) {
        this.lkmGender = lkmGender;
    }

    public String getLkmPhone() {
        return lkmPhone;
    }

    public void setLkmPhone(String lkmPhone) {
        this.lkmPhone = lkmPhone;
    }

    public String getLkmMobile() {
        return lkmMobile;
    }

    public void setLkmMobile(String lkmMobile) {
        this.lkmMobile = lkmMobile;
    }

    public String getLkmEmail() {
        return lkmEmail;
    }

    public void setLkmEmail(String lkmEmail) {
        this.lkmEmail = lkmEmail;
    }

    public String getLkmQq() {
        return lkmQq;
    }

    public void setLkmQq(String lkmQq) {
        this.lkmQq = lkmQq;
    }

    public String getLkmPosition() {
        return lkmPosition;
    }

    public void setLkmPosition(String lkmPosition) {
        this.lkmPosition = lkmPosition;
    }

    public String getLkmMemo() {
        return lkmMemo;
    }

    public void setLkmMemo(String lkmMemo) {
        this.lkmMemo = lkmMemo;
    }

    @Override
    public String toString() {
        return "Linkman [lkmId=" + lkmId + ", lkmName=" + lkmName + ", lkmCustid=" + lkmCustid + ", lkmGender="
                + lkmGender + ", lkmPhone=" + lkmPhone + ", lkmMobile=" + lkmMobile + ", lkmEmail=" + lkmEmail
                + ", lkmQq=" + lkmQq + ", lkmPosition=" + lkmPosition + ", lkmMemo=" + lkmMemo + ", customer="
                + customer + "]";
    }

}
