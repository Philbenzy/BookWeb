package wzy.test;

import org.junit.Test;
import wzy.dao.impl.OrderDaoImpl;
import wzy.dao.impl.UserDaoImpl;
import wzy.pojo.Order;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

public class OrderDaoImplTest extends UserDaoImpl {

    @Test
    public void saveOrder() {
        OrderDaoImpl orderDao = new OrderDaoImpl();
        orderDao.saveOrder(new Order("2",new Date(),new BigDecimal(200),1,2));
    }
}