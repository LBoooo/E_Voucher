package com.evoucher.accv.e_voucher.model.entity;

/**
 * Created by 李小白 on 2017/9/21.
 * Email WorkerLiBai@163.com
 */

public class LoginEntity {
    
    /**
     * flag : {"retCode":"0","retDetail":{"admin":false,"deleted":false,"password":"","status":"normal","userCode":"admin","userName":"admin"},"retMsg":"success"}
     */
    
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
         * retDetail : {"admin":false,"deleted":false,"password":"","status":"normal","userCode":"admin","userName":"admin"}
         * retMsg : success
         */
        
        private String retCode;
        private RetDetailBean retDetail;
        private String retMsg;
        
        public String getRetCode() {
            return retCode;
        }
        
        public void setRetCode(String retCode) {
            this.retCode = retCode;
        }
        
        public RetDetailBean getRetDetail() {
            return retDetail;
        }
        
        public void setRetDetail(RetDetailBean retDetail) {
            this.retDetail = retDetail;
        }
        
        public String getRetMsg() {
            return retMsg;
        }
        
        public void setRetMsg(String retMsg) {
            this.retMsg = retMsg;
        }
        
        public static class RetDetailBean {
            /**
             * admin : false
             * deleted : false
             * password :
             * status : normal
             * userCode : admin
             * userName : admin
             */
            
            private boolean admin;
            private boolean deleted;
            private String password;
            private String status;
            private String userCode;
            private String userName;
            
            public boolean isAdmin() {
                return admin;
            }
            
            public void setAdmin(boolean admin) {
                this.admin = admin;
            }
            
            public boolean isDeleted() {
                return deleted;
            }
            
            public void setDeleted(boolean deleted) {
                this.deleted = deleted;
            }
            
            public String getPassword() {
                return password;
            }
            
            public void setPassword(String password) {
                this.password = password;
            }
            
            public String getStatus() {
                return status;
            }
            
            public void setStatus(String status) {
                this.status = status;
            }
            
            public String getUserCode() {
                return userCode;
            }
            
            public void setUserCode(String userCode) {
                this.userCode = userCode;
            }
            
            public String getUserName() {
                return userName;
            }
            
            public void setUserName(String userName) {
                this.userName = userName;
            }
        }
    }
}
