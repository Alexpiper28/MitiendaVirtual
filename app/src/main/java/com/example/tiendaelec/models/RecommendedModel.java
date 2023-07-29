package com.example.tiendaelec.models;

public class RecommendedModel {
    String name;
    String descripcion;
    String rating;
    String img_url;
    int price;

    public RecommendedModel() {
    }

    public RecommendedModel(String name, String descripcion, String rating, String img_url, int price) {
        this.name = name;
        this.descripcion = descripcion;
        this.rating = rating;
        this.img_url = img_url;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
