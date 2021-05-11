package wzy.test;

import org.junit.Test;
import wzy.dao.UserDao;
import wzy.dao.impl.UserDaoImpl;
import wzy.pojo.User;

import static org.junit.Assert.*;

public class UserDaoTest{
    UserDao userDao = new UserDaoImpl();

    @Test
    public void testQueryUserByUsername() {
        System.out.println(userDao.queryUserByUsername("admin"));
    }

    @Test
    public void testQueryUserByUsernameAndPassword() {
        System.out.println(userDao.queryUserByUsernameAndPassword("admin","admin"));
    }

    @Test
    public void testSaveUser() {
        User user1 = new User(null,"wzy302","123456","wzy@qq.com");
        System.out.println(userDao.saveUser(user1));
    }
}