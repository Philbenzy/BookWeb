package wzy.dao.impl;

import wzy.dao.OrderItemDao;
import wzy.pojo.OrderItem;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`id`,`name`,`price`,`total_money`,`count`,`order_id`) values(?,?,?,?,?,?)";
        return update(sql,orderItem.getId(),orderItem.getName(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getCount(),orderItem.getOrderId());
    }
}
