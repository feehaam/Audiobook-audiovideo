import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class SaveFile {
	
	private final int DEFAULT_BUFFER_SIZE = 8192;
	public boolean copyInputStreamToFile(InputStream inputStream, String path){
		File file = new File(path);
        try (FileOutputStream outputStream = new FileOutputStream(file, false)) {
            int read;
            byte[] bytes = new byte[DEFAULT_BUFFER_SIZE];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } catch (Exception e) {
			System.out.println("Failed to save the audio file.");
			return false;
		} 
        
        return true;
    }
	
}
