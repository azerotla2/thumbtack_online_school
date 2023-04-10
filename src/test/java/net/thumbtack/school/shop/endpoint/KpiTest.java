package net.thumbtack.school.shop.endpoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.thumbtack.school.shop.calculator.KpiCalculator;
import net.thumbtack.school.shop.calculator.service.AverageRating;
import net.thumbtack.school.shop.calculator.service.IndexAttractiveness;
import net.thumbtack.school.shop.calculator.service.IndexSatisfaction;
import net.thumbtack.school.shop.daoImpl.KpiDaoImpl;
import net.thumbtack.school.shop.daoImpl.ProductDaoImpl;
import net.thumbtack.school.shop.daoImpl.ProductReviewDaoImpl;
import net.thumbtack.school.shop.dto.request.ProductDto;
import net.thumbtack.school.shop.dto.request.UserDto;
import net.thumbtack.school.shop.mapper.KpiMapper;
import net.thumbtack.school.shop.mapper.ProductMapper;
import net.thumbtack.school.shop.mapper.ProductReviewMapper;
import net.thumbtack.school.shop.model.Kpi;
import net.thumbtack.school.shop.service.GlobalErrorHandler;
import net.thumbtack.school.shop.service.Validation;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
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
@WebMvcTest(controllers = {KpiEndpoint.class, GlobalErrorHandler.class})
public class KpiTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private KpiCalculator kpiCalculator;
    @MockBean
    private KpiDaoImpl kpiDao;
    @MockBean
    private KpiMapper kpiMapper;
    @MockBean
    private Validation validation;

    @Test
    public void testGetOk() throws Exception {
        MvcResult result = mvc.perform(get("/api/products/123/metrics/kpi")).andReturn();
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    public void testPostOk() throws Exception{
        MvcResult result = mvc.perform(get("/api/products/123/metrics/kpi")).andReturn();
        assertEquals(200, result.getResponse().getStatus());
    }

}
