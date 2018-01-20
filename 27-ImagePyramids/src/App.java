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
		Mat dst2 = new Mat();
		Mat dst3 = new Mat();

		
		Imgproc.pyrUp(src, dst, new Size(src.cols() * 2, src.rows() * 2), Core.BORDER_DEFAULT);
		Imgproc.pyrUp(src, dst2, new Size(src.cols() * 2, src.rows() * 2), Core.BORDER_DEFAULT);
		Imgproc.pyrMeanShiftFiltering(src, dst3, 200, 300);

		Imgcodecs.imwrite("pyup.jpg", dst);
		Imgcodecs.imwrite("pydown.jpg", dst2);
		Imgcodecs.imwrite("shiftFiltering.jpg", dst3);

		System.out.println("Filtro");
		
	}
}
