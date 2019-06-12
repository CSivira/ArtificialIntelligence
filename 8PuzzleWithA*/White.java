/**
 * Class which represent the White heuristic.
 */
public class White extends Heuristic {

    /**
     * Default class constructor
     */
    public White() {
    }

    /**
     * Calculates the estimated cost from the current state to the goal.
     * 
     * @param state Current state.
     * @return The estimated cost from the current state to the goal.
     */
    public int getCostToGoal(State state) {
        // Getting the coordinate of the white box from its current state.
        Pair whitePos = state.getWhiteBox();

        // Return the number of steps to its goal position.
        return Math.abs(whitePos.i - 2) + Math.abs(whitePos.j - 2);
    }
}