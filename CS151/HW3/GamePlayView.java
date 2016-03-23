package CS151.HW3;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * This will be the user interface of the actual game displaying the connect four screen and allowing users to
 * drop chips into each column
 * Created by Momo on 3/21/16.
 */
public class GamePlayView extends JFrame{

    GameOptionView previousWindow;
    public GamePlayView(GameOptionView previousWindow){
        this.previousWindow = previousWindow;
        this.setSize(600,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("2 Player Connect Four");
        this.setLayout(null);

        IconPanel gameIcon = new IconPanel();
        PlayerPanel player1 = new PlayerPanel(previousWindow.getPlayer1Name());
        PlayerPanel player2 = new PlayerPanel(previousWindow.getPlayer2Name());
        this.add(gameIcon);
        this.add(player1);
        this.add(player2);

        //Compute hidden height and width of the frame
        int hiddenHeight = getInsets().top + getInsets().bottom;
        int hiddenWidth = getInsets().left + getInsets().right;

        //Maintains the game logo at the top of the window
        gameIcon.setLocation((this.getWidth() - hiddenWidth - gameIcon.getWidth())/2, (this.getHeight() - hiddenHeight - gameIcon.getHeight())/20);
        //Labels containing the Players names, will tell who's turn it is to go
        player1.setLocation((this.getWidth() - hiddenWidth - player1.getWidth())/10, (this.getHeight() - hiddenHeight - player1.getHeight())/2);
        player2.setLocation(9*(this.getWidth() - hiddenWidth - player2.getWidth())/10, (this.getHeight() - hiddenHeight - player2.getHeight())/2);



        this.setVisible(true);
    }

    /**
     * This is an inner class resembling the JPanel that contains the game's icon button.
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
            Image resized = gameIcon.getScaledInstance(360, 108, Image.SCALE_SMOOTH);
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
     * This class contains a label that holds the Players' names and also states whose turn it is
     */
    private class PlayerPanel extends JPanel{
        public PlayerPanel(String name){
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            JLabel nameLabel = new JLabel(name);
            JLabel turn = new JLabel();
            if(name.equals(previousWindow.getPlayer1Name()))
                turn.setText("Your Turn!");
            nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            turn.setAlignmentX(Component.CENTER_ALIGNMENT);

            this.add(nameLabel);
            this.add(turn);

            this.setSize(this.getPreferredSize());


        }
    }




}
