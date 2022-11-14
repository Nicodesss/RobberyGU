package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;

import java.util.Objects;
import java.util.ResourceBundle;

public class Controller extends Cats implements Initializable {
    static TextArea staticTxtArea;
    Target target = new Target();
    Cats cats = new Cats();
    Dogs dogs = new Dogs();
    Cat siomai = new Cat("Siomai", "The Sneaky Mingming", 7, "breaking in");
    Cat siopao = new Cat("Siopao", "The Big Mingming", 9, "breaking in");
    Cat chonki = new Cat("Chonki", "The Aggressive Mingming", 14, "breaking in");
    Item[] weapon = new Item[6];

    int robValue, catStoled, level;
    int brownyLevel = 0;
    boolean isSiopaoBought = false;
    boolean isChonkiBought = false;
    boolean isLevelOne = false;
    boolean isLevelTwo = false;
    boolean isLevelThree = false;
    boolean isLevelFour = false;
    boolean isLevelFive = false;
    int savedValue;

    Image brownyImg = new Image("file:///C:/Users/nicol/IdeaProjects/demo/src/browny.png");
    @FXML
    private Button playButton, brownyOkayButton, saveButton;
    @FXML
    private Label catName, catInfo, summedRobValue, brownyWon,  brownyWonTwo, stolenValue, catStealLabel, dogGuardLabel,levelCat;
    @FXML
    private ChoiceBox<String> chooseCat, chooseWeapon;
    @FXML
    private TextArea stolenList;
    @FXML
    private ImageView brownyImage, catImage;
    @FXML
    private AnchorPane pane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String image = Controller.class.getClassLoader().getResource("bg.png").toExternalForm();
        brownyImage.setImage(brownyImg);
        pane.setStyle("-fx-background-image: url('" + image + "'); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: stretch;");

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
        String[] catNames = {siomai.getName() + " - starter", siopao.getName() + " - ₱3000", chonki.getName() + " - ₱6500"};

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
        if (Integer.parseInt(summedRobValue.getText()) > 3000 && !isSiopaoBought) {
            savedValue = savedValue - 3000;
            System.out.println("Successfully bought Siopao!");
            isSiopaoBought = true;

        }
    }

    public void chonkiBought() {
        if (Integer.parseInt(summedRobValue.getText()) > 6500 && !isChonkiBought) {
            savedValue = savedValue - 6500;
            System.out.println("Successfully bought Chonki!");
            isChonkiBought = true;
        }
    }

    public void visibilityCat() {
        saveButton.setVisible(false);
        if (Objects.equals(chooseCat.getValue(), "Siomai - starter")) {
            playButton.setVisible(true);
        }else{
            if (Objects.equals(chooseCat.getValue(), "Siopao - ₱3000") && isSiopaoBought) {
                playButton.setVisible(true);
            } else {
                playButton.setVisible(false);
            }
            if (Objects.equals(chooseCat.getValue(), "Chonki - ₱6500") && isChonkiBought) {
                playButton.setVisible(true);
            } else {
                playButton.setVisible(false);
            }
        }
        brownyWon.setVisible(false);
        brownyWonTwo.setVisible(false);
        brownyOkayButton.setVisible(false);
    }

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
            case "Siomai - starter" -> {
//                URL sResource = Controller.class.getClassLoader().getResource("siomai.png");
//                URL url = getClass().getResource("file:///C:/Users/nicol/IdeaProjects/demo/src/main/resources/siomai.png");
//                Image siomaiImg = new Image(sResource.toString());
                Image siomaiImg = new Image(Controller.class.getClassLoader().getResource("siomai.png").toString());

                catImage.setImage(siomaiImg);
                catName.setText(siomai.getName() + " - " + siomai.getNickname());
                catInfo.setText(" 7 years of Age \n Expert in Sneaking");
                cats.setCatSuccessPercentage(70);
//                cats.setCatSuccessPercentage(60);
                dogs.setDogSuccessPercentage(30);
                //brownyLevel();
                playButton.setVisible(true);
            }
            case "Siopao - ₱3000" -> {
                Image siopaoImg = new Image(Controller.class.getClassLoader().getResource("Siopao.png").toString());
                catImage.setImage(siopaoImg);
                if (!isSiopaoBought) {
                    catName.setText("(locked) " + siopao.getName() + " - " + siopao.getNickname());
                    catInfo.setText(" 9 years of Age \n Expert in Breaking in");
                    System.out.println("Siopao is still LOCKED!");
                }
                siopaoBought();
                if (isSiopaoBought) {
                    catName.setText(siopao.getName() + " - " + siopao.getNickname());
                    cats.setCatSuccessPercentage(70);
                    brownyLevel();
                    playButton.setVisible(true);
                    summedRobValue.setText(String.valueOf(savedValue));
                } else {
                    playButton.setVisible(false);
                }
            }
            case "Chonki - ₱6500" -> {
                Image chonkiImg = new Image(Controller.class.getClassLoader().getResource("Chonki.png").toString());
                catImage.setImage(chonkiImg);
                if (!isChonkiBought) {
                    catName.setText("(locked) " + chonki.getName() + " - " + chonki.getNickname());
                    catInfo.setText(" 14 years of Age \n Expert in Beating");
                    System.out.println("Chonki is still LOCKED!");
                }
                chonkiBought();
                if (isChonkiBought) {
                    catName.setText(chonki.getName() + " - " + chonki.getNickname());
                    cats.setCatSuccessPercentage(90);
                    brownyLevel();
                    playButton.setVisible(true);
                    summedRobValue.setText(String.valueOf(savedValue));
                } else {
                    playButton.setVisible(false);
                }
            }
        }
    }

    public void getWeapon(ActionEvent event) {
        String weapon = chooseWeapon.getValue();
        switch (weapon) {
            case " ":
                if (Objects.equals(chooseCat.getValue(), "Siomai - starter")) {
                    playButton.setVisible(true);
                } else if (Objects.equals(chooseCat.getValue(), "Siopao - ₱3000")) {
                    if (isSiopaoBought) {
                        playButton.setVisible(true);
                    } else {
                        playButton.setVisible(false);
                    }
                } else if (Objects.equals(chooseCat.getValue(), "Chonki - ₱6500")) {
                    if (isChonkiBought) {
                        playButton.setVisible(true);
                    } else {
                        playButton.setVisible(false);
                    }
                }
                break;
            case "Clipped Claws - 1":
                if (isLevelOne && Objects.equals(chooseCat.getValue(), "Siomai - starter")) {
                    playButton.setVisible(true);
                } else if (isLevelOne && Objects.equals(chooseCat.getValue(), "Siopao - ₱3000")) {
                    if (isSiopaoBought) {
                        playButton.setVisible(true);
                    } else {
                        playButton.setVisible(false);
                    }
                } else if (isLevelOne && Objects.equals(chooseCat.getValue(), "Chonki - ₱6500")) {
                    if (isChonkiBought) {
                        playButton.setVisible(true);
                    } else {
                        playButton.setVisible(false);
                    }
                } else {
                    playButton.setVisible(false);
                }
                break;
            case "Sharp Claws - 2":
                if (isLevelTwo && Objects.equals(chooseCat.getValue(), "Siomai - starter")) {
                    playButton.setVisible(true);
                } else if (isLevelTwo && Objects.equals(chooseCat.getValue(), "Siopao - ₱3000")) {
                    if (isSiopaoBought) {
                        playButton.setVisible(true);
                    } else {
                        playButton.setVisible(false);
                    }
                } else if (isLevelTwo && Objects.equals(chooseCat.getValue(), "Chonki - ₱6500")) {
                    if (isChonkiBought) {
                        playButton.setVisible(true);
                    } else {
                        playButton.setVisible(false);
                    }
                } else {
                    playButton.setVisible(false);
                }
                break;

            case "Tuna Bone - 3":
                if (isLevelThree && Objects.equals(chooseCat.getValue(), "Siomai - starter")) {
                    playButton.setVisible(true);
                } else if (isLevelThree && Objects.equals(chooseCat.getValue(), "Siopao - ₱3000")) {
                    if (isSiopaoBought) {
                        playButton.setVisible(true);
                    } else {
                        playButton.setVisible(false);
                    }
                } else if (isLevelThree && Objects.equals(chooseCat.getValue(), "Chonki - ₱6500")) {
                    if (isChonkiBought) {
                        playButton.setVisible(true);
                    } else {
                        playButton.setVisible(false);
                    }
                } else {
                    playButton.setVisible(false);
                }
                break;
            case "Call a Friend - 4":
                if (isLevelFour && Objects.equals(chooseCat.getValue(), "Siomai - starter")) {
                    playButton.setVisible(true);
                } else if (isLevelFour && Objects.equals(chooseCat.getValue(), "Siopao - ₱3000")) {
                    if (isSiopaoBought) {
                        playButton.setVisible(true);
                    } else {
                        playButton.setVisible(false);
                    }
                } else if (isLevelFour && Objects.equals(chooseCat.getValue(), "Chonki - ₱6500")) {
                    if (isChonkiBought) {
                        playButton.setVisible(true);
                    } else {
                        playButton.setVisible(false);
                    }
                } else {
                    playButton.setVisible(false);
                }
                break;
            case "Kage Bunshin No Jutsu - 5":
                if (isLevelFive && Objects.equals(chooseCat.getValue(), "Siomai - starter")) {
                    playButton.setVisible(true);
                } else if (isLevelFive && Objects.equals(chooseCat.getValue(), "Siopao - ₱3000")) {
                    if (isSiopaoBought) {
                        playButton.setVisible(true);
                    } else {
                        playButton.setVisible(false);
                    }
                } else if (isLevelFive && Objects.equals(chooseCat.getValue(), "Chonki - ₱6500")) {
                    if (isChonkiBought) {
                        playButton.setVisible(true);
                    } else {
                        playButton.setVisible(false);
                    }
                } else {
                    playButton.setVisible(false);
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
            brownyLevel++;
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
                    case "Clipped Claws - 1" -> {
                        catStoled += cats.getSumRobbedValue() * 1.1;
                        level += cats.getSumRobbedValue();
                    }
                    case "Sharp Claws - 2" -> {
                        catStoled += cats.getSumRobbedValue() * 1.2;
                        level += cats.getSumRobbedValue();
                    }
                    case "Tuna Bone - 3" -> {
                        catStoled += cats.getSumRobbedValue() * 1.5;
                        level += cats.getSumRobbedValue();
                    }
                    case "Call a Friend - 4" -> {
                        catStoled += cats.getSumRobbedValue() * 2;
                        level += cats.getSumRobbedValue();
                    }
                    case "Kage Bunshin No Jutsu - 5" -> {
                        catStoled += cats.getSumRobbedValue() * 3;
                        level += cats.getSumRobbedValue();
                    }
                    case " " -> {
                        catStoled += cats.getSumRobbedValue();
                        level += cats.getSumRobbedValue();
                    }
                }
            }
            playButton.setVisible(true);
            brownyWon.setVisible(false);
            stolenValue.setText(String.valueOf(catStoled));
            saveButton.setVisible(true);
            cats.setSumRobbedValue(0);
        }
        catLevel();
    }

    private void brownyLevel() {
        if (brownyLevel > 1 && brownyLevel < 6) {
            dogs.setDogSuccessPercentage(31);
        } else if (brownyLevel > 5 && brownyLevel < 11) {
            dogs.setDogSuccessPercentage(32);
        } else if (brownyLevel > 10 && brownyLevel < 16) {
            dogs.setDogSuccessPercentage(33);
        } else if (brownyLevel > 15 && brownyLevel < 21) {
            dogs.setDogSuccessPercentage(34);
        } else if (brownyLevel > 20 && brownyLevel < 26) {
            dogs.setDogSuccessPercentage(35);
        } else if (brownyLevel == 0) {
            dogs.setDogSuccessPercentage(30);
        }
    }

    private void catLevel() {
        if (level >= 600 && level < 1000) {
            levelCat.setText("1");
            if (level >= 600 && level < 650) {
                System.out.println("You have reached level 1!");
            }
            isLevelOne = true;
        } else if (level >= 1500 && level < 2500) {
            levelCat.setText("2");
            if (level >= 1500 && level < 1550){
                System.out.println("You have reached level 2!");
            }
            isLevelTwo = true;
        } else if (level >= 2500 && level < 3500) {
            levelCat.setText("3");
            if (level >= 2500 && level < 2550) {
                System.out.println("You have reached level 3!");
            }
            isLevelThree = true;
        } else if (level >= 3500 && level < 4500) {
            levelCat.setText("4");
            if (level >= 3500 && level < 3550) {
                System.out.println("You have reached level 4!");
            }
            isLevelFour = true;
        } else if (level > 4500) {
            levelCat.setText("MAX");
            if (level >= 4500 && level < 4600) {
                System.out.println("You have reached MAX level!");
            }
            isLevelFive = true;
        }
    }
}