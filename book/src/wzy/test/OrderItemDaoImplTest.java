package wzy.test;

import org.junit.Test;
import wzy.dao.impl.OrderItemDaoImpl;
import wzy.dao.impl.UserDaoImpl;
import wzy.pojo.OrderItem;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderItemDaoImplTest extends UserDaoImpl {

    @Test
    public void saveOrderItem() {
        OrderItemDaoImpl dao = new OrderItemDaoImpl();
        //dao.saveOrderItem(new OrderItem(1,"Java从入门到精通",2,new BigDecimal(25),new BigDecimal(50),"1"));

        dao.saveOrderItem(new OrderItem(null,"JavaScript",2,new BigDecimal(25),new BigDecimal(50),"1"));
    }
}