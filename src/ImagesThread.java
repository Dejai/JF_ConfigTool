import java.util.*;

public class ImagesThread extends Thread {

	private String methodToRun; 
	private Listeners listener;

	public ImagesThread(Listeners listen, String mtr){
		methodToRun = mtr;	
		listener = listen;	
	}

	public void run(){
		switch(methodToRun){
			case "show":
				// listener.showImageProcessingSection();
				listener.showProcessingImage();
				break;
			case "start":
				listener.processImages();
				break;
			default:
				listener.showProcessingImage();
				// listener.showImageProcessingSection();
		}
    }

}