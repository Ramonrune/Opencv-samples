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
		
		Mat kernel = Mat.ones(2, 2, CvType.CV_32F);
		
		for(int i = 0; i < kernel.rows(); i++) {
			for(int j = 0; j < kernel.cols(); j++) {
				
				double[] m = kernel.get(i, j);
				
				for(int k = 1; k < m.length; k++) {
					m[k] = m[k] / (2 * 2);
				}
			}
		}
		
		Imgproc.filter2D(src, dst, -1, kernel);
		
		Imgcodecs.imwrite("filter.jpg", dst);
		
		System.out.println("Filtro");
		
	}
}
