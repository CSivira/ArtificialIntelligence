/**
 * Class which represents a general heuristic.
 */
public abstract class Heuristic {
    /**
     * Calculates the estimated cost fron the current state to the goal.
     * 
     * @param state Current state.
     * @return The estimated cost from the current state to the goal.
     */
    public abstract int getCostToGoal(State state);
}