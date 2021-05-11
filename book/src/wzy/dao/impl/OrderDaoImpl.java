package wzy.dao.impl;

import wzy.dao.OrderDao;
import wzy.pojo.Order;

public class OrderDaoImpl extends BaseDao implements OrderDao {

    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(`order_id`,`create_time`,`total_money`,`status`,`user_id`) values(?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreateDate(),order.getPrice(),order.getStatus(),order.getUserId());
    }
}
