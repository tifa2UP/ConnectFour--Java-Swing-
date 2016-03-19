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
	
	public GameView(){
		this.setSize(600,600);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	}
}
