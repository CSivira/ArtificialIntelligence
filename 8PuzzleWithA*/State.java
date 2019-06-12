import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import java.io.IOException;

/**
 * Class that represents a 8-puzzle state.
 */
public class State {
    /* Matrix with the content of the current state */
    private int[][] currentState_;

    /* Coordinates of the white box */
    private Pair whiteBox_;

    /* Predecessor of the current state */
    private State previousState_;

    /* Previous movement made in the puzzle */
    private boolean[] previousMovement_;

    /* Cost so far */
    public int gCost_;

    /* Estimated cost to goal */
    public int fCost_;

    /**
     * Empty class constructor.
     */
    public State() {
    }

    /**
     * Class constructor.
     * 
     * @param puzzle The matrix with the currect state of the puzzle.
     */
    public State(int[][] puzzle) {
        // Store the puzzle content in a new memory adress.
        int[][] matrix = new int[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = puzzle[i][j];
            }
        }
        // Setting the value of currentState_ with matrix.
        currentState_ = matrix;
        // Initialize the previous movement.
        previousMovement_ = new boolean[] { false, false, false, false };

        // Find the white box coordinates.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (currentState_[i][j] == 0) {
                    // Save this coordinates in whiteBox_;
                    whiteBox_ = new Pair(i, j);
                    break;
                }
            }
        }
    }

    /**
     * Return the white box coordinates.
     * 
     * @return Pair with the white box coordinates.
     */
    public Pair getWhiteBox() {
        // Store the coordinates in a new memory adress.
        Pair result = new Pair();
        result.i = this.whiteBox_.i;
        result.j = this.whiteBox_.j;

        return result;
    }

    /**
     * Return the state content in a matrix 3x3.
     * 
     * @return Matrix 3x3 with the content of the actual state.
     */
    public int[][] getCurrentStateMatrix() {
        // Return the adress of this state;
        return this.currentState_;
    }

    /**
     * Return the previous state of the current state.
     * 
     * @return Predecessor state of the current state.
     */
    public State getPreviousState() {
        // Return the previous state adress;
        return this.previousState_;
    }

    /**
     * Read and verify if the file is valid for a state.
     * 
     * @param data Name of the file text.
     * @return Matrix 3x3 with the file content.
     * @throws IOException If can't rad the fail or its content.
     */
    public int[][] load(String data) throws IOException {
        // Initialize the structures.
        boolean[] intChecker = new boolean[9];
        int[][] matrix = new int[3][3];

        for (int i = 0; i < 9; i++) {
            intChecker[i] = false;
        }

        // Load the file
        BufferedReader in = new BufferedReader(new FileReader(data));
        String line;

        // Looking every file line.
        for (int i = 0; i < 3; i++) {
            line = in.readLine();
            String[] currentLine = line.split(" ");
            for (int j = 0; j < 3; j++) {
                int value = Integer.parseInt(currentLine[j]);
                // Throw an Exception if there is a format error in the file.
                if (value < 0 && value > 8) {
                    throw new IOException();
                }

                if (intChecker[value]) {
                    throw new IOException();
                } else {
                    // Save the file content.
                    matrix[i][j] = value;
                    intChecker[value] = true;
                }
            }
        }
        // close the file.
        in.close();
        // Return its content.
        return matrix;
    }

    /**
     * Verify if this state matrix is equal to another state matrix.
     * 
     * @param matrix Matrix to compare
     * @return True if both are equals. Otherwise false;
     */
    public boolean isEqual(int[][] matrix) {
        // Comparing the content of both matrix.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.currentState_[i][j] != matrix[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Verify is the current state is solvable or not.
     * 
     * @return True if the state is solvable. Otherwise false.
     */
    public boolean isSolvable() {
        // Initialize the structures.
        int[] puzzle = new int[9];
        int counter = 0;

        // Save the matrix content in a array without the 0 box.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.currentState_[i][j] != 0) {
                    puzzle[counter] = this.currentState_[i][j];
                    counter++;
                }
            }
        }
        // Recycling the counter.
        counter = 0;

        // Calculates the number of inversions.
        for (int i = 0; i < 7; i++) {
            for (int j = i + 1; j < 8; j++) {
                if (puzzle[i] > puzzle[j]) {
                    counter++;
                }
            }
        }
        // If counter is odd return false. Otherwise return true.
        return (counter % 2 != 0) ? false : true;
    }

    /**
     * Return the possible movements in the current state.
     * 
     * @return Boolean array with the possible movements.
     */
    private boolean[] getMovements_() {
        // Initialize the boolean array.
        boolean[] moves = { false, false, false, false };

        // If a movement is possible, it becomes true.
        if (whiteBox_.i == 0) {
            if (whiteBox_.j == 0) {
                moves[1] = true;
                moves[3] = true;
            } else if (whiteBox_.j == 1) {
                moves[1] = true;
                moves[2] = true;
                moves[3] = true;
            } else if (whiteBox_.j == 2) {
                moves[1] = true;
                moves[2] = true;
            }
        } else if (whiteBox_.i == 1) {
            if (whiteBox_.j == 0) {
                moves[0] = true;
                moves[1] = true;
                moves[3] = true;
            } else if (whiteBox_.j == 1) {
                moves[0] = true;
                moves[1] = true;
                moves[2] = true;
                moves[3] = true;
            } else if (whiteBox_.j == 2) {
                moves[0] = true;
                moves[1] = true;
                moves[2] = true;
            }
        } else if (whiteBox_.i == 2) {
            if (whiteBox_.j == 0) {
                moves[0] = true;
                moves[3] = true;
            } else if (whiteBox_.j == 1) {
                moves[0] = true;
                moves[2] = true;
                moves[3] = true;
            } else {
                moves[0] = true;
                moves[2] = true;
            }
        }
        // Return possible movements.
        return moves;
    }

    /**
     * Interchange the content of two coordinates in a matrix.
     * 
     * @param a Pair with the first coordinate.
     * @param b Pair with the second coordinate.
     */
    private void swap_(Pair a, Pair b) {
        // Interchange both coordinates
        int aux = this.currentState_[a.i][a.j];
        this.currentState_[a.i][a.j] = this.currentState_[b.i][b.j];
        this.currentState_[b.i][b.j] = aux;
    }

    /**
     * Return the valid movements in the current state.
     * 
     * @return All the valid movements for this current state.
     */
    public ArrayList<State> getNextStates() {
        // Initialize the structure.
        ArrayList<State> states = new ArrayList<State>();
        boolean[] moves = this.getMovements_();

        // Set false the previous movement.
        for (int i = 0; i < 4; i++) {
            if (previousMovement_[i]) {
                moves[i] = false;
            }
        }

        // Looking every movement
        for (int i = 0; i < 4; i++) {
            // If it is a valid movement.
            if (moves[i]) {
                // Make a copy of the current state
                State nextState = new State(this.currentState_);
                nextState.previousState_ = this;
                Pair whiteBox = this.whiteBox_;
                Pair newWhiteBox = new Pair();
                boolean[] previousMove = new boolean[4];

                for (int j = 0; j < 4; j++) {
                    previousMove[j] = false;
                }
                // Verify what movement is.
                switch (i) {
                case 0:
                    // Up movement;
                    newWhiteBox.i = whiteBox.i - 1;
                    newWhiteBox.j = whiteBox.j;
                    previousMove[1] = true;
                    break;
                case 1:
                    // Down movement;
                    newWhiteBox.i = whiteBox.i + 1;
                    newWhiteBox.j = whiteBox.j;
                    previousMove[0] = true;
                    break;
                case 2:
                    // Left movement;
                    newWhiteBox.i = whiteBox.i;
                    newWhiteBox.j = whiteBox.j - 1;
                    previousMove[3] = true;
                    break;
                case 3:
                    // Right movement;
                    newWhiteBox.i = whiteBox.i;
                    newWhiteBox.j = whiteBox.j + 1;
                    previousMove[2] = true;
                    break;
                }

                // Modify the matrix with the new movement.
                nextState.swap_(newWhiteBox, this.whiteBox_);
                // Update its white box position.
                nextState.whiteBox_ = newWhiteBox;
                // Update its previous movement.
                nextState.previousMovement_ = previousMove;
                // Store the valid movement(state) in the list.
                states.add(nextState);
            }
        }
        // Return all the valid movements for this current state.
        return states;
    }

    /**
     * Indicates the printing form of a state.
     */
    @Override
    public String toString() {
        if (this.currentState_ == null) {
            return "Empty state \n";
        } else {
            String state = "";
            state += '\n';
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    state += String.valueOf(this.currentState_[i][j]) + " ";
                }
                state += '\n';
            }
            state += '\n';
            return state;
        }
    }
}