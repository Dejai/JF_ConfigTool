import java.util.*;

class Album {

	protected String albumName; 
	protected String folderName; 
	protected String coverImage; 
	protected boolean hasCoverImage;
	protected ArrayList<Picture> pictures = new ArrayList<Picture>();
	protected HashMap<String, String> pics = new HashMap<String, String>();

	public Album(String name){
		albumName = name;
		hasCoverImage = false;
	}

	public void setCoverImage(String img){
		String webPathImg = img.replace("\\", "/");
		coverImage = webPathImg;
		hasCoverImage = true; 
	}

	protected void addPicture(String p, int w, int h, String d){
		this.pictures.add(new Picture(p, w, h, d));
	}

	public String toString(){
		return String.format("Album:%s\n", this.albumName);
	}

// "profile" : {"folderName" :"profile","coverImg" :"/images/assets/profile/IMG_5427 (1).jpg","images" : [
// {"path" :"/images/assets/profile/IMG_5427 (1).jpg","width" : 1160 ,"height" : 1543 ,"dimension" : "portrait" 
// }]
// },
	public String returnJSONObject(){
		StringBuilder jsonObj = new StringBuilder();
		jsonObj.append(this.folderName + " : {");
			jsonObj.append("\"folderName\" : " + this.folderName);
			jsonObj.append("\"coverImg\" : " + this.coverImage);
			// jsonObj.append("\"images\" : " + this.folderName);
			// jsonObj.append("\"folderName\" : " + this.folderName);
		// jsonObj.append(String.format(this.folderName));
		// String.format()
		return jsonObj;
	}
}

class Picture {

	protected int width;
	protected int height;
	protected String path;
	protected String dimension;
	public Picture (String p, int w, int h, String d){
		path = p;
		width = w;
		height = h;
		dimension = d;
	}
}