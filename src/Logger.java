import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;


public class Logger{

	private static BufferedWriter logger;


	public static void logData(String fileParam){
		Calendar date = Calendar.getInstance();
		int year = date.get(date.YEAR);
		int month = date.get(date.MONTH)+1; 
		int day = date.get(date.DAY_OF_MONTH);
		String logTimeStamp = String.format("%d-%02d-%02d", year, month, day);
		writeToLogFile(fileParam, logTimeStamp, false);
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
