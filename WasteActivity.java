/**
 * Class: WasteActivity
 * Extends CarbonSource, implements CarbonCalculable + Reportable.
 *
 * Fields : wasteType, weightKg, recycled
 * Formula : weightKg x factor x (recycled ? 0.4 : 1.0) → kg CO2
 * Types   : general | organic | plastic | paper | electronic
 */
public class WasteActivity extends CarbonSource
        implements CarbonCalculable, Reportable {

    private String  wasteType;
    private double  weightKg;
    private boolean recycled;

    // Emission factors (kg CO2 per kg of waste)
    private static final double GEN_F  = 0.57;
    private static final double ORG_F  = 0.45;
    private static final double PLAS_F = 1.50;
    private static final double PAP_F  = 0.91;
    private static final double ELEC_F = 20.0;
    private static final double RECYCLE_REDUCTION = 0.4; // 60% saved

    public WasteActivity(String wasteType, double weightKg, boolean recycled)
            throws InvalidActivityTypeException, NegativeValueException {
        if (weightKg < 0) throw new NegativeValueException("Weight cannot be negative");

        this.activityName = "Waste";
        this.wasteType    = wasteType.toLowerCase().trim();
        this.weightKg     = weightKg;
        this.recycled     = recycled;
        setFactor();
    }

    private void setFactor() throws InvalidActivityTypeException {
        switch (wasteType) {
            case "general":    emissionFactor = GEN_F;  break;
            case "organic":    emissionFactor = ORG_F;  break;
            case "plastic":    emissionFactor = PLAS_F; break;
            case "paper":      emissionFactor = PAP_F;  break;
            case "electronic": emissionFactor = ELEC_F; break;
            default: throw new InvalidActivityTypeException(
                "Unknown waste: " + wasteType +
                ". Valid: general, organic, plastic, paper, electronic");
        }
    }

    @Override
    public double calculateEmissions() throws NegativeValueException {
        double raw = weightKg * emissionFactor;
        return recycled ? raw * RECYCLE_REDUCTION : raw;
    }

    @Override
    public void showEmissions() {
        try {
            System.out.printf(" [Waste] %-10s | %.1f kg | recycled=%-5b => %.4f kg CO2%n",
                wasteType, weightKg, recycled, calculateEmissions());
        } catch (NegativeValueException e) { System.out.println(e.getMessage()); }
    }

    @Override
    public void showTip() {
        System.out.println(" Tip: Recycling reduces waste emissions by up to 60%.");
        if ("electronic".equals(wasteType))
            System.out.println(" Tip: E-waste is 35x worse than plastic. Use certified e-recyclers.");
    }

    @Override
    public String getSummary() {
        try {
            return String.format("Waste | %-10s | %.1f kg | recycled=%b | %.4f kg CO2",
                wasteType, weightKg, recycled, calculateEmissions());
        } catch (NegativeValueException e) { return "Waste [error]"; }
    }
}
