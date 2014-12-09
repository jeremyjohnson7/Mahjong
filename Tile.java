/*Jeremy Johnson
 *CS 3230 - TR 9:30 AM
 *Lab 8: Tile.java
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public abstract class Tile extends JPanel /*implements MouseListener*/{
   public static final int WIDTH = 72, HEIGHT = 84, DEPTH = 16;
   
   protected final static Color RED = new Color(192, 0, 0);
   protected final static Color GREEN = new Color(0, 192, 0);
   protected final static Color BLUE = new Color(0, 0, 192);
   protected final static Color WHITE = new Color(232, 232, 232);
   
   protected final int x = 0, y = 16;//, width = 72, height = 84;
   private Block front, back, selectedFront, highlightedFront;
      
   public int layer;
   public Tile up, nw, sw, ne, se;
   private boolean selected, highlighted;
   protected Shadow shadow = null;
   
   public static Tile copy(Tile t){
      switch(t.toString()){
         case "Character 1": return new CharacterTile('1');
         case "Character 2": return new CharacterTile('2');
         case "Character 3": return new CharacterTile('3');
         case "Character 4": return new CharacterTile('4');
         case "Character 5": return new CharacterTile('5');
         case "Character 6": return new CharacterTile('6');
         case "Character 7": return new CharacterTile('7');
         case "Character 8": return new CharacterTile('8');
         case "Character 9": return new CharacterTile('9');
         
         case "Circle 1": return new CircleTile(1);
         case "Circle 2": return new CircleTile(2);
         case "Circle 3": return new CircleTile(3);
         case "Circle 4": return new CircleTile(4);
         case "Circle 5": return new CircleTile(5);
         case "Circle 6": return new CircleTile(6);
         case "Circle 7": return new CircleTile(7);
         case "Circle 8": return new CircleTile(8);
         case "Circle 9": return new CircleTile(9);
         
         case "Bamboo 1": return new Bamboo1Tile();
         case "Bamboo 2": return new BambooTile(2);
         case "Bamboo 3": return new BambooTile(3);
         case "Bamboo 4": return new BambooTile(4);
         case "Bamboo 5": return new BambooTile(5);
         case "Bamboo 6": return new BambooTile(6);
         case "Bamboo 7": return new BambooTile(7);
         case "Bamboo 8": return new BambooTile(8);
         case "Bamboo 9": return new BambooTile(9);
         
         case "North Wind": return new CharacterTile('N');
         case "East Wind": return new CharacterTile('E');
         case "West Wind": return new CharacterTile('W');
         case "South Wind": return new CharacterTile('S');
         
         case "Red Dragon": return new CharacterTile('C');
         case "Green Dragon": return new CharacterTile('F');
         case "White Dragon": return new WhiteDragonTile();
         
         case "Chrysanthemum": return new FlowerTile("Chrysanthemum");
         case "Orchid": return new FlowerTile("Orchid");
         case "Plum": return new FlowerTile("Plum");
         case "Bamboo": return new FlowerTile("Bamboo");
         
         case "Spring": return new SeasonTile("Spring");
         case "Summer": return new SeasonTile("Summer");
         case "Fall": return new SeasonTile("Fall");
         case "Winter": return new SeasonTile("Winter");
         
         default: return new CircleTile(1);
      }
      
      //return null;
   }
   
   public Tile(){
      setToolTipText(toString());
      setPreferredSize(new Dimension(WIDTH + 17, HEIGHT + 17));
      setSize(new Dimension(WIDTH + 17, HEIGHT + 17));
      
      front = new Block(Color.WHITE, WHITE, 0, 8, WIDTH, HEIGHT, DEPTH / 2);
      back = new Block(GREEN, GREEN.darker(), 8, 0, WIDTH, HEIGHT, DEPTH / 2);
      selectedFront = new Block(new Color(192, 192, 255), new Color(192, 192, 232), 0, 8, WIDTH, HEIGHT, DEPTH / 2);
      highlightedFront = new Block(new Color(160, 255, 160), new Color(160, 232, 160), 0, 8, WIDTH, HEIGHT, DEPTH / 2);
      //highlightedFront = new Block(new Color(255, 255, 128), new Color(232, 232, 128), 0, 8, WIDTH, HEIGHT, DEPTH / 2);
      
      //addMouseListener(this);
   }
   
   public boolean matches(Tile t){
      return getClass() == t.getClass();
   }
   
   public void paintComponent(Graphics g){
      super.paintComponent(g);
      
      //new Shadow(x, y).draw(g);
      back.draw(g);
      
      if(selected)
         selectedFront.draw(g);
      else if(highlighted)
         highlightedFront.draw(g);
      else
         front.draw(g);
   }
   
   protected class Block{
      private GradientPaint gradient;
      private Polygon side, end;
      private Rectangle face;
      private Color color;
      
      public Block(Color color1, Color color2, int x, int y, int width, int height, int depth){
         this.gradient = new GradientPaint(x, y, color1, x + width, y + height, color2);
         
         /*if(color == Color.WHITE)
            this.gradient = new GradientPaint(x, y, this.color, x + width, y + height, new Color(232, 232, 232));
         else
            this.gradient = new GradientPaint(x, y, color, x + width, y + height, color.darker());*/
         
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
   
   public void setSelected(boolean selected){
      this.selected = selected;
   }
   
   public boolean isSelected(){
      return selected;
   }
   
   public void setHighlighted(boolean highlighted){
      this.highlighted = highlighted;
   }
   
   public boolean isHighlighted(){
      return highlighted;
   }
   
   public void setVisible(boolean visible){
      super.setVisible(visible);
      setSelected(false);
      setHighlighted(false);
      
      if(shadow != null)
         shadow.setVisible(visible);
   }
   
   public boolean isOpen(){
      //return up == null && ((nw == null && sw == null) || (ne == null && se == null));
      //return !(up.isVisible() || ((nw.isVisible() || sw.isVisible()) && (ne.isVisible() || se.isVisible())));
      
      return isGone(up) && ((isGone(nw) && isGone(sw)) || (isGone(ne) && isGone(se)));
   }
   
   private boolean isGone(Tile t){
      return t == null || !t.isVisible();
   }
   
   public Shadow getShadow(){
      return shadow;
   }
   
   public void setShadow(Shadow s){
      shadow = s;
   }
   
   public String toString(){
      return getClass().getName();
   }
   
   //Listeners
   /*public void mouseClicked(MouseEvent e){
      //if(e.getSource)
      if(isOpen()){
         setVisible(false);
         getParent().repaint();
      }
   }
   
   public void mouseEntered(MouseEvent e){
   }
   
   public void mouseExited(MouseEvent e){
   }
   
   public void mousePressed(MouseEvent e){
   }
   
   public void mouseReleased(MouseEvent e){
   }*/
   
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
      //new Lab5();
      new Mahjong();
   }
}
