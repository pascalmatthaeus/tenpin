package priv.matthaeusp.tenpin;

public class Roll {
    
    protected int pinsHit;
    BonusQualifier bonusType = null;
    
    public Roll( int pinsHit ) { this.pinsHit = pinsHit; }
    
    public int getPinsHit() { return pinsHit; }
    
    @Override
    public String toString() {
        return "Pins hit: "+pinsHit+". Type: "+bonusType;
    }
}
