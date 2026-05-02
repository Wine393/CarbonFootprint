/**
 * Custom Checked Exception: InvalidActivityTypeException
 * Thrown when an unrecognized category is entered,
 * e.g. vehicleType="rocket" or mealType="unicorn".
 */
public class InvalidActivityTypeException extends Exception {

    public InvalidActivityTypeException(String message) {
        super(message);
    }
}
