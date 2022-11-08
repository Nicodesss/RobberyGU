package com.example.demo;

public class House {

    private String name;
    private Item[] items;

    public House(String name, Item[] items) {
        this.name = name;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public Item[] getItems() {
        return items;
    }
}
