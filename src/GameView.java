/**
 * Created by Abdellatif on 3/19/2016.
 * Functionality added by Mohammad on 3/19/2016
 */

import java.awt.*;
import javax.swing.*;

/**
 * Displays the user interface for the game.
 * Allows for users to play the game with just simple button clicks.
 * @author Mohammad
 */
public class GameView extends JFrame{
	
	public static void main(String[] args){
		new GameView();
	}
	/**
	 * Constructs the GUI, putting together all panels and buttons.
	 */
	public GameView(){
		this.setSize(600,600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		StartPanel startPanel = new StartPanel();
		
		this.setLayout(null);	
		
		this.add(startPanel);
		
		//Compute hidden height and width in the frame
		int hiddenHeight = getInsets().top + getInsets().bottom;
		int hiddenWidth = getInsets().left + getInsets().right;
		
		//Center the start panel in the frame
		startPanel.setLocation((this.getWidth() - hiddenWidth - startPanel.getWidth())/2, (this.getHeight() - hiddenHeight - startPanel.getHeight())/2);
		
		
		this.setVisible(true);
	}
	
	/**
	 * This is an inner class resembling the JPanel that contains the start button.
	 * @author Momo
	 */
	private class StartPanel extends JPanel{
		public StartPanel(){
			JButton startButton = new JButton("Start Game");
			this.add(startButton);
			this.setSize(this.getPreferredSize());
		}
	}
}
