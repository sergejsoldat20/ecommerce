package com.example.customersupport.beans;

import java.io.Serializable;
import java.sql.Timestamp;

public class MessageBean implements Serializable {


    private Integer id;
    private String messageText;
    private Timestamp createdTime;
    private Boolean belongsToAdmin;
    private Boolean isSeen;
    private Integer accountId;
    private String accountUsername;
    private String accountEmail;
    public MessageBean() {
    }

    public MessageBean(Integer id,
                             String messageText,
                             Timestamp createdTime,
                             Boolean isSeen,
                             Integer accountId,
                             Boolean belongsToAdmin,
                             String accountUsername,
                             String accountEmail) {
        this.id = id;
        this.messageText = messageText;
        this.createdTime = createdTime;
        this.isSeen = isSeen;
        this.accountId = accountId;
        this.belongsToAdmin = belongsToAdmin;
        this.accountUsername = accountUsername;
        this.accountEmail = accountEmail;
    }

    public String getAccountEmail() {
        return accountEmail;
    }

    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
    }

    public String getAccountUsername() {
        return accountUsername;
    }

    public void setAccountUsername(String accountUsername) {
        this.accountUsername = accountUsername;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Boolean getSeen() {
        return isSeen;
    }

    public void setSeen(Boolean seen) {
        isSeen = seen;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Boolean getBelongsToAdmin() {
        return belongsToAdmin;
    }

    public void setBelongsToAdmin(Boolean belongsToAdmin) {
        this.belongsToAdmin = belongsToAdmin;
    }

    @Override
    public String toString() {
        return "ReportMessageBean{" +
                "id=" + id +
                ", messageText='" + messageText + '\'' +
                ", createdTime=" + createdTime +
                ", belongsToAdmin=" + belongsToAdmin +
                ", isSeen=" + isSeen +
                ", accountId=" + accountId +
                ", accountUsername='" + accountUsername + '\'' +
                ", accountEmail='" + accountEmail + '\'' +
                '}';
    }


}