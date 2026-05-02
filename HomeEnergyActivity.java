
/**
 * Class: HomeEnergyActivity
 * Extends CarbonSource, implements CarbonCalculable + Reportable.
 *
 * Fields  : energySource, monthlyKwh, months
 * Formula : monthlyKwh x months x factor  → kg CO2
 * Types   : grid | solar | coal | gas
 */
public class HomeEnergyActivity extends CarbonSource implements CarbonCalculable, Reportable {

    private double monthlyKwh;
    private String energySource;
    private int months;

    // Emission factors (kg CO2 per kWh)
    private static final double GRID_F = 0.233;
    private static final double SOLAR_F = 0.041;
    private static final double COAL_F = 0.820;
    private static final double GAS_F = 0.490;

    public HomeEnergyActivity(String energySource, double monthlyKwh, int months)
            throws InvalidActivityTypeException, NegativeValueException {
        if (monthlyKwh < 0) {
            throw new NegativeValueException("monthlyKwh cannot be negative");
        }
        if (months < 0) {
            throw new NegativeValueException("months cannot be negative");
        }
        this.activityName = "Home energy";
        this.energySource = energySource.toLowerCase().trim();
        this.monthlyKwh = monthlyKwh;
        this.months = months;
        setFactor();

    }

    private void setFactor() throws InvalidActivityTypeException {
        switch (energySource) {
            case "grid":
                emissionFactor = GRID_F;
                break;
            case "solar":
                emissionFactor = SOLAR_F;
                break;
            case "coal":
                emissionFactor = COAL_F;
                break;
            case "gas":
                emissionFactor = GAS_F;
                break;

            default:
                throw new InvalidActivityTypeException(
                        "Unknown Home energy: " + energySource
                        + ". Valid: gas, coal, solar, gas");
        }
    }

    @Override
    public double calculateEmissions() throws NegativeValueException {
        return monthlyKwh * months * emissionFactor;
    }

    @Override
    public void showEmissions() {
        try {
            System.out.printf("  [Home Energy] %-11s %.1f hrs/day x %d days => %.4f kg CO2%n",
                    energySource, monthlyKwh, months, calculateEmissions());
        } catch (NegativeValueException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void showTip() {
        System.out.println("  Tip: Switch to LED bulbs — they use up to 80% less energy than traditional ones.");
        if ("server".equals(energySource)) {
            System.out.println("  Tip: Choose renewable energy — switch to a green energy plan or install solar panels if possible.");
        }
    }

    @Override
    public String getSummary() {
        try {
            return String.format("Home energy [%s, %.1fh x %d days]: %.4f kg CO2",
                    energySource, monthlyKwh, months, calculateEmissions());
        } catch (NegativeValueException e) {
            return "Home energy [error]";
        }
    }
}
