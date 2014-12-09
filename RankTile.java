/*Jeremy Johnson
 *CS 3230 - TR 9:30 AM
 *Lab 8: RankTile.java
 */

import java.awt.*;
import javax.swing.*;

public abstract class RankTile extends Tile{
   protected int rank;
   
   public RankTile(int rank){
      this.rank = rank;
      setToolTipText(toString());
   }
   
   public boolean matches(Tile t){
      return super.matches(t) && rank == ((RankTile)t).rank;
   }
}
