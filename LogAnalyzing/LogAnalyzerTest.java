/**
 * 
 */
package assignments.module6;

import java.util.List;
import java.util.Scanner;

/**
 * This class tests the methods of the LogAnalyzer class
 *
 * @author csc222
 * @version module 6
 *
 */
public class LogAnalyzerTest {

    private static Scanner input = new Scanner(System.in);

    /**
     * main method
     *
     * @param args None
     */
    public static void main(String[] args) {
        String[] logs = {
            "2025-03-29 12:05:23|INFO|Server started successfully.",
            "2025-03-29 12:06:10|WARN|High memory usage detected: 85%.",
            "2025-03-29 12:07:45|ERROR|Database connection failed: Timeout error.",
            "2025-03-29 12:08:30|INFO|User 'admin' logged in from 192.168.1.10.",
            "2025-03-29 12:09:12|DEBUG|Processing request ID: 789654.",
            "2025-03-29 12:10:05|ERROR|Failed to load configuration file: config.yaml.",
            "2025-03-29 12:11:32|INFO|Backup completed successfully.",
            "2025-03-29 12:12:18|WARN|Disk space running low: 10% remaining.",
            "2025-03-29 12:13:40|INFO|User 'guest' logged out.",
            "2025-03-29 12:14:55|ERROR|Unauthorized access attempt detected."
        };

        System.out.println("Creating a new LogAnalyzer object with initial log entries...");
        LogAnalyzer analyzer = new LogAnalyzer(logs);
        displayLog(analyzer);

        System.out.println("Adding new log entries...");
        addLogEntry(analyzer);

        System.out.println("Checking log thresholds...");
        System.out.println(analyzer.checkThreshold());

        System.out.println("Filter on an error level...");
        filterLog(analyzer);

        System.out.println("\nGraph of Log Entries using the count of each category...");
        System.out.println(analyzer.drawBarChart());

        System.out.println("\nDeleting Log entries for a given error level...");
        deleteLogEntries(analyzer);

        input.close();
    }

    /**
     * 
     * Deleting log entries for a given level
     *
     * @param analyzer
     */
    private static void deleteLogEntries(LogAnalyzer analyzer) {
        System.out.print("Input error level: ");
        String level = input.nextLine();
        try {
            int count = analyzer.deleteLogEntries(level);
            if (count >= 0) {
                System.out.printf("%d row(s) deleted from the log\n", count);
            }
        }
        catch (IllegalArgumentException ie) {
            System.out.println(ie.getMessage());
        }
    }

    /**
     * 
     * Add log entries to the current log
     *
     * @param analyzer
     */
    private static void addLogEntry(LogAnalyzer analyzer) {
        System.out.print("How many entries would you like to add to the log: ");
        int count = input.nextInt();
        input.nextLine();
        while (count-- > 0) {
            System.out.print("Input timestamp in the format YYYY-MM-DD HH:MM:SS :");
            String timeStamp = input.nextLine();
            System.out.print("Input error level (ERROR, WARN, INFO, DEBUG) ");
            String level = input.nextLine();
            System.out.print("Input description: ");
            String desc = input.nextLine();
            analyzer.addLogEntry(String.format("%s|%s|%s", timeStamp, level, desc));
        }
        displayLog(analyzer);
    }

    /**
     * 
     * Filters on a given error level
     *
     * @param analyzer
     */
    private static void filterLog(LogAnalyzer analyzer) throws IllegalArgumentException {
        List<String> filteredResults = null;
        System.out.print("Input error level: ");
        String errorLevel = input.next();
        input.nextLine();
        try {
            filteredResults = analyzer.filterByLevel(errorLevel);
            for (String entry : filteredResults) {
                System.out.print(entry);
            }
        }
        catch (IllegalArgumentException ie) {
            System.out.print(ie.getMessage());
        }
    }

    /**
     * 
     * Display the log
     *
     * @param analyzer
     */
    private static void displayLog(LogAnalyzer analyzer) {
        System.out.println("The Log currently has...");
        System.out.println(analyzer);
    }
}
