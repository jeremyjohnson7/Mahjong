/*Jeremy Johnson
 *CS 3230 - TR 9:30 AM
 *Lab 7: BambooTile.java
 */

import java.awt.*;
import javax.swing.*;

public class BambooTile extends RankTile{
   Bamboo[] bambooPoles;
   
   public BambooTile(int rank){
      super(rank);
      setToolTipText(toString());
      
      switch(rank){
         case 2:
            bambooPoles = new Bamboo[]{
               new Bamboo(BLUE, x + 32, y + 12),
               new Bamboo(GREEN, x + 32, y + 48)
            };
            break;
            
         case 3:
            bambooPoles = new Bamboo[]{
               new Bamboo(BLUE, x + 32, y + 12),
               new Bamboo(GREEN, x + 18, y + 48),
               new Bamboo(GREEN, x + 46, y + 48)
            };
            break;
            
         case 4:
            bambooPoles = new Bamboo[]{
               new Bamboo(BLUE, x + 18, y + 12),
               new Bamboo(GREEN, x + 18, y + 48),
               new Bamboo(GREEN, x + 46, y + 12),
               new Bamboo(BLUE, x + 46, y + 48)
            };
            break;
            
         case 5:
            bambooPoles = new Bamboo[]{
               new Bamboo(GREEN, x + 12, y + 12),
               new Bamboo(BLUE, x + 12, y + 48),
               new Bamboo(RED, x + 32, y + 30),
               new Bamboo(BLUE, x + 52, y + 12),
               new Bamboo(GREEN, x + 52, y + 48)
            };
            break;
            
         case 6:
            bambooPoles = new Bamboo[]{
               new Bamboo(GREEN, x + 12, y + 12),
               new Bamboo(BLUE, x + 12, y + 48),
               new Bamboo(GREEN, x + 32, y + 12),
               new Bamboo(BLUE, x + 32, y + 48),
               new Bamboo(GREEN, x + 52, y + 12),
               new Bamboo(BLUE, x + 52, y + 48)
            };
            break;
            
         case 7:
            bambooPoles = new Bamboo[]{
               new Bamboo(GREEN, x + 12, y + 30),
               new Bamboo(GREEN, x + 12, y + 56),
               new Bamboo(RED, x + 32, y + 4),
               new Bamboo(BLUE, x + 32, y + 30),
               new Bamboo(BLUE, x + 32, y + 56),
               new Bamboo(GREEN, x + 52, y + 30),
               new Bamboo(GREEN, x + 52, y + 56)
            };
            break;
            
         case 8:
            bambooPoles = new Bamboo[]{
               new Bamboo(GREEN, x + 12, y + 12),
               new Bamboo(GREEN, x + 24, y + 16, -45),
               new Bamboo(GREEN, x + 40, y + 16, 45),
               new Bamboo(GREEN, x + 52, y + 12),
               new Bamboo(BLUE, x + 12, y + 48),
               new Bamboo(BLUE, x + 24, y + 44, 45),
               new Bamboo(BLUE, x + 40, y + 44, -45),
               new Bamboo(BLUE, x + 52, y + 48)
            };
            break;
            
         case 9:
            bambooPoles = new Bamboo[]{
               new Bamboo(RED, x + 12, y + 4),
               new Bamboo(RED, x + 12, y + 30),
               new Bamboo(RED, x + 12, y + 56),
               new Bamboo(BLUE, x + 32, y + 4),
               new Bamboo(BLUE, x + 32, y + 30),
               new Bamboo(BLUE, x + 32, y + 56),
               new Bamboo(GREEN, x + 52, y + 4),
               new Bamboo(GREEN, x + 52, y + 30),
               new Bamboo(GREEN, x + 52, y + 56)
            };
            break;
      }
   }
   
   public void paintComponent(Graphics g){
      super.paintComponent(g);
      
      for(Bamboo b : bambooPoles){
         if(b != null)
            b.draw(g);
      }
   }
   
   public String toString(){
      return "Bamboo " + rank;
   }
   
   public static void main(String[] args){
      JFrame frame = new JFrame();
      
      frame.setLayout(new FlowLayout());
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setTitle("Bamboo Tiles");
      
      frame.add(new BambooTile(2));
      frame.add(new BambooTile(3));
      frame.add(new BambooTile(4));
      frame.add(new BambooTile(5));
      frame.add(new BambooTile(6));
      frame.add(new BambooTile(7));
      frame.add(new BambooTile(8));
      frame.add(new BambooTile(9));
      
      frame.pack();
      frame.setVisible(true);
   }
   
   protected class Bamboo{
      private Color color;
      private int x, y, rotation;
      
      public Bamboo(Color color, int x, int y, /*int length,*/ int rotation){
         this.color = color;
         this.x = x;
         this.y = y;
         this.rotation = rotation % 360;
      }
      
      public Bamboo(Color color, int x, int y/*, int length*/){
         this(color, x, y, 0);
      }
      
      public void draw(Graphics g){
         Graphics2D g2d = (Graphics2D)g;
         g2d.rotate(Math.toRadians(-rotation), x + 4, y + 12);
         g2d.setColor(color);
         
         //Shaft
         g2d.fillRect(x + 2, y + 1, 4, 22);
         
         //Highlight
         g2d.setColor(Color.WHITE);
         g2d.drawLine(x + 4, y + 0, x + 4, y + 24);
         
         //Knobs
         g2d.setColor(color);
         g2d.fillOval(x + 0, y + 0, 8, 4);
         g2d.fillOval(x + 0, y + 10, 8, 4);
         g2d.fillOval(x + 0, y + 20, 8, 4);
         
         g2d.rotate(Math.toRadians(rotation), x + 4, y + 12);
      }
   }
}
