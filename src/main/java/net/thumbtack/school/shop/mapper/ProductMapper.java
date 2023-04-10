package net.thumbtack.school.shop.mapper;

import net.thumbtack.school.shop.dto.request.ProductDto;
import net.thumbtack.school.shop.dto.request.ProductForUpdateDto;
import net.thumbtack.school.shop.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product addProduct(ProductDto productDto){
        return new Product(productDto.getEan(),
            productDto.getPrice(),
            productDto.getDescription(),
            productDto.getCountry(),
            productDto.getCompany(),
            productDto.getManufacturerAddress());
    }

    public Product update(Product oldProduct, ProductForUpdateDto newProduct){
        if(newProduct.getEan() != null)
            oldProduct.setIdEAN(newProduct.getEan());

        if(newProduct.getPrice() != null)
            oldProduct.setPrice(newProduct.getPrice());

        if(newProduct.getCompany() != null)
            oldProduct.setCompany(newProduct.getCompany());

        if(newProduct.getCountry() != null)
            oldProduct.setManufactureCountry(newProduct.getCountry());

        if(newProduct.getDescription() != null)
            oldProduct.setDescription(newProduct.getDescription());

        if(newProduct.getManufacturerAddress() != null)
            oldProduct.setAddress(newProduct.getManufacturerAddress());

        return oldProduct;
    }

}
