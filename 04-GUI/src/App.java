import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

public class App {

	public static void main(String[] args) throws IOException {
		
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		
		Mat image = Imgcodecs.imread("Lighthouse.jpg");
		
		MatOfByte matOfByte = new MatOfByte();
		
		//converte mat para matofbytes
		Imgcodecs.imencode(".jpg", image, matOfByte);
		
		
		byte[] byteArray = matOfByte.toArray();
		
		InputStream in = new ByteArrayInputStream(byteArray);
		
		BufferedImage bufferedImage = ImageIO.read(in);
		
		JFrame frame = new JFrame();
		
		frame.getContentPane().add(new JLabel(new ImageIcon(bufferedImage)));
		frame.pack();
		frame.setVisible(true);
		
		
		
		
	}
}
