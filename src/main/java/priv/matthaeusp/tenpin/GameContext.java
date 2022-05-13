package priv.matthaeusp.tenpin;

import java.util.List;
import java.util.ArrayList;

public class GameContext {
    public List<Roll> rolls = new ArrayList();
    
    public GameContext( Integer... rolls ) {
        if (rolls.length != 20 && rolls.length != 21) {
            throw new IllegalArgumentException( 
                "Input needs to contain 20 or 21 rolls"
            );
        }
        
        for ( int roll : rolls ) {
            this.rolls.add( new Roll(roll) );
        }
        
        
    }
}
