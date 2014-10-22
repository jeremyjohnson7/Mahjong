/*Jeremy Johnson
 *CS 3230 - TR 9:30 AM
 *Lab 5: Tile.java
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public abstract class Tile extends JPanel{
   protected final Color RED = new Color(192, 0, 0);
   protected final Color GREEN = new Color(0, 192, 0);
   protected final Color BLUE = new Color(0, 0, 192);
   
   protected final int x = 0, y = 16, width = 72, height = 84;
   private Block front, back;
   
   public Tile(){
      setToolTipText(toString());
      setPreferredSize(new Dimension(width + 17, height + 17));
      
      front = new Block(Color.WHITE, 0, 8, width, height, 8);
      back = new Block(GREEN, 8, 0, width, height, 8);
   }
   
   public boolean matches(Tile t){
      return getClass() == t.getClass();
   }
   
   public void paintComponent(Graphics g){
      super.paintComponent(g);
      
      back.draw(g);
      front.draw(g);
   }
   
   protected class Block{
      private Color color;
      private GradientPaint gradient;
      private Polygon side, end;
      private Rectangle face;
      
      public Block(Color color, int x, int y, int width, int height, int depth){
         this.color = color;
         
         if(color == Color.WHITE)
            this.gradient = new GradientPaint(x, y, this.color, x + width, y + height, new Color(224, 224, 224));
         else
            this.gradient = new GradientPaint(x, y, color.brighter(), x + width, y + height, color.darker());
         
         side = new Polygon();
         side.addPoint(x, y + depth);
         side.addPoint(x + depth, y);
         side.addPoint(x + width + depth, y);
         side.addPoint(x, y + width + depth);
         
         end = new Polygon();
         end.addPoint(x + width, y + depth);
         end.addPoint(x + width + depth, y);
         end.addPoint(x + width + depth, y + height);
         end.addPoint(x + width, y + height + depth);
         
         face = new Rectangle(x, y + depth, width, height);
      }
      
      public void draw(Graphics g){
         Graphics2D g2d = (Graphics2D)g;
         
         //Draw the side
         g2d.setPaint(gradient);
         g2d.fillPolygon(side);
         g2d.setColor(Color.BLACK);
         g2d.drawPolygon(side);
         
         //Draw the end
         g2d.setPaint(gradient);
         g2d.fillPolygon(end);
         g2d.setColor(Color.BLACK);
         g2d.drawPolygon(end);
         
         //Draw the face
         g2d.setPaint(gradient);
         g2d.fillRect(face.x, face.y, face.width, face.height);
         g2d.setColor(Color.BLACK);
         g2d.drawRect(face.x, face.y, face.width, face.height);
      }
   }
   
   public String toString(){
      return getClass().getName();
   }
   
   /*public static void main(String[] args){
      JFrame frame = new JFrame();
      
      frame.setLayout(new FlowLayout());
      //frame.setSize(new Dimension(800, 600));
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setTitle("Tile");
      
      frame.add(new Tile());
      
      frame.pack();
      frame.setVisible(true);
   }*/
   
   public static void main(String[] args){
      new Lab5();
   }
}
