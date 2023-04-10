package net.thumbtack.school.shop.endpoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.thumbtack.school.shop.daoImpl.UserDaoImpl;
import net.thumbtack.school.shop.dto.request.UserDto;
import net.thumbtack.school.shop.mapper.UserMapper;
import net.thumbtack.school.shop.model.User;
import net.thumbtack.school.shop.service.Validation;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserEndpoint.class)
public class UserTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private UserMapper userMapper;
    @MockBean
    private UserDaoImpl userDao;
    @MockBean
    private Validation validation;

    @Test
    public void testOk() throws Exception {
        UserDto userRequest = new UserDto(1988,
            "male",
            1234554545,
            false,
            "married",
            "no",
            "Omsk");
        MvcResult result = mvc.perform(post("/api/users").
            contentType(MediaType.APPLICATION_JSON).
            content(mapper.writeValueAsString(userRequest))).
            andReturn();

        assertEquals(result.getResponse().getStatus(), 200);
    }

    @Test
    public void testBad() throws Exception {
        UserDto user = new UserDto(
            1988,
            null,
            1234554545,
            false,
            "married",
            "no",
            "Omsk");
        mvc.perform(post("/api/users").
            contentType(MediaType.APPLICATION_JSON).
            content(mapper.writeValueAsString(user))).
            andExpect(status().isBadRequest());
    }

}
