import java.util.*; 
import java.io.*; 
import javax.swing.*;

public class Main {

	public static Frames mainFrame;

	public static void main (String args []){

		// This boolean indicates whether or not I am testing out functionality.

		boolean isTest = args.length > 0 ? true : false;

		String frameName = isTest ? "Testing Config Tool" : "Managing Config Tools";

		mainFrame = new Frames(frameName);

		FilePaths mainFilePaths = new FilePaths(isTest);
		
		Listeners mainListener = new Listeners(mainFrame, mainFilePaths);
		
		mainFrame.initFrame(mainFilePaths.opSystem);

		mainFrame.setVisible(true);
	}		

}
