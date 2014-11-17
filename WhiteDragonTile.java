/*Jeremy Johnson
 *CS 3230 - TR 9:30 AM
 *Lab 7: WhiteDragonTile.java
 */

import java.awt.*;
import javax.swing.*;

public class WhiteDragonTile extends Tile{
   public WhiteDragonTile(){
      setToolTipText(toString());
   }
   
   public void paintComponent(Graphics g){
      super.paintComponent(g);
      
      g.setColor(BLUE);
      
      //Top
      g.fillRect(x + 8, y + 8, 8, 8);
      g.fillRect(x + 24, y + 8, 8, 8);
      g.fillRect(x + 40, y + 8, 8, 8);
      g.fillRect(x + 56, y + 8, 8, 8);
      
      //Bottom
      g.fillRect(x + 8, y + HEIGHT - 16, 8, 8);
      g.fillRect(x + 24, y + HEIGHT - 16, 8, 8);
      g.fillRect(x + 40, y + HEIGHT - 16, 8, 8);
      g.fillRect(x + 56, y + HEIGHT - 16, 8, 8);
      
      //Left
      g.fillRect(x + 8, y + 23, 8, 8);
      g.fillRect(x + 8, y + 38, 8, 8);
      g.fillRect(x + 8, y + 53, 8, 8);
      
      //Right
      g.fillRect(x + WIDTH - 16, y + 23, 8, 8);
      g.fillRect(x + WIDTH - 16, y + 38, 8, 8);
      g.fillRect(x + WIDTH - 16, y + 53, 8, 8);
      
      //Lines
      g.drawRect(x + 8, y + 8, WIDTH - 16, HEIGHT - 16);
      g.drawRect(x + 16, y + 16, WIDTH - 32, HEIGHT - 32);
      g.drawRect(x + 12, y + 12, WIDTH - 24, HEIGHT - 24);
      
      g.setColor(Color.WHITE);
      g.drawRect(x + 10, y + 10, WIDTH - 20, HEIGHT - 20);
      g.drawRect(x + 14, y + 14, WIDTH - 28, HEIGHT - 28);
   }
   
   public String toString(){
      return "White Dragon";
   }
   
   public static void main(String[] args){
      JFrame frame = new JFrame();
      
      frame.setLayout(new FlowLayout());
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setTitle("White Dragon Tile");
      
      frame.add(new WhiteDragonTile());
      
      frame.pack();
      frame.setVisible(true);
   }
}
