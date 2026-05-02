/**
 * Custom Checked Exception: NegativeValueException
 * Thrown when the user inputs a negative number where
 * only non-negative values make sense (distance, weight, etc.)
 */
public class NegativeValueException extends Exception {

    public NegativeValueException(String message) {
        super(message);
    }
}
