package com.evoucher.accv.e_voucher.model.bean;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by 李小白 on 2017/9/8.
 * Email WorkerLiBai@163.com
 *
 * @Check check约束
 * @Column 列名
 * @Finder 一对多、多对一、多对多关系(见sample的Parent、Child中的使用)
 * @Foreign 外键
 * @Id 主键，当为int类型时，默认自增。 非自增时，需要设置id的值
 * @NoAutoIncrement 不自增
 * @NotNull 不为空
 * @Table 表名
 * @Transient 不写入数据库表结构
 * @Unique 唯一约束
 * <p>
 * name (String)       :  表名称
 * isId (boolean)      :  是否为主键
 * autoGen (boolean)   :  是否自增(默认: false)
 * proprety (String)   :  是否为空(默认: NOT NULL)
 */

@Table(name = "user")
public class User {
    @Column(name = "ID", isId = true, autoGen = false)
    public String userId;   // 用户id
    @Column(name = "userName")
    public String userName; // 用户名
    @Column(name = "passWord")
    public String passWord; // 密码
    
    
    @Override
    public String toString() {
        return "userId:" + userId + "\n" + "userName:" + userName + "\n" + "passWord:" + passWord;
    }
}
