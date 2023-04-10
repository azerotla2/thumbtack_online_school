package net.thumbtack.school.shop.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.thumbtack.school.shop.daoImpl.ProductDaoImpl;
import net.thumbtack.school.shop.daoImpl.ProductReviewDaoImpl;
import net.thumbtack.school.shop.dto.request.ProductDto;
import net.thumbtack.school.shop.dto.request.ProductReviewDto;
import net.thumbtack.school.shop.endpoint.ProductEndpoint;
import net.thumbtack.school.shop.mapper.ProductMapper;
import net.thumbtack.school.shop.mapper.ProductReviewMapper;
import net.thumbtack.school.shop.service.Validation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ProductEndpoint.class)
//@SpringBootTest(classes = ProductEndpoint.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private ProductDaoImpl productDao;
    @MockBean
    private Validation validation;
    @MockBean
    private ProductMapper productMapper;
    @MockBean
    private ProductReviewMapper productReviewMapper;
    @MockBean
    private  ProductReviewDaoImpl productReviewDao;


    @Test
    public void testGetAll() throws Exception {
        ProductDto product = new ProductDto("123",
            3.95,
            "Best chocolate from Siberia with love",
            "Russia",
            "who knows",
            "top secret");
        mvc.perform(post("/api/products").
            contentType(MediaType.APPLICATION_JSON).
            content(mapper.writeValueAsString(product))).
            andExpect(status().isOk());
    }

    @Test
    public void testAddReviews() throws Exception {
        ProductReviewDto reviewDto = new ProductReviewDto(null, 4, true, true, "2");
        mvc.perform(post("/api/products/123/reviews").
            contentType(MediaType.APPLICATION_JSON).
            content(mapper.writeValueAsString(reviewDto))).
            andExpect(status().isBadRequest());
    }



}
