package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.ByteArrayOutputStream;
import java.io.PipedInputStream;
import java.io.PrintStream;
import java.net.URL;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller extends Cats implements Initializable {
    Target target = new Target();
    Cats cats = new Cats();
    Dogs dogs = new Dogs();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        staticTxtArea = stolenList;

        brownyWonTwo.setVisible(false);
        brownyWon.setVisible(false);
        brownyOkayButton.setVisible(false);
        weapon[0] = new Item("Clipped Claw - 300", 1);
        weapon[1] = new Item("Sharp Claws - 800", 3);
        weapon[2] = new Item("Fish Bone - 900", 5);
        weapon[3] = new Item("Call a Friend - 1200", 10);
        weapon[4] = new Item("Kage Bunshin No Jutsu - 2000", 50);
        String[] catNames = {siomai.getName()+ " - starter", siopao.getName()+ " - 1000", chonki.getName() + " - 5000"};
        String[] catWeapon = {"Claw - +1%", "Sharp Claws - +3%", "Kage Bunshin No Jutsu - +50%", "Fish Bone - +5%", "Call a Friend - +10%"};
        chooseCat.getItems().addAll(catNames);
        chooseCat.setOnAction(this::getCatInfo);
        for (int i = 0; i < weapon.length; i++) {
            chooseWeapon.getItems().add(String.valueOf(weapon[i].getName()));
        }
        chooseWeapon.setOnAction(this::getWeapon);

    }
    Cat siomai = new Cat("Siomai", "The Sneaky Mingming", 7, "breaking in");
    Cat siopao = new Cat("Siopao", "The Big Mingming", 9, "breaking in");
    Cat chonki = new Cat("Chonki", "The Aggressive Mingming", 14, "breaking in");
    Item[] weapon = new Item[5];



    int cash;

    @FXML
    private Button playButton, brownyOkayButton, saveButton;
    @FXML
    private Label catName, catInfo, summedRobValue, brownyWon, brownyValue, brownyWonTwo, stolenValue,catStealLabel,dogGuardLabel,robDetails;
    @FXML
    private ChoiceBox<String> chooseCat, chooseWeapon;

    @FXML
    private TextArea stolenList,retrievedList;

    static TextArea staticTxtArea;


    int robValue, catStoled;

    @FXML
    public void catButton() {


        cats.letsRob(target.getHouse());
        dogGuardLabel.setText("Guarding... \uD83D\uDC15");
        catStealLabel.setText("Robbing... \uD83D\uDC08");


        if (dogs.catchCats(cats)) {
            robValue = (int) cats.getSumRobbedValue();
            playButton.setVisible(false);
            if (Objects.equals(chooseCat.getValue(), "Siopao - 1k")) {
                brownyWon.setText("Browny gained 1% strength!");
            }
            brownyWon.setVisible(true);
            brownyOkayButton.setVisible(true);



            if (catStoled > 0) {
                brownyWonTwo.setText("Browny retrieved the items worth ₱" + catStoled + "!");
            } else if (cats.getSumRobbedValue() > 0) {
                brownyWonTwo.setText("Browny retrieved the items worth ₱" + robValue + "!");
            }
            brownyWonTwo.setVisible(true);
            catStealLabel.setText("Caught!");

            stolenValue.setText(" ");
            cats.setSumRobbedValue(0);
            catStoled = 0;
            saveButton.setVisible(false);

        } else {
            
            catStoled += cats.getSumRobbedValue();
            playButton.setVisible(true);
            brownyWon.setVisible(false);
            stolenValue.setText("₱"+catStoled);
            saveButton.setVisible(true);

            if(cats.isSuccessfulRobbery()) {
                catStoled += cats.getSumRobbedValue() * 0.2;
            }
            cats.setSumRobbedValue(0);

        }

    }

    public void visibilityCat() {
        saveButton.setVisible(false);
        playButton.setVisible(true);
        brownyWon.setVisible(false);
        brownyWonTwo.setVisible(false);
        brownyOkayButton.setVisible(false);
    }

    @FXML
    public void sellButton() {
        int savedValue;
        savedValue = catStoled;
        cash += savedValue;
        summedRobValue.setText("₱" + cash);
        stolenValue.setText(" ");
        saveButton.setVisible(false);
        catStoled = 0;
        stolenList.clear();
    }

    @FXML
    public void brownyButton() {
        visibilityCat();
        cats.setSumRobbedValue(0);
        stolenList.clear();
    }



    public void getCatInfo(ActionEvent event) {
        String name = chooseCat.getValue();
        switch (name) {
            case "Siomai - starter":
                catName.setText(siomai.getName() + " - " + siomai.getNickname());
                catInfo.setText("7 years of Age \n Expert in Sneaking");
                cats.setCatSuccessPercentage(50);
                dogs.setDogSuccessPercentage(40);
                break;
            case "Siopao - 1000":
                catName.setText(siopao.getName() + " - " + siopao.getNickname());
                catInfo.setText("9 years of Age \n Expert in Breaking in");
                cats.setCatSuccessPercentage(70);
                dogs.setDogSuccessPercentage(40);
                break;
            case "Chonki - 5000":
                catName.setText(chonki.getName() + " - " + chonki.getNickname());
                catInfo.setText("14 years of Age \n Expert in Beating");
                cats.setCatSuccessPercentage(80);
                dogs.setDogSuccessPercentage(1);
                break;
        }
    }


    public void getWeapon(ActionEvent event) {
        String weapon = chooseWeapon.getValue();
        switch (weapon){
        }
    }

}