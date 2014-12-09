/*Jeremy Johnson
 *CS 3230 - TR 9:30 AM
 *Lab 8: Shadow.java
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Shadow extends JPanel{
   public static final int WIDTH = 72, HEIGHT = 84, DEPTH = 16;
   private Polygon shadow;
   
   public Shadow(int x, int y){
      setPreferredSize(new Dimension(WIDTH + 17, HEIGHT + 17));
      setSize(new Dimension(WIDTH + 17, HEIGHT + 17));
      setLocation(x, y);
      setOpaque(false);
      
      shadow = new Polygon();
      shadow.addPoint(0, DEPTH);
      shadow.addPoint(DEPTH, 0);
      shadow.addPoint(DEPTH + WIDTH, 0);
      shadow.addPoint(DEPTH + WIDTH, HEIGHT);
      shadow.addPoint(WIDTH, DEPTH + HEIGHT);
      shadow.addPoint(0, DEPTH + HEIGHT);
      
      /*shadow.addPoint(x + 0, y + 0);
      shadow.addPoint(x + WIDTH, y + 0);
      shadow.addPoint(x + WIDTH, y + HEIGHT);
      shadow.addPoint(x + 0, y + HEIGHT);*/
   }
   
   public void paintComponent(Graphics g){
      super.paintComponent(g);
      Graphics2D g2d = (Graphics2D)g;
      
      g2d.setColor(Color.BLACK);
      
      Composite cOld = g2d.getComposite();
      //Composite cNew = ((AlphaComposite)cOld).derive(0.5f);
      Composite cNew = ((AlphaComposite)cOld).derive(0.125f);
      
      g2d.setComposite(cNew);
      //int[] x = {DEPTH, DEPTH + WIDTH, DEPTH + WIDTH, 50};
      //int[] y = {0, 0, HEIGHT, DEPTH + HEIGHT};
      //Polygon shadow = new Polygon(x, y, 4);
      
      g2d.setClip(shadow);
      g2d.fillPolygon(shadow);
      g2d.setComposite(cOld);
   }
   
   public static void main(String[] args){
      //new Lab5();
      new Mahjong();
   }
}
