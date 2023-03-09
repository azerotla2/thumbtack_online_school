package net.thumbtack.school.shop.model;

public class Product {
    private String idEAN;
    private int price;
    private String description;
    private String manufactureCountry;
    private String company;
    private String address;

    public Product(String idEAN, int price, String description, String manufactureCountry, String company, String address) {
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

    public int getPrice() {
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
}
