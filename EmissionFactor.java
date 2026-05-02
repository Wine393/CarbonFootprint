/**
 * Class: EmissionFactor
 * A data-holder that stores one emission factor entry: its category label, kg-CO2 multiplier, and unit string.
 * Used as a lightweight lookup object.
 */
public class EmissionFactor {

    private String factorType;   // e.g. "car", "beef", "grid"
    private double factorValue;  // kg CO2 per unit
    private String unit;         // e.g. "per km", "per kWh"

    public EmissionFactor(String type, double value, String unit) {
        this.factorType  = type;
        this.factorValue = value;
        this.unit        = unit;
    }

    // Returns the factor value if the type matches, else 0
    public double getFactor(String type) {
        return this.factorType.equalsIgnoreCase(type) ? factorValue : 0.0;
    }

    public String getUnit()       { return unit; }
    public String getFactorType() { return factorType; }
    public double getFactorValue(){ return factorValue; }
}
