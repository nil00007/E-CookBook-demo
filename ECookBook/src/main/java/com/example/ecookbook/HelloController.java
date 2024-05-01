package com.example.ecookbook;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import model.meal;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private HBox cardLayout;
    private List<meal> recentlyAdded;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        recentlyAdded = new ArrayList<>(recentlyAdded());
        try{
           for (int i = 0; i < recentlyAdded.size(); i++) {
              FXMLLoader fxmlLoader = new FXMLLoader();
              fxmlLoader.setLocation(getClass().getResource("meats.fxml"));
              HBox cardBox = fxmlLoader.load();
              cardsController CardsController = fxmlLoader.getController();
              CardsController.setData(recentlyAdded.get(i));
              cardLayout.getChildren().add(cardBox);
           }
        }catch (IOException e){
        e.printStackTrace();
        }
}
    private List<meal> recentlyAdded(){
        List <meal> ls= new ArrayList<>();
        meal Meal = new meal();
        Meal.setName("Yeşil Bezelye");
        Meal.setImageSrc("/images/yesilbezelye.jpg");
        ls.add(Meal);


        Meal = new meal();
        Meal.setName("Börülce");
        Meal.setImageSrc("/images/börülce.png");
        ls.add(Meal);


        Meal = new meal();
        Meal.setName("Sarma");
        Meal.setImageSrc("/images/sarma.jpeg");
        ls.add(Meal);

        Meal = new meal();
        Meal.setName("Sinem Çorbası");
        Meal.setImageSrc("/images/sinemcorbasi.jpg");
        ls.add(Meal);

        Meal = new meal();
        Meal.setName("Manti");
        Meal.setImageSrc("/images/manti.jpg");
        ls.add(Meal);
        return ls;

    }
}
