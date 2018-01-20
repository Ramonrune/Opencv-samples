import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class App {

	
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		Mat src = Imgcodecs.imread("Lighthouse.jpg");
				
		Mat dst = new Mat();
		
		Size size = new Size(45,  45);
		Point point = new Point(-1, -1);
		
		Imgproc.sqrBoxFilter(src, dst, -1, new Size(1, 1));
		
		Imgcodecs.imwrite("filter.jpg", dst);
		
		System.out.println("Filtro");
		
	}
}
