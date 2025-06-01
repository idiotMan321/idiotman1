/**
 * 
 */
package assignments.module6;

/**
 * This class defines a log entry used in application logging 
 * Each log entry will consist of:
 * TimeStamp ([YYYY-MM-DD HH:MM:SS])  stored as a String (NOT as a Java Date)
 * Log Levels (INFO, WARN, ERROR, DEBUG) 
 * Messages (descriptions of events)
 *
 * @author csc222
 * @version module 6
 *
 */
public class LogEntry {
    private String timeStamp;
    private ErrorLevel errorLevel;
    private String description;
    
    /**
     * Construct a new LogEntry object.
     *
     * @param timeStamp
     * @param errorLevel
     * @param description
     */
    public LogEntry(String timeStamp, String errorLevel, String description) {
        this.timeStamp = timeStamp;
        this.errorLevel = ErrorLevel.fromString(errorLevel);
        this.description = description;
    }

    /**
     * Returns the TimeStamp of the log entry
     *
     * @return String
     */
    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     * Returns the ErrorLevel enum value
     *
     * @return ErrorLevel
     */
    public ErrorLevel getErrorLevel() {
        return errorLevel;
    }

    /**
     * Returns the description of the log entry
     *
     * @return String
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * 
     * {@inheritDoc}
     */
    public String toString() {
        return String.format("%s %s - %s", timeStamp, errorLevel, description);
    }

}
