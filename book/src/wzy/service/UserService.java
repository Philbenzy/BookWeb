package wzy.service;

import wzy.pojo.User;

public interface UserService {
    public void registerUser(User user);

    public User login(User user);

    public boolean existsUsername(String username);
}
