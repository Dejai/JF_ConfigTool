import java.util.*; 
import java.io.*; 
import javax.swing.*;

public class Main {

	public static void main (String args []){

		// This boolean indicates whether or not I am testing out functionality.
		// It is passed to the Frames then Listeners objects, to be used appropriately for the filePaths object
		boolean isTest = args.length > 0 ? true : false; 
		Frames mainFrame = new Frames("Managing Config Files", isTest); 
		mainFrame.setVisible(true);

	}		
}
