import java.util.*;
import java.net.*;
import java.io.*;
import java.awt.Desktop;

public class FilePaths{

	protected String opSystem;
	protected String opSystemFull;
	protected String aboutMeFilePath;
	protected String albumsJSONPath;
	protected String gifsPath;
	protected String galleryDirectoryPath;
	protected String slideshowDirectoryPath;
	protected String profileDirectoryPath;
	protected String processImagesLogFilePath;
	protected String aboutMeLogFilePath;
	protected String separator; 
	protected String prefix;
	protected String oopsImg; 
	protected String successProcessingImg; 

	private ArrayList<String> gifs = new ArrayList<String>();


	public FilePaths(boolean isTest){
		opSystemFull =  System.getProperty("os.name");
		opSystem = opSystemFull.toLowerCase().indexOf("mac") >= 0 ? "mac" : "windows" ;

		separator = opSystem == "mac" ? "/" : "\\";
		
		prefix = isTest ? ".." + separator : "";

		setFilePaths(opSystem);

		getGIFS();
	}

	public void setFilePaths(String os){

		aboutMeFilePath = buildPath("config_aboutMe.txt");
		albumsJSONPath = buildPath("config_albumsJSON.json");
		galleryDirectoryPath = buildPath("images_gallery");
		slideshowDirectoryPath = buildPath("images_slideshow");
		profileDirectoryPath = buildPath("images_assets_profile");
		processImagesLogFilePath = buildPath("log_processImagesLog.txt");
		aboutMeLogFilePath = buildPath("log_aboutMeLog.txt");
		gifsPath = buildPath("images_assets_gifs");
		oopsImg = buildPath("images_assets_icons_oops2.png");
		successProcessingImg = buildPath("images_assets_icons_successProcessing.png");

	}

	public String buildPath(String filePath){
		String buildSeparator = separator == "\\" ? "\\\\" : "/";
		return prefix + filePath.replaceAll("_", buildSeparator);
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

	// This is a function to open a folder on the current end system
	// Not sure yet ... But I feel like this could be useful to me at some point.
	// public void selectFolder(){
	// 	if (Desktop.isDesktopSupported()) {
	// 		try {
	// 			File folder = new File("/");
	// 			Desktop.getDesktop().open(folder);
	// 		} catch (IOException ex) { /* TODO: error handling */ 
	// 	   		System.out.println(ex.getMessage());
	// 	   	} catch (Exception ex){
	// 	   		System.out.println(ex.getMessage());		   		
	// 		}
	// 	} else { 
	// 		System.out.println("Desktop access is not supported. Cannot open the site from here. Go to http://localhost");
	// 	}
	// }

	
}