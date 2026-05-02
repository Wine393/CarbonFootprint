
/**
 * Interface: CarbonCalculable
 * Defines the contract for any activity that can calculate
 * carbon emissions. Implemented by all 5 activity subclasses.
 */
public interface CarbonCalculable {

    // Returns total kg CO2 for the activity
    double calculateEmissions() throws NegativeValueException;

    // Prints an eco-friendly tip to the console
    void showTip();

    // Prints a one-line emission summary to the console
    void showEmissions();
}
