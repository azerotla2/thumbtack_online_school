package net.thumbtack.school.shop.dto.request;

import javax.validation.constraints.NotNull;

public class ProductForUpdateDto {
    private String ean;
    private Double price;
    private String description;
    private String country;
    private String company;
    private String manufacturerAddress;

    public ProductForUpdateDto(String ean, Double price, String description, String country, String company, String manufacturerAddress) {
        this.ean = ean;
        this.price = price;
        this.description = description;
        this.country = country;
        this.company = company;
        this.manufacturerAddress = manufacturerAddress;
    }

    public String getEan() {
        return ean;
    }

    public Double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getCountry() {
        return country;
    }

    public String getCompany() {
        return company;
    }

    public String getManufacturerAddress() {
        return manufacturerAddress;
    }
}
