package com.sahith.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="breed")
    private String breed;

    @Column(name="height")
    private int height;

    @Column(name="weight")
    private int weight;

    @Column(name="colour")
    private String colour;

    @Column(name="age")
    private int age;


    @Column(name="cost")
    private int cost;

    @Column(name="imageURL")
    private String imageURL;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "category_id")
    private Category category;

    public Item() {
    }

    public Item(int id, String name, String breed, int height, int weight, String colour,  int age, int cost, String imageURL, Category category) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.height = height;
        this.weight = weight;
        this.colour = colour;
        this.age = age;
        this.cost = cost;
        this.imageURL = imageURL;
        this.category = category;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", colour='" + colour + '\'' +
                ", age=" + age +
                ", cost=" + cost +
                ", imageURL='" + imageURL + '\'' +
                ", category=" + category +
                '}';
    }
}
