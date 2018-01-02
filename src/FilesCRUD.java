import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.PrintWriter;


public class FilesCRUD {


	public static boolean writeJSONFile(String fileParam, ArrayList<Album> albumArrayList){
		try{
			File fp = new File(fileParam);
			fp.setWritable(true);
			BufferedWriter jsonOUT = new BufferedWriter(new FileWriter(fp, false));

			jsonOUT.write("");
			jsonOUT.write("{");
			jsonOUT.newLine();
			for (Album singleAlbum : albumArrayList){
				String openJSON = String.format( "\"" + singleAlbum.albumName + "\" : {"
				  +  "\"folderName\" :\"" + singleAlbum.albumName + "\","
				  +  "\"coverImg\" :\"/" + singleAlbum.coverImage + "\","
				  +  "\"images\" : [");
				jsonOUT.write(openJSON);
				jsonOUT.newLine();

				for (Picture yx : singleAlbum.pictures){
					String imgJSON = String.format("{"
						+ "\"path\" :\"/" + yx.path.replace("\\", "/") + "\","
						+ "\"width\" : " + yx.width  + " ,"
						+ "\"height\" : " + yx.height + " ,"
						+ "\"dimension\" : \"" + yx.dimension + "\" ");
					jsonOUT.write(imgJSON);
					jsonOUT.newLine();
					String commaOption = (singleAlbum.pictures.indexOf(yx) == singleAlbum.pictures.size()-1) ? "}]" : "},";
					jsonOUT.write(String.format(commaOption));
					jsonOUT.newLine();
				}
				String commaOption2 = ( albumArrayList.indexOf(singleAlbum) == albumArrayList.size()-1 ) ? "}" : "},";
				jsonOUT.write(String.format(commaOption2));
				jsonOUT.newLine();
			}
			jsonOUT.write("}");
			jsonOUT.close();

			return true;
		} catch (Exception ex){
		    return false;
		}
	}


	public static String getAboutMeText(String aboutMeFilePath){
		try {
			String line; 
			String fullText = ""; 
			BufferedReader aboutMeReader = new BufferedReader(new FileReader(aboutMeFilePath));
			while ( (line = aboutMeReader.readLine()) != null){
				fullText = fullText.concat(line).concat("\n");
			}
			return fullText;
		} catch (Exception ex){
			ex.printStackTrace();
			return null; 
		}
	}


	public static boolean writeAboutMeText(String fileParam, String newText){
		try{
			BufferedWriter writeAboutMe = new BufferedWriter(new FileWriter(fileParam));
			writeAboutMe.write(newText);
			writeAboutMe.close();
			return true;
		} catch (Exception ex){
			return false; 
		}
	}

	public static ArrayList<String> getGalleryAlbums(String path, String sep){
		try{
			ArrayList<String> galleryAlbums = new ArrayList<String>();
			File gallery = new File(path);
			File [] galleryList = gallery.listFiles();
			for (int x = 0; x < galleryList.length; x++){
				if (galleryList[x].isDirectory()){
					String newName = galleryList[x].getName().trim();
					String newPath = String.format("%s%s%s", path, sep, newName);
					galleryAlbums.add(newPath);
				}
			}
			return galleryAlbums;
		} catch (Exception ex){
			return null;
		}
	}



	
}