package com.example.demo;

import java.util.Random;

public class Dogs {

    private int dogSuccessPercentage;

    private Dog dogs;

    Dogs() {
        Item[] brownyItems = new Item[2];
        brownyItems[0] = new Item("Menacing Huge Paws", 0);
        brownyItems[1] = new Item("Bark", 0);
        Dog browny = new Dog("Browny", "Askal", 15, "guarding");
        dogs = browny;
    }


    public boolean catchCats(Cats cats) {

        if (areCatsCaught()) {
            if (cats.getSumRobbedValue() > 0) {
                System.out.println();
                System.out.println("The stolen items are recovered.");
            } else {
                System.out.println();
                System.out.println("The cats couldn't steal anything.");
            }
            return true;
        } else {
//            System.out.println("They managed to steal items valued ₱" + cats.getSumRobbedValue());
            System.out.println();
            return false;
        }
    }

    boolean areCatsCaught() {
        Random randomNumberGenerator = new Random();
        int dogRandom = randomNumberGenerator.nextInt(101);
        return dogRandom < getDogSuccessPercentage();
    }

    public void printDogData(){
        dogs.printBrownyData();
    }

    public int getDogSuccessPercentage() {
        return dogSuccessPercentage;
    }

    public void setDogSuccessPercentage(int dogSuccessPercentage) {
        this.dogSuccessPercentage = dogSuccessPercentage;
    }

}
