package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;

import java.util.Objects;
import java.util.ResourceBundle;

//       TODO
//        - add not accessible lack of money
//        - Add + percentage if browny won
//        - add percentage button click % for browny till sell button clicked
//        - add images

public class Controller extends Cats implements Initializable {
    static TextArea staticTxtArea;
    Target target = new Target();
    Cats cats = new Cats();
    Dogs dogs = new Dogs();
    Cat siomai = new Cat("Siomai", "The Sneaky Mingming", 7, "breaking in");
    Cat siopao = new Cat("Siopao", "The Big Mingming", 9, "breaking in");
    Cat chonki = new Cat("Chonki", "The Aggressive Mingming", 14, "breaking in");
    Item[] weapon = new Item[6];
    int robValue, catStoled, cash, level;
    boolean isSiopaoBought = false;
    boolean isChonkiBought = false;
    boolean isWeaponBought = false;
    @FXML
    private Button playButton, brownyOkayButton, saveButton;
    @FXML
    private Label catName, catInfo, summedRobValue, brownyWon, brownyValue, brownyWonTwo, stolenValue, catStealLabel, dogGuardLabel, robDetails, currentWeapon, levelCat;
    @FXML
    private ChoiceBox<String> chooseCat, chooseWeapon;
    @FXML
    private TextArea stolenList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        staticTxtArea = stolenList;

        brownyWonTwo.setVisible(false);
        brownyWon.setVisible(false);
        brownyOkayButton.setVisible(false);
        weapon[0] = new Item("Clipped Claws - 1", 1);
        weapon[1] = new Item("Sharp Claws - 2", 3);
        weapon[2] = new Item("Tuna Bone - 3", 5);
        weapon[3] = new Item("Call a Friend - 4", 10);
        weapon[4] = new Item("Kage Bunshin No Jutsu - 5", 50);
        weapon[5] = new Item(" ", 50);
        String[] catNames = {siomai.getName() + " - starter", siopao.getName() + " - ₱3000", chonki.getName() + " - ₱6000"};

