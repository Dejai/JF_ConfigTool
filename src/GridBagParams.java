import java.util.*;
import java.awt.*;

/*
	GridBagConstraints Explained (in my own way):

	'weightx' & 'weighty':
		> This determines how much space a component gets in the row (y) or column (x);
		> Range is from 0.0 to 1.0; Lesser number means take up less space
		> This probably works along with 'fill'
	'fill':
		> Of the amount of space left in a row or column, this determines how much of it the component should fill up
		> Could fill the remaining horizontal space, vertical space, both directions, or none


*/

public class GridBagParams extends GridBagConstraints {

	private static int MenuRows = 0; 

	GridBagParams(String type){
		super ();
		returnConstraints(type);
	}

	GridBagParams(String type, int row){
		super();
		returnConstraints(type, row);
	}

	public void returnConstraints(String type, int row){
		switch (type) {
			case "menuLabel":
				this.fill = GridBagConstraints.HORIZONTAL;
				this.weightx = 0.3;				
				this.gridx = 0; 
				this.gridy = row;
				this.ipadx = 50;
				this.ipady = 10;
				this.insets = new Insets(10,5,0,10);  
				break;
			case "menuButton":
			case "menuDropdown":
				this.weightx = 0.5;				
				this.gridx = 0; 
				this.gridy = row; 
				this.insets = type == "menuButton" ? new Insets(10,20,0,0) : new Insets(10,5,0,10);
				break;
			case "menuSection":
				// this.fill = GridBagConstraints.HORIZONTAL;
				this.weightx = 0.5;				
				this.gridx = 0; 
				this.gridy = row;
				this.ipady = 10;
				this.insets = new Insets(10,5,0,10);  
				break;
			case "viewAreaLabel":
				this.fill = GridBagConstraints.HORIZONTAL;
				this.weightx = 0.3;
				this.weighty = 0.0;
				this.gridx = 0; 
				this.gridy = row;
				this.anchor = GridBagConstraints.FIRST_LINE_START;
				this.insets = new Insets(10,0,10,0);
				break;
			case "viewAreaButton":
				// this.fill = GridBagConstraints.HORIZONTAL;
				this.gridx = 0;
				this.gridy = row;
				this.weightx = 0.5;
				this.weighty = 0;
				// this.insets = new Insets(0,0,20,60);
				break;
			case "viewAreaMainSection":
				// this.fill = type == "viewAreaScrollPane" ? GridBagConstraints.BOTH : GridBagConstraints.NONE;
				this.gridx = 0; 
				this.gridy = row;
				this.weighty = 0.5;
				this.weightx = 0.5;
				this.anchor = GridBagConstraints.FIRST_LINE_START;
				this.insets = new Insets(0,0,10,0); // top, right, bottom, left
				break;
			case "viewAreaDescription":
				this.fill = GridBagConstraints.HORIZONTAL;
				this.weighty = 0.3;
				this.gridx = 0; 
				this.gridy = row;
				this.weighty = 0.1;
				// this.insets = new Insets(10,0,0,0);  //top padding
				break;
			case "toggleAboutMeEditor" :
				this.fill = GridBagConstraints.HORIZONTAL;
				this.gridx = 1; 
				this.weightx = 0.2;
				this.gridy = row - row;
				this.anchor = GridBagConstraints.CENTER;
				this.insets = new Insets(10,0,20,10);  //top padding
				break;
			case "saveAboutMe" :
				this.fill = GridBagConstraints.BOTH;
				this.weightx = 0.2;
				this.gridx = 2; 
				this.gridy = row - row;
				this.anchor = GridBagConstraints.CENTER;
				this.insets = new Insets(10,0,20,10);  //top padding
				break;
			case "aboutMeScrollPane":
				this.fill = GridBagConstraints.BOTH;
				this.gridx = 0; 
				this.gridy = row;
				this.weighty = 0.5;
				this.ipadx = 30;
				this.weightx = 0.0;
				// this.anchor = GridBagConstraints.FIRST_LINE_START;
				this.gridwidth = 4;
				break;
			default:
				System.out.println("Not a valid option!");
		}
	}

	public void returnConstraints(String type){
		switch( type ){
			case "bottomPanel" :
				this.ipady = 0;      
				this.anchor = GridBagConstraints.LINE_START; 
				this.insets = new Insets(0,10,0,0); 
				this.gridx = 0;       
				this.gridwidth = 1;   
				this.gridy = 1;       
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
			default:
				System.out.println("Did not catch the GridBagConstraint selector");
		}
	}
}