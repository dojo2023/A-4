package model;

	import java.io.FileOutputStream;
import java.io.IOException;

	public class ImgUpload {
	    public void saveUserIcon(byte[] iconData, String fileName) {
	        try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
	            outputStream.write(iconData);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
