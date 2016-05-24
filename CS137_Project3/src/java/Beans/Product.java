/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

/**
 *
 * @author misoo
 */
public class Product {
    int pid;
    double price;
    String name;
    String description;
    String feature;
    String sub_feature;
    String img_url;
    String category;

    public Product(int pid, double price, String name, String description, String feature, String sub_feature, String img_url, String category) {
        this.pid = pid;
        this.price = price;
        this.name = name;
        this.description = description;
        this.feature = feature;
        this.sub_feature = sub_feature;
        this.img_url = img_url;
        this.category = category;
    }
    
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getSub_feature() {
        return sub_feature;
    }

    public void setSub_feature(String sub_feature) {
        this.sub_feature = sub_feature;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
}
