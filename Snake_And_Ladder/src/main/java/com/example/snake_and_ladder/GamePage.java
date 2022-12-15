package com.example.snake_and_ladder;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class GamePage {
    public AnchorPane root;
    GamePage() throws IOException {
            root = FXMLLoader.load(getClass().getResource("GamePage.fxml"));
    }
}
