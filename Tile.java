/*Jeremy Johnson
 *CS 3230 - TR 9:30 AM
 *Lab 7: Tile.java
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public abstract class Tile extends JPanel /*implements MouseListener*/{
   public static final int WIDTH = 72, HEIGHT = 84, DEPTH = 16;
   
   protected final Color RED = new Color(192, 0, 0);
   protected final Color GREEN = new Color(0, 192, 0);
   protected final Color BLUE = new Color(0, 0, 192);
   protected final Color WHITE = new Color(232, 232, 232);
   
   protected final int x = 0, y = 16;//, width = 72, height = 84;
   private Block front, back, selectedFront, highlightedFront;
      
   public int layer;
   public Tile up, nw, sw, ne, se;
   private boolean selected, highlighted;
   protected Shadow shadow = null;
   
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
