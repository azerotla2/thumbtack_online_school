package net.thumbtack.school.shop.daoImpl;

import net.thumbtack.school.shop.dao.GeneralDao;
import net.thumbtack.school.shop.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserDaoImpl implements GeneralDao<User> {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);
    private final ConcurrentHashMap<String, User> userMap = new ConcurrentHashMap<>();

    @Override
    public User findById(String id) {
        LOGGER.info("find user by id " + id);
        return userMap.get(id);
    }

    @Override
    public List<User> findAll() {
        LOGGER.info("find all user");
        return new ArrayList<>(userMap.values());
    }

    @Override
    public void insert(User obj) {
        LOGGER.info("insert user by id " + obj.getId());
        userMap.put(obj.getId(), obj);
    }
}
