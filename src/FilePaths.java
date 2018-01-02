import java.util.*;
import java.net.*;
import java.io.*;

public class FilePaths{

	protected String opSystem;
	protected String opSystemFull;
	protected String aboutMeFilePath;
	protected String albumsJSONPath;
	protected String gifsPath;
	protected String galleryDirectoryPath;
	protected String slideshowDirectoryPath;
	protected String profileDirectoryPath;
	protected String separator; 
	protected String oopsImg; 
	protected String successProcessingImg; 

	private ArrayList<String> gifs = new ArrayList<String>();


	public FilePaths(){

		opSystemFull =  System.getProperty("os.name");
		opSystem = opSystemFull.toLowerCase().indexOf("mac") >= 0 ? "mac" : "windows" ;
		setFilePaths(opSystem);
		getGIFS();
	}
	public FilePaths(String testing){
		opSystemFull =  System.getProperty("os.name");
		opSystem = opSystemFull.toLowerCase().indexOf("mac") >= 0 ? "mac" : "windows" ;
		setTestFilePaths(opSystem);
		getGIFS();
	}

	// This method is to set the file paths of the production tool
	// @Override
	public void setFilePaths(String os){
		aboutMeFilePath = os == "windows" ? "config\\aboutMe.txt" : "config/aboutMe.txt";

		albumsJSONPath = os == "windows" ? "config\\albumsJSON.json" : "config/albumsJSON.json";

		galleryDirectoryPath = os == "windows" ? "images\\gallery" : "images/gallery";

		slideshowDirectoryPath = os == "windows" ? "images\\slideshow" : "images/slideshow";

		profileDirectoryPath = os == "windows" ? "images\\assets\\profile" : "images/assets/profile";

		gifsPath = os == "windows" ? "images\\assets\\gifs\\" : "images/assets/gifs/";

		separator = os == "windows" ? "\\" : "/";

		oopsImg = os == "windows" ? "images\\assets\\icons\\oops2.png" : "images/assets/icons/oops2.png";

		successProcessingImg = os == "windows" ? "images\\assets\\icons\\successProcessing.png" : "images/assets/icons/successProcessing.png";	
	}

	// This is specifically for testing purposes
	// @Override
	public void setTestFilePaths(String os){
		aboutMeFilePath = os == "windows" ? "..\\config\\aboutMe.txt" : "../config/aboutMe.txt";

		albumsJSONPath = os == "windows" ? "..\\config\\albumsJSON.json" : "../config/albumsJSON.json";

		galleryDirectoryPath = os == "windows" ? "..\\images\\gallery" : "../images/gallery";

		slideshowDirectoryPath = os == "windows" ? "..\\images\\slideshow" : "../images/slideshow";

		profileDirectoryPath = os == "windows" ? "..\\images\\assets\\profile" : "../images/assets/profile";

		gifsPath = os == "windows" ? "..\\images\\assets\\gifs\\" : "../images/assets/gifs/";

		separator = os == "windows" ? "\\" : "/";

		oopsImg = os == "windows" ? "..\\images\\assets\\icons\\oops2.png" : "../images/assets/icons/oops2.png";

		successProcessingImg = os == "windows" ? "..\\images\\assets\\icons\\successProcessing.png" : "../images/assets/icons/successProcessing.png";	
	}


	public void getGIFS(){
		try{
			File gif = new File(this.gifsPath);
			File [] gifList = gif.listFiles();
			for (int x = 0; x < gifList.length; x++){
				if (gifList[x].getPath().contains(".gif")){
					gifs.add(gifList[x].getPath());
				}
			}
		} catch (Exception ex){
		    // ex.printStackTrace();
		}
	}
	public String getRandomGIF(){
		try{
			Random rand = new Random(); 
			String procImg = gifs.get(rand.nextInt(gifs.size()));
			return procImg;
		} catch(Exception ex) {
			// ex.printStackTrace();
			return null; 
		}
	}

	
}