import java.util.Random;
import java.util.Stack;

public class AIAgent {
    Random rand;

    public AIAgent() {
        rand = new Random();
    }


    public Move randomMove(Stack possibilities) {

        int moveID = rand.nextInt(possibilities.size());
        System.out.println("Agent randomly selected move : " + moveID);
        for (int i = 0; i < (possibilities.size() - (moveID)); i++) {
            Move moved = (Move) possibilities.pop();
        }
        Move selectedMove = (Move) possibilities.pop();
        System.out.println("piece taken was : " + selectedMove.getLanding().getName());
        return selectedMove;
    }

    public Move nextBestMove(Stack possibilities) {
        Stack newPoss = (Stack) possibilities.clone();
        Stack bestMove = new Stack();
        int score = 0;

        for (int i = 0; i < newPoss.size(); i++) {
            Move landing = (Move) possibilities.pop();
            int y = landing.getLanding().getYC();

            if (landing.getLanding().getName().contains("Pawn") && (score <= 2)) {
                if (score != 2) {
                    bestMove.clear();
                }
                score = 2;
                bestMove.add(landing);

            }
            if (landing.getLanding().getName().contains("Rook") && (score <= 5)) {
                if (score != 5) {
                    bestMove.clear();
                }
                score = 5;
                bestMove.add(landing);
            }
            if (landing.getLanding().getName().contains("Knight") && (score <= 3)) {
                if (score != 3) {
                    bestMove.clear();
                }
                score = 3;
                bestMove.add(landing);
            }
            if (landing.getLanding().getName().contains("Bishup") && (score <= 3)) {
                if (score != 3) {
                    bestMove.clear();
                }
                score = 3;
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
            if(((y == 3) || (y == 4)) && (score <= 1)){
                score = 1;
                bestMove.add(landing);
            }

        }

        Stack cloneBestMove =  (Stack)bestMove.clone();
        int moveID = rand.nextInt(bestMove.size());
        for (int j = 1; j < (cloneBestMove.size() - (moveID)); j++) {
            bestMove.pop();
        }
        Move selectedMove = (Move) bestMove.pop();
        System.out.println("piece taken was : " + selectedMove.getLanding().getName());
        return selectedMove;


    }//end next best move method

    /*
        This agent extends the function of the agent above. This agent looks ahead and tries to determine what the player is going to do next.

        Sounds just like the min max routine.

        We know how to get the possible movements of all the pieces as we need this functionality for making random moves. We now are able
        to capture the movements / potential movements of the players pieces exactly as we did for the white pieces. Once we have this stack of movement
        we need a utility function to be able to calculate the value of the movements and then estimate which movement the player will make and then
        the agent responds to this movement.

        Random --> get all possible movements for white
               --> select a random value

        NextBestMove --> get all possible movements for white
                     --> create a utility function based on the current move, this could be if we take a piece we score some points
                     --> loop through the stack of movements and a check if we are taking a piece and if so make this movements

        twoLevelsDeep --> get all possible movements for white (stack)
    */

    public Move twoLevelsDeep(Stack possibilities) {
        Move selectedMove = new Move();
        return selectedMove;
    }
}