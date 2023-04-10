package net.thumbtack.school.shop.model;

public class Product {
    private String idEAN;
    private double price;
    private String description;
    private String manufactureCountry;
    private String company;
    private String address;

    public Product(String idEAN, double price, String description, String manufactureCountry, String company, String address) {
        this.idEAN = idEAN;
        this.price = price;
        this.description = description;
        this.manufactureCountry = manufactureCountry;
        this.company = company;
        this.address = address;
    }

    public String getIdEAN() {
        return idEAN;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getManufactureCountry() {
        return manufactureCountry;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public void setIdEAN(String idEAN) {
        this.idEAN = idEAN;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setManufactureCountry(String manufactureCountry) {
        this.manufactureCountry = manufactureCountry;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
