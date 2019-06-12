/**
 * Class which represent the Manhattan heuristic.
 */
public class Manhattan extends Heuristic {

    /**
     * Default class constructor
     */
    public Manhattan() {
    }

    /**
     * Calculates the estimated cost from the current state to the goal.
     * 
     * @param state Current state.
     * @return The estimated cost from the current state to the goal.
     */
    public int getCostToGoal(State state) {
        // Getting the the puzzle form the current state.
        int[][] matrix = state.getCurrentStateMatrix();
        int h = 0;
        // Looking all the numbers.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Avoiding the 0.
                if (matrix[i][j] != 0) {
                    // For the current position, calculates the steps to its
                    // goal position.
                    int x = (matrix[i][j] - 1) / 3;
                    int y = (matrix[i][j] - 1) % 3;
                    // Increase h with this steps.
                    h += Math.abs(x - i) + Math.abs(y - j);
                }
            }
        }
        // Return the number of steps to reach the goal state.
        return h;
    }
}