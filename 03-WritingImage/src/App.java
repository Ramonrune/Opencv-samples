import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;

public class App {

	public static void main(String[] args) {
		//Método write é usado para escrever em uma imagem
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		Imgcodecs imgcodecs = new Imgcodecs();
		
		Mat matriz = imgcodecs.imread("Lighthouse.jpg");
		
		System.out.println("Imagem carregada");
		
		
		//modifica o scalar da imagem
		for(int i = 0; i < matriz.rows(); i++) {
			
			matriz.row(i).setTo(new Scalar(i));

		}
		
		
		imgcodecs.imwrite("Lighthouse1.jpg", matriz);
		
		System.out.println("Imagem salva");
		
	}
}
