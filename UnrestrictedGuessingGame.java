/*
 * The UnrestrictedGuessingGame inherits from GuessingGame. It lets the user pick a thing that is not on the pre-specified set
 * If the computer gets to the wrong answer, instead of quitting, it require asking the user to learn the new thing
 * 
 * @author: Anh Tran
 */
public class UnrestrictedGuessingGame extends GuessingGame {
    /*
     * Constructor to build the tree based on the file
     */
    public UnrestrictedGuessingGame(GameTree gameTree, String fileName) {
        super(gameTree, fileName);
    }

    // override the play method
    @Override
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
        } else if (finalAnswer.equals("n")) {
            System.out.println("Oh no! What were you thinking of? ");
            String item = scanner.nextLine();

            System.out.println("Please give me yes/no question that would have determined your thing");
            String question = scanner.nextLine();

            System.out.println("Is the answer to your question yes or no?");
            String newAnswer = scanner.nextLine();

            BinaryTreeNode<String> newQuestion = new GameTreeNode(question);
            BinaryTreeNode<String> newItem = new GameTreeNode(item);
            BinaryTreeNode<String> oldNode = new GameTreeNode(currentNode.getData());
            if (newAnswer.equals("y")) {
                currentNode.setData(newQuestion.getData());
                currentNode.setLeftChild(newItem);
                currentNode.setRightChild(oldNode);
            } else {
                currentNode.setData(newQuestion.getData());
                currentNode.setRightChild(newItem);
                currentNode.setLeftChild(oldNode);
            }
        }

        System.out.println("Do you want to play again? ");
        String demand = scanner.nextLine();
        if (demand.equals("y")) {
            play();
        }
    }
}