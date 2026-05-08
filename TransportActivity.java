
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
    private int passengers;

    // Emission factors (kg CO2 per km per vehicle)
    private static final double CAR_F = 0.210;
    private static final double BUS_F = 0.089;
    private static final double TRAIN_F = 0.041;
    private static final double PLANE_F = 0.255;
    private static final double MOTO_F = 0.114;

    public TransportActivity(String vehicleType, double distanceKM, int passengers)
            throws InvalidActivityTypeException, NegativeValueException {
        if (distanceKM < 0) {
            throw new NegativeValueException("distanceKM cannot be negative");
        }
        if (passengers < 0) {
            throw new NegativeValueException("passengers cannot be negative");
        }
        this.activityName = "Transport";
        this.vehicleType = vehicleType.toLowerCase().trim();
        this.distanceKM = distanceKM;
        this.passengers = passengers;
        setFactor();
    }

    private void setFactor() throws InvalidActivityTypeException {
        switch (vehicleType) {
            case "car":
                emissionFactor = CAR_F;
                break;
            case "bus":
                emissionFactor = BUS_F;
                break;
            case "train":
                emissionFactor = TRAIN_F;
                break;
            case "plane":
                emissionFactor = PLANE_F;
                break;
            case "motorbike":
                emissionFactor = MOTO_F;
                break;

            default:
                throw new InvalidActivityTypeException(
                        "Unknown device: " + vehicleType
                        + ". Valid: car, bus, train, plane, motorbike, ");
        }
    }

    @Override
    public double calculateEmissions() throws NegativeValueException {
        return (distanceKM * emissionFactor) / passengers;
    }

    @Override
    public void showEmissions() {
        try {
            System.out.printf(" [Transport]  %-11s | %6.1f km | %d pax => %.4f kg CO2%n",
                vehicleType, distanceKM, passengers, calculateEmissions());
        } catch (NegativeValueException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void showTip() {
        System.out.println("  Tip: Use public transport or bike instead of driving — cars are one of the biggest sources of personal CO₂ emissions.");
        if ("plane".equals(vehicleType)) {
            System.out.println("  Tip: Keep your tires properly inflated — underinflated tires increase fuel consumption by up to 3%.");
        }
    }

    @Override
    public String getSummary() {
        try {
            return String.format("Transport [%s, %.1fh x %d days]: %.4f kg CO2",
                    vehicleType, distanceKM, passengers, calculateEmissions());
        } catch (NegativeValueException e) {
            return "Transport [error]";
        }
    }

}
