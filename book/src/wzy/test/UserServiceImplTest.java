package wzy.test;

import org.junit.Test;
import wzy.dao.impl.UserDaoImpl;
import wzy.pojo.User;

import static org.junit.Assert.*;

public class UserServiceImplTest extends UserDaoImpl {

    @Test
    public void registerUser() {

    }

    @Test
    public void login() {
        System.out.println(new User(null,"admin","admin",null));
    }

    @Test
    public void existsUsername() {
    }
}