/*Jeremy Johnson
 *CS 3230 - TR 9:30 AM
 *Lab 3: PictureTile.java
 */

public abstract class PictureTile extends Tile{
   private String name;
   
   public PictureTile(String name){
      this.name = name;
   }
   
   public String toString(){
      return name;
   }
}
