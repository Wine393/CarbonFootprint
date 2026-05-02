/**
 * Interface: Reportable
 * Guarantees any implementing class can produce
 * a human-readable summary String for the report.
 */
public interface Reportable {

    // Returns a formatted summary string of the activity
    String getSummary();
}
