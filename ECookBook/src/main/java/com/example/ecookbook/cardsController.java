package com.example.ecookbook;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import model.meal;

public class cardsController {
    @FXML
    private HBox box;

    @FXML
    private ImageView mealImage;

    @FXML
    private Label mealName;

    private String [] colors = {"B9E5FF","BDB2FE", "FB9AA8", "FF5056", "E32636", "7FFFD4"};

    public void setData(meal Meal){
        Image ımage = new Image(getClass().getResourceAsStream(Meal.getImageSrc()));
        mealImage.setImage(ımage);
        mealName.setText(Meal.getName());
        box.setStyle("-fx-background-color: "+ colors[(int)(Math.random()*colors.length)] + ";" +
                "-fx-background-radius: 15;" +
                "-fx-effect: dropShadown(three-pass-box, rgba(0,0,0,0), 10,0,0,10);");
    }
}

