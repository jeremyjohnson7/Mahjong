/*Jeremy Johnson
 *CS 3230 - TR 9:30 AM
 *Lab 8: CharacterTile.java
 */

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class CharacterTile extends Tile{
   protected char symbol;
   
   protected static Map<Character, String> charmap;
   static{
      charmap = new HashMap<>(16);
      charmap.put('1', "\u4e00");
      charmap.put('2', "\u4e8c");
      charmap.put('3', "\u4e09");
      charmap.put('4', "\u56db");
      charmap.put('5', "\u4e94");
      charmap.put('6', "\u516d");
      charmap.put('7', "\u4e03");
      charmap.put('8', "\u516b");
      charmap.put('9', "\u4e5d");
      charmap.put('N', "\u5317");
      charmap.put('E', "\u6771");
      charmap.put('W', "\u897f");
      charmap.put('S', "\u5357");
      charmap.put('C', "\u4e2d");
      charmap.put('F', "\u767c");
   }
   
   public CharacterTile(char symbol){
      this.symbol = symbol;
      setToolTipText(toString());
   }
   
   public void paintComponent(Graphics g){
      super.paintComponent(g);
      
      FontMetrics fm = g.getFontMetrics();
      g.setColor(RED);
      g.drawString("" + symbol, x + WIDTH - fm.stringWidth("" + symbol) - 4, y + 16);
      
      g.setFont(new Font("serif", Font.BOLD, 48));
      fm = g.getFontMetrics();
      
      switch(symbol){
         default:
            g.setFont(new Font("serif", Font.BOLD, 32));
            fm = g.getFontMetrics();
            g.setColor(Color.BLACK);
            g.drawString(CharacterTile.charmap.get(symbol), x + (WIDTH - fm.stringWidth(CharacterTile.charmap.get(symbol))) / 2, y + 32);
            g.setColor(RED);
            g.drawString("\u842c", x + (WIDTH - fm.stringWidth(CharacterTile.charmap.get(symbol))) / 2, y + 70);
            break;
            
         case 'N': case 'E': case 'W': case 'S':
            g.setColor(Color.BLACK);
            g.drawString(CharacterTile.charmap.get(symbol), x + (WIDTH - fm.stringWidth(CharacterTile.charmap.get(symbol))) / 2, y + 58);
            break;
            
         case 'C':
            g.setColor(RED);
            g.drawString(CharacterTile.charmap.get(symbol), x + (WIDTH - fm.stringWidth(CharacterTile.charmap.get(symbol))) / 2, y + 58);
            break;
            
         case 'F':
            g.setColor(GREEN);
            g.drawString(CharacterTile.charmap.get(symbol), x + (WIDTH - fm.stringWidth(CharacterTile.charmap.get(symbol))) / 2, y + 58);
            break;
      }
   }
   
   public boolean matches(Tile t){
      return super.matches(t) && symbol == ((CharacterTile)t).symbol;
   }
   
   public String toString(){
      switch(symbol){
         default:
            return "Character " + symbol;
            
         case 'N':
            return "North Wind";
            
         case 'E':
            return "East Wind";
            
         case 'W':
            return "West Wind";
            
         case 'S':
            return "South Wind";
            
         case 'C':
            return "Red Dragon";
            
         case 'F':
            return "Green Dragon";
      }
   }
   
   public static void main(String[] args){
      JFrame  frame = new JFrame();
      JPanel  tiles = new JPanel();
      JScrollPane scroller = new JScrollPane(tiles);
      
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setTitle("Character Tiles");
      frame.add(scroller);
      
      //Try something like this if your tiles don't fit on the screen.
      //Replace "tile width" and "tile height" with your values.
      //scroller.setPreferredSize(new Dimension(8 * tile width, 40 + tile height));
      
      tiles.add(new CharacterTile('1'));
      tiles.add(new CharacterTile('2'));
      tiles.add(new CharacterTile('3'));
      tiles.add(new CharacterTile('4'));
      tiles.add(new CharacterTile('5'));
      tiles.add(new CharacterTile('6'));
      tiles.add(new CharacterTile('7'));
      tiles.add(new CharacterTile('8'));
      tiles.add(new CharacterTile('9'));
      tiles.add(new CharacterTile('N'));
      tiles.add(new CharacterTile('E'));
      tiles.add(new CharacterTile('W'));
      tiles.add(new CharacterTile('S'));
      tiles.add(new CharacterTile('C'));
      tiles.add(new CharacterTile('F'));
      
      frame.pack();
      frame.setVisible(true);
   }
}
