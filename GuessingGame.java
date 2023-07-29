/*
 * The Guessing Game get the tree from the file, print all possible answers and then navigate through
 * the tree, taking user input to go left or right as a user answers yes or no.
 * At the end of the game, the program should show the final answer, and ask if it is correct or not
 * Then, it asks the user wants to play again, going back to the start is yes and quitting if not 
 * 
 * @author: Anh Tran
 */
import java.util.ArrayList;
import java.util.Scanner;

public class GuessingGame {
    GameTree gameTree;
    Scanner scanner;

    /*
     * Constructor to return a game tree based on the file
     * @param gameTree - the game tree that is built based on the file
     * @param fileName - the file to build the game tree
     */
    public GuessingGame(GameTree gameTree, String fileName) {
        this.gameTree = new GameTree(fileName);
        this.scanner = new Scanner(System.in);
    }

    /*
     * printAnswers method to print all the items (leaf nodes)
     * 
     * @param node - the current node
     */
    public void printAnswers(BinaryTreeNode<String> node) {
        ArrayList<String> items = new ArrayList<>();
        // base case
        if (node == null) {
            return;
        }
        // base case
        else if (node.isLeaf()) {
            items.add(node.getData());
        } else {
            printAnswers(node.getLeftChild());
            printAnswers(node.getRightChild());
        }

        // print all the leaf nodes
        for (String item : items) {
            System.out.print(item + ", ");
        }
    }

    /*
     * play method - to run the game
     */
    public void play() {
        System.out.println("You will think of an answer and I will try to guess it!");
        System.out.println("All you have to do is answer 'yes' or 'no'!");
        System.out.println(
                "Now think of an item either from this set or from elsewhere, from an item not listed that you know about.\n");

        BinaryTreeNode<String> currentNode = gameTree.getRoot();

        System.out.print("Items: ");
        printAnswers(currentNode);
        System.out.println();

        while (!currentNode.isLeaf()) {
            System.out.println(currentNode.getData());
            String answer = scanner.nextLine();
            if (answer.equals("y")) {
                currentNode = currentNode.getLeftChild();
            } else if (answer.equals("n")) {
                currentNode = currentNode.getRightChild();
            }
        }

        System.out.println("Is it " + currentNode.getData());
        String finalAnswer = scanner.nextLine();

        if (finalAnswer.equals("y")) {
            System.out.println("Yay! I guessed it :D");
        } 
        else if (finalAnswer.equals("n")) {
            System.out.println("Oh no! I can't guess it");
        }

        System.out.println("Do you want to play again? ");
        String demand = scanner.nextLine();
        if (demand.equals("y")) {
            play();
        }
    }
}
