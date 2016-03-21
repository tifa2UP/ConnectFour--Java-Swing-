package CS151.HW3;

/**
 * Created by Abdellatif.
 */

/**
 * This is the "brain" behind the connect four game
 */
public class ConnectFour {

    //the structure of the board
    //the first array contains the x component, the second part contains the y one
    private Chip[][] structure;
    //the available position of the next insert (since the chip has to be above another one unless the column is empty)
    private int[] positionForInsert;
    //the first player
    private Player player1;
    //the second player
    private Player player2;

    public ConnectFour(int boardSize, Player player1, Player player2) {
        //create a new empty board of Chips
        structure = new Chip[boardSize][boardSize];
        //initialize the new position for insert at any column to 0
        positionForInsert = new int[boardSize];
        for (int i = 0; i < boardSize; i++) {
            positionForInsert[i] = 0;
        }
        this.player1 = player1;
        this.player2 = player2;
    }

    /**
     * checks whether there's a win condition
     *
     * @return true if one of the players win
     */
    private boolean didWin(int x, int y, Chip chip) {
        int counter = 0;
        //check horizontally
        for (int i = 0; i < structure.length; i++) {
            if (chip.getIdentifier() == structure[i][y].getIdentifier()) {
                counter++;
                if (counter >= 4) {
                    return true;
                }
            } else {
                counter = 0;
            }
        }
        //check vertically
        counter = 0;
        for (int i = 0; i < structure.length; i++) {
            if (chip.getIdentifier() == structure[x][i].getIdentifier()) {
                counter++;
                if (counter >= 4) {
                    return true;
                }
            } else {
                counter = 0;
            }

        }
        //TODO: implement the two diagonal orientations
        return false;
    }

    /** test2
     * prints out the board in the terminal (for debugging purposes)
     */
    public void printBoard() {
        for (int y = structure.length - 1; y > 0; y--) {
            for (int x = 0; x < structure.length - 1; x++) {
                if (structure[x][y] == null) {
                    System.out.print("-");
                } else {
                    System.out.print(" | " + structure[x][y].getIdentifier());
                }
            }
            //create a new line after every row
            System.out.println();
        }
    }

    /**
     * inserts a chip at a given column
     * @param columnPosition the position the user is inserting the chip at
     *
     */
    public void insertChip(int columnPosition, Player player) {
        //Create a new Chip that belongs a player
        Chip newChip = player.getChip();
        //insert a new chip a given column
        //the first part of the arrays is height which is given by the number of chips that are present in that column
        //the second part is simply the column in that the player puts the chip in
        structure[columnPosition][(positionForInsert[columnPosition])] = newChip;
        //increment the position for insert at that given column
        positionForInsert[columnPosition] = positionForInsert[columnPosition]++;
    }

}






