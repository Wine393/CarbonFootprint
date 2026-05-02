/**
 * Class: DigitalActivity
 * Extends CarbonSource, implements CarbonCalculable + Reportable.
 *
 * Fields  : deviceType, hoursPerDay, days
 * Formula : hoursPerDay x days x factor  → kg CO2
 * Types   : laptop | desktop | smartphone | tablet | tv | server
 */
public class DigitalActivity extends CarbonSource
        implements CarbonCalculable, Reportable {

    private String deviceType;
    private double hoursPerDay;
    private int    days;

    // Emission factors (kg CO2 per hour of use)
    private static final double LAPTOP_F = 0.050;
    private static final double DESK_F   = 0.100;
    private static final double PHONE_F  = 0.010;
    private static final double TAB_F    = 0.025;
    private static final double TV_F     = 0.088;
    private static final double SRV_F    = 0.200;

    public DigitalActivity(String deviceType, double hoursPerDay, int days)
            throws InvalidActivityTypeException, NegativeValueException {
        if (hoursPerDay < 0) throw new NegativeValueException("Hours cannot be negative");
        if (days       < 0) throw new NegativeValueException("Days cannot be negative");
        this.activityName = "Digital";
        this.deviceType   = deviceType.toLowerCase().trim();
        this.hoursPerDay  = hoursPerDay;
        this.days         = days;
        setFactor();
    }

    private void setFactor() throws InvalidActivityTypeException {
        switch (deviceType) {
            case "laptop":     emissionFactor = LAPTOP_F; break;
            case "desktop":    emissionFactor = DESK_F;   break;
            case "smartphone": emissionFactor = PHONE_F;  break;
            case "tablet":     emissionFactor = TAB_F;    break;
            case "tv":         emissionFactor = TV_F;     break;
            case "server":     emissionFactor = SRV_F;    break;
            default: throw new InvalidActivityTypeException(
                "Unknown device: " + deviceType +
                ". Valid: laptop, desktop, smartphone, tablet, tv, server");
        }
    }

    @Override
    public double calculateEmissions() throws NegativeValueException {
        return hoursPerDay * days * emissionFactor;
    }

    @Override
    public void showEmissions() {
        try {
            System.out.printf("  [Digital] %-11s %.1f hrs/day x %d days => %.4f kg CO2%n",
                deviceType, hoursPerDay, days, calculateEmissions());
        } catch (NegativeValueException e) { System.out.println(e.getMessage()); }
    }

    @Override
    public void showTip() {
        System.out.println("  Tip: Enable power-saving mode and unplug chargers when not in use.");
        if ("server".equals(deviceType))
            System.out.println("  Tip: A server emits 4x more than a desktop. Use cloud sparingly.");
    }

    @Override
    public String getSummary() {
        try {
            return String.format("Digital [%s, %.1fh x %d days]: %.4f kg CO2",
                deviceType, hoursPerDay, days, calculateEmissions());
        } catch (NegativeValueException e) { return "Digital [error]"; }
    }
}
