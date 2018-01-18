import java.awt.Image;
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
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class App {

	public static void main(String[] args) throws IOException {
	
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		Mat matrix = Imgcodecs.imread("Lighthouse.jpg");
		
		Imgproc.line(matrix, new Point(10, 200), new Point(300, 200), new Scalar(0, 0, 255), 5);
		
		
		MatOfByte matOfByte = new MatOfByte();
		
		Imgcodecs.imencode(".jpg", matrix, matOfByte);
		
		
		byte[] data = matOfByte.toArray();
		
		
		InputStream in = new ByteArrayInputStream(data);
		
		BufferedImage bufferedImage = ImageIO.read(in);
		

		JFrame frame = new JFrame();
		
		frame.getContentPane().add(new JLabel(new ImageIcon(bufferedImage)));
		frame.pack();
		frame.setVisible(true);
	
		
		
	}
}
