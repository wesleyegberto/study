package javaComoProgramar.cap24.exemplos;

// Fig 24.9: MediaPanel.java
// JPanel that plays a media file from a URL.
import java.awt.BorderLayout;
import java.awt.Component;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.media.CannotRealizeException;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MediaTest {
	// launch the application
	public static void main(String[] args) {
		// create a file chooser
		JFileChooser fileChooser = new JFileChooser();

		// show open file dialog
		int result = fileChooser.showOpenDialog(null);

		if(result == JFileChooser.APPROVE_OPTION) // user chose a file
		{
			URL mediaURL = null;

			try {
				// get the file as URL
				mediaURL = fileChooser.getSelectedFile().toURI().toURL();
			} // end try
			catch(MalformedURLException malformedURLException) {
				System.err.println("Could not create URL for the file");
			} // end catch

			if(mediaURL != null) // only display if there is a valid URL
			{
				JFrame mediaTest = new JFrame("Media Tester");
				mediaTest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				MediaPanel mediaPanel = new MediaPanel(mediaURL);
				mediaTest.add(mediaPanel);

				mediaTest.setSize(300, 300);
				mediaTest.setVisible(true);
			} // end inner if
		} // end outer if
	} // end main
} // end class MediaTest

class MediaPanel extends JPanel {
	public MediaPanel(URL mediaURL) {
		setLayout(new BorderLayout()); // use a BorderLayout

		// Use lightweight components for Swing compatibility
		Manager.setHint(Manager.LIGHTWEIGHT_RENDERER, true);

		try {
			// create a player to play the media specified in the URL
			Player mediaPlayer = Manager.createRealizedPlayer(mediaURL);

			// get the components for the video and the playback controls
			Component video = mediaPlayer.getVisualComponent();
			Component controls = mediaPlayer.getControlPanelComponent();

			if(video != null)
				add(video, BorderLayout.CENTER); // add video component

			if(controls != null)
				add(controls, BorderLayout.SOUTH); // add controls

			mediaPlayer.start(); // start playing the media clip
		} // end try
		catch(NoPlayerException noPlayerException) {
			System.err.println("No media player found"); // Não foi localizado
															// um player que
															// pudesse
															// reproduzir o
															// arquivo
		} // end catch
		catch(CannotRealizeException cannotRealizeException) {
			System.err.println("Could not realize media player"); // Não pode
																	// identificar
																	// adequadamente
																	// os
																	// recursos
																	// que o
																	// arquivo
																	// mídia
																	// precisa
		} // end catch
		catch(IOException iOException) {
			System.err.println("Error reading from the source");
		} // end catch
	} // end MediaPanel constructor
} // end class MediaPanel

/**************************************************************************
 * (C) Copyright 1992-2010 by Deitel & Associates, Inc. and * Pearson Education,
 * Inc. All Rights Reserved. * * DISCLAIMER: The authors and publisher of this
 * book have used their * best efforts in preparing the book. These efforts
 * include the * development, research, and testing of the theories and programs
 * * to determine their effectiveness. The authors and publisher make * no
 * warranty of any kind, expressed or implied, with regard to these * programs
 * or to the documentation contained in these books. The authors * and publisher
 * shall not be liable in any event for incidental or * consequential damages in
 * connection with, or arising out of, the * furnishing, performance, or use of
 * these programs. *
 *************************************************************************/
