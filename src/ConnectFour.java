package src;

/**
 * Created by Abdellatif.
 */

/**
 * This is the "brain" behind the connect four game
 */
public class ConnectFour {

    //the structure of the board
    private Chip[][] structure;
    //the available position of the next insert (since the chip has to be above another one unless the column is empty)
    private int[] positionForInsert;

    public ConnectFour(int boardSize) {
        //create a new empty board of Chips
        structure = new Chip[boardSize][boardSize];
        //initialize the new position for insert at any column to 0
        positionForInsert = new int[boardSize];
        for (int i = 0; i < boardSize; i++) {
            positionForInsert[i] = 0;
        }
    }

    /**
     * checks whether there's a win condition
     *
     * @return true if one of the players win
     */
    private boolean didWin() {
        return false;
    }

    /**
     * inserts a chip at a given column
     * @param columnPosition the position the user is inserting the chip at
     *
     */
    public void insertChip(int columnPosition, int player) {
//        Chip
    }


    }






