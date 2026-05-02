/**
 * Class: HomeEnergyActivity
 * Extends CarbonSource, implements CarbonCalculable + Reportable.
 *
 * Fields  : energySource, monthlyKwh, months
 * Formula : monthlyKwh x months x factor  → kg CO2
 * Types   : grid | solar | coal | gas
 */
public class HomeEnergyActivity extends CarbonSource
        implements CarbonCalculable, Reportable {

    private double monthlyKwh;
    private String energySource;
    private int    months;

    // Emission factors (kg CO2 per kWh)
    private static final double GRID_F  = 0.233;
    private static final double SOLAR_F = 0.041;
    private static final double COAL_F  = 0.820;
    private static final double GAS_F   = 0.490;

    public HomeEnergyActivity(String energySource, double monthlyKwh, int months)
            throws InvalidActivityTypeException, NegativeValueException {
       
    }

    private void setFactor() throws InvalidActivityTypeException {
       
    }

    @Override
    public double calculateEmissions() throws NegativeValueException {
        return monthlyKwh * months * emissionFactor;
    }

    @Override
    public void showEmissions() {
       
    }

    @Override
    public void showTip() {
     
    }

    @Override
    public String getSummary() {
       
}
