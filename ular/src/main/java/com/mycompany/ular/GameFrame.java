
package com.mycompany.ular;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;


public class GameFrame extends JFrame  {
    
  GameFrame() {
      this.add (new GamePanel());
      this.setTitle("The Snake Game");
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setResizable(false);
      this.pack();
      this.setLocationRelativeTo(null);
      this.setLayout(null);
      this.setVisible(true);

  }

 
}