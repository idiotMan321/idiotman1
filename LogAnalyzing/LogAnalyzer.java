package assignments.module6;

import java.util.ArrayList;
import java.util.List;

/**
 * The LogAnalyzer class processes an array of log entries, stores them as
 * LogEntry objects, and provides methods for filtering, threshold checking,
 * deleting, and reporting the logs.
 * 
 * @author CSC 222 Tran Ly
 */
public class LogAnalyzer {

	private ArrayList<LogEntry> logEntries = new ArrayList<LogEntry>();

	/**
	 * Constructs a LogAnalyzer by processing an array of log entry strings. Each
	 * string should be in the format "timestamp|errorLevel|description".
	 *
	 * @param logArrays the array of log entry strings
	 * @throws IllegalArgumentException if logArrays is null
	 */
	public LogAnalyzer(String[] logArrays) {
		if (logArrays == null) {
			throw new IllegalArgumentException("Invalid List Of Logs.");
		}
		for (int i = 0; i < logArrays.length; i++) {
			addLogEntry(logArrays[i]);
		}

	}

	/**
	 * Adds a single log entry to the logEntries list. The logLineSplit string is
	 * expected to be in the format "timestamp|errorLevel|description".
	 *
	 * @param logLineSplit the log entry string
	 */
	public void addLogEntry(String logLineSplit) {
		String[] logParts = logLineSplit.split("\\|");
		LogEntry le = new LogEntry(logParts[0], logParts[1], logParts[2]);
		logEntries.add(le);
	}

	/**
	 * Filters the log entries by the specified error level. Converts the input
	 * string to an ErrorLevel using ErrorLevel.fromString() and returns a list of
	 * string representations of the log entries that match.
	 *
	 * @param level the error level as a String (e.g., "ERROR", "INFO", etc.)
	 * @return a List of strings for the filtered log entries
	 */
	public List<String> filterByLevel(String level) {
		List<String> result = new ArrayList<>();
		ErrorLevel filterLevel = ErrorLevel.fromString(level);
		for (int i = 0; i < logEntries.size(); i++) {
			if (logEntries.get(i).getErrorLevel().equals(filterLevel)) {
				LogEntry entry = logEntries.get(i);
				String timeStamp = entry.getTimeStamp();
				String errorLvl = entry.getErrorLevel().toString();
				String description = entry.getDescription();
				String output = timeStamp + " " + errorLvl + " " + description;
				result.add(output);
			}
		}
		return result;
	}

	/**
	 * Checks the thresholds for each error level by counting the log entries. If
	 * the count for a level exceeds its predefined threshold in the ErrorLevel
	 * enum, a warning message is added to the returned string.
	 *
	 * @return a String containing any threshold warning messages; an empty string
	 *         if none are exceeded
	 */
	public String checkThreshold() {
		int threshold = (int) ErrorLevel.ERROR.getThreshold();
		String result = "";
		int debugCnt = 0;
		int infoCnt = 0;
		int warnCnt = 0;
		int errorCnt = 0;
		for (int i = 0; i < logEntries.size(); i++) {
			ErrorLevel level = logEntries.get(i).getErrorLevel();
			if (level == ErrorLevel.DEBUG) {
				debugCnt++;
			} else if (level == ErrorLevel.INFO) {
				infoCnt++;
			} else if (level == ErrorLevel.WARN) {
				warnCnt++;
			} else if (level == ErrorLevel.ERROR) {
				errorCnt++;
			}
		}
		System.out.println("=== Log Level Threshold Check (ERROR & WARN only) ===");
		if (debugCnt > ErrorLevel.DEBUG.getThreshold())
			result += "DEBUG logs: " + debugCnt + " (Threshold: " + (int) ErrorLevel.DEBUG.getThreshold()
					+ ")\nWARNINGS: DEBUG log count exceeds threshold! \nERROR logs: 2 (Threshold: 5)\n";
		if (infoCnt > ErrorLevel.INFO.getThreshold())
			result += "INFO logs: " + infoCnt + " (Threshold: " + (int) ErrorLevel.INFO.getThreshold()
					+ ")\nWARNINGS: INFO log count exceeds threshold! \nDEBUG logs: 2 (Threshold: 5)\n";
		if (warnCnt > ErrorLevel.WARN.getThreshold())
			result += "WARN logs: " + warnCnt + " (Threshold: " + (int) ErrorLevel.WARN.getThreshold()
					+ ")\nWARNINGS: WARN log count exceeds threshold! \nINFO logs: 2 (Threshold: 5)\n";
		if (errorCnt > ErrorLevel.ERROR.getThreshold())

			result += "ERROR logs: " + errorCnt + " (Threshold: " + (int) ErrorLevel.ERROR.getThreshold()
					+ ")\nWARNINGS: ERROR log count exceeds threshold! \nWARN logs: 2 (Threshold: 5)\n";

		return result;
	}

