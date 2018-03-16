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


			JLabel compressImagesReminder = new JLabel("<html><p>Step #1: Did you compress your images!?</p></html>");
			JButton useTinyPng = new JButton("<html><span id='useTinyPNG'></span><font color='blue'><strong>Click here to go to TinyPng.com</strong></font></html>");
			JButton openLocalhost = new JButton("Preview Beta Site");
			JButton startImageProcessing = new JButton("Start Processing");


			// Processing Images
				JLabel newPhotosLabel = new JLabel("Step #2: Does any album have NEW photos?");
				// JLabel newPhotosSentence = new JLabel("Select any album that has new photos.");
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
												continueProcessingPhotos, openLocalhost };
			// JComponent [] actionableButtons = { processImages, aboutMe, useTinyPng, toggleAboutMeEditor };
			JLabel [] subheaders= {htmlHelpLabel, newPhotosLabel, compressImagesReminder};
			JLabel [] headers= {menuLabel, processingNow, editAboutMe, getStartedLabel };
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

		// innerLeftPanel.add(menuLabel, new GridBagParams("menuLabel", 0));
		addToPanel(innerLeftPanel, menuLabel, "menuLabel");
		addToPanel(innerLeftPanel, openLocalhost, "menuButton");

		// innerLeftPanel.add(processImages, new GridBagParams("processImagesButton"));
		// innerLeftPanel.add(processImages, new GridBagParams("menuButton", 2));
		// innerLeftPanel.add(processImages, new GridBagParams("menuButton", 1));
		addToPanel(innerLeftPanel, processImages, "menuButton");

		// innerLeftPanel.add(aboutMe, new GridBagParams("aboutMeButton"));
		// innerLeftPanel.add(aboutMe, new GridBagParams("menuButton", 3));
		// innerLeftPanel.add(aboutMe, new GridBagParams("menuButton", 2));
		addToPanel(innerLeftPanel, aboutMe, "menuButton");


		// innerLeftPanel.add(htmlHelpLabel, new GridBagParams("htmlHelpLabel"));
		// innerLeftPanel.add(htmlHelpLabel, new GridBagParams("menuLabel", 3));
		addToPanel(innerLeftPanel, htmlHelpLabel, "menuLabel", false);

		// htmlHelpLabel.setVisible(false);
		// innerLeftPanel.add(htmlHelpDropdown, new GridBagParams("htmlHelpDropdown"));
		// innerLeftPanel.add(htmlHelpDropdown, new GridBagParams("menuDropdown", 4));
		addToPanel(innerLeftPanel, htmlHelpDropdown, "menuDropdown", false);

		// htmlHelpDropdown.setVisible(false);

		// innerLeftPanel.add(htmlExampleArea, new GridBagParams("htmlExampleArea"));
		// innerLeftPanel.add(htmlExampleArea, new GridBagParams("menuSection", 5));
		addToPanel(innerLeftPanel, htmlExampleArea, "menuSection", false);

		// htmlExampleArea.setVisible(false);

		innerRightPanel.add(getStartedLabel, new GridBagParams("getStartedLabel"));

		// Adding panels to the frame
		leftPanel.add(innerLeftPanel);
		this.add(leftPanel, new GridBagParams("leftPanel"));
		
		bottomPanel.add(poweredByLabel);
		this.add(bottomPanel, new GridBagParams("bottomPanel"));

		rightPanel.add(innerRightPanel);
		this.add(rightPanel, new GridBagParams("rightPanel"));
	}

	/*	clearPanel (JComponent):
			>> This function clears the given panel of all of its components
	*/
	public void clearPanel(JComponent panel){
		htmlHelpLabel.setVisible(false);
		htmlHelpDropdown.setVisible(false);
		htmlExampleArea.setVisible(false);
		panel.removeAll();
		panel.revalidate();
		panel.repaint();
		innerRightPanel.setPreferredSize(new Dimension(rightPanel.getWidth()-100, rightPanel.getHeight()-50));
	}

	public void validateView(){
		rightPanel.validate();
	}

	public void loadView(String view){
		switch(view){
			case "Pre-Image Processing":
				loadPreImageProcessing();
				break;
			default:
				// loadError();
				System.out.println("Error!");
		}
	}

	public void loadPreImageProcessing(){
		// clearPanel(this.innerRightPanel);
		// this.innerRightPanel.add(this.compressImagesReminder, new GridBagParams("compressImagesReminder"));
		processingNow.setText("<html>Processing Images</html>");
		addToPanel(innerRightPanel, processingNow, "viewAreaLabel");
		addToPanel(innerRightPanel, compressImagesReminder, "menuLabel");
		addToPanel(innerRightPanel, useTinyPng, "viewAreaButton");
		addToPanel(innerRightPanel, newPhotosLabel, "menuLabel");
		addToPanel(innerRightPanel, newPhotosAlbumListing, "viewAreaMainSection");
		addToPanel(innerRightPanel, startImageProcessing, "viewAreaButton");
		// this.innerRightPanel.add(this.useTinyPng, new GridBagParams("useTinyPng"));
		// this.innerRightPanel.add(this.startImageProcessing, new GridBagParams("startImageProcessing"));
	}


	/*	addToPanel(JPanel, JComponent, String):
			>> This function adds a new component to the given panel
			>> Uses getNumberOfComponents() to account for which row to add the component
	*/ 
	public void addToPanel(JPanel panel, JComponent component, String gridBagLabel){
		int row = getNumberOfComponents(panel);
		panel.add(component, new GridBagParams(gridBagLabel,row));
	}
	
	/* 	@Overload - addToPanel(JPanel, JComponent, String, Boolean):
			>> This function adds a new component to the given panel
			>> Uses getNumberOfComponents() to account for which row to add the component
			>> This also sets the visibility of the JComponent
	*/ 
	public void addToPanel(JPanel panel, JComponent component, String gridBagLabel, boolean showComponent){
		int row = getNumberOfComponents(panel);
		panel.add(component, new GridBagParams(gridBagLabel,row));
		component.setVisible(showComponent);
	}

	public int getNumberOfComponents(JComponent component){
		return component.getComponents().length;
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