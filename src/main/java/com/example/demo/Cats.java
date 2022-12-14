package com.example.demo;

import java.util.Random;

public class Cats {

    private  int catSuccessPercentage;
    private Random randomNumberGenerator = new Random();
    private String catWon;
    private double sumRobbedValue;





    public double getSumRobbedValue() {
        return sumRobbedValue;
    }
    public void setSumRobbedValue(double sumRobbedValue) {
        this.sumRobbedValue = sumRobbedValue;
    }
    public String getCatWon() {
        return catWon;
    }

    public void setCatWon(String catWon) {
        this.catWon = catWon;
    }


    public boolean isSuccessfulRobbery() {
        int randomNumber = randomNumberGenerator.nextInt(101);
        int summarizedSuccessChange = getCatSuccessPercentage() ;
        if (randomNumber >= summarizedSuccessChange) {
            return false;
        }
        return true;
    }


    public void letsRob(House[] houses) {
        int randomBuildingIndex = randomNumberGenerator.nextInt(houses.length );
        if (isSuccessfulRobbery()) {
            System.out.println("Successfully Robbed the following items from the " + houses[randomBuildingIndex].getName() + ":");
            setCatWon("Successfully Robbed the following items from the " + houses[randomBuildingIndex].getName() + ":");
            for (Item item : houses[randomBuildingIndex].getItems()) {
                sumRobbedValue += item.getValue();
                setSumRobbedValue(sumRobbedValue);
                System.out.println("-" + item.getName());

            }
        } else {
            System.out.println("Tried to rob the " + houses[randomBuildingIndex].getName() + " but he failed.");
            setCatWon("Tried to rob the " + houses[randomBuildingIndex].getName() + " but he failed.");

        }
    }
    public int getCatSuccessPercentage() {
        return catSuccessPercentage;
    }

    public void setCatSuccessPercentage(int catSuccessPercentage) {
        this.catSuccessPercentage = catSuccessPercentage;
    }
}
