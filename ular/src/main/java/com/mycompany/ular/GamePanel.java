/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ular;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author REZA PAHLEVI
 */
public class GamePanel extends JPanel implements ActionListener   { 
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static int UNIT_SIZE = 15;
    static final int GAME_UNIT = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    static final int DELAY = 70;
    final int x [] = new int [GAME_UNIT];
    final int y [] = new int [GAME_UNIT];
    int bodyPart = 6;
    int foodEaten;
    int foodX;
    int foodY;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;
   
    
    
    GamePanel() { 
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
      
        startGame();
    }
    public void startGame() {
        newFood();
        running = true;
        timer = new Timer (DELAY, this);
        timer.start();
        
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
        
    }
    public void draw (Graphics g) {
        //ini untuk membuat garis kotak-kotak di dalam game
        if(running) {
        
         for (int i =0; i < SCREEN_HEIGHT/UNIT_SIZE;i++) {
             //g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE,SCREEN_HEIGHT);
             //g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
         }
        
        //mengganti warna makanan ular
        g.setColor(Color.YELLOW);
        g.fillOval(foodX, foodY, UNIT_SIZE,UNIT_SIZE);
        for (int i = 0; i < bodyPart;i++) {
            if ( i == 0){
                g.setColor(Color.blue);
                g.fillRect(x [i], y [i], UNIT_SIZE,UNIT_SIZE);
            }
            else{
              g.setColor(new Color(40,180, 0) );
              g.setColor(Color.PINK);
           g.fillRect(x [i], y [i], UNIT_SIZE,UNIT_SIZE);
            }
        }
        g.setColor(Color.white);
        g.setFont(new Font("ink2", Font.BOLD,20 ));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: "+foodEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: "+foodEaten))/2, g.getFont().getSize());
        }
            else {
                    gameOver(g);
            }
        }
                
    public void newFood() {
        foodX = random.nextInt((int) (SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
        foodY = random.nextInt((int) (SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
    }
    //membuat pergerakan ular
    public void move() {
          for (int i = bodyPart; i > 0; i--) {
        x[i] = x[i - 1];
        y[i] = y[i - 1];
        }
        switch (direction) {
        case 'U' -> y[0] = y[0] - UNIT_SIZE;
        case 'D' -> y[0] = y[0] + UNIT_SIZE;
        case 'L' -> x[0] = x[0] - UNIT_SIZE;
        case 'R' -> x[0] = x[0] + UNIT_SIZE;
        }
        }
    public void checkfood (){
        if ((x[0]== foodX)&& (y[0]== foodY)) {
            bodyPart++;
            foodEaten++;
 
            newFood();
        }
        
    }
    public void checkCollisions() {
        //stop ketika kepala jumpa badan
        for (int i= bodyPart; i>0;i--) {
            if ((x [0]== x[i] && (y [0]== y[i]))) {
                running = false;
            }
        }
        //kepala jumpa garis kiri
        if (x[0]<0) {
            running =  false;
        }
        //kepala jumpa garis kanan
        if (x[0]> SCREEN_HEIGHT) {
            running =  false;
        }
        // kepala jumpa garis atas
        if (y[0]<0) {
            running =  false;
        }
        // kepala jumpa garis bawah
        if (y[0]> SCREEN_WIDTH) {
            running =  false;
        }
        if (!running) {
            timer.stop();
        }
    }
   public void gameOver(Graphics g) {
    // Tampilkan skor
    g.setColor(Color.white);
    g.setFont(new Font("Ink2", Font.BOLD, 20));
    FontMetrics metrics1 = getFontMetrics(g.getFont());
    String scoreMessage = "Score: " + foodEaten;
    int scoreMessageWidth = metrics1.stringWidth(scoreMessage);
    g.drawString(scoreMessage, (SCREEN_WIDTH - scoreMessageWidth) / 2, SCREEN_HEIGHT / 2 - 40); // Menggeser ke atas untuk menampilkan skor di atas "Game Over"
    
    // Tampilkan pesan "Game Over"
    g.setColor(Color.white);
    g.setFont(new Font("Helvetica", Font.BOLD, 40));
    FontMetrics metrics2 = getFontMetrics(g.getFont());
    String gameOverMessage = "Game Over";
    int gameOverMessageWidth = metrics2.stringWidth(gameOverMessage);
    g.drawString(gameOverMessage, (SCREEN_WIDTH - gameOverMessageWidth) / 2, SCREEN_HEIGHT / 2);
    
    g.setFont(new Font ("Arial",Font.PLAIN,20));
    g.drawString ("Better Luck Next Time :D",190 ,350);
    
}


    @Override
    public void actionPerformed(ActionEvent e) {
        if (running){
            move();
           checkfood();
           checkCollisions();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
     @Override
     
   
       public void  keyPressed (KeyEvent e) {
            switch (e.getKeyCode()) {
                
                case KeyEvent.VK_LEFT->{
                    if(direction != 'R') {
                    direction = 'L';
                }
                }
                case KeyEvent.VK_RIGHT->{
                    if(direction != 'L') {
                    direction = 'R';
            }
        }
                case KeyEvent.VK_UP->{
                    if(direction != 'D') {
                    direction = 'U';
                    }
                }
                case KeyEvent.VK_DOWN->{
                    if(direction != 'U') {
                    direction = 'D';
    }
    
}
                
            }
       }
    }
}