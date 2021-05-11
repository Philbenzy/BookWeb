package wzy.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import wzy.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {
    // JavaEE的第三层，为数据的持久化进行操作，CRUD
    private QueryRunner queryRunner =  new QueryRunner();


    // 执行insert update delete 语句
    public int update(String sql, Object...args) {
        Connection conn = JDBCUtils.getConnection();
        try {
            return queryRunner.update(conn, sql, args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    // 查询一个

    public <T> T queryForOne(Class<T> type, String sql, Object...args){
        Connection conn = JDBCUtils.getConnection();
        try {
            return queryRunner.query(conn,sql, new BeanHandler<T>(type),args);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
    // 查询多个

    public <T> List<T> queryForList(Class<T> type, String sql, Object...args){
        Connection conn = JDBCUtils.getConnection();
        try{
            return queryRunner.query(conn,sql,new BeanListHandler<T>(type), args);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    // 查询一个值
    public Object queryForSingleValue(String sql,Object...args){
        Connection conn = JDBCUtils.getConnection();
        try {
            return queryRunner.query(conn,sql,new ScalarHandler(),args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }
}
