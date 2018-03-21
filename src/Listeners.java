import java.util.*;
import java.net.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.Component;
import java.awt.event.*;

public class Listeners extends JFrame {

	protected String opSystem;

	private FilePaths filePaths;

	private Frames theMainFrame;

	private static String plainText;

	private ArrayList<String> newPhotosAlbums = new ArrayList<String>();


	public Listeners(Frames mainFrame, FilePaths mainFilePaths){

		theMainFrame = mainFrame;

		filePaths = mainFilePaths; 

		for (JComponent actionable : theMainFrame.actionableButtons){
			String action = parseAction(actionable.toString());				
			if (actionable instanceof JButton){
				addListeners( (JButton) actionable, action );
			} else  if (actionable instanceof JComboBox){
				addListeners( (JComboBox) actionable, action );
			}
		}
	}

	/*	addListeners( JButton, String):
			>> This function adds an event listener to the given JButton
			>> It dynamically assigns the action function via assignListenerFunction()
	*/
	public void addListeners(JButton button, String action){ 
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				assignListenerFunction(action);
			}
		});
	}

	/*	@Overload: addListeners( JComboBox, String):
			>> This function adds an event listener to the given JComboBox
			>> It dynamically assigns the action function via assignListenerFunction()
	*/
	public void addListeners(JComboBox box, String action){ 
		box.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				assignListenerFunction(action);
			}
		});
	}

	// @Overload
	// public void addListeners(JLabel label, String action){ 
	// 	label.addMouseListener(new MouseListener(){
	// 		public void mouseClicked(MouseEvent e){
	// 			assignListenerFunction(action);
	// 		}
	// 		public void mouseEntered(MouseEvent e){}
	// 		public void mouseExited(MouseEvent e){}
	// 		public void mousePressed(MouseEvent e){}
	// 		public void mouseReleased(MouseEvent e){}
	// 	});
	// }

	public void assignListenerFunction(String action){
		switch (action){
			case "Process Images" :
				showImagePreProcessing();
				break;
			case "About Me":
				showAboutMesection();
				break;
			case "useTinyPNG":
				openWebsite("http://tinypng.com");
				break;
			case "Preview":
			case "Edit":
				toggleAboutMeEditor();
				break;
			case "htmlHelpDropdown":
				selectHTMLExamples();
				break;
			case "Save":
				saveAboutMe();
				break;
			case "Start Processing":
				// setNewPhotosIndicator();
				startImageThreads();
				break;
			case "Continue Processing":
				startImageThreads();
				break;
			case "BetaSite":
				openWebsite("http://localhost");
				break;
			default:
				System.out.println(action);
				// System.out.println(actionable.toString());
		}
	}



/* HELPER FUNCTIONS */
	
	public void openWebsite(String site){
		if (Desktop.isDesktopSupported()) {
			try {
				URI website = new URI(site);
	        	Desktop.getDesktop().browse(website);
		   	} catch (URISyntaxException ex){
		   		System.out.println(ex.getMessage());
		   		theMainFrame.resultsMessageDialog(false, ex.getMessage());
			} catch (IOException ex) { /* TODO: error handling */ 
		   		theMainFrame.resultsMessageDialog(false, ex.getMessage());
		   	} catch (Exception ex){
		   		theMainFrame.resultsMessageDialog(false, ex.getMessage());
			}
		} else { 
			String errorMessage = String.format("Desktop access is not supported. Cannot open the site from here. Go to %s directly", site);
			theMainFrame.resultsMessageDialog(false, errorMessage);
		}
	}

	public static boolean checkIfIsImage(String imagePath){
		boolean isImage; 
		if (imagePath.contains(".")) {
			String extension = imagePath.substring(imagePath.lastIndexOf("."));
			switch(extension){
				case ".jpg":
				case ".png":
				case ".gif":
					isImage = true;
					break;
				default:
					isImage = false; 
			}
		} else {
			isImage = false; 
		}
		return isImage;
	} 

	public static String parseAction(String value){
		int firstIndex = value.indexOf("text=") > 0 ? value.indexOf("text=") : 0;
		String firstSub = value.substring(firstIndex); 
		boolean isHTML = firstSub.indexOf("<html>") > 0 ? true : false;
		boolean isComboBox = firstSub.indexOf("JComboBox") > 0 ? true : false;
		String action;
		if (isHTML){
			action = firstSub.substring(firstSub.indexOf("'")+1, firstSub.indexOf("'>"));
		} else if (isComboBox){
			action = "htmlHelpDropdown";
		} else {
			action = firstSub.substring(0, firstSub.indexOf(",")).split("=")[1];
		}
		return action;
	}

