package fr.univartois.butinfo.ihm.fourinaline.controller;

import java.util.Random;

import fr.univartois.butinfo.ihm.fourinaline.model.Grid;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class FourInALineController {

    public Grid gridGame;
    
    @FXML
    public Label log;
    @FXML
    public GridPane grid;
    @FXML
    public GridPane currentColor;
    
    @FXML
    void initialize() {
        gridGame = new Grid(this);
        resetGame();
    }
    
    @FXML
    void add1() {
        gridGame.addPionColumn(0);
    }
    @FXML
    void add2() {
        gridGame.addPionColumn(1);
    }

    @FXML
    void add3() {
        gridGame.addPionColumn(2);
    }

    @FXML
    void add4() {
        gridGame.addPionColumn(3);
    }

    @FXML
    void add5() {
        gridGame.addPionColumn(4);
    }

    @FXML
    void add6() {
        gridGame.addPionColumn(5);
    }

    @FXML
    void add7() {
        gridGame.addPionColumn(6);
    }
    
    @FXML
    void onClickRandom() {
        Random rand = new Random();
        int randomNumber = rand.nextInt(gridGame.COLUMNS); 
        gridGame.addPionColumn(randomNumber);
    }
    
    @FXML
    void onResetAction() {
        resetGame();
    }
    
    @FXML
    void onSwitchP1() {
        gridGame.switchStartPlayer();
        log.setText("Le joueur de départ a été changé.");
    }
    
    private void resetGame() {
        gridGame.vider();
        gridGame.setJoueur();
        gridGame.setVictoire(false);
        gridGame.currentColorDisplay();
        log.setText("");
    }
}
