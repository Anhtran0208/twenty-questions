/**
 * Example test file of how to use GameTree
 * @author Tayloe
 */
public class GameTreeTest {
    public static void main(String[] args){
        GameTree testTree = new GameTree(args[0]);
        
        //test the GuessingGame file
        GuessingGame game = new GuessingGame(testTree, args[0]);
        game.play();

        //test the UnrestrictedGuessingGame file
        // UnrestrictedGuessingGame game2 = new UnrestrictedGuessingGame(testTree, args[0]);
        // game2.play();
    }
}
