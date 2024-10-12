package SnakeGame;

import javax.swing.*;

public class Frame {
    public static void main(String[] args) throws Exception {
        int boardWidth = 600;
        int boardHeight = boardWidth;

        JFrame frame = new JFrame("Snake");   // Creating Window named Snake
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);    // opens window at center of the screen
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // exits when we click X 

        SnakeGame snakeGame = new SnakeGame(boardWidth, boardHeight);
        frame.add(snakeGame);
        frame.pack(); // intially dimensions are 600/600 but not exactly because of the white title name on top,
        // to Resolve this, we use pack()
        snakeGame.requestFocus();// here the snake listen for one person
    }
}
