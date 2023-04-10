package net.thumbtack.school.shop.dto.request;

import net.thumbtack.school.shop.validator.Ean;

import javax.validation.constraints.NotNull;

public class ProductDto {
    @Ean
    @NotNull
    private String ean;
    @NotNull
    private double price;
    private String description;
    @NotNull
    private String country;
    @NotNull
    private String company;
    private String manufacturerAddress;

    public ProductDto(String ean, double price, String description, String country, String company, String manufacturerAddress) {
        this.ean = ean;
        this.price = price;
        this.description = description;
        this.country = country;
        this.company = company;
        this.manufacturerAddress = manufacturerAddress;
    }

    public ProductDto(){
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getManufacturerAddress() {
        return manufacturerAddress;
    }

    public void setManufacturerAddress(String manufacturerAddress) {
        this.manufacturerAddress = manufacturerAddress;
    }
}
