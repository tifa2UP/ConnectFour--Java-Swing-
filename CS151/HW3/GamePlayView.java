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
        GamePanel names = new GamePanel();
        this.add(gameIcon);
        this.add(names);

        //Compute hidden height and width of the frame
        int hiddenHeight = getInsets().top + getInsets().bottom;
        int hiddenWidth = getInsets().left + getInsets().right;

        //Maintains the game logo at the top of the window
        gameIcon.setLocation((this.getWidth() - hiddenWidth - gameIcon.getWidth())/2, (this.getHeight() - hiddenHeight - gameIcon.getHeight())/20);
        names.setLocation((this.getWidth() - hiddenWidth - names.getWidth())/2, (this.getHeight() - hiddenHeight - names.getHeight())/2);



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

    private class GamePanel extends JPanel{
        public GamePanel(){
            JLabel name = new JLabel(previousWindow.getPlayer1Name());
            JLabel color = new JLabel(previousWindow.getPlayer1Color());
            JLabel name2 = new JLabel(previousWindow.getPlayer2Name());
            JLabel color2 = new JLabel(previousWindow.getPlayer2Color());
            JLabel rows = new JLabel("" + previousWindow.getRows());
            JLabel toWin = new JLabel("" + previousWindow.getChipsToWin());
            this.add(name);
            this.add(color);
            this.add(name2);
            this.add(color2);
            this.add(rows);
            this.add(toWin);
            this.setSize(this.getPreferredSize());

        }
    }




}
