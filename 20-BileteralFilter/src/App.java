import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class App {

	
	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		Mat src = Imgcodecs.imread("Lighthouse.jpg");
				
		Mat dst = new Mat();
		
		Imgproc.bilateralFilter(src, dst, 15, 80, 80, Core.BORDER_DEFAULT);
		
		Imgcodecs.imwrite("filter.jpg", dst);
		
		System.out.println("Filtro bilateral");
		
	}
}
