package priv.matthaeusp.tenpin;

public class Tenpin {

    public static void main( String [] args ) {
        Integer [] inputRolls = new Integer[args.length];
        for (int i=0; i<args.length; i++) {
            inputRolls[i] = Integer.valueOf(args[i]);
        }
        
        var game = new GameContext( inputRolls );
        
        System.out.println(game);
    }
}
