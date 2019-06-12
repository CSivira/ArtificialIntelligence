/**
 * Class which represent a position in a matrix.
 */
public class Pair {
    /* Row coordinate */
    public int i;
    /* Column coordinate */
    public int j;

    /**
     * Empty class constructor.
     */
    Pair() {
    }

    /**
     * Class constructor.
     * 
     * @param iCordinate Row coordinate.
     * @param jCordinate Column coordinate.
     */
    Pair(int iCordinate, int jCordinate) {
        i = iCordinate; // Set the row coordinate.
        j = jCordinate; // Set the column coordinate.
    }
}