/* ACTION METHODS - called from Event Listeners */


//  Image Processes
	public void addListOfAlbums(){
		try{
			ArrayList<String> galleryAlbums = FilesCRUD.getGalleryAlbums(filePaths.galleryDirectoryPath, filePaths.separator);
			theMainFrame.clearPanel(theMainFrame.newPhotosAlbumPanel);
			newPhotosAlbums.clear();

			for (String album : galleryAlbums){
				String albumName = album.substring(album.lastIndexOf(filePaths.separator)+1);
				JCheckBox thisCheck = new JCheckBox(albumName);
				theMainFrame.newPhotosAlbumPanel.add(thisCheck);
				thisCheck.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						if (thisCheck.isSelected()){
							newPhotosAlbums.add(albumName);
						} else if (newPhotosAlbums.contains(albumName) && !thisCheck.isSelected()) {
							newPhotosAlbums.remove(albumName);
						}
					}
				});
			}
			theMainFrame.validateView();
		} catch (Exception ex){
			theMainFrame.resultsMessageDialog(false, ex.getMessage());
		}
	}

	public void showImagePreProcessing(){
		theMainFrame.loadView("Pre-Image Processing");
		addListOfAlbums();
		theMainFrame.validateView();
	}

	public void startImageThreads(){
		try{
			ImagesThread showImagesThread = new ImagesThread(this, "show");
			showImagesThread.start(); //calls showImageProcessingSection()
			showImagesThread.join();

			ImagesThread startImagesThread = new ImagesThread(this, "start");
			if (!showImagesThread.isAlive()){
				startImagesThread.start(); // calls processImages()
			}
 		} catch(InterruptedException ie){
 			theMainFrame.resultsMessageDialog(false, ie.getMessage());
 		}	
	}

	public void showProcessingImage(){
		try{
			theMainFrame.loadView("Processing Image");
			String procImg = filePaths.getRandomGIF();
			theMainFrame.processingImage.setIcon(new ImageIcon(procImg));
			theMainFrame.imagePanel.add(theMainFrame.processingImage);

			theMainFrame.validateView();
		} catch (Exception ex){
			theMainFrame.resultsMessageDialog(false, ex.getMessage());
		}
	}

	public void processImages(){
		try{
			Logger.startNewLog(filePaths.processImagesLogFilePath);

			theMainFrame.workingOn.setText("Working on:");

			ArrayList<Album> albumsList = new ArrayList<Album>();
			
			Logger.addBlankLine(filePaths.processImagesLogFilePath);
			Logger.logData(filePaths.processImagesLogFilePath, filePaths.profileDirectoryPath);
			

			albumsList.add(createAlbumFromDirectory(filePaths.profileDirectoryPath));

			// Process the slideshow pictures
			Logger.addBlankLine(filePaths.processImagesLogFilePath);
			Logger.logData(filePaths.processImagesLogFilePath, filePaths.slideshowDirectoryPath);
			

			albumsList.add(createAlbumFromDirectory(filePaths.slideshowDirectoryPath));

			// First get the gallery albums, then process each one. 
			ArrayList<String> galleryAlbums = FilesCRUD.getGalleryAlbums(filePaths.galleryDirectoryPath, filePaths.separator);
			for (String gal : galleryAlbums){
				Logger.addBlankLine(filePaths.processImagesLogFilePath);
				Logger.logData(filePaths.processImagesLogFilePath, gal);
				albumsList.add(createAlbumFromDirectory(gal));
			}

			// Attempt to write the JSON file for all the albums
			boolean oneBool = FilesCRUD.writeJSONFile(filePaths.albumsJSONPath, albumsList);


			String successMesage = "<span style='color:green;font-weight:bold'>SUCCESS:</span> Images were processed successfully.";
			String failMessage = "<span style='color:red;font-weight:bold'>ERROR:</span>Could not complete the process.";

			String resultsMessage = oneBool ? successMesage : failMessage;
			String resultsMessageFormatted = String.format("<html> %s </html>", resultsMessage);

			if (oneBool){
				theMainFrame.resultsMessageDialog(true, resultsMessageFormatted);
				theMainFrame.loadView("Images Processed");
				theMainFrame.processingImage.setIcon(new ImageIcon(filePaths.successProcessingImg));
				// theMainFrame.workingOn.setText("");
				// theMainFrame.processingImage.setIcon(new ImageIcon(filePaths.successProcessingImg));
				// theMainFrame.innerRightPanel.remove(theMainFrame.workingOn);
				// // theMainFrame.innerRightPanel.remove(theMainFrame.processingImage);				
			} else {
				theMainFrame.workingOn.setText("<html>Something went wrong. To try and remedy this, go to the 'src' folder and click on the filePermission file (the one with the gear icon).</html>");
				theMainFrame.processingImage.setIcon(new ImageIcon(filePaths.oopsImg));
				theMainFrame.resultsMessageDialog(false, resultsMessageFormatted);
			}
		} catch (Exception ex){
			theMainFrame.resultsMessageDialog(false, ex.getMessage());
		} finally {
			theMainFrame.processingNow.setText("Processing Images");
			theMainFrame.toggleActionableButtons(true);
			theMainFrame.addLogMessage( FilesCRUD.getLogMessages(filePaths.processImagesLogFilePath) );
			theMainFrame.validateView();			
		}
	}

	public Album createAlbumFromDirectory(String directoryPath){
		try{
			File dir = new File(directoryPath);
			File dirList[] = dir.listFiles();
			String albumName = directoryPath.substring(directoryPath.lastIndexOf(filePaths.separator)+1);

			// System.out.println(newPhotosAlbums.toString());
			Album temp = new Album(albumName);
			if (newPhotosAlbums.contains(albumName)){
				temp.setNewPhotosIndicators();
				// System.out.println(albumName + " HAS NEW PHOTOS");
			}
			for (int x = 0; x < dirList.length; x++){
				boolean isImage; 
				String dimensionTemp = "";
				isImage = checkIfIsImage(dirList[x].getPath());
				String startedProcessingMessage = String.format("%s\t%s", dirList[x].getPath(), "<--> started processing");
				Logger.logData(filePaths.processImagesLogFilePath, startedProcessingMessage);

				if (!dirList[x].isDirectory() && isImage){

					BufferedImage imageB = ImageIO.read(new File(dirList[x].getPath()));
					theMainFrame.workingOn.setText(String.format("<html>Working on:&nbsp;<span style='font-weight:bold; color:white;'>%s</span></html>", dirList[x].getPath()));

					if (imageB.getHeight() > imageB.getWidth()){
						dimensionTemp = "portrait";
					} else if ( imageB.getWidth() > imageB.getHeight() ){
						dimensionTemp = "landscape";
					} else if (imageB.getWidth() == imageB.getHeight() ) {
						dimensionTemp = "square";
					} else {
						dimensionTemp = "landscape";
					}

					temp.addPicture(dirList[x].getPath(), imageB.getWidth(), imageB.getHeight(), dimensionTemp);

					String processedMessage = String.format("%s\t%s", dirList[x].getPath(), "<--> successfully processed");
					Logger.logData(filePaths.processImagesLogFilePath, processedMessage);
					Logger.addBlankLine(filePaths.processImagesLogFilePath);

					if (!temp.hasCoverImage || dirList[x].getPath().contains("cover_") ){
						temp.setCoverImage(dirList[x].getPath());
					}
				} else {
					String errorMessage = String.format("%s\nDetails:\t%s", dirList[x].getPath(), "This image is not considered an image that can be added");
					Logger.logError(filePaths.processImagesLogFilePath, errorMessage );
				}
			}
			return temp;
		} catch (Exception ex){
			Logger.logError(filePaths.processImagesLogFilePath, ex.getMessage());
		    System.out.println(ex.getMessage());
		 	ex.printStackTrace();
		 	return null;
		}
	}

 

