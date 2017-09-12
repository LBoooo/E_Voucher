package com.evoucher.accv.e_voucher.utils;

import com.evoucher.accv.e_voucher.model.bean.User;

import org.xutils.DbManager;
import org.xutils.db.table.TableEntity;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.List;

/**
 * Created by 李小白 on 2017/9/8.
 * Email WorkerLiBai@163.com
 * <p>
 * 表操作
 * .getDaoConfig  // 获取数据库的配置信息
 * .getDatabase   // 获取数据库实例
 * .replace       // 只有存在唯一索引时才有用 （慎重）
 * .dropTable     // 删除表
 * .addColumn     // 添加一列
 * .dropDb        // 删除数据库
 * <p>
 * 增**************************************************
 * try {
 * List<person> list = new ArrayList<person>();
 * // ... 加载数据
 * <p>
 * db.save(list); // 保存实体类或者实体类的List到数据库
 * db.saveOrUpdate(list); // 保存或更新实体类或者实体类的List到数据库，根据id对应的数据是否存在
 * db.saveBindingId(list); // 保存实体类或实体类的List到数据库，如果该类型的id是自动生成的，则保存完后会给id赋值
 * } catch (DbException e) {
 * }
 * <p>
 * <p>
 * 删**************************************************
 * try {
 * db.delete(person.class);//该方法是删除表中的全部数据
 * db.deleteById(person.class, 12);//该方法主要是根据表的主键(id)进行单条记录的删除
 * db.delete(person.class, WhereBuilder.b("age", ">", "20"));//根据where语句的条件进行删除操作
 * List<person> findAll = db.selector(person.class).expr("age > 20").findAll();
 * db.delete(findAll);//根据实体bean进行对表里面的一条或多条数据进行删除
 * } catch (DbException e) {
 * }
 *
 *
 * 改**************************************************
 * // 第一种
 * try {
 * List<Person> findAll = db.findAll(Person.class);
 * for (Person person : findAll) {
 * person.setAge(10);
 * }
 * db.update(findAll, "age"); //可以使对象、集合
 * } catch (DbException e) {
 * }
 * // 第二种
 * try {
 * PersonTable person = db.findById(Person.class, 1);
 * person.setAge(25);
 * db.update(person, "age");
 * } catch(DbException e){
 * }
 * <p>
 * 查**************************************************
 * try {
 * db.findById(person.class, 1);//通过主键的值来进行查找表里面的数据
 * db.findFirst(person.class);//返回当前表里面的第一条数据
 * List<person> findAll = db.findAll(person.class);//返回当前表里面的所有数据
 * db.findDbModelAll(new SqlInfo("select * from person where age > 25"));
 * DbModel model = db.findDbModelFirst(new SqlInfo("select * from person where age > 25"));
 * model.getString("age");//model相当于游标
 * List<person> findAll2 = db.selector(person.class).expr("age >10").findAll();//主要是用来进行一些特定条件的查找
 * } catch (DbException e) {
 * }
 */

public class DatabaseHelper {
    private static class DatabaseHelpHolder {
        private static final DatabaseHelper INSTANCE = new DatabaseHelper();
    }
    
    private DatabaseHelper() {
        
    }
    
    public static DatabaseHelper getInstance() {
        return DatabaseHelpHolder.INSTANCE;
    }
    
    private DbManager dbManager;
    
    public void init() {
        //本地数据的初始化
        DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
                .setDbName("e_voucher_user.db") //设置数据库名
                .setDbVersion(1) //设置数据库版本,每次启动应用时将会检查该版本号,
                // 发现数据库版本低于这里设置的值将进行数据库升级并触发DbUpgradeListener
                .setAllowTransaction(true) //设置是否开启事务,默认为false关闭事务
                .setTableCreateListener(new DbManager.TableCreateListener() {
                    @Override
                    public void onTableCreated(DbManager dbManager, TableEntity<?> tableEntity) {
                    }
                })
                .setDbOpenListener(new DbManager.DbOpenListener() {
                    @Override
                    public void onDbOpened(DbManager db) {
                        // 开启WAL, 对写入加速提升巨大
                        db.getDatabase().enableWriteAheadLogging();
                    }
                })
                // 设置数据库创建时的Listener
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                        // TODO: ...
                        // db.addColumn(...);
                        // db.dropTable(...);
                        // ...
                        // or
                        // db.dropDb();
                    }
                }); //设置数据库升级时的Listener,这里可以执行相关数据库表的相关修改,比如alter语句增加字段等
        // .setDbDir(null);//设置数据库.db文件存放的目录,默认为包名下databases目录下
        
        dbManager = x.getDb(daoConfig);
    }
    
    
    public void saveUserToDatabase(){
        try {
            User user = new User();
            user.age = 10;
            user.name = "ss";
            user.sex = "nan";
//            dbManager.save(user);
            dbManager.saveOrUpdate(user);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
    
    public List<User> getUsersFormDatabase(){
        try {
            List<User> users = dbManager.findAll(User.class);
            return users;
        } catch (DbException e) {
            e.printStackTrace();
        }
        return null;
    }
}
