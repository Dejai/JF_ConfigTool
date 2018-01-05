import java.util.*;

class Album {

	protected String albumName; 
	protected String coverImage; 
	protected boolean hasCoverImage;
	protected boolean hasNewPhotos = false;
	protected String newPhotosExpiration = "N/A";
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

	protected void setNewPhotosIndicators(){
		this.hasNewPhotos = true;
		Calendar date = Calendar.getInstance();
		date.add(date.DATE, 7);
		int year = date.get(date.YEAR);
		int month = date.get(date.MONTH)+1; 
		int day = date.get(date.DAY_OF_MONTH);
		this.newPhotosExpiration = String.format("%d-%02d-%02d", year, month, day);
	}

	public String toString(){
		return String.format("Album:%s\n", this.albumName);
	}

	public String albumJSONObject(boolean isLast){
		StringBuilder jsonObj = new StringBuilder();
		Calendar date = Calendar.getInstance();
		Random rando = new Random();
		String code = String.format("%d%02d%02d-%d", date.get(date.YEAR), date.get(date.MONTH)+1, date.get(date.DAY_OF_MONTH), rando.nextInt(9999)+1 );
		jsonObj.append("\"" + this.albumName + "\" : {");
			jsonObj.append("\"hasNewPhotos\" : " + this.hasNewPhotos + ", ");
			jsonObj.append("\"newPhotosCode\" : \"" + code + "\", ");
			jsonObj.append("\"newPhotosExpiration\" : \"" + this.newPhotosExpiration +"\", ");
			jsonObj.append("\"folderName\" : \"" + this.albumName + "\", ");
			jsonObj.append("\"coverImg\" : \"/" + this.coverImage + "\", ");

			StringBuilder picsObj = new StringBuilder();
			for (Picture pic : this.pictures){
				picsObj.append("{");
				picsObj.append("\"path\" : \"/" + pic.path + "\", ");
				picsObj.append("\"width\" : " + pic.width + ", ");
				picsObj.append("\"height\" : " + pic.height + ", ");
				picsObj.append("\"dimension\" : \"" + pic.dimension + "\"");
				picsObj.append("}");
				boolean addComma = this.pictures.indexOf(pic) == this.pictures.size()-1 ? false : true;
				if (addComma){
					picsObj.append(",");
				}
			}
			jsonObj.append("\"images\" : [ " + picsObj.toString() +  " ] ");
		String closingBrace = isLast ? "}" : "},";
		jsonObj.append(closingBrace);

		return jsonObj.toString();
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