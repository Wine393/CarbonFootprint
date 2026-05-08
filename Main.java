/**
 * Class: Main (Entry Point)
 * Interactive console application that drives the FootprintCalculator.
 * Demonstrates:
 *   - Polymorphism via CarbonSource[] (polymorphic assignment)
 *   - Checked exception handling (try/catch for both custom exceptions)
 *   - Clean user input loop with NumberFormatException validation
 */
import java.util.*;

public class Main {

    private static final Scanner sc   = new Scanner(System.in);
    private static final FootprintCalculator calc = new FootprintCalculator();

    public static void main(String[] args) {
        printBanner();
        boolean running = true;
        while (running) {
            printMenu();
            System.out.println();
            int choice = readInt(" Enter choice >> ");
            switch (choice) {
                case 1: addTransport();         break;
                case 2: addHomeEnergy();        break;
                case 3: addFood();              break;
                case 4: addDigital();           break;
                case 5: addWaste();             break;
                case 6: calc.showAllActivities(); break;
                case 7: showReport();           break;
                case 8: running = false;        break;
                default: System.out.println(" Invalid option. Try 1-8.");
            }
        }
        System.out.println("Thank you for using Carbon Footprint Calculator.");
        System.out.println("Every small action helps the environment.");
        sc.close();
    }

    private static void addTransport() {
        System.out.println("\n>> TRANSPORT ACTIVITY");
        System.out.println("-------------------------------------------");
        System.out.println("Available: car | bus | train | plane | motorbike");
        String v = readStr("Vehicle type : ");
        double d = readDbl("Distance km  : ");
        int    p = readInt("Passengers   : ");
        try {
            TransportActivity ta = new TransportActivity(v, d, p);
            calc.addActivity(ta);
            ta.showEmissions();
            ta.showTip();
        } catch (InvalidActivityTypeException | NegativeValueException e) {
            System.out.println(" [ERROR] " + e.getMessage());
        }
    }

    private static void addHomeEnergy() {
        System.out.println("\n>> HOME ENERGY ACTIVITY");
        System.out.println("-------------------------------------------");
        System.out.println("Available: grid | solar | coal | gas");
        String src = readStr("Energy source : ");
        double kwh = readDbl("Monthly kWh   : ");
        int    mo  = readInt("Months        : ");
        try {
            HomeEnergyActivity ha = new HomeEnergyActivity(src, kwh, mo);
            calc.addActivity(ha);
            ha.showEmissions();
            ha.showTip();
        } catch (InvalidActivityTypeException | NegativeValueException e) {
            System.out.println(" [ERROR] " + e.getMessage());
        }
    }

    private static void addFood() {
        System.out.println("\n>> FOOD ACTIVITY");
        System.out.println("-------------------------------------------");
        System.out.println("Available: beef | pork | chicken | fish | vegetarian | vegan");
        String m = readStr("Meal type      : ");
        int    s = readInt("Servings/week  : ");
        int    w = readInt("Weeks          : ");
        try {
            FoodActivity fa = new FoodActivity(m, s, w);
            calc.addActivity(fa);
            fa.showEmissions();
            fa.showTip();
        } catch (InvalidActivityTypeException | NegativeValueException e) {
            System.out.println(" [ERROR] " + e.getMessage());
        }
    }

    private static void addDigital() {
        System.out.println("\n>> DIGITAL ACTIVITY");
        System.out.println("-------------------------------------------");
        System.out.println("Available: laptop | desktop | smartphone | tablet | tv | server");
        String dv = readStr("Device type : ");
        double h  = readDbl("Hours/day   : ");
        int    dy = readInt("Days        : ");
        try {
            DigitalActivity da = new DigitalActivity(dv, h, dy);
            calc.addActivity(da);
            da.showEmissions();
            da.showTip();
        } catch (InvalidActivityTypeException | NegativeValueException e) {
            System.out.println(" [ERROR] " + e.getMessage());
        }
    }

    private static void addWaste() {
        System.out.println("\n>> WASTE ACTIVITY");
        System.out.println("-------------------------------------------");
        System.out.println("Available: general | organic | plastic | paper | electronic");
        String wt = readStr("Waste type   : ");
        double kg = readDbl("Weight kg    : ");
        String rb = readStr("Recycled? (yes/no): ");
        boolean rec = rb.trim().equalsIgnoreCase("yes");
        try {
            WasteActivity wa = new WasteActivity(wt, kg, rec);
            calc.addActivity(wa);
            wa.showEmissions();
            wa.showTip();
        } catch (InvalidActivityTypeException | NegativeValueException e) {
            System.out.println(" [ERROR] " + e.getMessage());
        }
    }

    private static void showReport() {
        double total = calc.getTotalEmissions();
        int    count = calc.getActivityCount();
        String line  = "============================================================";
        System.out.println();
        System.out.println(line);
        System.out.println("        CARBON FOOTPRINT FINAL REPORT");
        System.out.println(line);
        calc.showAllActivities();
        System.out.println(line);
        System.out.printf(" Total activities recorded : %d%n", count);
        System.out.printf(" Total CO2 emissions       : %.2f kg%n", total);
        System.out.printf(" Equivalent in tonnes      : %.6f t CO2%n", total / 1000);
        System.out.println(line);
        if      (total < 50)  { System.out.println(" RATING: ****  EXCELLENT  — Excellent! Very low impact."); }
        else if (total < 200) { System.out.println(" RATING: ***   GOOD       — Below average. Keep it up!"); }
        else if (total < 500) { System.out.println(" RATING: **    MODERATE   — Room for meaningful improvement."); }
        else                  { System.out.println(" RATING: *     HIGH       — Significant reduction recommended."); }
        System.out.println(line);
    }

    private static void printBanner() {
        System.out.println("+--------------------------------------------------+");
        System.out.println("|      CARBON FOOTPRINT CALCULATOR  v1.0           |");
        System.out.println("|      Sustainable Living System                   |");
        System.out.println("+--------------------------------------------------+");
        System.out.println("|  Track your activities and monitor your CO2      |");
        System.out.println("+--------------------------------------------------+");
    }

    private static void printMenu() {
        System.out.println("\n+==================== MAIN MENU ===================+");
        System.out.println("| [1] Transport Activity                           |");
        System.out.println("| [2] Home Energy Activity                         |");
        System.out.println("| [3] Food Consumption                             |");
        System.out.println("| [4] Digital Device Usage                         |");
        System.out.println("| [5] Waste Management                             |");
        System.out.println("| [6] View All Activities                          |");
        System.out.println("| [7] Generate Carbon Report                       |");
        System.out.println("| [8] Exit Program                                 |");
        System.out.println("+==================================================+");
    }

    private static String readStr(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    private static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(" Please enter a whole number.");
            }
        }
    }

    private static double readDbl(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(" Please enter a decimal number.");
            }
        }
    }
}
