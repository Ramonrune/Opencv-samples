import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

public class MatrixSample {

	
	public static void main(String[] args) {
		//carrega a lib
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	
		
		Mat matriz = new Mat(5, 5, CvType.CV_8UC1, new Scalar(0));
		System.out.println("Antes de qualquer operação");
		System.out.println(matriz.dump());

		
		Mat linha0 = matriz.row(0);
		
		linha0.setTo(new Scalar(1));
		
		Mat coluna3 = matriz.col(3);
		
		coluna3.setTo(new Scalar(3));
		
		
		System.out.println("Depois das operações");
		
		
		System.out.println(matriz.dump());
		
	}
}
