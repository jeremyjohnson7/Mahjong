/*Jeremy Johnson
 *CS 3230 - TR 9:30 AM
 *Lab 7: Mahjong.java
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Mahjong extends JFrame{
   private Tile selected = null;
   private boolean debug = true, tournament = false, sound = true;
   protected JMenuBar menuBar = new JMenuBar();
   protected MahjongPanel mahjongPanel = new MahjongPanel();
   protected JMenuItem undoMI, redoMI, soundMI;
   protected Random rand = new Random();
   
   public Mahjong(){
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setTitle("Mahjong");
      
      add(mahjongPanel);
      
      //Menu bar setup
      JMenu m;
      JMenuItem mi;
      
      //Game menu
      m = new JMenu("Game");
      m.setMnemonic(KeyEvent.VK_G);
      
      mi = new JMenuItem("Play");
      mi.setToolTipText("Play a new game");
      mi.setMnemonic(KeyEvent.VK_P);
      mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
      mi.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            mahjongPanel.newGame();
         }
      });
      m.add(mi);
      
      /*mi = new JMenuItem("Solvable Game");
      mi.setToolTipText("Play a new game which is guaranteed to be solvable");
      mi.setMnemonic(KeyEvent.VK_S);
      mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK | ActionEvent.SHIFT_MASK));
      mi.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            mahjongPanel.newSolvableGame();
         }
      });
      m.add(mi);*/
      
      mi = new JMenuItem("Restart");
      mi.setToolTipText("Start this game over");
      mi.setMnemonic(KeyEvent.VK_R);
      mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
      mi.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            mahjongPanel.startOver();
         }
      });
      m.add(mi);
      
      m.addSeparator();
      
      mi = new JMenuItem("Numbered");
      mi.setToolTipText("Play a numbered (repeatable) game");
      mi.setMnemonic(KeyEvent.VK_N);
      mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
      mi.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            mahjongPanel.newNumberedGame();
         }
      });
      m.add(mi);
      
      mi = new JMenuItem("Tournament");
      mi.setToolTipText("Play in tournament mode");
      mi.setMnemonic(KeyEvent.VK_T);
      mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
      mi.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            //mahjongPanel.startOver();
         }
      });
      mi.setEnabled(false);
      m.add(mi);
      
      menuBar.add(m);
      
      //Sound menu
      m = new JMenu("Sound");
      m.setMnemonic(KeyEvent.VK_S);
      
      mi = new JMenuItem(sound ? "On" : "Off");
      soundMI = mi;
      mi.setToolTipText("Turn the sound on or off");
      mi.setMnemonic(KeyEvent.VK_O);
      mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0));
      mi.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            sound = !sound;
            soundMI.setText(sound ? "On" : "Off");
         }
      });
      mi.setEnabled(false);
      m.add(mi);
      
      menuBar.add(m);
      
      //Move menu
      m = new JMenu("Move");
      m.setMnemonic(KeyEvent.VK_M);
      
      mi = new JMenuItem("Undo");
      mi.setToolTipText("Undo the last move");
      mi.setMnemonic(KeyEvent.VK_U);
      mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
      mi.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            mahjongPanel.undo();
         }
      });
      mi.setEnabled(false);
      undoMI = mi;
      m.add(mi);
      
      mi = new JMenuItem("Redo");
      mi.setToolTipText("Redo the last move");
      mi.setMnemonic(KeyEvent.VK_R);
      mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
      mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK | ActionEvent.SHIFT_MASK));
      mi.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            mahjongPanel.redo();
         }
      });
      mi.setEnabled(false);
      redoMI = mi;
      m.add(mi);
      
      m.addSeparator();
      
      mi = new JMenuItem("Find match");
      mi.setToolTipText("Find matching tiles");
      mi.setMnemonic(KeyEvent.VK_F);
      mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
      mi.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            mahjongPanel.findMatch();
         }
      });
      m.add(mi);
      
      /*mi = new JMenuItem("Find All");
      mi.setToolTipText("Find all matching tiles");
      mi.setMnemonic(KeyEvent.VK_A);
      mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK | ActionEvent.SHIFT_MASK));
      mi.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            mahjongPanel.findAllMatches();
         }
      });
      m.add(mi);*/
      
      menuBar.add(m);
      
      //Help menu
      m = new JMenu("Help");
      m.setMnemonic(KeyEvent.VK_H);
      
      mi = new JMenuItem("Operation");
      mi.setToolTipText("Undo the last move");
      mi.setMnemonic(KeyEvent.VK_O);
      mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
      mi.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            //mahjongPanel.undo();
         }
      });
      mi.setEnabled(false);
      m.add(mi);
      
      mi = new JMenuItem("Game Rules");
      mi.setToolTipText("Learn how to play the game");
      mi.setMnemonic(KeyEvent.VK_R);
      mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, ActionEvent.SHIFT_MASK));
      mi.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            //mahjongPanel.undo();
         }
      });
      mi.setEnabled(false);
      m.add(mi);
      
      menuBar.add(m);
      
      //Debug menu
      m = new JMenu("Debug");
      m.setMnemonic(KeyEvent.VK_D);
      
      mi = new JCheckBoxMenuItem("Enforce Rules");
      mi.setToolTipText("Enforce Rules");
      mi.setMnemonic(KeyEvent.VK_E);
      mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0));
      mi.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            debug = !debug;
         }
      });
      mi.setSelected(!debug);
      m.add(mi);
      
      m.addSeparator();
      
      mi = new JMenuItem("Repaint");
      mi.setToolTipText("Repaint");
      mi.setMnemonic(KeyEvent.VK_P);
      mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
      mi.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            repaint();
         }
      });
      mi.setSelected(debug);
      m.add(mi);
      
      mi = new JMenuItem("Revalidate");
      mi.setToolTipText("Revalidate");
      mi.setMnemonic(KeyEvent.VK_V);
      mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, ActionEvent.CTRL_MASK));
      mi.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            revalidate();
         }
      });
      mi.setSelected(debug);
      m.add(mi);
      
      /*m.addSeparator();
      
      mi = new JMenuItem("Solve");
      mi.setToolTipText("Solve");
      mi.setMnemonic(KeyEvent.VK_S);
      mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0));
      mi.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            mahjongPanel.solve(true);
         }
      });
      m.add(mi);*/
      
      menuBar.add(m);
      
      //setSize(1144, 768);
      setJMenuBar(menuBar);
      setLookAndFeel();
      pack();
      center();
      setVisible(true);
   }
   
   public class MahjongPanel extends JPanel implements MouseListener{
      /*Location algorithm
       *x = row * FACE +/- layer * EDGE
       *y = col * FACE +/- layer * EDGE
       *setLocation(x, y)
       *
       *x = row * WIDTH +/- layer * DEPTH
       *y = col * HEIGHT +/- layer * DEPTH
       *setLocation(x, y)
       */
      
      long seed = System.currentTimeMillis() % 100000;
      ArrayList<Tile> tiles = getTiles(seed);
      Stack<Tile> undoStack = new Stack<>();
      Stack<Tile> redoStack = new Stack<>();
      Image img = new ImageIcon(getClass().getResource("images/dragon_bg.png")).getImage();
      String message = "";
      
      public MahjongPanel(){
         setLayout(null);
         setBackground(new Color(254, 208, 45));
         
         int[] layoutDimensions = getBoardLayout()[144];
         setPreferredSize(new Dimension(Tile.WIDTH * layoutDimensions[0] + Tile.DEPTH * 3, Tile.HEIGHT * layoutDimensions[1] + Tile.DEPTH * 3));
         
         initGame();
         
         repaint();
         revalidate();
         //Collections.shuffle(List<T>, Random)
         //Random, SecureRandom
      }
      
      public void paintComponent(Graphics g){
         super.paintComponent(g);
         g.drawImage(img, (getWidth() - img.getWidth(null)) / 2, (getHeight() - img.getHeight(null)) / 2, null);
         g.drawString("" + seed, 8, 16);
         g.drawString(message, 8, 32);
      }
      
      public void initGame(){
         Queue<Tile> tileQueue = new LinkedList<>();
         Queue<Shadow> shadowQueue = new LinkedList<>();
         int layer = -128;
         
         removeAll();
         message = "";
         
         for(Tile t : tiles){
            if(t.layer != layer){
               for(; tileQueue.peek() != null; add(tileQueue.remove()));
               for(; shadowQueue.peek() != null; add(shadowQueue.remove()));
               layer = t.layer;
            }
            
            //System.out.println(t);
            t.addMouseListener(this);
            tileQueue.add(t);
            
            Shadow s = new Shadow(t.getX() + Tile.DEPTH, t.getY() + Tile.DEPTH);
            t.setShadow(s);
            shadowQueue.add(s);
         }
         
         for(; tileQueue.peek() != null; add(tileQueue.remove()));
         for(; shadowQueue.peek() != null; add(shadowQueue.remove()));
      }
      
      public void undo(){
         if(!debug){
            Tile t = undoStack.pop();
            t.setSelected(false);
            t.setVisible(true);
            redoStack.push(t);
            
            t = undoStack.pop();
            t.setSelected(false);
            t.setVisible(true);
            redoStack.push(t);
         }else{
            Tile t = undoStack.pop();
            t.setSelected(false);
            t.setVisible(true);
            redoStack.push(t);
         }
         
         if(undoStack.size() == 0)
            undoMI.setEnabled(false);
         
         redoMI.setEnabled(true);
         message = "";
         
         revalidate();
         repaint();
      }
      
      public void redo(){
         if(!debug){
            Tile t = redoStack.pop();
            t.setSelected(false);
            t.setVisible(false);
            undoStack.push(t);
            
            t = redoStack.pop();
            t.setSelected(false);
            t.setVisible(false);
            undoStack.push(t);
         }else{
            Tile t = redoStack.pop();
            t.setSelected(false);
            t.setVisible(false);
            undoStack.push(t);
         }
         
         if(redoStack.size() == 0)
            redoMI.setEnabled(false);
         
         undoMI.setEnabled(true);
         
         if(!hasTiles()){
            message = "Congratulations";
            repaint();
            fireworks();
         }else if(!hasMatch()){
            message = "Game Over";
         }
         
         revalidate();
         repaint();
      }
      
      public void solve(boolean calledByUser){
         while(hasMatch()){
            for(Tile t : tiles){
               for(Tile tt : tiles){
                  if(t.isVisible() && t.isOpen() && tt.isVisible() && tt.isOpen() && t != tt && t.matches(tt)){
                     t.setVisible(false);
                     undoStack.add(t);
                     tt.setVisible(false);
                     undoStack.add(tt);
                     undoMI.setEnabled(true);
                  }
               }
            }
         }
         
         if(!hasTiles()){
            message = "Congratulations";
            repaint();
            
            if(calledByUser)
               fireworks();
         }else{
            message = "Game Over";
         }
         
         repaint();
      }
      
      public boolean hasTiles(){
         for(Tile t : tiles){
            if(t.isVisible())
               return true;
         }
         
         return false;
      }
      
      public boolean hasMatch(){
         for(Tile t : tiles){
            for(Tile tt : tiles){
               if(t.isVisible() && t.isOpen() && tt.isVisible() && tt.isOpen() && t != tt && t.matches(tt))
                  return true;
            }
         }
         
         return false;
      }
      
      public boolean findMatch(Tile t){
         boolean found = false;
         
         for(Tile tt : tiles){
            if(tt.isVisible() && tt.isOpen() && t != tt && t.matches(tt)){
               tt.setHighlighted(true);
               found = true;
            }else{
               tt.setHighlighted(false);
            }
         }
         
         t.setHighlighted(found);
         repaint();
         return found;
      }
      
      public void findMatch(){
         if(selected != null && findMatch(selected))
            return;
         
         for(Tile t : tiles){
            t.setSelected(false);
            t.setHighlighted(false);
         }
         
         for(Tile t : tiles){
            for(Tile tt : tiles){
               if(t.isVisible() && t.isOpen() && tt.isVisible() && tt.isOpen() && t != tt && t.matches(tt)){
                  //System.out.println("Hint: " + t + " <--> " + tt);
                  t.setHighlighted(true);
                  tt.setHighlighted(true);
                  repaint();
                  return;
               }
            }
         }
      }
      
      public void findAllMatches(){
         for(Tile t : tiles){
            t.setSelected(false);
            t.setHighlighted(false);
         }
         
         for(Tile t : tiles){
            for(Tile tt : tiles){
               if(t.isVisible() && t.isOpen() && tt.isVisible() && tt.isOpen() && t != tt && t.matches(tt)){
                  //System.out.println("Hint: " + t + " <--> " + tt);
                  t.setHighlighted(true);
                  tt.setHighlighted(true);
               }
            }
         }
         
         repaint();
      }
      
      public void startOver(){
         for(Tile t : tiles)
            t.setVisible(true);
         
         undoStack.clear();
         undoMI.setEnabled(false);
         redoStack.clear();
         redoMI.setEnabled(false);
         
         message = "";
         
         revalidate();
         repaint();
      }
      
      public void newGame(){
         for(Tile t : tiles)
            remove(t);
         
         undoStack.clear();
         undoMI.setEnabled(false);
         redoStack.clear();
         redoMI.setEnabled(false);
         
         seed = new Random().nextInt(100000);
         tiles = getTiles(seed);
         
         /*for(Tile t : tiles){
            t.addMouseListener(this);
            add(t);
         }*/
         
         initGame();
         
         revalidate();
         repaint();
      }
      
      public void newNumberedGame(){
         String str = JOptionPane.showInputDialog(this, "Please enter a five digit number", "Numbered Game", JOptionPane.PLAIN_MESSAGE);
         
         while(!str.matches("\\d{1,5}")){
            str = JOptionPane.showInputDialog(this, "Please enter a five digit number", "Numbered Game", JOptionPane.PLAIN_MESSAGE);;
         }
         
         newNumberedGame(Integer.parseInt(str));
      }
      
      public void newNumberedGame(long seed){
         for(Tile t : tiles)
            remove(t);
         
         undoStack.clear();
         undoMI.setEnabled(false);
         redoStack.clear();
         redoMI.setEnabled(false);
         
         this.seed = seed;
         tiles = getTiles(seed);
         
         initGame();
         
         revalidate();
         repaint();
      }
      
      public void newSolvableGame(){
         do{
            removeAll();         
            seed = new Random().nextInt(100000);
            tiles = getTiles(seed);
            initGame();
            solve(false);
         }while(hasTiles());
         
         startOver();
         
         revalidate();
         repaint();
      }
      
      public void fireworks(){
         //TODO: Fireworks display
      }
      
      //Listeners
      public void mousePressed(MouseEvent e){
         Object obj = e.getSource();
         if(!(obj instanceof Tile))
            return;
         
         Tile src = (Tile)obj;
         
         if(debug){
            src.setVisible(false);
            undoStack.push(src);
            undoMI.setEnabled(true);
            redoStack.clear();
            redoMI.setEnabled(false);
            repaint();
         }else if((e.getModifiers() & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK){
            for(Tile t: tiles){
               t.setHighlighted(false);
               t.setSelected(false);
            }
            
            if(src.isOpen() && findMatch(src)){
               if(selected != null)
                  selected.setSelected(false);
               
               selected = src;
               src.setSelected(true);
               repaint();
            }else if(src.isOpen()){
               selected = src;
               src.setSelected(true);
               repaint();
            }
         }else if((e.getModifiers() & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK){
            if(src.isOpen()){
               if(selected != null && src != selected && src.matches(selected)){
                  //Remove the tile from the board
                  src.setVisible(false);
                  undoStack.push(src);
                  selected.setVisible(false);
                  undoStack.push(selected);
                  selected.setSelected(false);
                  selected = null;
                  undoMI.setEnabled(!tournament);
                  redoStack.clear();
                  redoMI.setEnabled(false);
                  
                  for(Tile t: tiles)
                     t.setHighlighted(false);
                  
                  if(!hasTiles()){
                     message = "Congratulations";
                     repaint();
                     fireworks();
                  }else if(!hasMatch()){
                     message = "Game Over";
                  }
                  
                  revalidate();
                  repaint();
               }else if(src == selected){
                  src.setSelected(false);
                  selected = null;
                  repaint();
               }else{
                  if(selected != null)
                     selected.setSelected(false);
                  
                  selected = src;
                  src.setSelected(true);
                  repaint();
               }
            }
         }
      }
      
      public void mouseReleased(MouseEvent e){
      }
      
      public void mouseEntered(MouseEvent e){
      }
      
      public void mouseExited(MouseEvent e){
      }
      
      public void mouseClicked(MouseEvent e){
      }
   }
   
   protected ArrayList<Tile> getTiles(long seed){
      ArrayList<Tile> tiles = new ArrayList<Tile>();
      
      //Generate tiles
      for(int x = 0; x < 4; x++){
         for(char y = '1'; y <= '9'; y++)
            tiles.add(new CharacterTile(y));
         
         tiles.add(new CircleTile(1));
         tiles.add(new Bamboo1Tile());
         
         for(int y = 2; y <= 9; y++){
            tiles.add(new CircleTile(y));
            tiles.add(new BambooTile(y));
         }
         
         tiles.add(new CharacterTile('N'));
         tiles.add(new CharacterTile('S'));
         tiles.add(new CharacterTile('E'));
         tiles.add(new CharacterTile('W'));
         
         tiles.add(new CharacterTile('C'));
         tiles.add(new CharacterTile('F'));
         tiles.add(new WhiteDragonTile());
      }
      
      tiles.add(new FlowerTile("Chrysanthemum"));
      tiles.add(new FlowerTile("Orchid"));
      tiles.add(new FlowerTile("Plum"));
      tiles.add(new FlowerTile("Bamboo"));
      
      tiles.add(new SeasonTile("Spring"));
      tiles.add(new SeasonTile("Summer"));
      tiles.add(new SeasonTile("Fall"));
      tiles.add(new SeasonTile("Winter"));
      
      //Put the tiles in random order
      Collections.shuffle(tiles, new Random(seed));
      
      //Adjust the layout of the tiles
      int[][] layout = getBoardLayout();
      final int X = 0, Y = 1, Z = 2, X_OFFSET = 3, Y_OFFSET = 4, UP = 5, NW = 6, SW = 7, NE = 8, SE = 9;
      int i = 0;
      
      for(Tile t : tiles){
         int[] arr = layout[i++];
         t.setLocation(16 + Tile.WIDTH * arr[X] - Tile.DEPTH * arr[Z] + (Tile.WIDTH / 2) * arr[X_OFFSET],
            16 + Tile.HEIGHT * arr[Y] + Tile.DEPTH * arr[Z] + (Tile.WIDTH / 2) * arr[Y_OFFSET]);
         t.setOpaque(false);
         
         t.up = (arr[UP] != -1) ? tiles.get(arr[UP]) : null;
         t.nw = (arr[NW] != -1) ? tiles.get(arr[NW]) : null;
         t.sw = (arr[SW] != -1) ? tiles.get(arr[SW]) : null;
         t.ne = (arr[NE] != -1) ? tiles.get(arr[NE]) : null;
         t.se = (arr[SE] != -1) ? tiles.get(arr[SE]) : null;
         t.layer = arr[Z];
      }
      
      return tiles;
   }
   
   protected static int[][] getBoardLayout(){
      final int UP = -1, NW = -1, SW = -1, NE = -1, SE = -1;
      
      int[][] layout = new int[][]{
         //x, y, z, xoffset, yoffset, above, northwest, southwest, northeast, southeast
         
         //4th layer
         {6, 3, 4,   1, 1,   UP, NW, SW, NE, SE},   //0
         
         //3rd layer
         {7, 3, 3,   0, 0,   0, 2, 2, NE, SE},   //1
         {6, 3, 3,   0, 0,   0, NW, SW, 1, 1},   //2
         
         {7, 4, 3,   0, 0,   0, 4, 4, NE, SE},   //3
         {6, 4, 3,   0, 0,   0, NW, SW, 3, 3},   //4
         
         //2nd layer
         {8, 2, 2,   0, 0,   UP, 6, 6, NE, SE},   //5
         {7, 2, 2,   0, 0,   UP, 7, 7, 5, 5},   //6
         {6, 2, 2,   0, 0,   UP, 8, 8, 6, 6},   //7
         {5, 2, 2,   0, 0,   UP, NW, SW, 7, 7},   //8
         
         {8, 3, 2,   0, 0,   UP, 10, 10, NE, SE},   //9
         {7, 3, 2,   0, 0,   1, 11, 11, 9, 9},   //10
         {6, 3, 2,   0, 0,   2, 12, 12, 10, 10},   //11
         {5, 3, 2,   0, 0,   UP, NW, NW, 11, 11},   //12
         
         {8, 4, 2,   0, 0,   UP, 14, 14, NE, SE},   //13
         {7, 4, 2,   0, 0,   3, 15, 15, 13, 13},   //14
         {6, 4, 2,   0, 0,   4, 16, 16, 14, 14},   //15
         {5, 4, 2,   0, 0,   UP, NW, SW, 15, 15},   //16
         
         {8, 5, 2,   0, 0,   UP, 18, 18, NE, SE},   //17
         {7, 5, 2,   0, 0,   UP, 19, 19, 17, 17},   //18
         {6, 5, 2,   0, 0,   UP, 20, 20, 18, 18},   //19
         {5, 5, 2,   0, 0,   UP, NW, SW, 19, 19},   //20
         
         //1st layer
         {9, 1, 1,   0, 0,   UP, 22, 22, NE, SE},   //21
         {8, 1, 1,   0, 0,   UP, 23, 23, 21, 21},   //22
         {7, 1, 1,   0, 0,   UP, 24, 24, 22, 22},   //23
         {6, 1, 1,   0, 0,   UP, 25, 25, 23, 23},   //24
         {5, 1, 1,   0, 0,   UP, 26, 26, 24, 24},   //25
         {4, 1, 1,   0, 0,   UP, NW, SW, 25, 25},   //26
         
         {9, 2, 1,   0, 0,   UP, 28, 28, NE, SE},   //27
         {8, 2, 1,   0, 0,   5, 29, 29, 27, 27},   //28
         {7, 2, 1,   0, 0,   6, 30, 30, 28, 28},   //29
         {6, 2, 1,   0, 0,   7, 31, 31, 29, 29},   //30
         {5, 2, 1,   0, 0,   8, 32, 32, 30, 30},   //31
         {4, 2, 1,   0, 0,   UP, NW, SW, 31, 31},   //32
         
         {9, 3, 1,   0, 0,   UP, 34, 34, NE, SE},   //33
         {8, 3, 1,   0, 0,   9, 35, 35, 33, 33},   //34
         {7, 3, 1,   0, 0,   10, 36, 36, 34, 34},   //35
         {6, 3, 1,   0, 0,   11, 37, 37, 35, 35},   //36
         {5, 3, 1,   0, 0,   12, 38, 38, 36, 36},   //37
         {4, 3, 1,   0, 0,   UP, NW, SW, 37, 37},   //38
         
         {9, 4, 1,   0, 0,   UP, 40, 40, NE, SE},   //39
         {8, 4, 1,   0, 0,   13, 41, 41, 39, 39},   //40
         {7, 4, 1,   0, 0,   14, 42, 42, 40, 40},   //41
         {6, 4, 1,   0, 0,   15, 43, 43, 41, 41},   //42
         {5, 4, 1,   0, 0,   16, 44, 44, 42, 42},   //43
         {4, 4, 1,   0, 0,   UP, NW, SW, 43, 43},   //44
         
         {9, 5, 1,   0, 0,   UP, 46, 46, NE, SE},   //45
         {8, 5, 1,   0, 0,   17, 47, 47, 45, 45},   //46
         {7, 5, 1,   0, 0,   18, 48, 48, 46, 46},   //47
         {6, 5, 1,   0, 0,   19, 49, 49, 47, 47},   //48
         {5, 5, 1,   0, 0,   20, 50, 50, 48, 48},   //49
         {4, 5, 1,   0, 0,   UP, NW, SW, 49, 49},   //50
         
         {9, 6, 1,   0, 0,   UP, 52, 52, NE, SE},   //51
         {8, 6, 1,   0, 0,   UP, 53, 53, 51, 51},   //52
         {7, 6, 1,   0, 0,   UP, 54, 54, 52, 52},   //53
         {6, 6, 1,   0, 0,   UP, 55, 55, 53, 53},   //54
         {5, 6, 1,   0, 0,   UP, 56, 56, 54, 54},   //55
         {4, 6, 1,   0, 0,   UP, NW, SW, 55, 55},   //56
         
         //0th layer
         {12, 0, 0,   0, 0,   UP, 58, 58, NE, SE},   //57
         {11, 0, 0,   0, 0,   UP, 59, 59, 57, 57},   //58
         {10, 0, 0,   0, 0,   UP, 60, 60, 58, 58},   //59
         {9, 0, 0,   0, 0,   UP, 61, 61, 59, 59},   //60
         {8, 0, 0,   0, 0,   UP, 62, 62, 60, 60},   //61
         {7, 0, 0,   0, 0,   UP, 63, 63, 61, 61},   //62
         {6, 0, 0,   0, 0,   UP, 64, 64, 62, 62},   //63
         {5, 0, 0,   0, 0,   UP, 65, 65, 63, 63},   //64
         {4, 0, 0,   0, 0,   UP, 66, 66, 64, 64},   //65
         {3, 0, 0,   0, 0,   UP, 67, 67, 65, 65},   //66
         {2, 0, 0,   0, 0,   UP, 68, 68, 66, 66},   //67
         {1, 0, 0,   0, 0,   UP, NW, SW, 67, 67},   //68
         
         {10, 1, 0,   0, 0,   UP, 70, 70, NE, SE},   //69
         {9, 1, 0,   0, 0,   21, 71, 71, 69, 69},   //70*
         {8, 1, 0,   0, 0,   22, 72, 72, 70, 70},   //71
         {7, 1, 0,   0, 0,   23, 73, 73, 71, 71},   //72
         {6, 1, 0,   0, 0,   24, 74, 74, 72, 72},   //73
         {5, 1, 0,   0, 0,   25, 75, 75, 73, 73},   //74
         {4, 1, 0,   0, 0,   26, 76, 76, 74, 74},   //75*
         {3, 1, 0,   0, 0,   UP, NW, SW, 75, 75},   //76
         
         {11, 2, 0,   0, 0,   UP, 78, 78, NE, SE},   //77
         {10, 2, 0,   0, 0,   UP, 79, 79, 77, 77},   //78
         {9, 2, 0,   0, 0,   27, 80, 80, 78, 78},   //79*
         {8, 2, 0,   0, 0,   28, 81, 81, 79, 79},   //80
         {7, 2, 0,   0, 0,   29, 82, 82, 80, 80},   //81
         {6, 2, 0,   0, 0,   30, 83, 83, 81, 81},   //82
         {5, 2, 0,   0, 0,   31, 84, 84, 82, 82},   //83
         {4, 2, 0,   0, 0,   32, 85, 85, 83, 83},   //84*
         {3, 2, 0,   0, 0,   UP, 86, 86, 84, 84},   //85
         {2, 2, 0,   0, 0,   UP, NW, SW, 85, 85},   //86
         
         {14, 3, 0,   0, 1,   UP, 88, 88, NE, SE},   //87
         {13, 3, 0,   0, 1,   UP, 89, 101, 87, 87},   //88
         {12, 3, 0,   0, 0,   UP, 90, 90, NE, 88},   //89
         {11, 3, 0,   0, 0,   UP, 91, 91, 89, 89},   //90
         {10, 3, 0,   0, 0,   UP, 92, 92, 90, 90},   //91
         {9, 3, 0,   0, 0,   33, 93, 93, 91, 91},   //92*
         {8, 3, 0,   0, 0,   34, 94, 94, 92, 92},   //93
         {7, 3, 0,   0, 0,   35, 95, 95, 93, 93},   //94
         {6, 3, 0,   0, 0,   36, 96, 96, 94, 94},   //95
         {5, 3, 0,   0, 0,   37, 97, 97, 95, 95},   //96
         {4, 3, 0,   0, 0,   38, 98, 98, 96, 96},   //97*
         {3, 3, 0,   0, 0,   UP, 99, 99, 97, 97},   //98
         {2, 3, 0,   0, 0,   UP, 100, 100, 98, 98},   //99
         {1, 3, 0,   0, 0,   UP, NW, 113, 99, 99},   //100
         
         {12, 4, 0,   0, 0,   UP, 102, 102, 88, SE},   //101
         {11, 4, 0,   0, 0,   UP, 103, 103, 101, 101},   //102
         {10, 4, 0,   0, 0,   UP, 104, 104, 102, 102},   //103
         {9, 4, 0,   0, 0,   39, 105, 105, 103, 103},   //104*
         {8, 4, 0,   0, 0,   40, 106, 106, 104, 104},   //105
         {7, 4, 0,   0, 0,   41, 107, 107, 105, 105},   //106
         {6, 4, 0,   0, 0,   42, 108, 108, 106, 106},   //107
         {5, 4, 0,   0, 0,   43, 109, 109, 107, 107},   //108
         {4, 4, 0,   0, 0,   44, 110, 110, 108, 108},   //109*
         {3, 4, 0,   0, 0,   UP, 111, 111, 109, 109},   //110
         {2, 4, 0,   0, 0,   UP, 112, 112, 110, 110},   //111
         {1, 4, 0,   0, 0,   UP, 113, SW, 111, 111},   //112
         {0, 3, 0,   0, 1,   UP, NW, SW, NE, 112},   //113
         
         {11, 5, 0,   0, 0,   UP, 115, 115, NE, SE},   //114
         {10, 5, 0,   0, 0,   UP, 116, 116, 114, 114},   //115
         {9, 5, 0,   0, 0,   45, 117, 117, 115, 115},   //116*
         {8, 5, 0,   0, 0,   46, 118, 118, 116, 116},   //117
         {7, 5, 0,   0, 0,   47, 119, 119, 117, 117},   //118
         {6, 5, 0,   0, 0,   48, 120, 120, 118, 118},   //119
         {5, 5, 0,   0, 0,   49, 121, 121, 119, 119},   //120
         {4, 5, 0,   0, 0,   50, 122, 122, 120, 120},   //121*
         {3, 5, 0,   0, 0,   UP, 123, 123, 121, 121},   //122
         {2, 5, 0,   0, 0,   UP, NW, SW, 122, 122},   //123
         
         {10, 6, 0,   0, 0,   UP, 125, 125, NE, SE},   //124
         {9, 6, 0,   0, 0,   51, 126, 126, 124, 124},   //125*
         {8, 6, 0,   0, 0,   52, 127, 127, 125, 125},   //126
         {7, 6, 0,   0, 0,   53, 128, 128, 126, 126},   //127
         {6, 6, 0,   0, 0,   54, 129, 129, 127, 127},   //128
         {5, 6, 0,   0, 0,   55, 130, 130, 128, 128},   //129
         {4, 6, 0,   0, 0,   56, 131, 131, 129, 129},   //130*
         {3, 6, 0,   0, 0,   UP, NW, SW, 130, 130},   //131
         
         {12, 7, 0,   0, 0,   UP, 133, 133, NE, SE},   //132
         {11, 7, 0,   0, 0,   UP, 134, 134, 132, 132},   //133
         {10, 7, 0,   0, 0,   UP, 135, 135, 133, 133},   //134
         {9, 7, 0,   0, 0,   UP, 136, 136, 134, 134},   //135
         {8, 7, 0,   0, 0,   UP, 137, 137, 135, 135},   //136
         {7, 7, 0,   0, 0,   UP, 138, 138, 136, 136},   //137
         {6, 7, 0,   0, 0,   UP, 139, 139, 137, 137},   //138
         {5, 7, 0,   0, 0,   UP, 140, 140, 138, 138},   //139
         {4, 7, 0,   0, 0,   UP, 141, 141, 139, 139},   //140
         {3, 7, 0,   0, 0,   UP, 142, 142, 140, 140},   //141
         {2, 7, 0,   0, 0,   UP, 143, 143, 141, 141},   //142
         {1, 7, 0,   0, 0,   UP, NW, SW, 142, 142},   //143
         
         //Dimensions: cols, rows, layers
         {15, 8, 5},
      };
      
      return layout;
   }
   
   public void center(){
      Point centerPoint = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
      setLocation(centerPoint.x - getWidth() / 2, centerPoint.y - getHeight() / 2);
   }
   
   public boolean setLookAndFeel(String className){
      try{
         UIManager.setLookAndFeel(className);
         SwingUtilities.updateComponentTreeUI(this);
      }catch(Exception ex){
         return false;
      }
      
      return true;
   }
   
   public boolean setLookAndFeel(){
      return setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
   }
   
   public static void main(String[] args){
      new Mahjong();
   }
}
