import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;

public class AppJavaFX extends Application{

	
	@Override
	public void start(Stage primaryStage) throws Exception {

		
		WritableImage writableImage = carregaImagem();
		
		ImageView imageView = new ImageView(writableImage);
		
		imageView.setX(50);
		imageView.setY(50);
		
		
		imageView.setFitHeight(720);
		imageView.setFitWidth(1024);
		
		
		imageView.setPreserveRatio(true);
		
		Group group = new Group(imageView);
		
		
		Scene scene = new Scene(group, 500, 500);
		
		primaryStage.setTitle("Carregando a imagem");
		
		primaryStage.setScene(scene);
		
		primaryStage.show();
		
	}
	
	public WritableImage carregaImagem() throws IOException{
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		
		Mat image = Imgcodecs.imread("Lighthouse.jpg");
		
		MatOfByte matOfByte = new MatOfByte();
		
		Imgcodecs.imencode(".jpg", image, matOfByte);
		
		
		byte[] byteArray = matOfByte.toArray();
		
		InputStream in = new ByteArrayInputStream(byteArray);
		
		BufferedImage bufferedImage = ImageIO.read(in);
		
		WritableImage writableImage = SwingFXUtils.toFXImage(bufferedImage, null);
		
		return writableImage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
