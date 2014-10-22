/*Jeremy Johnson
 *CS 3230 - TR 9:30 AM
 *Lab 5: PictureTile.java
 */

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public abstract class PictureTile extends Tile{
   private String name;
   private BufferedImage img;
   
   public PictureTile(String name){
      this.name = name;
      setToolTipText(toString());
      
      try{
         img = ImageIO.read(new File("./images/" + name + ".png"));
      }catch(Exception ex){
         ex.printStackTrace();
      }
   }
   
   public String toString(){
      return name;
   }
   
   public void paintComponent(Graphics g){
      super.paintComponent(g);
      
      g.drawImage(img, x + (width - img.getWidth()) / 2, y + (height - img.getHeight()) / 2, null);
   }
   
   public static void main(String[] args){
      JFrame frame = new JFrame();
      
      frame.setLayout(new FlowLayout());
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setTitle("Picture Tiles");
      
      frame.add(new Bamboo1Tile());
      
      frame.add(new FlowerTile("Chrysanthemum"));
      frame.add(new FlowerTile("Orchid"));
      frame.add(new FlowerTile("Plum"));
      frame.add(new FlowerTile("Bamboo"));
      
      frame.add(new SeasonTile("Spring"));
      frame.add(new SeasonTile("Summer"));
      frame.add(new SeasonTile("Fall"));
      frame.add(new SeasonTile("Winter"));
      
      frame.pack();
      frame.setVisible(true);
   }
}
