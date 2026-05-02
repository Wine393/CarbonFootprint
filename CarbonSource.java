
/**
 * Abstract Class: CarbonSource
 * The root of the activity hierarchy. Stores the shared fields (activityName, emissionFactor) and declares the four abstract methods every subclass must implement.
 *
 * Subclasses: TransportActivity, HomeEnergyActivity,
 *             FoodActivity, DigitalActivity, WasteActivity
 */
public abstract class CarbonSource {

    protected String activityName;   // human-readable label
    protected double emissionFactor;  // kg CO2 per base unit

    // --- Abstract methods (polymorphism core) ---
    public abstract void showEmissions();

    public abstract void showTip();

    public abstract double calculateEmissions() throws NegativeValueException;

    public abstract String getSummary();

    // Common getter used by FootprintCalculator
    public String getActivityName() {
        return activityName;
    }
}
