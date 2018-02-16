import java.util.*;
import javax.swing.*;
import java.awt.*;

public class Frames extends JFrame {

	// JComponent Attributes
		// Main Window & Content Panel
			JPanel contentPane = new JPanel();

		// Bottom Section
			JPanel bottomPanel = new JPanel(new FlowLayout());
			JLabel poweredByLabel = new JLabel("");

		
		//Left Side 
			JPanel leftPanel = new JPanel(new FlowLayout());
			JPanel innerLeftPanel = new JPanel(new GridBagLayout());

			JLabel menuLabel = new JLabel("Menu:");

			JButton processImages = new JButton("Process Images");
			JButton aboutMe = new JButton("About Me");
		
		// Right Side
			JPanel rightPanel = new JPanel(new FlowLayout());
			JPanel innerRightPanel = new JPanel(new GridBagLayout());

			JLabel getStartedLabel = new JLabel("Click on a menu option to get started.");


			JLabel compressImagesReminder = new JLabel("<html><p>Remember to compress your images!</p></html>");
			JButton useTinyPng = new JButton("<html><span id='useTinyPNG'></span><font color='blue'><strong>Click here to go to TinyPng.com</strong></font></html>");
			JButton startImageProcessing = new JButton("Start Processing");


			// Processing Images
				JLabel newPhotosLabel = new JLabel("Any New Photos?");
				JLabel newPhotosSentence = new JLabel("Select any album that has new photos.");
				JPanel newPhotosAlbumPanel = new JPanel(new GridLayout(1,1));
				int vsp = JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED; // vertical scroll policy
				int hsp = JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED; //horizontal scroll policy
				JScrollPane newPhotosAlbumListing = new JScrollPane(newPhotosAlbumPanel, vsp ,hsp );
				JButton continueProcessingPhotos = new JButton ("Continue Processing");


				JPanel imagePanel = new JPanel(new GridLayout(0,1));
				JLabel processingImage = new JLabel("");

				JLabel processingNow = new JLabel("");
				JLabel workingOn = new JLabel("Working on:");

			// About Me Sections
				JLabel editAboutMe = new JLabel("Edit About Me");
				JButton toggleAboutMeEditor = new JButton("Preview");
				JButton saveAboutMe = new JButton("Save");

				JPanel aboutMePanel = new JPanel(new GridLayout(0,1));
				JEditorPane aboutMeTextEditor = new JEditorPane();
				// JTextPane aboutMeTextEditor = new JTextPane();
				JScrollPane aboutMeScrollPane = new JScrollPane(aboutMeTextEditor);
				String plainText = "";
				// String htmlHelpText = "Add a new line:\t<br/>";

			// Help Info for Styling About Me Text
				JLabel htmlHelpLabel = new JLabel("How To Style the Text");
				// JComboBox<String> htmlHelpDropdown;
				HTMLExamples html = new HTMLExamples();
				JComboBox<String> htmlHelpDropdown = new JComboBox<String>(html.examples);
				JLabel htmlExampleArea = new JLabel("");

		// Containers of Components
			JComponent [] actionableButtons = { processImages, aboutMe, useTinyPng, 
												toggleAboutMeEditor, saveAboutMe, 
												startImageProcessing, htmlHelpDropdown,
												continueProcessingPhotos };
			// JComponent [] actionableButtons = { processImages, aboutMe, useTinyPng, toggleAboutMeEditor };
			JLabel [] subheaders= {htmlHelpLabel, newPhotosSentence};
			JLabel [] headers= {menuLabel, newPhotosLabel, processingNow, editAboutMe, getStartedLabel, compressImagesReminder };
			JComponent [] leftSide = {leftPanel, innerLeftPanel, bottomPanel};
			JComponent [] rightSide = {rightPanel, innerRightPanel };


	// public Frames( String title, boolean testState, Listeners frameListener ){
	public Frames( String title ){

		super ( title );
		setSize(800, 500);
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new GridBagLayout());
	}

	public static void ToString(){
		System.out.println("This is the main -- OG -- Frame");
	}

	public void initFrame(String opSystem){

		//  Style & Text
		for (JLabel subH : subheaders){
			subH.setFont(new Font("Arial", Font.BOLD, 16));
		}

		for (JLabel header : headers){
			header.setFont(new Font("Arial", Font.BOLD, 22));
		}

		for (JComponent left : leftSide){
			left.setBackground(Color.LIGHT_GRAY);
		}
		this.getContentPane().setBackground(Color.GRAY);
		bottomPanel.setBackground(Color.GRAY);

		for (JComponent right : rightSide){
			right.setBackground(new Color(0.2f, 0.5f, 0.6f));
		}
		poweredByLabel.setText(String.format("<html><div style='color:white;'>Powered By: <span style='font-weight:bold; font-style:italics'>%s</span></div></html>", opSystem));


		// Adding Components to panels
		innerLeftPanel.add(menuLabel, new GridBagParams("menuLabel"));
		innerLeftPanel.add(processImages, new GridBagParams("processImagesButton"));
		innerLeftPanel.add(aboutMe, new GridBagParams("aboutMeButton"));
		innerLeftPanel.add(htmlHelpLabel, new GridBagParams("htmlHelpLabel"));
		htmlHelpLabel.setVisible(false);
		innerLeftPanel.add(htmlHelpDropdown, new GridBagParams("htmlHelpDropdown"));
		htmlHelpDropdown.setVisible(false);
		innerLeftPanel.add(htmlExampleArea, new GridBagParams("htmlExampleArea"));
		htmlExampleArea.setVisible(false);

		innerRightPanel.add(getStartedLabel, new GridBagParams("getStartedLabel"));

		// Adding panels to the frame
		leftPanel.add(innerLeftPanel);
		this.add(leftPanel, new GridBagParams("leftPanel"));
		
		bottomPanel.add(poweredByLabel);
		this.add(bottomPanel, new GridBagParams("bottomPanel"));

		rightPanel.add(innerRightPanel);
		this.add(rightPanel, new GridBagParams("rightPanel"));
	}


	public void resultsMessageDialog(boolean success, String msg){
		String message = !msg.isEmpty() ? msg : "Unknown Error!";
		if (success){
			JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
		}
	}


}