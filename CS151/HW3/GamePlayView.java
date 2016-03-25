package CS151.HW3;

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
 * Created by Momo on 3/21/16.
 */
public class GamePlayView extends JFrame{

    GameOptionView previousWindow;
    GameBoard gameBoard;

    int clickCount = 0;
    boolean isRed;

    public GamePlayView(GameOptionView previousWindow){
        this.previousWindow = previousWindow;
        this.setSize(600,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("2 Player Connect Four");
        this.setLayout(null);

        IconPanel gameIcon = new IconPanel();
        PlayerPanel player1 = new PlayerPanel(previousWindow.getPlayerName(previousWindow.player1));
        PlayerPanel player2 = new PlayerPanel(previousWindow.getPlayerName(previousWindow.player2));
        gameBoard = new GameBoard();


        this.add(gameIcon);
        this.add(player1);
        this.add(player2);
        this.add(gameBoard);

        //Compute hidden height and width of the frame
        int hiddenHeight = getInsets().top + getInsets().bottom;
        int hiddenWidth = getInsets().left + getInsets().right;

        //Maintains the game logo at the top of the window
        gameIcon.setLocation((this.getWidth() - hiddenWidth - gameIcon.getWidth())/2, (this.getHeight() - hiddenHeight - gameIcon.getHeight())/20);
        //Labels containing the Players names, will tell who's turn it is to go
        player1.setLocation((this.getWidth() - hiddenWidth - player1.getWidth())/15, (this.getHeight() - hiddenHeight - player1.getHeight())/2);
        player2.setLocation(14*(this.getWidth() - hiddenWidth - player2.getWidth())/15, (this.getHeight() - hiddenHeight - player2.getHeight())/2);
        //Centers the gameboard in the middle of the screen
        gameBoard.setLocation((this.getWidth() - hiddenWidth - gameBoard.getWidth())/2, (this.getHeight() - hiddenHeight - gameBoard.getHeight())/2 + 50);



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
            if(name.equals(previousWindow.getPlayerName(previousWindow.player1)))
                turn.setText("Your Turn!");
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
    private class GameBoard extends JPanel{

        ListenForButton listenForButton;
        ChipButton[][] slots;
        public GameBoard(){
            int rows = previousWindow.getRows();
            this.setMaximumSize(new Dimension(325,325));
            this.setSize(this.getMaximumSize());
            this.setBackground(new Color(0, 0, 128));
            this.setLayout(new GridLayout(rows,rows));
            this.setBorder(BorderFactory.createMatteBorder(10,10,0,0, new Color(0, 0, 128)));

            slots = new ChipButton[rows][rows];
            listenForButton = new ListenForButton();
            for(int row = 0; row < rows; row++)
            {
                for(int column = 0; column < rows; column++)
                {
                    slots[row][column] =  new ChipButton();
                    ChipButton button = slots[row][column];
                    button.addActionListener(listenForButton);
                    this.add(button);


                }
            }
        }

        /**
         * These represent the slots in the gameboard, once clicked they will drop a chip into the column
         */
        private class ChipButton extends JButton{
            public boolean isColored = false;
            public Color chipColor = null;
            public ChipButton(){

                //Creates a button that is perfectly round
                Dimension size = this.getPreferredSize();
                size.width = size.height = Math.max(size.width, size.height);
                this.setBorderPainted(false);
                this.setContentAreaFilled(false);
                this.setPreferredSize(size);
                UIManager.put("Button.disabledForeground", Color.RED);


            }

            public void setChipColor(Color color){
                chipColor = color;
            }



            protected void paintComponent(Graphics g) {
                if(!isColored) {
                    g.setColor(Color.white);
                } else {
                    g.setColor(this.chipColor);
                }
                g.fillOval(0, 0, getSize().width - 10, getSize().height - 10);
                super.paintComponent(g);

            }

            // Paint the border of the button using a simple stroke.
            protected void paintBorder(Graphics g) {
                if(!isColored) {
                    g.setColor(getForeground());
                    g.drawOval(0, 0, getSize().width - 10, getSize().height - 10);
                }
            }

            //This will color the slot yellow resembling the appropriate player's chip
            public void recolorYellow(){
                Graphics g = this.getGraphics();
                g.setColor(Color.YELLOW);
                g.fillOval(0, 0, this.getSize().width-10, this.getSize().height-10);
                this.setChipColor(Color.YELLOW);
                this.isColored = true;
            }

            //This will color the slot red resembling the appropriate player's chip
            public void recolorRed(){
                Graphics g = this.getGraphics();
                g.setColor(Color.RED);
                g.fillOval(0, 0, this.getSize().width-10, this.getSize().height-10);
                this.setChipColor(Color.RED);
                this.isColored = true;
            }



        }



        private class ListenForButton implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                int rowIndex = 0;
                int columnIndex = 0;
                outerloop:
                for(int i = 0; i < slots.length; i++){
                    for(int j = 0; j < slots.length; j++){
                        if(e.getSource() == slots[i][j]){
                            rowIndex = i;
                            columnIndex = j;
                            break outerloop;
                        }

                    }

                }

                for(int i = rowIndex; i < slots.length; i++){
                    boolean isPlayer1 = true;
                    boolean isPlayer1Red = true;
                    if(!previousWindow.getPlayer1Color().equals("Red")){
                        isPlayer1Red = false;
                    }
                    if(clickCount%2 != 0){
                        isPlayer1 = false;
                    }

                    if(isPlayer1 && !isPlayer1Red)
                        isRed = false;
                    else if(!isPlayer1 && isPlayer1Red)
                        isRed = false;
                    else
                        isRed = true;



                    if(i == slots.length - 1){
                        ChipButton temp = slots[i][columnIndex];
                        if(!temp.isColored) {
                            if (isRed) {
                                temp.recolorRed();
                            } else {
                                temp.recolorYellow();
                            }
                            temp.removeActionListener(listenForButton);
                            temp.removeMouseListener(temp.getMouseListeners()[0]);
                            clickCount++;
                            return;
                        }
                    }
                    else if(slots[i+1][columnIndex].isColored){
                        ChipButton temp = slots[i][columnIndex];
                        if(!temp.isColored) {
                            if (isRed) {
                                temp.recolorRed();
                            } else {
                                temp.recolorYellow();
                            }
                            clickCount++;
                            return;
                        }
                    }
                    else if(!slots[slots.length-1][columnIndex].isColored){
                        ChipButton temp = slots[slots.length-1][columnIndex];
                        if(isRed){
                            temp.recolorRed();
                        }
                        else {
                            temp.recolorYellow();
                        }
                        clickCount++;
                        return;

                    }
                    else if(slots[i][columnIndex].isColored){
                        ChipButton temp = slots[i - 1][columnIndex];
                        while(i < 0){
                            temp = slots[i-1][columnIndex];
                            if(!temp.isColored){
                                if (isRed) {
                                    temp.recolorRed();
                                } else {
                                    temp.recolorYellow();
                                }
                                clickCount++;
                                return;
                            }
                            if(i == 0)
                                return;
                            i--;
                        }
                    }

                }
            }
        }


    }




}
