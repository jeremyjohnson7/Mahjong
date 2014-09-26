/*Jeremy Johnson
 *CS 3230 - TR 9:30 AM
 *Lab 3: Tile.java
 */

public abstract class Tile{
   public boolean matches(Tile t){
      return getClass() == t.getClass();
   }
}
