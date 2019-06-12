import java.util.ArrayList;

/**
 * Class that represents the 8-puzzle problem.
 */
class OchoPuzzle {

    /* The initial state of the puzzle */
    public State initialState = new State();
    /* The goal state of the puzzle */
    public int[][] GOALSTATE = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };

    /**
     * Class constructor
     * 
     * @param fileText name of the file that contains the initial state.
     */
    OchoPuzzle(String fileText) {
        // Try to load the current file.
        try {
            initialState = new State(initialState.load(fileText));
        } catch (Exception e) {
            System.out.println("Can't load the file " + fileText);
        }
    }

    /**
     * Implementation of the algorithm A star for 8-puzzle problem.
     * 
     * @param initialState The start node of pathfinding.
     * @param goalState    The end node of pathfinding.
     * @param h            The current heuristic of the algorithm.
     */
    public void aStar(State initialState, int[][] goalState, Heuristic h) {
        // Start recording the time.
        long start = System.currentTimeMillis();

        // Initialize the initial state.
        initialState.gCost_ = 0;
        int hCost = h.getCostToGoal(initialState);
        initialState.fCost_ = initialState.gCost_ + hCost;

        // Initialize the open and close PathfindingList.
        PathfindingList openList = new PathfindingList();
        openList.add(initialState); // Add the initial state to open list.
        PathfindingList closeList = new PathfindingList();

        State currentState = new State();
        // There is a open path.
        int openCounter = 1;

        // Repeat until openList is empty.
        while (!openList.isEmpty()) {
            // Getting the head form the open list.
            currentState = openList.getHead();

            // Verify if the current state is the goal state.
            if (currentState.isEqual(goalState)) {
                break;
            }

            // Add current state to the close list.
            closeList.add(currentState);

            // Getting the next movements from the current state.
            ArrayList<State> nextStates = currentState.getNextStates();

            // Looking all the next states.
            for (State state : nextStates) {
                if (!closeList.isState(state)) {
                    // update the gCost and fCost of the next state.
                    int stateCost = currentState.gCost_ + 1;
                    state.gCost_ = stateCost;
                    state.fCost_ = stateCost + h.getCostToGoal(state);
                    // Add the next state to the open list.
                    openList.add(state);
                    openCounter++;// Increase the number of open states.
                }
            }
        }

        // Stop recording the time.
        long end = System.currentTimeMillis();
        // Calling the path recovery method.
        showPath_(currentState, openCounter, start, end);
    }

    /**
     * Path recovery for the pathfinding result.
     * 
     * @param state Final state of the path.
     * @param open  Number of open path.
     * @param start Start time of recording.
     * @param end   End time of recording.
     */
    private void showPath_(State state, int open, long start, long end) {
        // Set the current state white the supplied state.
        State currentState = state;
        // Initialize the list for the path states.
        ArrayList<State> inversePath = new ArrayList<State>();

        // Repeat until current state is not the initial state.
        while (!currentState.isEqual(initialState.getCurrentStateMatrix())) {
            inversePath.add(currentState); // Add the state.
            // Go for the previous state.
            currentState = currentState.getPreviousState();
        }

        // Save the number of movements.
        int nMovement = inversePath.size();

        // Show the initial state.
        System.out.print(currentState);

        // Show the path in the correct order.
        for (int i = inversePath.size() - 1; i >= 0; i--) {
            System.out.print(inversePath.remove(i));
        }

        // Show the number of movements.
        System.out.println("Number of movements: " + nMovement);

        // Show the number of open paths.
        System.out.println("Number of open states: " + String.valueOf(open));

        // Calculate and show the time of the pathfinding.
        Double time = (double) end - start;
        System.out.println("Time: " + String.valueOf(time) + " ms \n");
    }

    /**
     * Main method of OchoPuzzle.
     * 
     * @param args Heuristic and file name.
     */
    public static void main(String[] args) {
        // Setting the heuristic and the file name.
        String heuristic = args[0];
        String file = args[1];

        OchoPuzzle puzzle = new OchoPuzzle(file);
        Heuristic h = new Manhattan();

        // Select the correct heuristic
        switch (heuristic) {
        case "z":
            h = new Zero();
            break;
        case "d":
            h = new Unordered();
            break;
        case "m":
            h = new Manhattan();
            break;
        case "b":
            h = new White();
            break;
        default:
            System.out.println("Incompatible heuristic");
            System.exit(0);
            break;
        }

        // Verify if the puzzle is solvable.
        if (puzzle.initialState.isSolvable()) {
            System.out.println("Can solve this 8-puzzle");
            // Calling aStar for the pathfinding solution.
            puzzle.aStar(puzzle.initialState, puzzle.GOALSTATE, h);
        } else {
            System.out.println("Can't solve this 8-puzzle");
            System.exit(0);
        }
    }
}