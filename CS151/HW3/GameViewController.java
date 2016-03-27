package CS151.HW3;

import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;

/**
 * Created by Abdellatif on 3/19/2016.
 */
public class GameViewController {

    private static GameOptionView gameOptionView;
    private static GamePlayView gamePlayView;
    private static int numRows = 7;
    private static int numConnectionsToWin = 4;
    private static String player1Name = "Player 1";
    private static String player2Name = "Player 2";
    private static String player1Color = "Red";
    private static String player2Color = "Yellow";
    private static ConnectFour connectFour;
    private static Player player1;
    private static Player player2;
    private static Chip p1Chip;
    private static Chip p2Chip;


    //Handles data when needed for the GameOptionView class
    public GameViewController(GameOptionView gameOptionView){
        this.gameOptionView = gameOptionView;
    }

    //Handles data when needed for the GameViewController class
    public GameViewController(GamePlayView gamePlayView){
        this.gamePlayView = gamePlayView;
        p1Chip = new Chip(player1Color, 1);
        p2Chip = new Chip(player2Color, 2);
        player1 = new Player(player1Name, p1Chip);
        player2 = new Player(player2Name, p2Chip);
        connectFour = new ConnectFour(numRows, player1, player2, numConnectionsToWin);

    }

    public static void main(String[] args) {
        System.out.println("test me please");
    }


