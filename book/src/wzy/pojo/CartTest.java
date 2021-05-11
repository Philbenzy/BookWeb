package wzy.pojo;

import org.junit.Test;
import wzy.dao.impl.UserDaoImpl;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CartTest extends UserDaoImpl {

    @Test
    public void addItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"javaSE",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(2,"数据结构与算法",1,new BigDecimal(200),new BigDecimal(200)));
        cart.addItem(new CartItem(2,"数据结构与算法",1,new BigDecimal(200),new BigDecimal(200)));
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
    }

    @Test
    public void clear() {
    }

    @Test
    public void updateCount() {
    }
}