/*Jeremy Johnson
 *CS 3230 - TR 9:30 AM
 *Lab 3: CharacterTile.java
 */

public class CharacterTile extends Tile{
   protected char symbol;
   
   public CharacterTile(char symbol){
      this.symbol = symbol;
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
}