    //When the start game button is clicked on the GameOptionView this code is executed handling the data
    public void gameOptionActionHandler(ActionEvent e){
        //Tries the entire block of code for any exceptions
        try {
            int rows = gameOptionView.getRows();
            int numConnectionsToWin = gameOptionView.getChipsToWin();
            if (e.getSource() == gameOptionView.startButton) {

                if (rows != 7) {
                    //Rows cannot be larger than 15
                    if (rows > 15) {
                        JOptionPane tooLarge = new JOptionPane();
                        tooLarge.showMessageDialog(gameOptionView, "Please set the rows to a whole number between 2 and 15.", "Rows too large", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    //Rows cannot be set to zero
                    if (rows < 2) {
                        JOptionPane tooLarge = new JOptionPane();
                        tooLarge.showMessageDialog(gameOptionView, "Please set the rows to a whole number between 2 and 15.", "Can't Be Zero", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    gameOptionView.setRows("" + rows);
                    numRows = rows;
                }
                //The connections cannot be greater than the rows, or else winning would be impossible
                if (numConnectionsToWin > rows) {
                    JOptionPane connectionsLargerThanRows = new JOptionPane();
                    connectionsLargerThanRows.showMessageDialog(gameOptionView, "The connection length must be less than or equal to the number of rows/columns",
                            "Connections larger than Rows", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //The connections cannot be less than two or else the first move in the game would win
                if (numConnectionsToWin < 2) {
                    JOptionPane tooLarge = new JOptionPane();
                    tooLarge.showMessageDialog(gameOptionView, "The connection length must be greater than 1", "Can't Be Zero", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                gameOptionView.setConnections("" + numConnectionsToWin);
                this.numConnectionsToWin = numConnectionsToWin;

                //If the default name for Player 1 has been changed, this will set the new name
                if (!gameOptionView.getPlayerName(gameOptionView.player1).equals("Player 1")) {
                    gameOptionView.setPlayerName(gameOptionView.player1, gameOptionView.getPlayerName(gameOptionView.player1));
                    player1Name = gameOptionView.getPlayerName(gameOptionView.player1);
                }
                //If the default name for Player 2 has been changed, this will set the new name
                if (!gameOptionView.getPlayerName(gameOptionView.player2).equals("Player 2")) {
                    gameOptionView.setPlayerName(gameOptionView.player2, gameOptionView.getPlayerName(gameOptionView.player2));
                    player2Name = gameOptionView.getPlayerName(gameOptionView.player2);
                }
                //Stores data of the players chip color
                player1Color = gameOptionView.getPlayer1Color();
                player2Color = gameOptionView.getPlayer2Color();


                //Upon the button being clicked, the frame will switch to that of the game.
                gameOptionView.setVisible(false);
                new GamePlayView(gameOptionView);

            }
        } catch(NumberFormatException nFE)
        {
            JOptionPane tooLarge = new JOptionPane();
            tooLarge.showMessageDialog(gameOptionView, "Please set the rows to a whole number between 2 and 15\nand make sure the connections are less than or equal to\nthe rows.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }
    //This method is responsible for registering clicks on certain slots on the game board and dropping a chip to the lowest open spot
    public void gamePlayActionHandler(ActionEvent e){
        int columnIndex = 0;
        boolean isPlayer1;
        boolean isPlayer1Red = true;
        boolean isRed;
        String[] buttons = {"Exit Game", "Options", "Rematch"};

        if (!gamePlayView.previousWindow.getPlayer1Color().equals("Red")) {
            isPlayer1Red = false;
        }
        if (gamePlayView.clickCount % 2 != 0) {
            isPlayer1 = false;
            gamePlayView.setTurn(1);
        } else {
            isPlayer1 = true;
            gamePlayView.setTurn(2);
        }

        outerloop:
        for (int i = 0; i < gamePlayView.slots.length; i++) {
            for (int j = 0; j < gamePlayView.slots.length; j++) {
                if (e.getSource() == gamePlayView.slots[i][j]) {
                    columnIndex = j;
                    break outerloop;
                }

            }

        }

        //These conditionals decide whether the chip needs to be red or not
        if (isPlayer1 && !isPlayer1Red)
            isRed = false;
        else if (!isPlayer1 && isPlayer1Red)
            isRed = false;
        else
            isRed = true;

        //Iterates from the bottom of the column, fills the lowest empty slot with the proper chip
        JOptionPane winner = new JOptionPane();
        int res = -1;
        boolean gameOver = false;
        int decrease = gamePlayView.slots.length;
        while (decrease > 0) {
            gamePlayView.temp = gamePlayView.slots[decrease - 1][columnIndex];
            if (!gamePlayView.temp.isColored) {
                if (isRed) {
                    gamePlayView.temp.recolorRed();
                    if(isPlayer1) {
                        connectFour.insertChip(columnIndex, player1);
                        if(connectFour.didWin(columnIndex, player1)){
                            res = winner.showOptionDialog(gamePlayView, player1Name + " wins! What would you like to do?", "Winner", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE, null, buttons, buttons[2]);
                            gameOver = true;
                        }
                        connectFour.printBoard();
                    }
                    else{
                        connectFour.insertChip(columnIndex, player2);
                        if(connectFour.didWin(columnIndex, player2)){
                            res = winner.showOptionDialog(gamePlayView, player2Name + " wins! What would you like to do?", "Winner", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE, null, buttons, buttons[2]);
                            gameOver = true;
                        }
                        connectFour.printBoard();

                    }
                } else {
                    gamePlayView.temp.recolorYellow();
                    if(isPlayer1) {
                        connectFour.insertChip(columnIndex, player1);
                        if(connectFour.didWin(columnIndex, player1)){
                            res = winner.showOptionDialog(gamePlayView, player1Name +" wins! What would you like to do?", "Winner", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE, null, buttons, buttons[2]);
                            gameOver = true;
                        }
                        connectFour.printBoard();

                    }
                    else {
                        connectFour.insertChip(columnIndex, player2);
                        if(connectFour.didWin(columnIndex, player2)){
                            res = winner.showOptionDialog(gamePlayView, player2Name +" wins! What would you like to do?", "Winner", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE, null, buttons, buttons[2]);
                            gameOver = true;
                        }
                        connectFour.printBoard();

                    }
                }
                if(res == 2) {
                    gamePlayView = new GamePlayView(gamePlayView.previousWindow);
                }
                else if(res == 1) {
                    gamePlayView.previousWindow.setVisible(true);
                }
                else if(res == 0 || gameOver)
                    System.exit(0);


                gamePlayView.clickCount++;
                return;
            }
            decrease--;
        }
    }
}
