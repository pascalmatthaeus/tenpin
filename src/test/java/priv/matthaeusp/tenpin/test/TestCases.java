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
    void failsIfTooLittleOrTooManyRolls() {
        Integer [] rolls = {
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 // 22 rolls
        };
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new GameContext(rolls);
        } );
    }
    
    @Test
    void rollsParsedCorrectly() {
        Integer [] rolls = { 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1 };
        
        var ctx = new GameContext(rolls);
        var rollsInsideArrayList = ctx.rolls
            .stream()
            .map( r -> r.getPinsHit() )
            .toArray();
        
        Assertions.assertArrayEquals(rolls, rollsInsideArrayList);
    }
    
    @Test
    void zeroScoreIfNoPinsHitDuringGame() {
        Integer [] rolls = { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
        
        var ctx = new GameContext( rolls );
        
        Assertions.assertEquals(0, ctx.calculateTotalScore());
    }
    
    
}
