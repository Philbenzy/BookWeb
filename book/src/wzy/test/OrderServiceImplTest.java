package wzy.test;

import org.junit.Test;
import wzy.dao.impl.UserDaoImpl;
import wzy.pojo.Cart;
import wzy.pojo.CartItem;
import wzy.pojo.Order;
import wzy.service.impl.OrderServiceImpl;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderServiceImplTest extends UserDaoImpl {

    @Test
    public void createOrder() {
        Cart cart = new Cart();

        cart.addItem(new CartItem(1,"java",1,new BigDecimal(50),new BigDecimal(50)));
        cart.addItem(new CartItem(2,"Python",4,new BigDecimal(25),new BigDecimal(100)));

        OrderServiceImpl orderService = new OrderServiceImpl();

        System.out.println(orderService.createOrder(cart,1));
    }
}