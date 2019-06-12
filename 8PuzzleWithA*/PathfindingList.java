import java.util.PriorityQueue;
import java.util.Comparator;

/**
 * Class which represents a implementation of the priority queue for 8-puzzle
 * problem.
 */
public class PathfindingList {

    /* The priority queue for all states */
    private PriorityQueue<State> states_;

    /**
     * Defaul class constructor
     */
    public PathfindingList() {
        // Initialize the priority queue with the StateComparator comparator.
        states_ = new PriorityQueue<State>(new StateComparator());
    }

    /**
     * Add a state to the priority queue.
     * 
     * @param state Current state
     */
    public void add(State state) {
        // Calling the add method from the priority queue.
        states_.add(state);
    }

    /**
     * Return the head state removing it from the priority queue.
     * 
     * @return The head state.
     */
    public State getHead() {
        // Calling the poll method from the priority queue.
        return states_.poll();
    }

    /**
     * Verify if the current state is in the priority queue or not.
     * 
     * @param state Current state
     * @return True if the state is in the queue. Otherwise return false.
     */
    public boolean isState(State state) {
        // Calling the contains method from the priority queue.
        return states_.contains(state);
    }

    /**
     * Verify if the priority queue is empty or not.
     * 
     * @param state Current state
     * @return True if the queue is empty. Otherwise return false.
     */
    public boolean isEmpty() {
        // Calling the size method from the priority queue.
        // Return true if size == 0. Otherwise return false.
        return states_.size() == 0 ? true : false;
    }

    /**
     * Class that establishes a comparator for the priority queue.
     */
    class StateComparator implements Comparator<State> {

        /**
         * Method which compare two states by its fCost variable.
         */
        public int compare(State a, State b) {
            int fCostA = a.fCost_;// The total cost of a.
            int fCostB = b.fCost_;// The total cost of b.

            // Compare both states and return the result.
            if (fCostA > fCostB) {
                return 1;
            } else if (fCostA < fCostB) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}