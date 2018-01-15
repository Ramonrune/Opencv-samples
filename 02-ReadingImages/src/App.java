import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class App {

	public static void main(String[] args) {
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		//classe que deixa ler e escrever imagens
		//permite armazenar imagens em matrizes e realizar 
		//as operações necessárias
		Imgcodecs imgcodecs = new Imgcodecs();
		
		//parametro é o caminho da imagem
		Mat matriz = imgcodecs.imread("Lighthouse.jpg");
		
		
		System.out.println("Linhas x colunas:" + matriz.rows() + " x " + matriz.cols());
		System.out.println(matriz.dims());
	
	}
}