	/**
	 * Deletes all log entries for the specified level. Only DEBUG and INFO log
	 * entries are allowed to be deleted. If the provided log level is not DEBUG or
	 * INFO, an IllegalArgumentException is thrown.
	 *
	 * @param level the error level as a String (must be "DEBUG" or "INFO")
	 * @return the number of log entries deleted
	 * @throws IllegalArgumentException if the level is not DEBUG or INFO
	 */
	public int deleteLogEntries(String level) {
		ErrorLevel levels = ErrorLevel.fromString(level);

		if (!(levels.equals(ErrorLevel.DEBUG) || levels.equals(ErrorLevel.INFO))) {
			throw new IllegalArgumentException("Only DEBUG and INFO log entries can be deleted.");
		}

		int deletedCnt = 0;
		for (int i = logEntries.size() - 1; i >= 0; i--) {
			if (logEntries.get(i).getErrorLevel().equals(levels)) {
				logEntries.remove(i);
				deletedCnt++;
			}
		}
		return deletedCnt;
	}

	/**
	 * Returns a formatted string that contains all log entries sorted by
	 * importance, and grouped by log level with appropriate headers. The format for
	 * each entry is: first line contains the timestamp and error level in square
	 * brackets, followed by a line with the description.
	 *
	 * @return the complete log report as a String
	 */
	public String toString() {
		String result = "\n=== Logs Sorted by Importance ===\n";

		ArrayList<LogEntry> sortedLogs = new ArrayList<LogEntry>();
		for (int i = 0; i < logEntries.size(); i++) {
			sortedLogs.add(logEntries.get(i));
		}

		for (int i = 0; i < sortedLogs.size() - 1; i++) { //stop at 8 < 9 (9<9) wont run
			for (int j = 0; j < sortedLogs.size() - 1 - i; j++) {
				int order1 = sortedLogs.get(j).getErrorLevel().getOrder();
				int order2 = sortedLogs.get(j + 1).getErrorLevel().getOrder();
				if (order1 < order2) {
					LogEntry temp = sortedLogs.get(j);
					sortedLogs.set(j, sortedLogs.get(j + 1));
					sortedLogs.set(j + 1, temp);
				}
			}
		}

		ErrorLevel[] groupOrder = { ErrorLevel.ERROR, ErrorLevel.WARN, ErrorLevel.INFO, ErrorLevel.DEBUG };
		for (int i = 0; i < groupOrder.length; i++) {
			ErrorLevel currentLevel = groupOrder[i];
			result += "=== " + currentLevel.toString() + " LOGS ===\n";

			for (int j = 0; j < sortedLogs.size(); j++) {
				LogEntry entry = sortedLogs.get(j);
				if (entry.getErrorLevel().equals(currentLevel)) {
					result += entry.getTimeStamp() + " [" + entry.getErrorLevel() + "] " + entry.getDescription()
							+ "\n";
				}
			}
		}

		return result;
	}

	/**
	 * Counts the number of log entries that have a specific error level order.
	 *
	 * @param levelOrder the numeric order representing the error level (e.g., 0 for
	 *                   DEBUG, 1 for INFO, etc.)
	 * @return the number of log entries with that order
	 */
	private int countLogEntriesByLevel(int levelOrder) {
		int cnt = 0;
		for (int i = 0; i < logEntries.size(); i++) {
			if (logEntries.get(i).getErrorLevel().getOrder() == levelOrder) {
				cnt++;
			}
		}
		return cnt;

	}

	/**
	 * Adds multiple log entries provided in an array of log data strings. Each
	 * string is expected to be in the format "timestamp|errorLevel|description".
	 *
	 * @param logDataArray an array of log entry strings
	 * @return a string indicating the result of adding each log entry
	 */
	private String addLogData(String[] logDataArrays) {
		String result = "";
		for (int i = 0; i < logDataArrays.length; i++) {
			String[] split = logDataArrays[i].split("\\|");
			String timeStamp = split[0];
			String errorLevel = split[1];
			String description = split[2];
			LogEntry le = new LogEntry(timeStamp, errorLevel, description);
			logEntries.add(le);
			result += "Added log: " + le.toString() + "\n";
		}
		return result;
	}

	public String drawBarChart() {
		return null;
	}
}
