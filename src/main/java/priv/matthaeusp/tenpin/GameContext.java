package priv.matthaeusp.tenpin;

import java.util.List;
import java.util.ArrayList;

public class GameContext {
    public List<Roll> rolls = new ArrayList();
    private int totalScore;
    
    public GameContext( Integer... rolls ) {
        if (rolls.length > 22) {
            throw new IllegalArgumentException( 
                "Input should not contain more than 22 rolls."
            );
        }
        
        for ( int roll : rolls ) {
            this.rolls.add( new Roll(roll) );
        }
    }
    
    public int calculateTotalScore() {
        
        //rolls.forEach( r -> totalScore += r.getPinsHit() );
        int currentFrame = 0;
        
        for (int i=0;i<rolls.size();i++) {
            int r1Score = rolls.get(i).getPinsHit();
            totalScore += r1Score;
            
            if (r1Score == 10) {
                totalScore += rolls.get(i+1).getPinsHit();
                totalScore += rolls.get(i+2).getPinsHit();
                if (i == rolls.size()-3) break;
                currentFrame++;
                continue;
            }
            
            if (i<rolls.size()-1) {
                int r2Score = rolls.get(i+1).getPinsHit();
                totalScore += r2Score;

                if ( (r1Score + r2Score) == 10 ) {
                    totalScore += rolls.get(i+1).getPinsHit();
                }
                currentFrame++;
            }
        }
        
        return totalScore;
    }
}
