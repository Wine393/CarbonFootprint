/**
 * Class: FootprintCalculator
 * Manages a fixed-size array of CarbonSource activities (max 30).
 * Provides add, total, and display operations.
 *
 * Demonstrates:
 *   - Array of objects (CarbonSource[])
 *   - Polymorphic dispatch via CarbonSource reference
 *   - Method OVERLOADING: showAllActivities() vs showAllActivities(String)
 */
public class FootprintCalculator {

    private CarbonSource[] activities;
    private int activityCount;
    private static final int MAX_SIZE = 30;

    public FootprintCalculator() {
        activities    = new CarbonSource[MAX_SIZE];
        activityCount = 0;
    }

    // Polymorphic parameter — accepts any CarbonSource subclass
    public void addActivity(CarbonSource a) {
        if (activityCount < MAX_SIZE) {
            activities[activityCount++] = a;
            System.out.println(" [+] Activity recorded. Total: " + activityCount);
        } else {
            System.out.println(" [!] Max " + MAX_SIZE + " activities reached.");
        }
    }

    public double getTotalEmissions() {
        double total = 0.0;
        for (int i = 0; i < activityCount; i++) {
            try {
                total += activities[i].calculateEmissions();  // polymorphic call
            } catch (NegativeValueException e) {
                System.out.println(" Error in activity #" + (i + 1) + ": " + e.getMessage());
            }
        }
        return total;
    }

    // OVERLOAD 1 — show all activities (no filter)
    public void showAllActivities() {
        if (activityCount == 0) {
            System.out.println(" No activities recorded yet.");
            return;
        }
        printDivider();
        for (int i = 0; i < activityCount; i++) {
            System.out.printf(" %2d. %s%n", i + 1, activities[i].getSummary());
        }
    }

    // OVERLOAD 2 — show only activities matching a given type name
    public void showAllActivities(String filterType) {
        System.out.println(" [Filtered by: " + filterType + "]");
        printDivider();
        int count = 0;
        for (int i = 0; i < activityCount; i++) {
            if (activities[i].getActivityName().equalsIgnoreCase(filterType)) {
                System.out.printf(" %2d. %s%n", i + 1, activities[i].getSummary());
                count++;
            }
        }
        if (count == 0) System.out.println(" No activities of type: " + filterType);
    }

    private void printDivider() {
        for (int i = 0; i < 60; i++) System.out.print("=");
        System.out.println();
    }

    public int getActivityCount() { return activityCount; }
}
