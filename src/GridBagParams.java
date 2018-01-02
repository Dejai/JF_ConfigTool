import java.util.*;
import java.awt.*;

public class GridBagParams extends GridBagConstraints {

	GridBagParams(String type){
		super ();
		returnConstraints(type);
	}

	public void returnConstraints(String type){
		switch( type ){
			case "menuLabel":
				this.fill = GridBagConstraints.HORIZONTAL;
				this.weightx = 0.5;				
				this.gridx = 0; 
				this.gridy = 0;
				this.ipadx = 50;
				this.insets = new Insets(10,5,0,10);  //top padding
				break;
			case "processImagesButton":
				this.weightx = 0.5;				
				this.gridx = 0; 
				this.gridy = 1;
				this.insets = new Insets(10,20,0,0);
				break;
			case "aboutMeButton" :
				this.weightx = 0.5;				
				this.gridx = 0; 
				this.gridy = 2;
				this.insets = new Insets(10,20,0,0); 
				break;
			case "getStartedLabel":
				this.insets = new Insets(40,0,0,0); 
				break;
			case "bottomPanel" :
				this.ipady = 0;       //reset to default
				this.anchor = GridBagConstraints.LINE_START; //bottom of space
				this.insets = new Insets(0,10,0,0);  //top padding
				this.gridx = 0;       //aligned with button 2
				this.gridwidth = 1;   //2 columns wide
				this.gridy = 1;       //third row
				break;
			case "leftPanel":
				this.fill = GridBagConstraints.BOTH;
				this.gridx = 0;
				this.gridy = 0;
				this.weighty = 1.0;
				break;
			case "rightPanel" : 
				this.fill = GridBagConstraints.BOTH;
				this.weightx = 0.5;
				this.gridx = 1;
				this.gridy = 0;
				this.weighty = 1.0;
				break; 
			case "compressImagesReminder":
				this.fill = GridBagConstraints.HORIZONTAL;
				this.gridx = 0;
				this.gridy = 0;
				this.weightx = 1;
				this.insets = new Insets(0,0,20,60);
				break;
			case "useTinyPng":
				this.fill = GridBagConstraints.HORIZONTAL;
				this.gridx = 0;
				this.gridy = 1;
				this.weightx = 1;
				this.insets = new Insets(0,0,20,60);
				break;
			case "startImageProcessing":
				this.fill = GridBagConstraints.HORIZONTAL;
				this.gridx = 0;
				this.gridy = 2;
				this.weightx = 0;
				this.insets = new Insets(0,0,20,60);
				break;
			case "editAboutMe" :
				this.fill = GridBagConstraints.BOTH;
				this.gridx = 0; 
				this.gridy = 0;
				this.anchor = GridBagConstraints.CENTER;
				this.insets = new Insets(10,0,20,10);  //top padding
				break;
			case "toggleAboutMeEditor" :
				this.fill = GridBagConstraints.HORIZONTAL;
				this.gridx = 1; 
				this.gridy = 0;
				this.anchor = GridBagConstraints.CENTER;
				this.insets = new Insets(10,0,20,10);  //top padding
				break;
			case "saveAboutMe" :
				this.fill = GridBagConstraints.BOTH;
				this.gridx = 2; 
				this.gridy = 0;
				this.anchor = GridBagConstraints.CENTER;
				this.insets = new Insets(10,0,20,10);  //top padding
				break;
			case "aboutMeScrollPane":
				this.fill = GridBagConstraints.BOTH;
				this.gridx = 0; 
				this.gridy = 1;
				this.weighty = 0.5;
				this.ipadx = 30;
				this.weightx = 0.0;
				// this.anchor = GridBagConstraints.FIRST_LINE_START;
				this.gridwidth = 4;
				break;
			case "htmlHelpLabel":
				this.fill = GridBagConstraints.HORIZONTAL;
				this.gridx = 0; 
				this.gridy = 4;
				this.insets = new Insets(10,5,0,0);  //top padding
				break;
			case "htmlHelpDropdown":
				this.weightx = 0.5;			
				this.gridx = 0; 
				this.gridy = 5;
				this.insets = new Insets(10,5,0,10);
				break;
			case "htmlExampleArea":
				this.fill = GridBagConstraints.HORIZONTAL;
				this.weightx = 0.5;			
				this.gridx = 0; 
				this.gridy = 6;
				this.ipady = 10;
				this.insets = new Insets(10,5,0,0);
				break;
			case "processingNow" :
				this.fill = GridBagConstraints.BOTH;
				this.gridx = 0; 
				this.gridy = 0;
				this.anchor = GridBagConstraints.CENTER;
				this.insets = new Insets(10,0,20,0);  //top padding
				break;
			case "imagePanel":
				this.fill = GridBagConstraints.BOTH;
				this.gridx = 0; 
				this.gridy = 1;
				this.anchor = GridBagConstraints.CENTER;
				break;
			case "workingOn":
				this.fill = GridBagConstraints.HORIZONTAL;
				this.weighty = 1.0;
				this.gridx = 0; 
				this.gridy = 2;
				this.insets = new Insets(10,0,0,0);  //top padding
				break;
			default:
				System.out.println("Did not catch the GridBagConstraint selector");
		}
	}
}