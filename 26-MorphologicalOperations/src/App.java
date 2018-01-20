import org.opencv.core.Core;
import org.opencv.core.CvType;
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
		

		Mat kernel = Mat.ones(5, 5, CvType.CV_32F);
		
		Imgproc.morphologyEx(src, dst, Imgproc.MORPH_TOPHAT, kernel);
		
		Imgcodecs.imwrite("filter.jpg", dst);
		
		System.out.println("Filtro");
		
	}
}
