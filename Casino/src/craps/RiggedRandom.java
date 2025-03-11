package craps;

import java.util.Random;

@SuppressWarnings("serial")
public class RiggedRandom extends Random {

	public RiggedRandom() {
        super();
    }

    /**
     * Generates a random integer between 0 (inclusive) and bound (exclusive),
     * with a higher probability of selecting numbers in the specified "boosted" range.
     *
     * @param bound       The upper bound (exclusive).
     * @param boostStart  The start of the boosted range (inclusive).
     * @param boostEnd    The end of the boosted range (inclusive).
     * @param boostFactor The weight factor (>1 increases the chance of boosted numbers).
     * 
     * @return A randomly generated integer with a weighted probability.
     */
    public int nextRiggedInt(int bound, int boostStart, int boostEnd, double boostFactor) {
        if (boostStart < 0 || boostEnd >= bound || boostStart > boostEnd || boostFactor <= 1.0) {
            return nextInt(bound); // Fall back to normal random behavior
        }

        while (true) {
            int num = nextInt(bound);
            
            // If the number is in the boosted range, apply bias
            if (num >= boostStart && num <= boostEnd) {
                if (nextDouble() < (boostFactor - 1) / boostFactor) {
                    return num; // Accept the number based on probability
                }
            } else {
                // Outside the boost range, return normally
                return num;
            }
        }
    }
    
}