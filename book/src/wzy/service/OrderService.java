package wzy.service;

import wzy.pojo.Cart;
import wzy.pojo.OrderItem;

public interface OrderService {
    public String createOrder(Cart cart, Integer userId);
}
