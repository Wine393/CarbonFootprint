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
    private int    servingsPerWeek;
    private int    weeks;

    // Emission factors (kg CO2 per serving)
    private static final double BEEF_F  = 6.61;
    private static final double PORK_F  = 1.72;
    private static final double CHKN_F  = 0.97;
    private static final double FISH_F  = 0.87;
    private static final double VEGI_F  = 0.39;
    private static final double VGAN_F  = 0.18;

    public FoodActivity(String mealType, int servingsPerWeek, int weeks)
            throws InvalidActivityTypeException, NegativeValueException {
       
    }

    private void setFactor() throws InvalidActivityTypeException {
       
    }

    @Override
    public double calculateEmissions() throws NegativeValueException {
        return servingsPerWeek * weeks * emissionFactor;
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
