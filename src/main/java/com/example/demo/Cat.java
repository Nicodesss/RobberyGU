package com.example.demo;

public class Cat extends Individual {

    public Cat(String name, String nickname, int age, String expertIn) {
        super(name, nickname, age, expertIn);
    }
    @Override
    public void printData() {
        System.out.println("Cat:");
        super.printData();
    }
}
