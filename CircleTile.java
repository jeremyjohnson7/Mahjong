/*Jeremy Johnson
 *CS 3230 - TR 9:30 AM
 *Lab 5: CircleTile.java
 */

import java.awt.*;
import javax.swing.*;

public class CircleTile extends RankTile{
   protected Circle[] circles;
   protected int radius;
   
   public CircleTile(int rank){
      super(rank);
      setToolTipText(toString());
      
      switch(rank){
         case 1:
            radius = 27;
            circles = new Circle[]{
               new Pancake(x + 36, y + 42, radius)
            };
            break;
            
         case 2:
            radius = 16;
            circles = new Circle[]{
               new Circle(GREEN, x + 36, y + 23, radius),
               new Circle(BLUE, x + 36, y + 61, radius)
            };
            break;
            
         case 3:
            radius = 13;
            circles = new Circle[]{
               new Circle(BLUE, x + 20, y + 17, radius),
               new Circle(RED, x + 36, y + 42, radius),
               new Circle(GREEN, x + 52, y + 67, radius)
            };
            break;
            
         case 4:
            radius = 12;
            circles = new Circle[]{
               new Circle(BLUE, x + 22, y + 20, radius),
               new Circle(GREEN, x + 50, y + 20, radius),
               new Circle(GREEN, x + 22, y + 64, radius),
               new Circle(BLUE, x + 50, y + 64, radius)
            };
            break;
            
         case 5:
            radius = 12;
            circles = new Circle[]{
               new Circle(BLUE, x + 22, y + 20, radius),
               new Circle(GREEN, x + 50, y + 20, radius),
               new Circle(RED, x + 36, y + 42, radius),
               new Circle(GREEN, x + 22, y + 64, radius),
               new Circle(BLUE, x + 50, y + 64, radius)
            };
            break;
            
         case 6:
            radius = 12;
            circles = new Circle[]{
               new Circle(GREEN, x + 22, y + 16, radius),
               new Circle(RED, x + 22, y + 42, radius),
               new Circle(RED, x + 22, y + 68, radius),
               new Circle(GREEN, x + 50, y + 16, radius),
               new Circle(RED, x + 50, y + 42, radius),
               new Circle(RED, x + 50, y + 68, radius)
            };
            break;
            
         case 7:
            radius = 10;
            circles = new Circle[]{
               new Circle(GREEN, x + 14, y + 12, radius),
               new Circle(GREEN, x + 36, y + 22, radius),
               new Circle(GREEN, x + 58, y + 32, radius),
               new Circle(RED, x + 22, y + 52, radius),
               new Circle(RED, x + 50, y + 52, radius),
               new Circle(RED, x + 22, y + 72, radius),
               new Circle(RED, x + 50, y + 72, radius)
            };
            break;
            
         case 8:
            radius = 10;
            circles = new Circle[]{
               new Circle(BLUE, x + 22, y + 12, radius),
               new Circle(BLUE, x + 50, y + 12, radius),
               new Circle(BLUE, x + 22, y + 32, radius),
               new Circle(BLUE, x + 50, y + 32, radius),
               new Circle(BLUE, x + 22, y + 52, radius),
               new Circle(BLUE, x + 50, y + 52, radius),
               new Circle(BLUE, x + 22, y + 72, radius),
               new Circle(BLUE, x + 50, y + 72, radius)
            };
            break;
            
         case 9:
            radius = 10;
            circles = new Circle[]{
               new Circle(GREEN, x + 14, y + 17, radius),
               new Circle(RED, x + 14, y + 42, radius),
               new Circle(BLUE, x + 14, y + 67, radius),
               new Circle(GREEN, x + 36, y + 17, radius),
               new Circle(RED, x + 36, y + 42, radius),
               new Circle(BLUE, x + 36, y + 67, radius),
               new Circle(GREEN, x + 58, y + 17, radius),
               new Circle(RED, x + 58, y + 42, radius),
               new Circle(BLUE, x + 58, y + 67, radius)
            };
            break;
      }
   }
   
   public void paintComponent(Graphics g){
      super.paintComponent(g);
      
      for(Circle c : circles){
         if(c != null)
            c.draw(g);
      }
   }
   
   public String toString(){
      return "Circle " + rank;
   }
   
   public static void main(String[] args){
      JFrame frame = new JFrame();
      
      frame.setLayout(new FlowLayout());
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setTitle("Circle Tiles");
      
      frame.add(new CircleTile(1));
      frame.add(new CircleTile(2));
      frame.add(new CircleTile(3));
      frame.add(new CircleTile(4));
      frame.add(new CircleTile(5));
      frame.add(new CircleTile(6));
      frame.add(new CircleTile(7));
      frame.add(new CircleTile(8));
      frame.add(new CircleTile(9));
      
      frame.pack();
      frame.setVisible(true);
   }
   
   protected class Circle{
      protected Color color;
      protected int x, y, radius;
      
      public Circle(Color color, int x, int y, int radius){
         this.color = color;
         this.x = x;
         this.y = y;
         this.radius = radius;
      }
      
      public void draw(Graphics g){
         Graphics2D g2d = (Graphics2D)g;
         
         g2d.setColor(color);
         g2d.fillOval(x - radius, y - radius, radius * 2, radius * 2);
         
         //Embellishments
         g2d.setColor(Color.WHITE);
         g2d.rotate(Math.toRadians(45), x, y);
         g2d.fillOval(x - radius / 8, y - radius + radius / 8, radius / 4, radius - radius / 4);
         
         for(int i = 0; i < 3; i++){
            g2d.rotate(Math.toRadians(90), x, y);
            g2d.fillOval(x - radius / 8, y - radius + radius / 8, radius / 4, radius - radius / 4);
         }
         
         g2d.rotate(Math.toRadians(-315), x, y);
      }
   }
   
   protected class Pancake extends Circle{
      protected int radius;
      
      public Pancake(int x, int y, int radius){
         super(RED, x, y, radius / 2);
         this.radius = radius;
      }
      
      public void draw(Graphics g){
         Graphics2D g2d = (Graphics2D)g;
         
         g2d.setColor(GREEN);
         g2d.fillOval(x - radius, y - radius, radius * 2, radius * 2);
         
         //Embellishments
         g2d.setColor(Color.WHITE);
         g2d.rotate(Math.toRadians(11.25), x, y);
         g2d.fillOval(x - radius / 6, y - radius + radius / 5, radius / 6, radius / 6);
         
         for(int i = 0; i < 15; i++){
            g2d.rotate(Math.toRadians(22.5), x, y);
            g2d.fillOval(x - radius / 6, y - radius + radius / 5, radius / 6, radius / 6);
         }
         
         g2d.rotate(Math.toRadians(-348.75), x, y);
         super.draw(g2d);
      }
   }
}
