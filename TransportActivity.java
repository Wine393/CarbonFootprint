/**
 * Class: TransportActivity
 * Extends CarbonSource, implements CarbonCalculable + Reportable.
 *
 * Fields  : vehicleType, distanceKM, passengers
 * Formula : (distanceKM x factor) / passengers  → kg CO2
 * Types   : car | bus | train | plane | motorbike
 */
public class TransportActivity extends CarbonSource
        implements CarbonCalculable, Reportable {

    private double distanceKM;
    private String vehicleType;
    private int    passengers;

    // Emission factors (kg CO2 per km per vehicle)
    private static final double CAR_F      = 0.210;
    private static final double BUS_F      = 0.089;
    private static final double TRAIN_F    = 0.041;
    private static final double PLANE_F    = 0.255;
    private static final double MOTO_F     = 0.114;

    public TransportActivity(String vehicleType, double distanceKM, int passengers)
            throws InvalidActivityTypeException, NegativeValueException {
        
    }

    private void setFactor() throws InvalidActivityTypeException {
        
    }

    @Override
    public double calculateEmissions() throws NegativeValueException {
        return (distanceKM * emissionFactor) / passengers;
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
}
