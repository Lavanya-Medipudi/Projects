package SnakeGame;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;  // storing the segments of the snakes body
import java.util.Random;     // Getting random x or y values used to place our food   
import javax.swing.*;

public class SnakeGame extends JPanel implements ActionListener, KeyListener{  // Inheriting -> taking the properties of JPanel
    
    private class Tile{
        int x;
        int y;

        Tile(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    int boardWidth;
    int boardHeight;
    int tileSize = 25;

    Tile snakeHead;  // snake
    ArrayList<Tile> snakeBody;

    Tile food;  // food
    Random random;

    // Game Logic -> we need to mave snake
    Timer gameLoop;
    int velocityX;
    int velocityY;
    boolean gameOver= false;

    SnakeGame(int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        setPreferredSize(new Dimension(this.boardWidth, this.boardHeight));
        setBackground(Color.black);
        addKeyListener(this);
        setFocusable(true);

        snakeHead = new Tile(5, 5); // Initial position of snake
        snakeBody =new ArrayList<Tile>();//Constructor 



        food = new Tile(10, 10); // Initial position of snake
        random = new Random();
        placeFood();

        velocityX = 0; // snake moves 
        velocityY = 0;

        gameLoop = new Timer(100, this);
        gameLoop.start();
    }

    public void paintComponent(Graphics g) {  // method
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        // Grid for easy Visualization
        // x1 y1 x2 y2
        // for (int i = 0; i < boardWidth/ tileSize; i++) {   // 600/25 gives 24 means 24 rows & 25 columns
        //     g.drawLine(i * tileSize, 0, i * tileSize, boardHeight);   // vertical lines
        //     g.drawLine(0, i * tileSize, boardWidth, i * tileSize);
        // }
        // food
        g.setColor(Color.red);
        //g.fillRect(food.x * tileSize,food.y * tileSize, tileSize, tileSize);
        g.fill3DRect(food.x * tileSize,food.y * tileSize, tileSize, tileSize,true);
        // Snake
        //snake head
        g.setColor(Color.green);
        // g.fillRect(snakeHead.x, snakeHead.y, tileSize, tileSize);  -> it was in 0,0 position, bec it taking as 5px, 5px,
        // so we need to convert it into 5 units
        //g.fillRect(snakeHead.x * tileSize, snakeHead.y * tileSize, tileSize, tileSize);
        
        g.fill3DRect(snakeHead.x * tileSize, snakeHead.y * tileSize, tileSize, tileSize,true);
        //snakeBody
        for(int i=0;i<snakeBody.size();i++){
            Tile snakepart=snakeBody.get(i);
            //g.fillRect(snakepart.x*tileSize, snakepart.y*tileSize, tileSize, tileSize);
            g.fill3DRect(snakepart.x*tileSize, snakepart.y*tileSize, tileSize, tileSize,true);
        }
        //Score
        g.setFont(new Font("Arial",Font.PLAIN,16));
        if(gameOver){
            g.setColor(Color.red);
            g.drawString("Game Over:"+String.valueOf(snakeBody.size()),tileSize-16,tileSize);
        }
        else{
            g.drawString("Score:"+String.valueOf(snakeBody.size()),tileSize-16,tileSize);
        }
    }

    public void placeFood() {
        food.x = random.nextInt(boardWidth/tileSize);  // 600/25 = 24 -> random number from 0 to 24
        food.y = random.nextInt(boardHeight/tileSize);  
    }
    public boolean collision(Tile tile1,Tile tile2){
        return tile1.x==tile2.x && tile1.y==tile2.y;
    }

    public void move() {
        //eat  food
        if (collision(snakeHead, food)){
            snakeBody.add(new Tile(food.x, food.y));
            placeFood();
        }
        //snake Body
        for(int i=snakeBody.size()-1;i>=0;i--){
            Tile snakepart=snakeBody.get(i);
            if(i==0){
                snakepart.x=snakeHead.x;
                snakepart.y=snakeHead.y;

            }
            else{
                Tile prevSnakepart=snakeBody.get(i-1);
                snakepart.x=prevSnakepart.x;
                snakepart.y=prevSnakepart.y;
            }
        }
        snakeHead.x += velocityX;
        snakeHead.y += velocityY;
        //game over conditions
        for(int i=0;i<snakeBody.size();i++){
            Tile snakepart =snakeBody.get(i);
            if(collision(snakeHead,snakepart)){
                gameOver=true;
            }
        }
        if(snakeHead.x*tileSize<0|| snakeHead.x*tileSize>boardWidth||
        snakeHead.y*tileSize<0||snakeHead.y*tileSize>boardHeight){
            gameOver=true;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();  // we call for every 100s
        if (gameOver){
            gameLoop.stop();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP && velocityY !=1) {
            velocityX = 0;
            velocityY = -1;
        } else if(e.getKeyCode() == KeyEvent.VK_DOWN && velocityY !=-1) {
            velocityX = 0;
            velocityY = 1;
        } else if(e.getKeyCode() == KeyEvent.VK_LEFT && velocityX !=1) {
            velocityX = -1;
            velocityY = 0;
        } else if(e.getKeyCode() == KeyEvent.VK_RIGHT && velocityX !=-1) {
            velocityX = 1;
            velocityY = 0;
        }
    }

    // key typed
    @Override
    public void keyTyped(KeyEvent e) {};

    @Override
    public void keyReleased(KeyEvent e) {};

}
