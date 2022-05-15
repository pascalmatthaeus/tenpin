package priv.matthaeusp.tenpin;

import java.util.List;
import java.util.ArrayList;

public class GameContext {
    public List<Roll> rolls = new ArrayList();
    protected int strikeBonus;
    protected int spareBonus;
    protected int totalScore;
    
    public GameContext( Integer... rolls ) {
        if (rolls.length > 22) {
            throw new IllegalArgumentException( 
                "Input should not contain more than 22 rolls."
            );
        }
        
        for ( int roll : rolls ) {
            this.rolls.add( new Roll(roll) );
        }
        
        calculateTotalScore();
    }
    
    public int getTotalScore() { return totalScore; }
    
    @Override
    public String toString() {
        final var sb = new StringBuilder();
        rolls.forEach( r -> sb.append(r+"\n") );
        sb.append("Score: "+totalScore);
        return sb.toString();
    }
    
    private int calculateTotalScore() {
        // pass 1
        int totalPinsHit = rolls.stream()
            .map( r -> r.getPinsHit() )
            .reduce(0, (a,b) -> a+b);
        
        // pass 2 - strikes
        for (int i=0;i<rolls.size()-3;i++) {
            Roll r = rolls.get(i);
            if (r.getPinsHit() == 10) {
                r.bonusType = BonusQualifier.STRIKE;
            }
        }
        
        // pass 3 - spares
        for (int i=0;i<rolls.size()-2;i++) {
            Roll r1 = rolls.get(i);
            Roll r2 = rolls.get(i+1);
            if ( (r1.getPinsHit() + r2.getPinsHit()) == 10) {
                r2.bonusType = BonusQualifier.SPARE;
            }
        }
        
        calculateStrikeBonus();
        calculateSpareBonus();
        totalScore = totalPinsHit + strikeBonus + spareBonus;
        
        return totalScore;
    }
    
    private void calculateStrikeBonus() {
        strikeBonus = 0;
        rolls.stream()
            .filter( r -> r.bonusType == BonusQualifier.STRIKE)
            .forEach( r -> {
                int idx=rolls.indexOf(r);
                strikeBonus += rolls.get(idx+1).getPinsHit();
                strikeBonus += rolls.get(idx+2).getPinsHit();
        });
    }
    
    private void calculateSpareBonus() {
        spareBonus = 0;
        rolls.stream()
            .filter( r -> r.bonusType == BonusQualifier.SPARE)
            .forEach( r -> {
                int idx=rolls.indexOf(r);
                spareBonus += rolls.get(idx+1).getPinsHit();
        });
    }
}
