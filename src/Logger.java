import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;



public class Logger{

	private static BufferedWriter logger;


	public static void startNewLog(String fileParam){
		DateFormat dateFormat = new SimpleDateFormat("EEE, MMMMM d - HH:mm");
		Date dateTime = Calendar.getInstance().getTime();
		String logTimeStamp = dateFormat.format(dateTime);
		writeToLogFile(fileParam, logTimeStamp, false);
		addBlankLine(fileParam);
	}
	
	public static void logData(String fileParam, String logData){
		writeToLogFile(fileParam, logData, true);
	}

	public static void addBlankLine(String fileParam){
		writeToLogFile(fileParam, " ", true);
	}

	public static void logError(String fileParam, String errorMessage){
		String errorIndicator = "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^";
		addBlankLine(fileParam);
		logData(fileParam, "ERROR: ");
		logData(fileParam, errorMessage);

		logData(fileParam, errorIndicator);
		addBlankLine(fileParam);
	}

	public static void writeToLogFile(String fileParam, String logData, boolean isOverwrite){
		try{
			File logFile = new File(fileParam);
			logger = new BufferedWriter(new FileWriter(logFile, isOverwrite));
			logger.write(logData);
			if (isOverwrite){
				logger.newLine();
			}
		} catch (Exception ex){
			System.out.println(ex.getMessage());
		} finally {
			try{
				logger.close();
			} catch (Exception ex){
				System.out.println(ex.getMessage());
			}
		}
	}

}
