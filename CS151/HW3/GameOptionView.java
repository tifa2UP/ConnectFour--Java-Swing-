package CS151.HW3;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Created by Mohammad on 3/20/16.
 * This class contains the user interface that will carry out the game functions as well as display the game
 */
public class GameOptionView extends JFrame{


    public GameOptionView(){
        this.setSize(600,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("2 Player Connect Four");
        this.setLayout(null);

        IconPanel gameIcon = new IconPanel();
        GridNumber gridNumber = new GridNumber();
        ConnectionsToWin connectionsToWin = new ConnectionsToWin();
        PlayerPanel player1 = new PlayerPanel(1, "Red");
        PlayerPanel player2 = new PlayerPanel(2, "Yellow");
        ColorButtonPanel colorButtonPanel = new ColorButtonPanel();

        this.add(gameIcon);
        this.add(gridNumber);
        this.add(connectionsToWin);
        this.add(player1);
        this.add(player2);
        this.add(colorButtonPanel);

        //Compute hidden height and width of the frame
        int hiddenHeight = getInsets().top + getInsets().bottom;
        int hiddenWidth = getInsets().left + getInsets().right;

        //Maintains the game logo at the top of the window
        gameIcon.setLocation((this.getWidth() - hiddenWidth - gameIcon.getWidth())/2, (this.getHeight() - hiddenHeight - gameIcon.getHeight())/20);

        //Asks user for input of what the area of the gameboard should be, located above start button
        gridNumber.setLocation((this.getWidth() - hiddenWidth - gridNumber.getWidth())/5, (this.getHeight() - hiddenHeight - gridNumber.getHeight())/2 + this.getHeight()/4 - 50);

        //Asks user for input of how many chips must connect for a player to win
        connectionsToWin.setLocation(4*(this.getWidth() - hiddenWidth - connectionsToWin.getWidth())/5, (this.getHeight() - hiddenHeight - connectionsToWin.getHeight())/2 + this.getHeight()/4 - 50);

        player1.setLocation((this.getWidth() - hiddenWidth - player1.getWidth())/5, (this.getHeight() - hiddenHeight - player1.getHeight())/3);

        player2.setLocation(4*(this.getWidth() - hiddenWidth - player2.getWidth())/5, (this.getHeight() - hiddenHeight - player2.getHeight())/3);

        colorButtonPanel.setLocation((this.getWidth() - hiddenWidth - colorButtonPanel.getWidth())/2, (this.getHeight() - hiddenHeight - colorButtonPanel.getHeight())/3 + 30);


        this.setVisible(true);

    }

    /**
     * This is an inner class resembling the JPanel that contains the game's icon button.
     * @author Mohammad
     */
    private class IconPanel extends JPanel{
        public IconPanel(){
            //Sets the layout of the panel to a BoxLayout allowing for better component alignment
            //this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


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
     * This class contains the panel holding the Text info for the user as well as taking user input
     * to know how many rows and columns should be in the grid. Maximum 10.
     */
    private class GridNumber extends JPanel {
        public GridNumber(){
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            JLabel title = new JLabel("How many rows/columns?");
            JTextField gridField = new JTextField("7",2);

            //Used to keep a small text field that is meant to hold 2 digits
            gridField.setMaximumSize(gridField.getPreferredSize());

            title.setAlignmentX(Component.CENTER_ALIGNMENT);
            gridField.setAlignmentX(Component.CENTER_ALIGNMENT);

            this.add(title);
            this.add(gridField);
            this.setSize(this.getPreferredSize());

        }
    }

    /**
     * This class is a panel containing the Text info for the user as well as how many chips need to connect
     * for someone to win. It cannot be greater than the amount of rows or columns.
     */
    private class ConnectionsToWin extends JPanel {
        public ConnectionsToWin(){
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            JLabel title = new JLabel("How many connections to win?");
            JTextField connectionsField = new JTextField("4",2);

            //Used to keep a small text field that is meant to hold 2 digits
            connectionsField.setMaximumSize(connectionsField.getPreferredSize());

            title.setAlignmentX(Component.CENTER_ALIGNMENT);
            connectionsField.setAlignmentX(Component.CENTER_ALIGNMENT);

            this.add(title);
            this.add(connectionsField);
            this.setSize(this.getPreferredSize());

        }
    }

    /**
     * This class will contain the user input area for the player's name
     */
    private class PlayerPanel extends JPanel {
        public PlayerPanel(int playerNumber, String colorString){
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            JLabel title = new JLabel("Player " + playerNumber + "'s name");
            JTextField connectionsField = new JTextField("Player " + playerNumber, 10);
            JLabel colorTitle = new JLabel("Color:");
            JLabel colorLabel = new JLabel(colorString);

            //Used to keep a small text field that is meant to hold 2 digits
            connectionsField.setMaximumSize(connectionsField.getPreferredSize());

            title.setAlignmentX(Component.CENTER_ALIGNMENT);
            connectionsField.setAlignmentX(Component.CENTER_ALIGNMENT);
            colorTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
            colorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(title);
            this.add(connectionsField);
            this.add(colorTitle);
            this.add(colorLabel);
            this.setSize(this.getPreferredSize());


        }
    }

    /**
     * This class contains the button that will switch which player has the red or yellow chip
     */
    private class ColorButtonPanel extends JPanel{
        public ColorButtonPanel(){
            JButton switchButton = new JButton("Switch");
            this.add(switchButton);
            this.setLayout(new FlowLayout());
            this.setSize(this.getPreferredSize());

        }
    }


}
