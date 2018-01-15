import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.opencv.core.Core;

public class LoadingImage {

	
	public static void main(String[] args) throws IOException {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		File file = new File("Lighthouse.jpg");
		
		BufferedImage image = ImageIO.read(file);
		
		File output = new File("Lighthouse1.jpg");
		
		ImageIO.write(image, "jpg", output);
		
		
		
	}
}
