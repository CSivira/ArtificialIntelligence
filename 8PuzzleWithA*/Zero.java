/**
 * Class which represent the Zero heuristic.
 */
public class Zero extends Heuristic {

    /**
     * Default class constructor
     */
    public Zero() {
    }

    /**
     * Calculates the estimated cost from the current state to the goal.
     * 
     * @param state Current state.
     * @return The estimated cost from the current state to the goal.
     */
    public int getCostToGoal(State state) {
        // For every state return 0.
        return 0;
    }

}