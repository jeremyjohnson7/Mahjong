/*Jeremy Johnson
 *CS 3230 - TR 9:30 AM
 *Lab 3: RankTile.java
 */

public abstract class RankTile extends Tile{
   protected int rank;
   
   public RankTile(int rank){
      this.rank = rank;
   }
   
   public boolean matches(Tile t){
      return super.matches(t) && rank == ((RankTile)t).rank;
   }
}
