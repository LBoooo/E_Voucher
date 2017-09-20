package com.evoucher.accv.e_voucher.model.bean;

/**
 * Created by 李小白 on 2017/9/19.
 * Email WorkerLiBai@163.com
 */

public class MessageBean {
     boolean isRead;
     int messageNum;
    
    public MessageBean() {
    }
    
    public MessageBean(boolean isRead, int messageNum) {
        this.isRead = isRead;
        this.messageNum = messageNum;
    }
    
    public boolean isRead() {
        return isRead;
    }
    
    public void setRead(boolean read) {
        isRead = read;
    }
    
    public int getMessageNum() {
        return messageNum;
    }
    
    public void setMessageNum(int messageNum) {
        this.messageNum = messageNum;
    }
}
