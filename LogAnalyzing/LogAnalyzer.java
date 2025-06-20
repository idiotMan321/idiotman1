package assignments.module6;

import java.util.ArrayList;
import java.util.List;

/**
 * The LogAnalyzer class processes an array of log entries, stores them as
 * LogEntry objects, and provides methods for filtering, threshold checking,
 * deleting, and reporting the logs.
 * Test
 * @author Tran Ly
 */
public class LogAnalyzer {

	private ArrayList<LogEntry> logEntries = new ArrayList<LogEntry>();

	/**
	 * Constructs a LogAnalyzer in format "timestamp|errorLevel|description".
	 *
	 * @param logArrays the array of log entry strings
	 * @throws IllegalArgumentException if logArrays is null
	 */
	public LogAnalyzer(String[] logArrays) {
		if (logArrays == null) {
			throw new IllegalArgumentException("Invalid logs.");
		}

		for (int i = 0; i < logArrays.length; i++) {
			addLogEntry(logArrays[i]);
		}
	}

	/**
	 * logLineSplit string is in the format "timestamp|errorLevel|description".
	 *
	 * @param logLineSplit the log entry string
	 */
	public void addLogEntry(String logLineSplit) {
		String[] splitting = logLineSplit.split("\\|");
		LogEntry le = new LogEntry(splitting[0], splitting[1], splitting[2]);
		logEntries.add(le);
	}

	/**
	 * Filters the log entries by the specified error level. Converts the input
	 * string to an ErrorLevel using ErrorLevel.fromString() 
	 * @param level the error level as a String (e.g., "ERROR", "INFO", etc.)
	 * @return a List of strings for the filtered log entries
	 */
	public List<String> filterByLevel(String level) {
		ErrorLevel lvl = ErrorLevel.fromString(level);
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < logEntries.size(); i++) {
			if (logEntries.get(i).getErrorLevel().equals(lvl)) {
				LogEntry logE = logEntries.get(i);
				String timeStamp = logE.getTimeStamp();
				String errLvl = logE.getErrorLevel().toString();
				String desc = logE.getDescription();
				String op = timeStamp + " [" + errLvl + "] " + desc + "\n";
				result.add(op);
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
		int numberErrLvl = 0;
		int numberWarnLvl = 0;
		for (int i = 0; i < logEntries.size(); i++) {
			if (logEntries.get(i).getErrorLevel().equals(ErrorLevel.ERROR)) {
				numberErrLvl += 1;
			} else if (logEntries.get(i).getErrorLevel().equals(ErrorLevel.WARN)) {
				numberWarnLvl += 1;
			}
		}
		String op = "ERROR logs: " + numberErrLvl + " (Threshold: " + (int) ErrorLevel.ERROR.getThreshold() + ")\n";
		op += "WARN logs: " + numberWarnLvl + " (Threshold: " + (int) ErrorLevel.WARN.getThreshold() + ")\n";
		if (numberErrLvl > 1) {
			op += "Error exceed!\n";
		}
		if (numberWarnLvl > 5) {
			op += "Warn exceeds";
		}

		return op;
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
		ErrorLevel e = ErrorLevel.fromString(level);
		String result;
		if (!(e.equals(ErrorLevel.DEBUG) || e.equals(ErrorLevel.INFO))) {
			throw new IllegalArgumentException("NOT DEBUG OR INFO");
		} else {
			for (int i = 0; i < logEntries.size(); i++) {
				if (logEntries.get(i).getErrorLevel().equals(level)) {
					logEntries.remove(i);
				}
			}
		}
		return 1;
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
		String output = "";
		System.out.println("=== Logs Sorted by Importance ===");
		for (int i = 0; i < logEntries.size() - 1; i++) {
			for (int j = i; j < logEntries.size() - 1; j++) {
				int c1 = logEntries.get(i).getErrorLevel().getOrder();
				int c2 = logEntries.get(j + 1).getErrorLevel().getOrder();
				if (c1 < c2) {
					LogEntry small = logEntries.get(i);
					LogEntry large = logEntries.get(j + 1);
					logEntries.set(i, large);
					logEntries.set(j + 1, small);
				}
			}
		}
		ErrorLevel[] logGroups = { ErrorLevel.ERROR, ErrorLevel.WARN, ErrorLevel.INFO, ErrorLevel.DEBUG };
		for (int i = 0; i < logGroups.length; i++) {
			output += "=== " + logGroups[i] + " LOGS ===\n";
			for (int j = 0; j < logEntries.size(); j++) {
				if (logEntries.get(j).getErrorLevel().equals(logGroups[i])) {
					output += logEntries.get(j).getTimeStamp() + " [" + logEntries.get(j).getErrorLevel() + "] "
							+ logEntries.get(j).getDescription() + "\n";
				}
			}
		}
		return output;
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
				cnt += 1;
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
		String[] unknownArrays;
		String result = "";
		for (int i = 0; i < logDataArrays.length; i++) {
			String[] split = logDataArrays[i].split("\\|");
			String ts = split[0];
			String el = split[1];
			String d = split[2];
			LogEntry les = new LogEntry(ts, el, d);
			result += "Added logs: " + les.toString() + "\n";
		}
		return result;
	}

	public String drawBarChart() {
		return null;
	}
}
