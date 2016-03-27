package CS151.HW3; /**
 * Created by Abdellatif on 3/19/2016.
 * Functionality added by Mohammad on 3/19/2016
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

		IconPanel iconPanel = new IconPanel();
		ButtonPanel buttonPanel = new ButtonPanel();
		TextPanel textPanel = new TextPanel();


		this.setLayout(null);

		this.add(iconPanel);
		this.add(buttonPanel);
		this.add(textPanel);


		//Compute hidden height and width of the frame
		int hiddenHeight = getInsets().top + getInsets().bottom;
		int hiddenWidth = getInsets().left + getInsets().right;

		//Center the icon panel in the frame
		iconPanel.setLocation((this.getWidth() - hiddenWidth - iconPanel.getWidth())/2, (this.getHeight() - hiddenHeight - iconPanel.getHeight())/4);

		//Put the button underneath the game icon, with a decent amount of space
		buttonPanel.setLocation((this.getWidth() - hiddenWidth - buttonPanel.getWidth())/2, (this.getHeight() - hiddenHeight - buttonPanel.getHeight())/2 + this.getHeight()/8);

		//Put the copyright text at the bottom of the window
		textPanel.setLocation((this.getWidth() - hiddenWidth - textPanel.getWidth())/2, (this.getHeight() - hiddenHeight - textPanel.getHeight())/2 + this.getHeight()/3);

		this.setVisible(true);
	}

	public static void main(String[] args) {
		new GameView();
	}
	
	/**
	 * This is an inner class resembling the JPanel that contains the game's icon.
	 * @author Mohammad
	 */
	private class IconPanel extends JPanel{
		public IconPanel(){

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

			//Makes the panel be the exact size to hold its components
			this.setSize(this.getPreferredSize());
		}
	}

	/**
	 * This class contains the button that will be responsible for starting the game
	 */
	private class ButtonPanel extends JPanel{
		JButton startButton;
		public ButtonPanel(){
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

			//Creating the Start Button for the game
			startButton = new JButton("Start Game");
			this.add(startButton);
			startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

			//Create a listener for the start button
			ListenForButton listenForButton = new ListenForButton();
			startButton.addActionListener(listenForButton);

			//Makes the panel be the exact size to hold its components
			this.setSize(this.getPreferredSize());

		}

		/**
		 * This class will monitor any clicks carried out on the start button,
		 * upon the click the frame will disappear and the game frame will open.
		 */
		private class ListenForButton implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == startButton){
					//Upon the button being clicked, the frame will switch to that of the game.
					GameView.this.setVisible(false);
					new GameOptionView();
				}
			}
		}


	}

	/**
	 * This class contains the text for the bottom of the screen within a JPanel
	 */
	private class TextPanel extends JPanel{
		public TextPanel(){
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

			//Creating a JLabel to contain the text for the bottom of the page
			JLabel bottomText = new JLabel("© Mohammad Sharif & Abdellatif Abdellfatah");
			bottomText.setAlignmentX(Component.CENTER_ALIGNMENT);
			this.add(bottomText);

			//Makes the panel be the exact size to hold its components
			this.setSize(this.getPreferredSize());

		}
	}




}
