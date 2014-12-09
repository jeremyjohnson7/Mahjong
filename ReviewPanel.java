/*Jeremy Johnson
 *CS 3230 - TR 9:30 AM
 *Lab 8: ReviewPanel.java
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class ReviewPanel extends JPanel{
   public ReviewPanel(final Stack<Tile> undoStack){
      setPreferredSize(new Dimension(192, (undoStack.peek().getHeight()
         + ((FlowLayout)getLayout()).getVgap()) * (undoStack.size() / 2)
         + ((FlowLayout)getLayout()).getVgap()));
      
      Object[] tiles = undoStack.toArray();
      
      for(int x = tiles.length - 1; x >= 0; x--)
         add(Tile.copy((Tile)tiles[x]));
      
      /*for(Tile t : undoStack)
         add(Tile.copy(t));*/
   }
   
   public void paintComponent(Graphics g){
      super.paintComponent(g);
   }
   
   public static void main(String[] args){
      new Mahjong();
   }
}
