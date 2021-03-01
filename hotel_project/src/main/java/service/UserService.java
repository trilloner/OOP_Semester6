package service;

import dao.FactoryDao;
import dao.UserDao;
import model.user.User;

public class UserService {

    private final FactoryDao factoryDao = FactoryDao.getInstance();

    public User loginUser(String name, String password) {
        User resultOfUser;
        try (UserDao userDao = factoryDao.createUserDao()) {
            resultOfUser = userDao.findByEmail(name)
                    .orElseThrow(() -> new RuntimeException("Empty user"));
        }
        if (resultOfUser.getPassword().equals(password))
            return resultOfUser;
        throw new IllegalArgumentException("Different password");
    }

    public User registerNewUser(User user) throws Exception {
        try (UserDao userDao = factoryDao.createUserDao()) {
            return userDao.create(user).orElseThrow(() -> new Exception("User with email already exist"));
        }
    }
}
