import java.util.Random;
import java.util.Stack;

public class AIAgent {
    Random rand;

    public AIAgent() {
        rand = new Random();
    }


    //Random Move AIAgent
    public Move randomMove(Stack possibilities) {

        int moveID = rand.nextInt(possibilities.size());
        System.out.println("Agent randomly selected move : " + moveID);
        for (int i = 0; i < (possibilities.size() - (moveID)); i++) {
            possibilities.pop();
        }
        Move selectedMove = (Move) possibilities.pop();
        System.out.println("piece taken was : " + selectedMove.getLanding().getName());
        return selectedMove;
    }//end random move ai method

    //Next Best Move AIAgent
    public Move nextBestMove(Stack possibilities) {
        Stack clonedPossibilities = (Stack) possibilities.clone();
        Stack bestMove = new Stack();
        int score = 0;

        for (int i = 0; i < clonedPossibilities.size(); i++) { //run a loop through a clone of possibilities so the original stack of possibilities is not shortened
            Move landing = (Move) possibilities.pop(); //an object that will pop possibilities from the top of the stack
            int y = landing.getLanding().getYC(); //a variable for a pieces y coordinate movement

            if (landing.getLanding().getName().contains("Pawn") && (score <= 2)) { //if a piece is a pawn and has a score that is less than or equal to 2
                if (score != 2) { //if the score is not equal to 2
                    bestMove.clear(); //remove it from the bestMove stack
                }
                score = 2; //otherwise the score is equal to 2
                bestMove.add(landing); //add the object of that particular piece to the best move stack

            }
            if (landing.getLanding().getName().contains("Knight") || (landing.getLanding().getName().contains("Bishup")) && (score <= 3)) {
                if (score != 3) {
                    bestMove.clear();
                }
                score = 3;
                bestMove.add(landing);
            }
            if (landing.getLanding().getName().contains("Rook") && (score <= 5)) {
                if (score != 5) {
                    bestMove.clear();
                }
                score = 5;
                bestMove.add(landing);
            }
            if (landing.getLanding().getName().contains("Queen") && (score <= 9)) {
                if (score != 9) {
                    bestMove.clear();
                }
                score = 9;
                bestMove.add(landing);
            }
            if ((landing.getLanding().getName().contains("King")) && (score <= 100)) {
                if (score != 100) {
                    bestMove.clear();
                }
                score = 100;
                bestMove.add(landing);
            }
            if (((y == 3) || (y == 4)) && (score <= 1)) {//if the the y coordinates of the board are e
                score = 1;
                bestMove.add(landing);
            }
        }//end for loop for clones possibilities

        if (score >= 1) { //if the score is of a significant value perform a move based on moves that will give the ai points
            Stack cloneBestMove = (Stack) bestMove.clone();
            int moveID = rand.nextInt(bestMove.size());
            for (int j = 1; j < (cloneBestMove.size() - (moveID)); j++) {
                bestMove.pop();
            }
            Move nextBest = (Move) bestMove.pop();
            return nextBest;
        }
        return randomMove(clonedPossibilities); //else if there is no move of any significant value just make a random move.
    }//end next best move ai method

    //Two Levels Deep AIAgent
    public Move twoLevelsDeep(Stack possibilities) {
       return nextBestMove(possibilities);
    }


}//end AIAgent
