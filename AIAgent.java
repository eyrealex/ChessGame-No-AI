import java.util.*;

public class AIAgent{
    Random rand;

    public AIAgent(){
        rand = new Random();
    }

/*
  The method randomMove takes as input a stack of potential moves that the AI agent
  can make. The agent uses a random number generator to randomly select a move from
  the inputted Stack and returns this to the calling agent.
*/

    public Move randomMove(Stack possibilities){

        int moveID = rand.nextInt(possibilities.size());
        System.out.println("Agent randomly selected move : "+moveID);
        for(int i=1;i < (possibilities.size()-(moveID));i++){
            possibilities.pop();
        }
        Move selectedMove = (Move)possibilities.pop();
        return selectedMove;
    }

    /*
        This strategy doesn't really care about what happens once the move is made.
        This could mean that the AI agent could take a piece even though the player will immediately gain some advantage.

        The AI agent takes a pawn with his Queen and in response to this attack the player takes the
        queen (AI agents queen) with another pawn.

        AI after making the move is up one point as the pawn is worth 1
        however the Queen has a value of nine points and when the player takes the AI agents queen it is down eight points.

        Pawn: 1
        Knight / Bishop : 3
        Rook: 5
        Queen: 9
        King is worth the game

        get all the possible moves just like above with the Random Agent and then apply a utility function to work out
        which move to make

    */

    public Move nextBestMove(Stack possibilities){

        int moveID = rand.nextInt(possibilities.size());
        System.out.println("Agent selected the next best move : "+moveID);
        for(int i=1;i < (possibilities.size()-(moveID));i++){
            possibilities.pop();
        }
        Move selectedMove = (Move)possibilities.pop();
        return selectedMove;
    }

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

    public Move twoLevelsDeep(Stack possibilities){
        Move selectedMove = new Move();
        return selectedMove;
    }
}