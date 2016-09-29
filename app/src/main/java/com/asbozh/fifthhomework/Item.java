package com.asbozh.fifthhomework;


public class Item {

    private String title;
    private String description;
    private String icon;
    private String price;

    public Item(String title, String description, String image, String price) {
        this.title = title;
        this.description = description;
        this.icon = image;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
