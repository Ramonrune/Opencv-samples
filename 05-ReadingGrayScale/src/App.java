import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class App {

	public static void main(String[] args) {
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		Imgcodecs  imgcodecs = new Imgcodecs();
		
		Mat src = imgcodecs.imread("Lighthouse.jpg", Imgcodecs.IMREAD_GRAYSCALE);
		
		byte [] data = new byte[src.rows() * src.cols() * (int)(src.elemSize())];
		src.get(0, 0, data);
		
		
		BufferedImage bufferedImage = new BufferedImage(src.cols(), src.rows(), BufferedImage.TYPE_BYTE_GRAY);
		
		
		bufferedImage.getRaster().setDataElements(0, 0, src.cols(), src.rows(), data);
		
		
		JFrame frame = new JFrame();
		
		frame.getContentPane().add(new JLabel(new ImageIcon(bufferedImage)));
		frame.pack();
		frame.setVisible(true);
		
		
	}
}
