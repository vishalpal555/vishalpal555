package com.vishal.skillvertexProject.FoodItems;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "item_name")
    private String name;

    @Column(name = "price")
    private int price;

    @Column(name = "icon_url")
    private String iconUrl;

    @Column(name = "rating")
    private int rating;

    public Item(){

    }

    public Item(String name, int price, String iconUrl, int rating){
        this.name = name;
        this.price = price;
        this.iconUrl = iconUrl;
        
        if (this.rating <= 5 && this.rating >=0 ) {
            this.rating = rating;
        } 
        else if (this.rating > 5) {
            this.rating = 5;            
        } 
        else {
            this.rating = 0;
        }
        this.rating = rating;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
    public void setRating(int rating){
        if (this.rating <= 5 && this.rating >=0 ) {
            this.rating = rating;
        } 
        else if (this.rating > 5) {
            this.rating = 5;            
        } 
        else {
            this.rating = 0;
        }
        this.rating = rating;
    }

    public String getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public int getPrice() {
        return this.price;
    }
    public String getIconUrl() {
        return this.iconUrl;
    }
    public int getRating() {
        return this.rating;
    }
}
