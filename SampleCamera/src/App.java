import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JCheckBox;

public class App extends JFrame {
	private ScheduledExecutorService timer;
	private VideoCapture capture;
	private boolean cameraActive = false;
	private int cameraId = 0;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App frame = new App();
					frame.capture = new VideoCapture();

					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public App() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 717, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnIniciarCamera = new JButton("Iniciar camera");
		btnIniciarCamera.setBounds(9, 539, 574, 23);
		contentPane.add(btnIniciarCamera);

		btnIniciarCamera.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!cameraActive) {
					// start the video capture
					capture.open(cameraId);

					// is the video stream available?
					if (capture.isOpened()) {
						cameraActive = true;

						// grab a frame every 33 ms (30 frames/sec)
						Runnable frameGrabber = new Runnable() {

							@Override
							public void run() {
								// effectively grab and process a single frame
								Mat frame = grabFrame();

								ImageIcon image = new ImageIcon(Util.matToBufferedImage(frame));

								label.setIcon(image);
							}
						};

						timer = Executors.newSingleThreadScheduledExecutor();
						timer.scheduleAtFixedRate(frameGrabber, 0, 60, TimeUnit.MILLISECONDS);

					} else {
						// log the error
						System.err.println("Impossible to open the camera connection...");
					}
				} else {
					// the camera is not active at this point
					cameraActive = false;
					// update again the button content

					// stop the timer
					stopAcquisition();
				}
			}
		});

		label = new JLabel("");
		label.setBounds(9, 10, 574, 529);
		contentPane.add(label);

		chckbxCinza = new JCheckBox("Cinza");
		chckbxCinza.setBounds(598, 70, 97, 23);
		contentPane.add(chckbxCinza);

		chckbxNormal = new JCheckBox("Normal");
		chckbxNormal.setBounds(598, 110, 97, 23);
		contentPane.add(chckbxNormal);
		chckbxNormal.setSelected(true);

		chckbxHsv = new JCheckBox("HSV");
		chckbxHsv.setBounds(598, 160, 97, 23);
		contentPane.add(chckbxHsv);

		chckbxNormal.addActionListener((l) -> {
			chckbxNormal.setSelected(true);
			chckbxCinza.setSelected(false);
			chckbxHsv.setSelected(false);
		});
		chckbxCinza.addActionListener((l) -> {
			chckbxNormal.setSelected(false);
			chckbxCinza.setSelected(true);
			chckbxHsv.setSelected(false);
		});
		chckbxHsv.addActionListener((l) -> {
			chckbxNormal.setSelected(false);
			chckbxCinza.setSelected(false);
			chckbxHsv.setSelected(true);
		});
	}

	private JCheckBox chckbxCinza, chckbxNormal, chckbxHsv;
	private static JLabel label;

	private Mat grabFrame() {
		// init everything
		Mat frame = new Mat();

		// check if the capture is open
		if (capture.isOpened()) {
			try {
				// read the current frame
				capture.read(frame);

				// if the frame is not empty, process it
				if (!frame.empty()) {
					if (chckbxNormal.isSelected()) {
						Imgproc.cvtColor(frame, frame, Imgproc.COLOR_BGR2RGB);

					}

					if (chckbxCinza.isSelected()) {
						Imgproc.cvtColor(frame, frame, Imgproc.COLOR_BGR2GRAY);

					}

					if (chckbxHsv.isSelected()) {
						Imgproc.cvtColor(frame, frame, Imgproc.COLOR_BGR2HSV);

					}
				}

			} catch (Exception e) {
				// log the error
				System.err.println("Exception during the image elaboration: " + e);
			}
		}

		return frame;
	}

	private void stopAcquisition() {
		if (timer != null && !timer.isShutdown()) {
			try {
				// stop the timer
				timer.shutdown();
				timer.awaitTermination(60, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
				// log any exception
				System.err.println("Exception in stopping the frame capture, trying to release the camera now... " + e);
			}
		}

		if (capture.isOpened()) {
			// release the camera
			capture.release();
		}
	}

	protected void setClosed() {
		stopAcquisition();
	}
}
