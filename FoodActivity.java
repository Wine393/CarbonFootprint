
/**
 * Class: FoodActivity
 * Extends CarbonSource, implements CarbonCalculable + Reportable.
 *
 * Fields  : mealType, servingsPerWeek, weeks
 * Formula : servingsPerWeek x weeks x factor  → kg CO2
 * Types   : beef | pork | chicken | fish | vegetarian | vegan
 */
public class FoodActivity extends CarbonSource
        implements CarbonCalculable, Reportable {

    private String mealType;
    private int servingsPerWeek;
    private int weeks;

    // Emission factors (kg CO2 per serving)
    private static final double BEEF_F = 6.61;
    private static final double PORK_F = 1.72;
    private static final double CHKN_F = 0.97;
    private static final double FISH_F = 0.87;
    private static final double VEGI_F = 0.39;
    private static final double VGAN_F = 0.18;

    public FoodActivity(String mealType, int servingsPerWeek, int weeks)
            throws InvalidActivityTypeException, NegativeValueException {
        if (servingsPerWeek < 0) {
            throw new NegativeValueException("servingsPerWeek cannot be negative");
        }
        if (weeks < 0) {
            throw new NegativeValueException("weeks cannot be negative");
        }
        this.activityName = "Food";
        this.mealType = mealType.toLowerCase().trim();
        this.servingsPerWeek = servingsPerWeek;
        this.weeks = weeks;
        setFactor();
    }

    private void setFactor() throws InvalidActivityTypeException {
        switch (mealType) {
            case "beef":
                emissionFactor = BEEF_F;
                break;
            case "pork":
                emissionFactor = PORK_F;
                break;
            case "chicken":
                emissionFactor = CHKN_F;
                break;
            case "fish":
                emissionFactor = FISH_F;
                break;
            case "vegetable":
                emissionFactor = VEGI_F;
                break;
            case "vegan":
                emissionFactor = VGAN_F;
                break;
            default:
                throw new InvalidActivityTypeException(
                        "Unknown Food: " + mealType
                        + ". Valid: beef, pork, chicken, fish, vegetarian, vegan");
        }
    }

    @Override
    public double calculateEmissions() throws NegativeValueException {
        return servingsPerWeek * weeks * emissionFactor;
    }

    @Override
    public void showEmissions() {
        try {
            System.out.printf("  [Food] %-11s %.1f hrs/day x %d days => %.4f kg CO2%n",
                    mealType, servingsPerWeek, weeks, calculateEmissions());
        } catch (NegativeValueException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void showTip() {
        System.out.println("  Tip: Waste less food — plan meals and buy only what you need.");
        if ("beef".equals(mealType)) {
            System.out.println("  Tip: Eat less meat — it produces up to 50x more CO₂ than plants.");
        }
    }

    @Override
    public String getSummary() {
        try {
            return String.format("Food [%s, %.1fh x %d days]: %.4f kg CO2",
                    mealType, servingsPerWeek, weeks, calculateEmissions());
        } catch (NegativeValueException e) {
            return "Food [error]";
        }
    }

}
