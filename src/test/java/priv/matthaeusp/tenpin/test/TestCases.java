package priv.matthaeusp.tenpin.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import priv.matthaeusp.tenpin.*;

public class TestCases {
    @Test
    void gameInstantiates() {
        Integer [] rolls = {
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 // 20 rolls
        };
        Assertions.assertDoesNotThrow(
            () -> {
                new GameContext(rolls);
            }
        );
    }
    
    @Test
    void failsIfTooManyRolls() {
        Integer [] rolls = {
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 // 23 rolls
        };
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new GameContext(rolls);
        } );
    }
    
    @Test
    void rollsParsedCorrectly() {
        Integer [] rolls = { 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1 };
        
        var ctx = new GameContext(rolls);
        var gameRolls = ctx.rolls
            .stream()
            .map( r -> r.getPinsHit() )
            .toArray();
        
        Assertions.assertArrayEquals(rolls, gameRolls);
    }
    
    @Test
    void zeroScoreIfNoPinsHitDuringGame() {
        Integer [] rolls = { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
        
        var ctx = new GameContext( rolls );
        
        Assertions.assertEquals(0, ctx.getTotalScore());
    }
    
    @Test
    void highestScoreEquals300IfAllRollsAreStrike() {
        Integer [] rolls = {
            10,10,10,10,10,10,10,10,10,10,10,10 
        };
        
        var ctx = new GameContext( rolls );
        
        Assertions.assertEquals(300, ctx.getTotalScore());
    }
    
    @Test
    void testGameScoreEquals141() {
        Integer [] rolls = {
            10,8,2,5,5,0,2,10,3,6,1,9,10,4,4,10,2,8
        };
        
        var ctx = new GameContext( rolls );
        
        Assertions.assertEquals( 141, ctx.getTotalScore() );
    }
    
    
}
