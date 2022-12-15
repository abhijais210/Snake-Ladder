package com.example.snake_and_ladder;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

public class PlayAreaController {
    int turn = 1;
    //in map storing the start and end Coordinate
    HashMap<Pair<Double,Double>,Pair<Double,Double>> snakeLadderCoordinateChange;
    @FXML
    Text number,changeTurn;
    @FXML
    ImageView player1,player2;
    @FXML
    public void roll(MouseEvent event) throws IOException {
        //rolling the dice
        getSnakeLadderCoordinate();
        Random random = new Random();
        int rolling = random.nextInt(6) + 1;//gives [0,6) to make it [0,6)+1 --> [1,6]

        number.setText("" + rolling);

        if (turn == 1)
        {
            Pair<Double, Double> moveCoordinates = movement(player1.getTranslateX(), player1.getTranslateY(), rolling);
            player1.setTranslateX(moveCoordinates.getKey());//because there is only one key ,value pair
            player1.setTranslateY(moveCoordinates.getValue());//(x,y) --> (key,value)

            if(snakeLadderCoordinateChange.containsKey(new Pair<>(moveCoordinates.getKey(),moveCoordinates.getValue())))
            {
                player1.setTranslateX(snakeLadderCoordinateChange.get(
                        new Pair<>(moveCoordinates.getKey(),moveCoordinates.getValue())).getKey());
                player1.setTranslateY(snakeLadderCoordinateChange.get(
                        new Pair<>(moveCoordinates.getKey(),moveCoordinates.getValue())).getValue());
            }
            checkWin(player1.getTranslateX(),player1.getTranslateY());

        }
        else
        {
            Pair<Double, Double> moveCoordinates = movement(player2.getTranslateX(), player2.getTranslateY(), rolling);
            player2.setTranslateX(moveCoordinates.getKey());//because there is only one key ,value pair
            player2.setTranslateY(moveCoordinates.getValue());//(x,y) --> (key,value)

            if(snakeLadderCoordinateChange.containsKey(new Pair<>(moveCoordinates.getKey(),moveCoordinates.getValue())))
            {
                player2.setTranslateX(snakeLadderCoordinateChange.get(
                        new Pair<>(moveCoordinates.getKey(),moveCoordinates.getValue())).getKey());
                player2.setTranslateY(snakeLadderCoordinateChange.get(
                        new Pair<>(moveCoordinates.getKey(),moveCoordinates.getValue())).getValue());
            }
            checkWin(player2.getTranslateX(),player2.getTranslateY());
        }
        //changing the turn of player
        if(rolling != 6)
        {
            if(turn == 1) {
                turn = 2;
                changeTurn.setText("Player 2's turn");
            }
            else
            {
                turn = 1;
                changeTurn.setText("Player 1's turn");
            }
        }
    }
    //track the movement of each token(player)
    Pair<Double,Double> movement(Double x,Double y,int rolling)
    {
        double moveX = x;
        double moveY = y;

        if(moveY%100 == 0)
        {
            moveX += rolling * 50;
            if (moveX > 500) {
                moveX = 500 * 2 - (moveX - 50);
                moveY -= 50;
            }
        }
        else
        {
            moveX -= rolling*50;
            if(moveX < 50)
            {
                if(moveY == -450)//reach to end of row it will not move further row
                    return (new Pair<>(x,y));
                moveX = -1*(moveX - 50);
                moveY -= 50;
            }
        }
        return new Pair<>(moveX,moveY);
    }
    //check win if a player reaches to 100 of board
    public void checkWin(Double x,Double y) throws IOException {
        Alert winAlert = new Alert(Alert.AlertType.INFORMATION);
        winAlert.setHeaderText("Result");
        if(x == 50 && y == -450) {
            if (turn == 1)
            {
                winAlert.setContentText("Player1 has won the game");
            }
            else
            {
                winAlert.setContentText("Player1 has won the game");
            }
            GamePage page = new GamePage();
            HelloApplication.root.getChildren().setAll(page.root);
            winAlert.showAndWait();
        }
    }
    public void getSnakeLadderCoordinate()
    {
        snakeLadderCoordinateChange = new HashMap<>();
        snakeLadderCoordinateChange.put(new Pair<>(50.0,0.0),new Pair<>(150.0,-150.0));
        snakeLadderCoordinateChange.put(new Pair<>(200.0,0.0),new Pair<>(350.0,-50.0));
        snakeLadderCoordinateChange.put(new Pair<>(200.0,-50.0),new Pair<>(350.0,0.0));
        snakeLadderCoordinateChange.put(new Pair<>(450.0,0.0),new Pair<>(500.0,-150.0));
        snakeLadderCoordinateChange.put(new Pair<>(50.0,-100.0),new Pair<>(100.0,-200.0));
        snakeLadderCoordinateChange.put(new Pair<>(400.0,-100.0),new Pair<>(200.0,-400.0));
        snakeLadderCoordinateChange.put(new Pair<>(100.0,-300.0),new Pair<>(100.0,-50.0));
        snakeLadderCoordinateChange.put(new Pair<>(500.0,-250.0),new Pair<>(350.0,-300.0));
        snakeLadderCoordinateChange.put(new Pair<>(500.0,-350.0),new Pair<>(500.0,-450.0));
        snakeLadderCoordinateChange.put(new Pair<>(50.0,-350.0),new Pair<>(50.0,-450.0));
        snakeLadderCoordinateChange.put(new Pair<>(200.0,-300.0),new Pair<>(50.0,-250.0));
        snakeLadderCoordinateChange.put(new Pair<>(350.0,-250.0),new Pair<>(350.0,-150.0));
        snakeLadderCoordinateChange.put(new Pair<>(150.0,-450.0),new Pair<>(100.0,-350.0));
        snakeLadderCoordinateChange.put(new Pair<>(300.0,-450.0),new Pair<>(300.0,-350.0));
        snakeLadderCoordinateChange.put(new Pair<>(400.0,-450.0),new Pair<>(400.0,-350.0));
        snakeLadderCoordinateChange.put(new Pair<>(350.0,-400.0),new Pair<>(200.0,-100.0));

    }
}
