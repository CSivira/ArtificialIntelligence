/**
 * Class which represent the Unordered boxes number heuristic.
 */
public class Unordered extends Heuristic {

    /**
     * Default class constructor
     */
    public Unordered() {
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
        int counter = 1;
        // Looking all the numbers.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matrix[i][j] == 0) {
                    // Increase h if 0 isn't in its goal position.
                    if (counter != 9) {
                        h++;
                    }
                } else {
                    // Increase h if the current number isn't in its goal
                    // position.
                    if (matrix[i][j] != counter) {
                        h++;
                    }
                }
                counter++;
            }
        }
        // Return the number of inversions in this state.
        return h;
    }
}