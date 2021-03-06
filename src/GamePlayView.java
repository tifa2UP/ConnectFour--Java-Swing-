import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

/**
 * This will be the user interface of the actual game displaying the connect four screen and allowing users to
 * drop chips into each column
 * Created by Mohammad on 3/21/16.
 */
public class GamePlayView extends JFrame {

    GameOptionView previousWindow;
    GameBoard gameBoard;
    PlayerPanel player1;
    PlayerPanel player2;
    ChipButton[][] slots;
    ChipButton temp;
    JButton options;
    private ListenForButton listenForButton = new ListenForButton();
    GameViewController gameViewController = new GameViewController(GamePlayView.this);



    int clickCount = 0;

    public GamePlayView(GameOptionView previousWindow) {
        this.previousWindow = previousWindow;
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("2 Player Connect Four");
        this.setLayout(null);

        IconPanel gameIcon = new IconPanel();
        player1 = new PlayerPanel(previousWindow.getPlayerName(previousWindow.player1));
        player2 = new PlayerPanel(previousWindow.getPlayerName(previousWindow.player2));
        player2.setSize(player1.getSize());
        gameBoard = new GameBoard();
        options = new JButton("<< Options");
        options.addActionListener(listenForButton);
        options.setSize(options.getPreferredSize());


        this.add(gameIcon);
        this.add(player1);
        this.add(player2);
        this.add(gameBoard);
        this.add(options);

        //Compute hidden height and width of the frame
        int hiddenHeight = getInsets().top + getInsets().bottom;
        int hiddenWidth = getInsets().left + getInsets().right;

        //Maintains the game logo at the top of the window
        gameIcon.setLocation((this.getWidth() - hiddenWidth - gameIcon.getWidth()) / 2, (this.getHeight() - hiddenHeight - gameIcon.getHeight()) / 20);
        //Labels containing the Players names, will tell who's turn it is to go
        player1.setLocation((this.getWidth() - hiddenWidth - player1.getWidth()) / 15, (this.getHeight() - hiddenHeight - player1.getHeight()) / 2);
        player2.setLocation(14 * (this.getWidth() - hiddenWidth - player2.getWidth()) / 15, (this.getHeight() - hiddenHeight - player2.getHeight()) / 2);
        //Centers the gameboard in the middle of the screen
        gameBoard.setLocation((this.getWidth() - hiddenWidth - gameBoard.getWidth()) / 2, (this.getHeight() - hiddenHeight - gameBoard.getHeight()) / 2 + 50);

        options.setLocation(hiddenWidth, hiddenHeight);

        this.setVisible(true);
    }

    //This method will switch the label underneath a players name whether it is their turn or not
    public void setTurn(int player) {
        if (player == 1) {
            player1.turn.setText("Your Turn!");
            player2.turn.setText("");
        } else {
            player1.turn.setText("");
            player2.turn.setText("Your Turn!");


        }

    }

    /**
     * This is an inner class resembling the JPanel that contains the game's icon button.
     *
     * @author Mohammad
     */
    private class IconPanel extends JPanel {
        public IconPanel() {

            //Creating the Icon for the game and added it to the StartPanel
            BufferedImage gameIcon = null;
            try {
                gameIcon = ImageIO.read(new File("img/connect4_logo.png"));
            } catch (IOException ioEx) {
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
    private class PlayerPanel extends JPanel {
        JLabel turn;

        public PlayerPanel(String name) {
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            JLabel nameLabel = new JLabel(name);
            turn = new JLabel();
            //Initially it will be Player 1's turn, so they will have the Your Turn! label
            if (name.equals(previousWindow.getPlayerName(previousWindow.player1)))
                turn.setText("Your Turn!");
            else
                turn.setText(" ");

            nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            turn.setAlignmentX(Component.CENTER_ALIGNMENT);

            this.add(nameLabel);
            this.add(turn);

            this.setSize(this.getPreferredSize());
        }


    }


    /**
     * This class will contain the presentation of the gameboard that will fill a designated slot with a chip
     */
    private class GameBoard extends JPanel {


        public GameBoard() {
            int rows = previousWindow.getRows();
            this.setMaximumSize(new Dimension(325, 325));
            this.setSize(this.getMaximumSize());
            this.setBackground(new Color(0, 0, 128));
            this.setLayout(new GridLayout(rows, rows));
            this.setBorder(BorderFactory.createMatteBorder(10, 10, 0, 0, new Color(0, 0, 128)));

            slots = new ChipButton[rows][rows];
            for (int row = 0; row < rows; row++) {
                for (int column = 0; column < rows; column++) {
                    slots[row][column] = new ChipButton();
                    ChipButton button = slots[row][column];
                    button.addActionListener(listenForButton);
                    this.add(button);


                }
            }
        }


    }

    /**
     * This class comprehends whether a slot on a certain column was clicked,
     * adds a chip to the lowest slot in the column
     */
    private class ListenForButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().getClass().equals(options.getClass())) {
                GamePlayView.this.setVisible(false);
                previousWindow.setVisible(true);
            }
            else {
                gameViewController.gamePlayActionHandler(e);
            }
        }
    }

    /**
     * These represent the slots in the gameboard, once clicked they will drop a chip into the column,
     * the access hasn't been declared as private because the game view controller needs the ability to acces it.
     */
    class ChipButton extends JButton {
        public boolean isColored = false;
        public Color chipColor = null;

        public ChipButton() {

            //Creates a button that is perfectly round
            Dimension size = this.getPreferredSize();
            size.width = size.height = Math.max(size.width, size.height);
            this.setBorderPainted(false);
            this.setContentAreaFilled(false);
            this.setPreferredSize(size);
            UIManager.put("Button.disabledForeground", Color.RED);


        }

        public void setChipColor(Color color) {
            chipColor = color;
        }

        //This sets the slot color, if the slot is already colored it will maintain the color
        protected void paintComponent(Graphics g) {
            if (!isColored) {
                g.setColor(Color.white);
            } else {
                g.setColor(this.chipColor);
            }
            g.fillOval(0, 0, getSize().width - 10, getSize().height - 10);
            super.paintComponent(g);

        }

        // Paint the border of the button using a simple stroke.
        protected void paintBorder(Graphics g) {
            if (!isColored) {
                g.setColor(getForeground());
                g.drawOval(0, 0, getSize().width - 10, getSize().height - 10);
            }
        }

        //This will color the slot yellow resembling the appropriate player's chip
        public void recolorYellow() {
            Graphics g = this.getGraphics();
            g.setColor(Color.YELLOW);
            g.fillOval(0, 0, this.getSize().width - 10, this.getSize().height - 10);
            this.setChipColor(Color.YELLOW);
            this.isColored = true;
        }

        //This will color the slot red resembling the appropriate player's chip
        public void recolorRed() {
            Graphics g = this.getGraphics();
            g.setColor(Color.RED);
            g.fillOval(0, 0, this.getSize().width - 10, this.getSize().height - 10);
            this.setChipColor(Color.RED);
            this.isColored = true;
        }


    }


}
