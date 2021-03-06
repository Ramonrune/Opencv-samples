import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class App {
	
	public static void main(String[] args) {
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		Mat src = Imgcodecs.imread("Lighthouse.jpg");
		
		Mat dst = new Mat();
		
		Imgproc.cvtColor(src, dst, Imgproc.COLOR_RGB2GRAY);
		
		
		//extraindo dados da imagem que foi transformada
		
		byte[] data = new byte[dst.rows() * dst.cols() * (int)(dst.elemSize())];
		
		dst.get(0, 0, data);
		
		BufferedImage bufferedImage = new BufferedImage(dst.cols(), dst.rows(), BufferedImage.TYPE_BYTE_GRAY);
		
		bufferedImage.getRaster().setDataElements(0, 0, dst.cols(), dst.rows(), data);

		
		
		JFrame frame = new JFrame();
		
		frame.getContentPane().add(new JLabel(new ImageIcon(bufferedImage)));
		frame.pack();
		frame.setVisible(true);
		
		
	}

}
