package src.src; /**
 * Created by Abdellatif on 3/19/2016.
 * Functionality added by Mohammad on 3/19/2016
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Displays the user interface for the game.
 * Allows for users to play the game with just simple button clicks.
 * @author Mohammad
 */
public class GameView extends JFrame{
	
	/**
	 * Constructs the GUI, putting together all panels and buttons.
	 */
	public GameView(){
		this.setSize(600,600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("2 Player Connect Four");
		StartPanel startPanel = new StartPanel();

		this.setLayout(null);

		this.add(startPanel);

		//Compute hidden height and width of the frame
		int hiddenHeight = getInsets().top + getInsets().bottom;
		int hiddenWidth = getInsets().left + getInsets().right;

		//Center the start panel in the frame
		startPanel.setLocation((this.getWidth() - hiddenWidth - startPanel.getWidth())/2, (this.getHeight() - hiddenHeight - startPanel.getHeight())/2);


		this.setVisible(true);
	}

	public static void main(String[] args) {
		new GameView();
	}
	
	/**
	 * This is an inner class resembling the JPanel that contains the start button.
	 * @author Mohammad
	 */
	private class StartPanel extends JPanel{
		public StartPanel(){
			//Sets the layout of the panel to a BoxLayout allowing for better component alignment
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			
			
			//Creating the Icon for the game and added it to the StartPanel
			BufferedImage gameIcon = null;
			try{
				gameIcon = ImageIO.read(new File("img/connect4_logo.png"));
			}
			catch(IOException ioEx) {
				System.out.println("Problem loading icon");
			}
			//Resizing the Image to fit properly in the game window
			Image resized = gameIcon.getScaledInstance(540, 162, Image.SCALE_SMOOTH);
			ImageIcon icon = new ImageIcon(resized);
			JLabel iconLabel = new JLabel();
			//Containing the image within a label to add to the panel
			iconLabel.setIcon(icon);
			iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
			this.add(iconLabel);
			
			//Creating the Start Button for the game
			JButton startButton = new JButton("Start Game");
			this.add(startButton);
			startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			JLabel bottomText = new JLabel("© Mohammad Sharif & Abdellatif Abdellfatah");
			bottomText.setAlignmentX(Component.CENTER_ALIGNMENT);
			this.add(bottomText);
			
			//Makes the panel be the exact size to hold its components
			this.setSize(this.getPreferredSize());
		}
	}
}