// About Me Processes

	public void selectHTMLExamples(){
		String example = theMainFrame.htmlHelpDropdown.getSelectedItem().toString();
		theMainFrame.htmlExampleArea.setText(HTMLExamples.htmlExamples.get(example));
	}

	public void showAboutMesection(){
		theMainFrame.loadView("About Me");
		theMainFrame.aboutMeTextEditor.setText(FilesCRUD.getAboutMeText(filePaths.aboutMeFilePath));
		theMainFrame.validateView();
	}

	public void saveAboutMe(){
		try{
			Logger.startNewLog(filePaths.aboutMeLogFilePath);

			String saveText;
			boolean isSaved; 
			if (theMainFrame.aboutMeTextEditor.getContentType() == "text/html"){
				isSaved = FilesCRUD.writeAboutMeText(filePaths.aboutMeFilePath, plainText);
			} else {
				isSaved = FilesCRUD.writeAboutMeText(filePaths.aboutMeFilePath, theMainFrame.aboutMeTextEditor.getText());
			}
			
			String message = isSaved ? "file successfully updated" : "file <strong>NOT</strong> updated!";
			String messageHTML = String.format("<span style='font-style:italics'>'About Me'</span> %s", message);
			String color = isSaved ? "green" : "red";
			String results = isSaved ? "SUCCESS" : "ERROR";
			String resultsHTML = String.format("<span style='color:%s;font-weight:bold'>%s</span>", color, results);
			String fullMessage = String.format("<html>%s:  %s</hml>", resultsHTML, messageHTML);


			Logger.addBlankLine(filePaths.aboutMeLogFilePath);
			Logger.logData(filePaths.aboutMeLogFilePath, message);
			Logger.addBlankLine(filePaths.aboutMeLogFilePath);
			Logger.logData(filePaths.aboutMeLogFilePath, theMainFrame.aboutMeTextEditor.getText());
			
			theMainFrame.resultsMessageDialog(isSaved, fullMessage);
			theMainFrame.aboutMeTextEditor.setText("");
			theMainFrame.aboutMeTextEditor.setText(FilesCRUD.getAboutMeText(filePaths.aboutMeFilePath));


		} catch (Exception ex){
			Logger.logError(filePaths.aboutMeLogFilePath, ex.getMessage());
			theMainFrame.resultsMessageDialog(false, ex.getMessage());
		}
	}

	public void toggleAboutMeEditor(){
		try{

			String contentType = theMainFrame.aboutMeTextEditor.getContentType();
			if(contentType == "text/plain"){
				theMainFrame.toggleAboutMeEditor.setText("Edit");
				plainText = theMainFrame.aboutMeTextEditor.getText();
				theMainFrame.aboutMeTextEditor.setBackground(Color.BLACK);
				theMainFrame.aboutMeTextEditor.setForeground(Color.WHITE);
				theMainFrame.aboutMeTextEditor.setContentType("text/html");
				theMainFrame.aboutMeTextEditor.setEditable(false);
				String allWhite = "<style> body { color:white; } </style><div>";
				String htmlVersion = allWhite.concat(plainText).concat("</div>");
				theMainFrame.aboutMeTextEditor.setText(htmlVersion);
			} else {

				theMainFrame.toggleAboutMeEditor.setText("Preview");
				theMainFrame.aboutMeTextEditor.setBackground(Color.WHITE);
				theMainFrame.aboutMeTextEditor.setForeground(Color.BLACK);
				theMainFrame.aboutMeTextEditor.setContentType("text/plain");
				theMainFrame.aboutMeTextEditor.setEditable(true);
				theMainFrame.aboutMeTextEditor.setText(plainText);
			}
		} catch (Exception ex){
			theMainFrame.resultsMessageDialog(false, ex.getMessage());
		}
	}

}