/**
 * 
 */
package assignments.module6;

/**
 * Enumerated values for Error Levels used in logging
 * Each error level has two values, the order of importance and the threshold 
 * The threshold indicates the tolerance level. The objective is to provide a warning once log levels
 * exceed this threshold.
 *
 * @author csc222
 * @version module 6
 *
 */
public enum ErrorLevel {
    /**
     * Critical issues; system failures
     */
    ERROR(3, 1),  
    /**
     * Warnings about potential issues
     */
    WARN(2, 5),
    /**
     * General system activity logs
     */
    INFO(1, Double.POSITIVE_INFINITY),
    /**
     * Low-priority debugging messages
     */
    DEBUG(0, Double.POSITIVE_INFINITY),
    /**
     * used to indicate incorrect error level value supplied
     */
    INVALID(-1, -1);
    
    
    private final int order;
    private final double threshold;
    
    
    /**
     * 
     * Construct a new ErrorLevel object.
     *
     * @param order
     * @param threshold
     */
    ErrorLevel(int order, double threshold) {
        this.order = order;
        this.threshold = threshold;
    }

    /**
     * Returns the error level given a String 
     * or a default value if no error level found
     *
     * @param level
     * @return ErrorLevel
     */
    public static ErrorLevel fromString(String level) {
        try {
            return ErrorLevel.valueOf(level.toUpperCase());
        } catch (IllegalArgumentException e) {
            return INVALID;
        }
    }

    /**
     * Returns the error Level 
     *
     * @return int
     */
    public int getOrder() {
        return order;
    }

    /**
     * Returns the threshold value
     *
     * @return double
     */
    public double getThreshold() {
        return threshold;
    }
}
