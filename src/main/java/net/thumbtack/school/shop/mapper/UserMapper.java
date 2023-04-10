package net.thumbtack.school.shop.mapper;

import net.thumbtack.school.shop.daoImpl.UserDaoImpl;
import net.thumbtack.school.shop.dto.request.UserDto;
import net.thumbtack.school.shop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final UserDaoImpl userDao;

    @Autowired
    public UserMapper(UserDaoImpl userDao){
        this.userDao = userDao;
    }

    public User addUser(UserDto userDto){
        String id = String.valueOf(userDao.findAll().size() + 1);
        return new User(id,
            userDto.getBirthday(),
            userDto.getEducation(),
            userDto.getCardNumber(),
            userDto.isHasKids(),
            userDto.getMarried(),
            userDto.getEducation(),
            userDto.getResidencePlace());
    }
}
