package net.thumbtack.school.shop.endpoint;

import net.thumbtack.school.shop.daoImpl.UserDaoImpl;
import net.thumbtack.school.shop.dto.request.UserDto;
import net.thumbtack.school.shop.mapper.UserMapper;
import net.thumbtack.school.shop.model.User;
import net.thumbtack.school.shop.service.Validation;
import net.thumbtack.school.shop.service.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserEndpoint {

    private final UserMapper userMapper;
    private final UserDaoImpl userDao;
    private final Validation validation;

//    @Autowired
    public UserEndpoint(UserMapper userMapper, UserDaoImpl userDao, Validation validation){
        this.userMapper = userMapper;
        this.userDao = userDao;
        this.validation = validation;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public User create(@RequestBody @Valid UserDto userDto){
        User user = userMapper.addUser(userDto);
        userDao.insert(user);
        return user;
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public User remove(@PathVariable("id") String id) throws ValidationException {
        validation.validationUser(id);
        return userDao.remove(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public int count(){
        return userDao.findAll().size();
    }

}
