/**
 * Class: FootprintCalculator
 * Manages a fixed-size array of CarbonSource activities
 * (max 30). Provides add, total, and display operations.
 *
 * Demonstrates: array-based collection, polymorphic dispatch
 */
public class FootprintCalculator {

    private CarbonSource[] activities;
    private int activityCount;
    private static final int MAX_SIZE = 30;

    public FootprintCalculator() {
        activities    = new CarbonSource[MAX_SIZE];
        activityCount = 0;
    }

    public void addActivity(CarbonSource a) {
        if (activityCount < MAX_SIZE) {
            activities[activityCount++] = a;
            System.out.println("  [+] Activity recorded. Total: " + activityCount);
        } else {
            System.out.println("  [!] Max " + MAX_SIZE + " activities reached.");
        }
    }

    public double getTotalEmissions() {
        double total = 0.0;
        for (int i = 0; i < activityCount; i++) {
            try {
                total += activities[i].calculateEmissions();
            } catch (NegativeValueException e) {
                System.out.println("  Error in activity #" + (i+1) + ": " + e.getMessage());
            }
        }
        return total;
    }

    public void showAllActivities() {
        if (activityCount == 0) {
            System.out.println("  No activities recorded yet.");
            return;
        }
        for (int i = 0; i < 50; i++) {
            System.out.print("=");
        }
        System.out.println();

        for (int i = 0; i < activityCount; i++) {
            System.out.printf("  %2d. %s%n", i + 1, activities[i].getSummary());
        }
    }

    public int getActivityCount() { return activityCount; }
}
