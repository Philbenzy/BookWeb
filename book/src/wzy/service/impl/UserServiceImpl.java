package wzy.service.impl;

import wzy.dao.UserDao;
import wzy.dao.impl.UserDaoImpl;
import wzy.pojo.User;
import wzy.service.UserService;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();

    @Override
    public void registerUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        if (userDao.queryUserByUsername(username)!=null){
            return true;
        }
        return false;
    }
}
