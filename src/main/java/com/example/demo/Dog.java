package com.example.demo;

public class Dog extends Individual {



    public Dog(String name, String nickname, int yearOfBorn, String expertIn) {
        super(name, nickname, yearOfBorn, expertIn);
    }

    @Override
    public void printBrownyData() {
        System.out.println("---------------------------");
        System.out.println("Dog:");
        super.printBrownyData();
    }
}
