package com.evoucher.accv.e_voucher.model.entity;

/**
 * Created by 李小白 on 2017/9/21.
 * Email WorkerLiBai@163.com
 */

public class BaseEntity {
    private FlagBean flag;
    
    public FlagBean getFlag() {
        return flag;
    }
    
    public void setFlag(FlagBean flag) {
        this.flag = flag;
    }
    
    public static class FlagBean {
        /**
         * retCode : 0
         * retMsg : success
         */
        
        private String retCode;
        private String retMsg;
    
        public String getRetCode() {
            return retCode;
        }
    
        public void setRetCode(String retCode) {
            this.retCode = retCode;
        }
    
        public String getRetMsg() {
            return retMsg;
        }
    
        public void setRetMsg(String retMsg) {
            this.retMsg = retMsg;
        }
    }

}
