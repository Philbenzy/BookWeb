package wzy.service.impl;

import wzy.dao.BookDao;
import wzy.dao.OrderDao;
import wzy.dao.OrderItemDao;
import wzy.dao.impl.BookDaoImpl;
import wzy.dao.impl.OrderDaoImpl;
import wzy.dao.impl.OrderItemDaoImpl;
import wzy.pojo.*;
import wzy.service.OrderService;

import java.util.Date;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    // 订单
    private OrderDao orderDao = new OrderDaoImpl();
    // 订单详情
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {
        String orderId = System.currentTimeMillis() + "" + userId;
        //
        Order order = new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId);
        // 保存订单
        orderDao.saveOrder(order);
        for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
            CartItem cartItem = entry.getValue();
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(), cartItem.getPrice(), cartItem.getTotalPrice(), orderId);
            // 保存到订单详情
            orderItemDao.saveOrderItem(orderItem);

            // 销量变化
            BookDao bookDao = new BookDaoImpl();
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales() + cartItem.getCount());
            book.setStock(book.getStock() - cartItem.getCount());
            bookDao.updateBook(book);
        }
        // 清空购物车
        cart.clear();
        return orderId;
    }
}