        chooseCat.getItems().addAll(catNames);
        chooseCat.setOnAction(this::getCatInfo);
        for (int i = 0; i < weapon.length; i++) {
            chooseWeapon.getItems().add(String.valueOf(weapon[i].getName()));
        }
        chooseWeapon.setOnAction(this::getWeapon);

    }

    @FXML
    public void catButton() {
        cats.letsRob(target.getHouse());
        dogGuardLabel.setText("Guarding... \uD83D\uDC15");
        catStealLabel.setText("Robbing... \uD83D\uDC08");
        gameStart();
    }
    public void siopaoBought() {
        if (Integer.parseInt(summedRobValue.getText()) > 3000) {
            isSiopaoBought = true;
        }else{
            isSiopaoBought = false;
        }
    }

    public void chonkiBought() {
        if (Integer.parseInt(summedRobValue.getText()) > 6500) {
            isChonkiBought = true;
        }else{
            isChonkiBought = false;
        }
    }


    public void visibilityCat() {
        saveButton.setVisible(false);
        playButton.setVisible(true);
        brownyWon.setVisible(false);
        brownyWonTwo.setVisible(false);
        brownyOkayButton.setVisible(false);
    }
        int savedValue;
    @FXML
    public void sellButton() {
        savedValue += catStoled;
        summedRobValue.setText(String.valueOf(savedValue));
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
                catInfo.setText(" 7 years of Age \n Expert in Sneaking");
                cats.setCatSuccessPercentage(99);
                dogs.setDogSuccessPercentage(1);
                playButton.setVisible(true);
                if (!isWeaponBought) {
                    playButton.setVisible(false);
                } else if (Objects.equals(chooseWeapon.getValue(), " ")) {
                    playButton.setVisible(true);
                }
                break;
            case "Siopao - ₱3000":
                int siopaoPrice = 3000;
                siopaoBought();
                if (isSiopaoBought) {
                    summedRobValue.setText((String.valueOf(savedValue - 3000)));
                    savedValue = savedValue - siopaoPrice;
                    catName.setText(siopao.getName() + " - " + siopao.getNickname());
                    catInfo.setText(" 9 years of Age \n Expert in Breaking in");
                    cats.setCatSuccessPercentage(70);
                    dogs.setDogSuccessPercentage(35);
                    playButton.setVisible(true);

                } else {
                    playButton.setVisible(false);
                }
                break;
            case "Chonki - ₱6500":
                chonkiBought();
                if (isChonkiBought) {
                    int chonkiPrice = 6500;
                    summedRobValue.setText((String.valueOf(savedValue - 6500)));
                    savedValue = savedValue - chonkiPrice;
                    catName.setText(chonki.getName() + " - " + chonki.getNickname());
                    catInfo.setText(" 14 years of Age \n Expert in Beating");
                    cats.setCatSuccessPercentage(85);
                    dogs.setDogSuccessPercentage(30);
                    playButton.setVisible(true);
                } else {
                    playButton.setVisible(false);
                }
                break;
        }
    }

    public void getWeapon(ActionEvent event) {
        String weapon = chooseWeapon.getValue();
        switch (weapon) {
            case " ":
                if (Objects.equals(chooseCat.getValue(), "Siomai - starter")) {
                    playButton.setVisible(true);
                }else if (!isChonkiBought){
                    playButton.setVisible(false);
                }else if (!isSiopaoBought){
                    playButton.setVisible(false);
                }else if(Objects.equals(chooseCat.getValue(), "Siopao - ₱3000") && isSiopaoBought){
                    playButton.setVisible(true);
                }else if(Objects.equals(chooseCat.getValue(), "Chonki - ₱6000") && isChonkiBought){
                    playButton.setVisible(true);
                }
                break;
            case "Clipped Claws - 1":
                if (levelCat.getText().contains("3") || levelCat.getText().contains("4") || levelCat.getText().contains("MAX") || levelCat.getText().contains("2") || levelCat.getText().contains("1")) {
                    if (!isChonkiBought) {
                        currentWeapon.setText("Using Clipped Claws as a weapon");
                        playButton.setVisible(false);
                        isWeaponBought = false;
                    }if (!isSiopaoBought) {
                        currentWeapon.setText("Using Clipped Claws as a weapon");
                        playButton.setVisible(false);
                        isWeaponBought = false;
                    }if (Objects.equals(chooseCat.getValue(), "Siomai - starter")) {
                        currentWeapon.setText("Using Clipped Claws as a weapon");
                        playButton.setVisible(true);
                        isWeaponBought = true;
                    }
                } else {
                    playButton.setVisible(true);
                    isWeaponBought = true;
                }
                break;
            case "Sharp Claws - 2":
                if (levelCat.getText().contains("3") || levelCat.getText().contains("4") || levelCat.getText().contains("MAX") || levelCat.getText().contains("2")) {
                    if (isChonkiBought) {
                        currentWeapon.setText("Using Sharp Claws as a weapon");
                        playButton.setVisible(true);
                        isWeaponBought = true;
                    } else if (isSiopaoBought) {
                        currentWeapon.setText("Using Sharp Claws as a weapon");
                        playButton.setVisible(true);
                        isWeaponBought = true;
                    } else if (Objects.equals(chooseCat.getValue(), "Siomai - starter")) {
                        currentWeapon.setText("Using Sharp Claws as a weapon");
                        playButton.setVisible(true);
                        isWeaponBought = true;
                    }
                } else {
                    playButton.setVisible(false);
                    isWeaponBought = false;

                }
                break;
            case "Tuna Bone - 3":
                if (levelCat.getText().contains("3") || levelCat.getText().contains("4") || levelCat.getText().contains("MAX")) {
                    if (isChonkiBought) {
                        currentWeapon.setText("Using Tuna Bone as a weapon");
                        playButton.setVisible(true);
                        isWeaponBought = true;
                    } else if (isSiopaoBought) {
                        currentWeapon.setText("Using Tuna Bone as a weapon");
                        playButton.setVisible(true);
                        isWeaponBought = true;
                    } else if (Objects.equals(chooseCat.getValue(), "Siomai - starter")) {
                        currentWeapon.setText("Using Tuna Bone as a weapon");
                        playButton.setVisible(true);
                        isWeaponBought = true;
                    }
                } else {
                    playButton.setVisible(false);
                    isWeaponBought = false;

                }
                break;
            case "Call a Friend - 4":
                if (levelCat.getText().contains("4") || levelCat.getText().contains("MAX")) {
                    if (isChonkiBought) {
                        currentWeapon.setText("Calls a friend to help him rob");
                        playButton.setVisible(true);
                        isWeaponBought = true;
                    } else if (isSiopaoBought) {
                        currentWeapon.setText("Calls a friend to help him rob");
                        playButton.setVisible(true);
                        isWeaponBought = true;
                    } else if (Objects.equals(chooseCat.getValue(), "Siomai - starter")) {
                        currentWeapon.setText("Calls a friend to help him rob");
                        playButton.setVisible(true);
                        isWeaponBought = true;
                    }
                } else {
                    playButton.setVisible(false);
                    isWeaponBought = false;
                }
                break;
            case "Kage Bunshin No Jutsu - 5":
                if (Objects.equals(levelCat.getText(), "MAX")) {
                    if (isChonkiBought) {
                        currentWeapon.setText("Duplicates himself");
                        playButton.setVisible(true);
                        isWeaponBought = true;
                    } else if (isSiopaoBought) {
                        currentWeapon.setText("Duplicates himself");
                        playButton.setVisible(true);
                        isWeaponBought = true;
                    } else if (Objects.equals(chooseCat.getValue(), "Siomai - starter")) {
                        currentWeapon.setText("Duplicates himself");
                        playButton.setVisible(true);
                        isWeaponBought = true;
                    }
                } else {
                    playButton.setVisible(false);
                    isWeaponBought = false;
                }
                break;
        }
    }

    private void gameStart() {

        if (dogs.catchCats(cats)) {
            robValue = (int) cats.getSumRobbedValue();
            playButton.setVisible(false);
            brownyWon.setVisible(true);
            brownyOkayButton.setVisible(true);

            if (catStoled > 0) {
                brownyWonTwo.setText("Browny managed to catch and retrieve the items worth ₱" + catStoled + "!");
            } else if (cats.getSumRobbedValue() > 0) {
                brownyWonTwo.setText("Browny managed to catch and retrieve the items worth ₱" + robValue + "!");
            }
            brownyWonTwo.setVisible(true);
            catStealLabel.setText("Caught!");

            if (dogs.areCatsCaught()) {
                brownyWonTwo.setText("Browny managed to catch the culprit.");
            }

            stolenValue.setText("0");
            cats.setSumRobbedValue(0);
            catStoled = 0;
            saveButton.setVisible(false);

        } else {
            brownyWonTwo.setVisible(true);
            brownyWonTwo.setText("Browny couldn't catch the culprit.");
            if (chooseWeapon.getValue() == null) {
                catStoled += cats.getSumRobbedValue();
                level += cats.getSumRobbedValue();
            } else {
                switch (chooseWeapon.getValue()) {
                    case "Clipped Claws - 1":
                        catStoled += cats.getSumRobbedValue() * 1.1;
                        level += cats.getSumRobbedValue();
                        break;
                    case "Sharp Claws - 2":
                        catStoled += cats.getSumRobbedValue() * 1.2;
                        level += cats.getSumRobbedValue();
                        break;
                    case "Tuna Bone - 3":
                        catStoled += cats.getSumRobbedValue() * 1.5;
                        level += cats.getSumRobbedValue();
                        break;
                    case "Call a Friend - 4":
                        catStoled += cats.getSumRobbedValue() * 2;
                        level += cats.getSumRobbedValue();
                        break;
                    case "Kage Bunshin No Jutsu - 5":
                        catStoled += cats.getSumRobbedValue() * 3;
                        level += cats.getSumRobbedValue();
                        break;
                    case " ":
                        catStoled += cats.getSumRobbedValue();
                        level += cats.getSumRobbedValue();
                }
            }
//            catStoled += cats.getSumRobbedValue();
            playButton.setVisible(true);
            brownyWon.setVisible(false);
            stolenValue.setText(String.valueOf(catStoled));
            saveButton.setVisible(true);
            cats.setSumRobbedValue(0);
        }

        if (level >= 600 && level < 1000) {
            levelCat.setText("1");
        } else if (level >= 1500 && level < 2500) {
            levelCat.setText("2");
        } else if (level >= 2500 && level < 3500) {
            levelCat.setText("3");
        } else if (level >= 3500 && level < 4500) {
            levelCat.setText("4");
        } else if (level > 4500) {
            levelCat.setText("MAX");
        }
    }


}